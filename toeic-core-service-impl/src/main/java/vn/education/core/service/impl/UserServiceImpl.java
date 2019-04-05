package vn.education.core.service.impl;

import org.apache.commons.lang.StringUtils;
import vn.education.core.common.utils.SessionUtil;
import vn.education.core.dao.UserDao;
import vn.education.core.daoimpl.UserDaoImpl;
import vn.education.core.dto.CheckLogin;
import vn.education.core.dto.UserDTO;
import vn.education.core.dto.UserImportDTO;
import vn.education.core.persistence.entity.RoleEntity;
import vn.education.core.persistence.entity.UserEntity;
import vn.education.core.service.UserService;
import vn.education.core.service.utils.SingletonDaoUtil;
import vn.education.core.utils.UserBeanUtil;

import java.sql.Timestamp;
import java.util.*;

public class UserServiceImpl implements UserService {

    public Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = SingletonDaoUtil.getUserDaoInstance().findByProperty(property,sortExpression,sortDirection,offset,limit);
        List<UserDTO> userDTOs = new ArrayList<UserDTO>();
        for( UserEntity item : (List<UserEntity>)objects[1]) {
            UserDTO userDTO = UserBeanUtil.entity2Dto(item);
            userDTOs.add(userDTO);
        }
        objects[1] = userDTOs;
        return objects;
    }

    public UserDTO findById(Integer userId) {
        UserEntity entity = SingletonDaoUtil.getUserDaoInstance().findById(userId);
        return UserBeanUtil.entity2Dto(entity);
    }

    public void saveUser(UserDTO userDTO) {
        Timestamp createDate = new Timestamp(System.currentTimeMillis());
        userDTO.setCreatedDate(createDate);
        UserEntity entity = UserBeanUtil.dto2Entity(userDTO);
        SingletonDaoUtil.getUserDaoInstance().save(entity);
    }

    public UserDTO updateUser(UserDTO userDTO) {
        UserEntity entity = UserBeanUtil.dto2Entity(userDTO);
        entity = SingletonDaoUtil.getUserDaoInstance().update(entity);
        return UserBeanUtil.entity2Dto(entity);
    }

    public CheckLogin checkLogin(String name, String password) {
        CheckLogin checkLogin = new CheckLogin();
        if(name != null && password != null) {
            Object[] objects = SingletonDaoUtil.getUserDaoInstance().checkLogin(name, password);
            checkLogin.setUserExist((Boolean) objects[0]);
            if(checkLogin.getUserExist()) {
                checkLogin.setRoleName((String) objects[1]);
            }
        }
        return checkLogin;
    }

    public void validateImportUser(List<UserImportDTO> userImportDTOS) {
        List<String> names = new ArrayList<String>();
        List<String> roles = new ArrayList<String>();

        for (UserImportDTO item : userImportDTOS) {
            if(item.isValid()) {
                names.add(item.getUserName());
                if(!roles.contains(item.getRoleName())) {
                    roles.add(item.getRoleName());
                }
            }
        }

        Map<String,UserEntity> userEntityMap = new HashMap<String, UserEntity>();
        Map<String, RoleEntity> roleEntityMap = new HashMap<String, RoleEntity>();

        if(names.size() >0) {
            List<UserEntity> userEntities = SingletonDaoUtil.getUserDaoInstance().findByUsers(names);
            for (UserEntity item : userEntities) {
                userEntityMap.put(item.getName().toUpperCase(), item);
            }
        }

        if(roles.size() >0 ){
            List<RoleEntity> roleEntities = SingletonDaoUtil.getRoleDaoInstance().findByRoles(roles);
            for(RoleEntity item : roleEntities) {
                roleEntityMap.put(item.getName().toUpperCase(),item);
            }
        }

        for (UserImportDTO item : userImportDTOS) {
            String message = "";
            if(item.isValid()) {
                UserEntity userEntity = userEntityMap.get(item.getUserName().toUpperCase());
                if(userEntity != null) {
                    message += "<br />";
                    message += "Tên đăng nhập tồn tại";
                }

                RoleEntity roleEntity = roleEntityMap.get(item.getRoleName().toUpperCase());
                if(roleEntity == null) {
                    message += "<br />";
                    message += "Vai trò không tồn tại";
                }
                if(StringUtils.isNotBlank(message)) {
                    item.setValid(false);
                    item.setError(message.substring(6));
                }
            }
        }
    }

    public void saveUserImport(List<UserImportDTO> userImportDTOS) {
        for(UserImportDTO item : userImportDTOS) {
            if(item.isValid()) {
                UserEntity userEntity = new UserEntity();
                userEntity.setName(item.getUserName());
                userEntity.setFullName(item.getFullName());
                userEntity.setPassword(item.getPassword());
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                userEntity.setCreatedDate(timestamp);
                RoleEntity roleEntity = SingletonDaoUtil.getRoleDaoInstance().findEqualUnique("name", item.getRoleName().toUpperCase());
                userEntity.setRoleEntity(roleEntity);
                SingletonDaoUtil.getUserDaoInstance().save(userEntity);
            }
        }
    }
}
