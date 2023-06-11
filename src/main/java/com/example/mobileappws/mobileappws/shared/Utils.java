package com.example.mobileappws.mobileappws.shared;

import com.example.mobileappws.mobileappws.ui.model.response.UserRest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Service
public class Utils {
    public Map<String, UserRest> getUsers() {
        if(users == null) {
            users = new HashMap<>();
        }
        return users;
    }

    public void setUsers(Map<String, UserRest> users) {
        this.users = users;
    }

    private Map<String, UserRest> users;
    public  String generateUserId() {
        return UUID.randomUUID().toString();
    }
}
