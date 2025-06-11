package com.example.wheretoeatback.repositories;

import com.example.wheretoeatback.entities.User;
import jakarta.annotation.Nonnull;
import jakarta.jws.soap.SOAPBinding;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Qualifier("users")
@Repository
public interface UsersRepository extends CrudRepository<User, Long> {

    @Nonnull
    List<User> findAll();

    @Nonnull
    User findById(int id);

    @Nonnull
    User findByFirstNameAndLastName(String firstName, String lastName);

    @Nonnull
    User save(User user);
}
