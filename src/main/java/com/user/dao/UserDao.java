package com.user.dao;

import com.user.entity.User;
import com.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDao {

    @Autowired
    private UserRepository repository;

    public User add(User user) {
        return repository.save(user);
    }

    public User update(User user, Long userId){
        user.setId(userId);
        return repository.save(user);
    }

    public List<User> viewAll(){
        return repository.findAll();
    }

    public Optional<User> viewById(Long userId){
        return repository.findById(userId);
    }

    public void delete(Long userId) {
        repository.deleteById(userId);
    }

    public User findUserByCode(String code){
        return repository.findByVerificationCode(code);
    }

    public User findUserByEmail(String email){
        return repository.findByEmailAddress(email);
    }
}
