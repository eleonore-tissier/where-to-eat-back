package com.example.wheretoeatback.controllers;

import com.example.wheretoeatback.entities.Restaurant;
import com.example.wheretoeatback.entities.Season;
import com.example.wheretoeatback.entities.Submission;
import com.example.wheretoeatback.entities.User;
import com.example.wheretoeatback.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private UserSubmissionsService userSubmissionsService;

    @Autowired
    public WhereToEatController(
            RestaurantsService restaurantsService,
            SubmissionsService submissionsService,
            SeasonsService seasonsService,
            UsersService usersService,
            UserSubmissionsService userSubmissionsService
    ) {
        this.restaurantsService = restaurantsService;
        this.submissionsService = submissionsService;
        this.seasonsService = seasonsService;
        this.usersService = usersService;
        this.userSubmissionsService = userSubmissionsService;
    }

    @GetMapping("/login")
    public ResponseEntity<User> login(@RequestParam String firstName, @RequestParam String lastName) {
        return new ResponseEntity<>(this.usersService.getUserByName(firstName, lastName), HttpStatus.OK);
//        TODO: à chaque appel à l'api, checker le cookie pour vérifier que l'utilisateur est bien connecté
    }

    @GetMapping("/restaurants")
    public ResponseEntity<List<Restaurant>> getRestaurants() {
        return new ResponseEntity<>(this.restaurantsService.getRestaurants(), HttpStatus.OK);
    }

    @GetMapping("/findRestaurant")
    public ResponseEntity<Restaurant> findRestaurant(@RequestParam String name) {
        return new ResponseEntity<>(this.restaurantsService.findRestaurant(name), HttpStatus.OK);
    }

    @GetMapping("/restaurantsInSubmissions")
    public ResponseEntity<List<Restaurant>> getRestaurantsInSubmissions() {
        return new ResponseEntity<>(this.restaurantsService.getRestaurantsInSubmissions(), HttpStatus.OK);
    }

    @GetMapping("submissions")
    public ResponseEntity<List<Submission>> getSubmissions() {
        return new ResponseEntity<>(this.submissionsService.getSubmissions(), HttpStatus.OK);
    }

    @PostMapping("/submitRestaurant")
    public ResponseEntity<Submission> submitRestaurant(@RequestParam String restaurantName, @RequestParam LocalDate eventDate, @RequestParam String userId) {
        User user = this.usersService.getUser(Integer.parseInt(userId));
        this.submissionsService.submitRestaurant(restaurantName, user.getId(), eventDate);
        Restaurant restaurant = this.restaurantsService.findRestaurant(restaurantName);
        this.restaurantsService.updateAlreadyDone(restaurant);
        Submission submission = new Submission(restaurantName,user, eventDate);
        return new ResponseEntity<>(submission, HttpStatus.OK);
    }

    @GetMapping("/seasons")
    public ResponseEntity<List<Season>> getSeasons() {
        return new ResponseEntity<>(this.seasonsService.getSeasons(), HttpStatus.OK);
    }

    @GetMapping("/currentSeason")
    public ResponseEntity<Season> getCurrentSeason() {
        return new ResponseEntity<>(this.seasonsService.getCurrentSeason(), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(this.usersService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestParam int id) {
        return new ResponseEntity<>(this.usersService.getUser(id), HttpStatus.OK);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return new ResponseEntity<>(this.usersService.updateUser(user), HttpStatus.OK);
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return new ResponseEntity<>(this.usersService.addUser(user), HttpStatus.OK);
    }

    @PostMapping("/addUserSubmission")
    public HttpStatus addUserSubmission(@RequestParam int userId, @RequestParam int submissionId) {
        if (this.userSubmissionsService.UserSubmissionAlreadyExist(userId, submissionId)) {
            this.userSubmissionsService.addUserSubmission(userId, submissionId);
            return HttpStatus.valueOf(200);
        }
        return HttpStatus.valueOf(500);
    }
}
