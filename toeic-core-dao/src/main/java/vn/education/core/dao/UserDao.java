package vn.education.core.dao;

import vn.education.core.data.dao.GenericDao;
import vn.education.core.persistence.entity.UserEntity;

import java.util.List;

public interface UserDao extends GenericDao<Integer, UserEntity> {
    Object[] checkLogin(String name, String password);
    List<UserEntity> findByUsers (List<String> names);
}
