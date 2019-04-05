package vn.education.core.daoimpl;


import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import vn.education.core.common.utils.HibernateUtil;
import vn.education.core.dao.UserDao;
import vn.education.core.data.daoimpl.AbstractDao;
import vn.education.core.persistence.entity.RoleEntity;
import vn.education.core.persistence.entity.UserEntity;

import javax.jws.soap.SOAPBinding;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDao<Integer, UserEntity> implements UserDao {

    public Object[] checkLogin(String name, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Boolean isUserExist = false;
        String roleName = null;
        try {
            StringBuilder sql = new StringBuilder(" FROM UserEntity ue WHERE ue.name= :name AND ue.password= :password");
            Query query = session.createQuery(sql.toString());
            query.setParameter("name", name);
            query.setParameter("password", password);
            if(query.list().size() >0 ) {
                isUserExist = true;
                UserEntity userEntity = (UserEntity) query.uniqueResult();
                roleName = userEntity.getRoleEntity().getName();
            }
        } catch (HibernateException e) {
            transaction.rollback();
            throw  e;
        } finally {
            session.close();
        }
        return new Object[]{isUserExist, roleName};
    }

    public List<UserEntity> findByUsers(List<String> names) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<UserEntity> userEntityList = new ArrayList<UserEntity>();
        String roleName = null;
        try {
            String sql = " FROM UserEntity ue WHERE ue.name IN (:names) ";
            Query query = session.createQuery(sql);
            query.setParameterList("names", names);
            userEntityList = query.list();
        } catch (HibernateException e) {
            transaction.rollback();
            throw  e;
        } finally {
            session.close();
        }
        return userEntityList;
    }
}
