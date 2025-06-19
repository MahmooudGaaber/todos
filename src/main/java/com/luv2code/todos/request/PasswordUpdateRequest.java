package com.luv2code.todos.request;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PasswordUpdateRequest {

    @NotEmpty(message = "Old password Is Mandatory")
    @Size(min = 5 , max = 30 , message = "Old password Must be at least 3 Characters long")
    private String oldPassword ;

    @NotEmpty(message = "New password Is Mandatory")
    @Size(min = 5 , max = 30 , message = "New password Must be at least 3 Characters long")
    private String newPassword ;

    @NotEmpty(message = "Confirmed password Is Mandatory")
    @Size(min = 5 , max = 30 , message = "Confirmed password Must be at least 3 Characters long")
    private String newPassword2 ;


    public PasswordUpdateRequest(String oldPassword, String newPassword2, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword2 = newPassword2;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword2() {
        return newPassword2;
    }

    public void setNewPassword2(String newPassword2) {
        this.newPassword2 = newPassword2;
    }


}




