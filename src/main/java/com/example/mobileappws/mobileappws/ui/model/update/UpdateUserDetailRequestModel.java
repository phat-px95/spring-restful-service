package com.example.mobileappws.mobileappws.ui.model.update;


import javax.validation.constraints.NotNull;

public class UpdateUserDetailRequestModel {
    @NotNull(message = "First name cannot be empty")
    private String firstName;
    @NotNull(message = "First name cannot be empty")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
