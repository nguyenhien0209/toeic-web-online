package vn.education.core.dto;

import java.io.Serializable;

public class CheckLogin implements Serializable {
    private Boolean isUserExist;
    private String roleName;

    public Boolean getUserExist() {
        return isUserExist;
    }

    public void setUserExist(Boolean userExist) {
        isUserExist = userExist;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
