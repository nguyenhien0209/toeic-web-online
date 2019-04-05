package vn.education.command;

import vn.education.core.dto.RoleDTO;
import vn.education.core.dto.UserDTO;
import vn.education.core.dto.UserImportDTO;
import vn.education.core.web.command.AbstractCommand;

import java.util.List;

public class UserCommand extends AbstractCommand<UserDTO> {
    private String confirmPassword;
    private List<RoleDTO> roleDTOS;
    private Integer roleId;
    private List<UserImportDTO> userImportDTOS;

    public List<UserImportDTO> getUserImportDTOS() {
        return userImportDTOS;
    }

    public void setUserImportDTOS(List<UserImportDTO> userImportDTOS) {
        this.userImportDTOS = userImportDTOS;
    }

    public UserCommand() {
        this.pojo = new UserDTO();
    }

    public List<RoleDTO> getRoleDTOS() {
        return roleDTOS;
    }

    public void setRoleDTOS(List<RoleDTO> roleDTOS) {
        this.roleDTOS = roleDTOS;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
