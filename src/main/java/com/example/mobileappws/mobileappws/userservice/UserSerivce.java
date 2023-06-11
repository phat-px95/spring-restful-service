package com.example.mobileappws.mobileappws.userservice;

import com.example.mobileappws.mobileappws.ui.model.request.UserDetailRequestModel;
import com.example.mobileappws.mobileappws.ui.model.response.UserRest;

public interface UserSerivce {
    UserRest createUser(UserDetailRequestModel userDetails);
}
