package vn.education.core.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import vn.education.core.common.utils.HibernateUtil;
import vn.education.core.dao.RoleDao;
import vn.education.core.data.daoimpl.AbstractDao;
import vn.education.core.persistence.entity.RoleEntity;

import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl extends AbstractDao<Integer, RoleEntity> implements RoleDao {


    public List<RoleEntity> findByRoles(List<String> roles) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<RoleEntity> roleEntityList = new ArrayList<RoleEntity>();
        String roleName = null;
        try {
            String sql = " FROM RoleEntity re WHERE re.name IN (:roles) ";
            Query query = session.createQuery(sql);
            query.setParameterList("roles", roles);
            roleEntityList = query.list();
        } catch (HibernateException e) {
            transaction.rollback();
            throw  e;
        } finally {
            session.close();
        }
        return roleEntityList;
    }
}
