package com.Impelsys.UserDemo.Service;

import com.Impelsys.UserDemo.Exception.RecordNotFound;
import com.Impelsys.UserDemo.Model.User;
import com.Impelsys.UserDemo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public List <User> getAllUsers(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page <User> pagedResult = repository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList <User>();
        }
    }

    public User getUserById(Long id) throws RecordNotFound
    {
        Optional <User> employee = repository.findById(id);

        if(employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFound("No employee record exist for given id");
        }
    }

    public User createOrUpdateUser(User entity) throws RecordNotFound
    {
        Optional<User> user = repository.findById(entity.getId());

        if(user.isPresent())
        {
            User newEntity = user.get();
            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());
            newEntity.setEmail(entity.getLastName());
            newEntity.setPhone(entity.getPhone());

            newEntity = repository.save(newEntity);

            return newEntity;
        } else {
            entity = repository.save(entity);

            return entity;
        }
    }

    public void deleteUserById(Long id) throws RecordNotFound
    {
        Optional<User> user = repository.findById(id);

        if(user.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFound("No employee record exist for given id");
        }
    }
}
