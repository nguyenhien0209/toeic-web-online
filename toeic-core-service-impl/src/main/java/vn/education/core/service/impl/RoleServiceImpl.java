package vn.education.core.service.impl;

import vn.education.core.dao.RoleDao;
import vn.education.core.daoimpl.RoleDaoImpl;
import vn.education.core.dto.RoleDTO;
import vn.education.core.persistence.entity.RoleEntity;
import vn.education.core.service.RoleService;
import vn.education.core.service.utils.SingletonDaoUtil;
import vn.education.core.utils.RoleBeanUtil;

import java.util.ArrayList;
import java.util.List;

public class RoleServiceImpl implements RoleService {
    public List<RoleDTO> findAll() {
        List<RoleEntity> entities = SingletonDaoUtil.getRoleDaoInstance().findAll();
        List<RoleDTO> dtos = new ArrayList<RoleDTO>();
        for(RoleEntity entity : entities) {
            RoleDTO dto = RoleBeanUtil.entity2Dto(entity);
            dtos.add(dto);
        }
        return dtos;
    }
}
