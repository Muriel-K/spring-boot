package com.Impelsys.UserDemo.Controller;

import com.Impelsys.UserDemo.Exception.RecordNotFound;
import com.Impelsys.UserDemo.Model.User;
import com.Impelsys.UserDemo.Repository.UserRepository;
import com.Impelsys.UserDemo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController //creation of RESTful web services.
@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("/users")
public class UserController
{
    @Autowired
    private UserService service;

    //used to map Spring MVC controller methods

    //Display all the user data

    @GetMapping
    public ResponseEntity <List<User>> getAllEmployees(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<User> list = service.getAllUsers(pageNo, pageSize, sortBy);

        return new ResponseEntity<List<User>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getEmployeeById(@PathVariable("id") Long id)
            throws  RecordNotFound {
        User entity = service.getUserById(id);

        return new ResponseEntity<User>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createOrUpdateEmployee(User employee)
            throws RecordNotFound {
        User updated = service.createOrUpdateUser(employee);
        return new ResponseEntity<User>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteEmployeeById(@PathVariable("id") Long id)
            throws RecordNotFound {
        service.deleteUserById(id);
        return HttpStatus.FORBIDDEN;
    }


}
