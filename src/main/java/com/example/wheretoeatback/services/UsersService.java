package com.example.wheretoeatback.services;

import com.example.wheretoeatback.entities.User;
import com.example.wheretoeatback.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> getUsers() {
        return this.usersRepository.findAll();
    }

    public User getUser(int id) {
        return this.usersRepository.findById(id);
    }

    public User getUserByName(String firstName, String lastName) {
        return this.usersRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public User addUser(User user) {
        return this.usersRepository.save(user);
    }

    public User updateUser(User user) {
        if (this.usersRepository.findById(user.getId()) != null) {
            user.setPoints(user.getPoints() + 1);
        }

        return this.usersRepository.save(user);
    }
}
