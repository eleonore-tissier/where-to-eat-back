package com.example.wheretoeatback.services;

import com.example.wheretoeatback.entities.Restaurant;
import com.example.wheretoeatback.repositories.RestaurantsRepository;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantsService {

    private RestaurantsRepository restaurantsRepository;

    public RestaurantsService(RestaurantsRepository restaurantsRepository) {
        this.restaurantsRepository = restaurantsRepository;
    }

    public List<Restaurant> getRestaurants() {
        return this.restaurantsRepository.findAll();
    }

    public Restaurant findRestaurant(String name) {
        return this.restaurantsRepository.findByName(name);
    }

    public List<Restaurant> getRestaurantsInSubmissions() {
        return this.restaurantsRepository.findAllInSubmissions();
    }

    public void updateAlreadyDone(Restaurant restaurant) {
        restaurant.setAlreadyDone(true);
        this.restaurantsRepository.save(restaurant);
    }

}
