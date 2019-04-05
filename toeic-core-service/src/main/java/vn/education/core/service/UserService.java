package vn.education.core.service;

import vn.education.core.dto.CheckLogin;
import vn.education.core.dto.UserDTO;
import vn.education.core.dto.UserImportDTO;
import vn.education.core.persistence.entity.UserEntity;

import java.util.List;
import java.util.Map;

public interface UserService {
    Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    UserDTO findById(Integer userId);
    void saveUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO);
    CheckLogin checkLogin(String name, String password);
    void validateImportUser(List<UserImportDTO> userImportDTOS);
    void saveUserImport(List<UserImportDTO> userImportDTOS);
}
