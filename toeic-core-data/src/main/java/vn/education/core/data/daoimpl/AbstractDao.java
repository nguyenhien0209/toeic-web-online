package vn.education.core.data.daoimpl;

import javassist.tools.rmi.ObjectNotFoundException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import vn.education.core.common.constant.CoreConstant;
import vn.education.core.common.utils.HibernateUtil;
import vn.education.core.data.dao.GenericDao;

import java.awt.*;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AbstractDao<ID extends Serializable, T> implements GenericDao<ID, T> {
    private final Logger log = Logger.getLogger(this.getClass());
    private Class<T> persistenceClass;

    public AbstractDao(){
        this.persistenceClass=(Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];

    }

    public String getPersistenceClassName(){
        return persistenceClass.getSimpleName();
    }

    public List<T> findAll() {
        List<T> list=new ArrayList<T>();
        Transaction transaction=null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction=session.beginTransaction();
            //Use HQL
            StringBuilder sql=new StringBuilder("from ");
            sql.append(this.getPersistenceClassName());
            Query query=session.createQuery(sql.toString());
            list=query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            log.error(e.getMessage(), e);
            throw e;
        }
        finally {
            session.close();
        }
        return list;
    }

    public T update(T entity) {
        T result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        try {
            Object object=session.merge(entity);
            result = (T) object;
            transaction.commit();
        }
        catch(HibernateException e) {
            transaction.rollback();
            log.error(e.getMessage(), e);
            throw e;
        } finally {
            session.close();
        }

        return result;
    }

    public T save(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            log.error(e.getMessage(), e);
            throw e;
        } finally {
            session.close();
        }
        return entity;
    }
    public T findById(ID id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        T result=null;
        try {
            result = (T) session.get(persistenceClass, id);
            if(result == null){
                try {
                    throw new ObjectNotFoundException("NOT FOUND " + id, null);
                } catch (ObjectNotFoundException e) {
                    e.printStackTrace();
                }
            }

        } catch (HibernateException e) {
            transaction.rollback();
            log.error(e.getMessage(), e);
            throw e;
        }
        finally {
            session.close();
        }
        return result;
    }

    public Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        List<T> list = new ArrayList<T>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Object totalItem=0;
        Object[] nameQuery = HibernateUtil.buildNameQuery(property);

        try {
            StringBuilder sql1= new StringBuilder("from ");
            sql1.append(this.getPersistenceClassName()).append(" where 1=1 ").append(nameQuery[0]);
            if( sortExpression !=null && sortDirection!=null){
                sql1.append(" order by ").append(sortExpression);
                sql1.append(" "+(sortDirection.equals(CoreConstant.SORT_ASC) ? "ASC" : "DESC"));
            }
            Query query1 = session.createQuery(sql1.toString());
            setParameterToQuery(nameQuery, query1);
            if(offset !=null && offset >=0 ) {
                query1.setFirstResult(offset);
            }
            if(limit !=null && limit >0) {
                query1.setMaxResults(limit);
            }
            list = query1.list();
            StringBuilder sql2 = new StringBuilder("select count(*) from ");
            sql2.append(this.getPersistenceClassName()).append(" where 1=1 ").append(nameQuery[0]);
            Query query2 = session.createQuery(sql2.toString());
            setParameterToQuery(nameQuery, query2);
            totalItem = query2.list().get(0);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            log.error(e.getMessage(), e);
            throw e;
        } finally {
            session.close();
        }
        return new Object[]{totalItem,list};
    }


    private void setParameterToQuery(Object[] nameQuery, Query query){
        if(nameQuery.length == 3) {
            String[] params = (String[]) nameQuery[1];
            Object[] values = (Object[]) nameQuery[2];
            for(int i = 0; i < params.length; i++) {
                query.setParameter(params[i], values[i]);
            }
        }
    }

    public Integer delete(List<ID> ids) {
        Integer count=0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            for (ID item : ids) {
                T t =(T) session.get(this.persistenceClass,item);
                session.delete(t);
                count++;
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            log.error(e.getMessage(), e);
            throw e;
        } finally {
            session.close();
        }
        return count;
    }

    public T findEqualUnique(String property, Object value) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        T result = null;
        try {
            String sql = " FROM " + this.getPersistenceClassName() + " model WHERE model." + property + "= :value";
            Query query = session.createQuery(sql);
            query.setParameter("value", value);
            result = (T)query.uniqueResult();
        } catch (HibernateException e) {
            transaction.rollback();
            log.error(e.getMessage(), e);
            throw e;
        } finally {
            session.close();
        }
        return result;
    }
}
