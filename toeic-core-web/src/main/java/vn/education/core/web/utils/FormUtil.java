package vn.education.core.web.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

public class FormUtil {
    public static <T> T populate(Class<T> clazz, HttpServletRequest request) {
        T object = null;
        try{
            object = (T) clazz.newInstance();
            BeanUtils.populate(object, request.getParameterMap());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return object;
    }
}

//Phuong thuc request.getParameterMap(), dung de lay tat ca cac tham so trong URL duoc truyen kem theo request
//Phuong thuc BeanUtils.populate(object, request.getParameterMap()) dung de chuyen tat ca cac tham so co trong URL
// vao trong Object
