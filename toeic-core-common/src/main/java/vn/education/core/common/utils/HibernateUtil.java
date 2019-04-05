package vn.education.core.common.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Map;

public class HibernateUtil {
    private static final SessionFactory sessionFactory=buildSessionFactory();
    private static SessionFactory buildSessionFactory() {
        //create sessionfactory form hibernate.cfg.xml
        try{
            return new Configuration().configure().buildSessionFactory();
        }catch (Throwable e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Object[] buildNameQuery(Map<String, Object> property) {
        StringBuilder nameQuery = new StringBuilder();
        if(property != null && property.size() >0) {
            String[] params = new String[property.size()];
            Object[] values = new Object[property.size()];
            int i = 0;
            for(Map.Entry<String, Object> item : property.entrySet()) {
                params[i] = item.getKey();
                values[i] = item.getValue();
                i++;
            }

            for(int j=0; j<params.length; j++) {
                //params[j] 1 la property truyen vao de tim theo
                //params[j] 2 la gia tri cua property do can tim
                nameQuery.append(" and ").append(" LOWER("+ params[j] +") LIKE '%' || :"+ params[j] +" || '%'");
            }
            return new Object[]{nameQuery.toString(), params, values};
        }
        return new Object[]{nameQuery.toString()};
    }
}
