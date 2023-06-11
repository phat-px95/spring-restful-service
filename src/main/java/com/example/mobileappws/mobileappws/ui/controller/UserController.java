package com.example.mobileappws.mobileappws.ui.controller;

import com.example.mobileappws.mobileappws.exceptions.UserServiceException;
import com.example.mobileappws.mobileappws.shared.Utils;
import com.example.mobileappws.mobileappws.ui.model.request.UserDetailRequestModel;
import com.example.mobileappws.mobileappws.ui.model.response.UserRest;
import com.example.mobileappws.mobileappws.ui.model.update.UpdateUserDetailRequestModel;
import com.example.mobileappws.mobileappws.userservice.UserSerivce;
import com.example.mobileappws.mobileappws.userservice.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("users") // http://localhost:8080/user
public class UserController {
    @Autowired
    UserSerivce userSerivce;
    @Autowired
    Utils utils;
    @GetMapping
    public ResponseEntity<UserRest[]> getUsers(@RequestParam(value="page", defaultValue="1") int page,
                           @RequestParam(value="limit", defaultValue="10") int limit,
                           @RequestParam(value="sort", defaultValue = "desc", required = false) String sort) {
        if (!utils.getUsers().isEmpty()) {
            return new ResponseEntity<UserRest[]>(utils.getUsers().values().toArray(new UserRest[0]), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @GetMapping(path = "/{userId}", produces = { MediaType.APPLICATION_XML_VALUE,
                                                MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
        Boolean errorChecker = false;
        if(errorChecker) {
            throw new UserServiceException("Throw a user service exception");
        }
        UserRest returnVale = new UserRest();
        returnVale.setEmail("phat@gmmail.com");
        returnVale.setFirstName("Xuan");
        returnVale.setLastName("XXX");
        if(utils.getUsers() == null) {
            utils.setUsers(new HashMap<>());
        }
        if (utils.getUsers().containsKey(userId)) {
            return new ResponseEntity<UserRest>(utils.getUsers().get(userId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @PostMapping(
            consumes = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            },
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public ResponseEntity<UserRest> creteUser(@Valid @RequestBody UserDetailRequestModel userDetails) {
        UserRest returnValue = userSerivce.createUser(userDetails);
        return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
    }
    @PutMapping(path = "/{userId}",
            consumes = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            },
            produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<UserRest> updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserDetailRequestModel userDetails) {
        if(utils.getUsers().containsKey(userId)) {
            UserRest returnValue = utils.getUsers().get(userId);
            returnValue.setFirstName(userDetails.getFirstName());
            returnValue.setLastName(userDetails.getLastName());
//            returnValue.setUserId(userId);
            utils.getUsers().put(userId, returnValue);
            return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @DeleteMapping(path = "/{userId}")
    public ResponseEntity deleteUser(@PathVariable String userId) {
        if (utils.getUsers().containsKey(userId)) {
            utils.getUsers().remove(userId);
            return ResponseEntity.ok().build();
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(
//            MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }
}
