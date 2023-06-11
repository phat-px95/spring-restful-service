package com.example.mobileappws.mobileappws.userservice.impl;

import com.example.mobileappws.mobileappws.shared.Utils;
import com.example.mobileappws.mobileappws.ui.model.request.UserDetailRequestModel;
import com.example.mobileappws.mobileappws.ui.model.response.UserRest;
import com.example.mobileappws.mobileappws.userservice.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserSerivce {
    Utils utils;
    @Autowired
    public  UserServiceImpl(Utils utils) {
        utils.setUsers(new HashMap<>());
        this.utils = utils;
    }
    @Override
    public UserRest createUser(UserDetailRequestModel userDetails) {
        UserRest returnValue = new UserRest();
        returnValue.setFirstName(userDetails.getFirstName());
        returnValue.setLastName(userDetails.getLastName());
        returnValue.setEmail(userDetails.getEmail());
//        returnValue.setFirstName(null);

        String userId = utils.generateUserId();
        returnValue.setUserId(userId);
        if (utils.getUsers() == null) {
            utils.setUsers(new HashMap<>());
        }
        utils.getUsers().put(userId, returnValue);
        return returnValue;
    }
}
