package vn.education.core.utils;

import vn.education.core.dto.RoleDTO;
import vn.education.core.persistence.entity.RoleEntity;

public class RoleBeanUtil {
    public static RoleDTO entity2Dto(RoleEntity entity) {
        RoleDTO dto = new RoleDTO();
        dto.setRoleId(entity.getRoleId());
        dto.setName(entity.getName());
        return dto;
    }

    public static RoleEntity dto2Entity(RoleDTO dto) {
        RoleEntity entity = new RoleEntity();
        entity.setRoleId(dto.getRoleId());
        entity.setName(dto.getName());
        return entity;
    }
}
