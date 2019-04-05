package vn.education.core.dao;

import vn.education.core.data.dao.GenericDao;
import vn.education.core.persistence.entity.RoleEntity;

import java.util.List;

public interface RoleDao extends GenericDao<Integer, RoleEntity> {
    List<RoleEntity> findByRoles (List<String> roles);

}
