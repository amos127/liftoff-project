package org.launchcode.liftoffproject.models.dto;


import javax.validation.constraints.NotNull;

public class LoginRegisterFormDTO {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String verifyPassword;

    public LoginRegisterFormDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        checkPasswordForRegistration();
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
        checkPasswordForRegistration();
    }

    private void checkPasswordForRegistration() {
        if (!getPassword().equals(verifyPassword)) {
            verifyPassword = null;
        }
    }
}
