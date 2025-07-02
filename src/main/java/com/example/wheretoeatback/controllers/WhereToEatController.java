package com.example.wheretoeatback.controllers;

import com.example.wheretoeatback.entities.Restaurant;
import com.example.wheretoeatback.entities.Season;
import com.example.wheretoeatback.entities.Submission;
import com.example.wheretoeatback.entities.User;
import com.example.wheretoeatback.services.RestaurantsService;
import com.example.wheretoeatback.services.SeasonsService;
import com.example.wheretoeatback.services.SubmissionsService;
import com.example.wheretoeatback.services.UsersService;
import jakarta.servlet.http.HttpSession;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
public class WhereToEatController {

    private RestaurantsService restaurantsService;

    private SubmissionsService submissionsService;

    private SeasonsService seasonsService;

    private UsersService usersService;

    @Autowired
    public WhereToEatController(
            RestaurantsService restaurantsService,
            SubmissionsService submissionsService,
            SeasonsService seasonsService,
            UsersService usersService
    ) {
        this.restaurantsService = restaurantsService;
        this.submissionsService = submissionsService;
        this.seasonsService = seasonsService;
        this.usersService = usersService;
    }

    @GetMapping("/restaurants")
    public ResponseEntity<List<Restaurant>> getRestaurants(HttpSession session) {
        return new ResponseEntity<>(this.restaurantsService.getRestaurants(), HttpStatus.OK);
    }

    @GetMapping("/findRestaurant")
    public ResponseEntity<Restaurant> findRestaurant(@RequestParam String name, HttpSession session) {
        return new ResponseEntity<>(this.restaurantsService.findRestaurant(name), HttpStatus.OK);
    }

    @GetMapping("/restaurantsInSubmissions")
    public ResponseEntity<List<Restaurant>> getRestaurantsInSubmissions(HttpSession session) {
        return new ResponseEntity<>(this.restaurantsService.getRestaurantsInSubmissions(), HttpStatus.OK);
    }

    @GetMapping("submissions")
    public ResponseEntity<List<Submission>> getSubmissions(HttpSession session) {
        return new ResponseEntity<>(this.submissionsService.getSubmissions(), HttpStatus.OK);
    }

    @PostMapping("/submitRestaurant")
    public ResponseEntity<Submission> submitRestaurant(@RequestParam String restaurantName, @RequestParam LocalDate eventDate) {
        this.submissionsService.submitRestaurant(restaurantName, 1, eventDate);
        Restaurant restaurant = this.restaurantsService.findRestaurant(restaurantName);
        this.restaurantsService.updateAlreadyDone(restaurant);
        Submission submission = new Submission(restaurantName,1, eventDate);
        return new ResponseEntity<>(submission, HttpStatus.OK);
    }

    @GetMapping("/seasons")
    public ResponseEntity<List<Season>> getSeasons(HttpSession session) {
        return new ResponseEntity<>(this.seasonsService.getSeasons(), HttpStatus.OK);
    }

    @GetMapping("/currentSeason")
    public ResponseEntity<Season> getCurrentSeason(HttpSession session) {
        return new ResponseEntity<>(this.seasonsService.getCurrentSeason(), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(HttpSession session) {
        return new ResponseEntity<>(this.usersService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestParam int id, HttpSession session) {
        return new ResponseEntity<>(this.usersService.getUser(id), HttpStatus.OK);
    }

    @PutMapping("/updateUserPoints")
    public ResponseEntity<User> updateUserPoints(@RequestBody User user, HttpSession session) {
        return new ResponseEntity<>(this.usersService.updateUserPoints(user), HttpStatus.OK);
    }
}
