package com.example.wheretoeatback.repositories;

import com.example.wheretoeatback.entities.Restaurant;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Qualifier("restaurants")
@Repository
public interface RestaurantsRepository extends CrudRepository<Restaurant, Long>{

    @Nonnull
    List<Restaurant> findAll();

    @Nonnull
    Restaurant findByName(String name);

    @Nonnull
    @Query(value = "select * from restaurants where nom in (select nom_restaurant from submissions)", nativeQuery = true)
    List<Restaurant> findAllInSubmissions();

    @Nonnull
    Restaurant save(Restaurant restaurant);
}
