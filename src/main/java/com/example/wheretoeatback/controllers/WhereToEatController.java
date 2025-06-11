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

    @GetMapping("/checkUser")
    public ResponseEntity<User> checkUser(@RequestParam String firstName, @RequestParam String lastName, Model model) {
        User userToLogIn = this.usersService.getUserByName(firstName, lastName);
        if (userToLogIn != null) {
            model.addAttribute("loggedUser", userToLogIn);
            return new ResponseEntity<>(userToLogIn, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/loggedUser")
    private User getLoggedUser(@SessionAttribute("loggedUser") User user) {
        return user;
    }

    @GetMapping("/restaurants")
    public ResponseEntity<List<Restaurant>> getRestaurants(HttpSession session) {
        if (this.checkAuthor(session)) {
            return new ResponseEntity<>(this.restaurantsService.getRestaurants(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/findRestaurant")
    public ResponseEntity<Restaurant> findRestaurant(@RequestParam String name, HttpSession session) {
        if (this.checkAuthor(session)) {
            return new ResponseEntity<>(this.restaurantsService.findRestaurant(name), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/restaurantsInSubmissions")
    public ResponseEntity<List<Restaurant>> getRestaurantsInSubmissions(HttpSession session) {
        if (this.checkAuthor(session)) {
            return new ResponseEntity<>(this.restaurantsService.getRestaurantsInSubmissions(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("submissions")
    public ResponseEntity<List<Submission>> getSubmissions(HttpSession session) {
        if (this.checkAuthor(session)) {
            return new ResponseEntity<>(this.submissionsService.getSubmissions(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/submitRestaurant")
    public ResponseEntity<Submission> submitRestaurant(@RequestParam String restaurantName, @RequestParam LocalDate eventDate, HttpSession session) {
        if (this.checkAuthor(session)) {
            this.submissionsService.submitRestaurant(restaurantName, ((User) session.getAttribute("loggedUser")).getId(), eventDate);
            Restaurant restaurant = this.restaurantsService.findRestaurant(restaurantName);
            this.restaurantsService.updateAlreadyDone(restaurant);
            Submission submission = new Submission(restaurantName, ((User) session.getAttribute("loggedUser")).getId(), eventDate);
            return new ResponseEntity<>(submission, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/seasons")
    public ResponseEntity<List<Season>> getSeasons(HttpSession session) {
        if (this.checkAuthor(session)) {
            return new ResponseEntity<>(this.seasonsService.getSeasons(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/currentSeason")
    public ResponseEntity<Season> getCurrentSeason(HttpSession session) {
        if (this.checkAuthor(session)) {
            return new ResponseEntity<>(this.seasonsService.getCurrentSeason(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(HttpSession session) {
        if (this.checkAuthor(session)) {
            return new ResponseEntity<>(this.usersService.getUsers(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestParam int id, HttpSession session) {
        if (this.checkAuthor(session)) {
            return new ResponseEntity<>(this.usersService.getUser(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/updateUserPoints")
    public ResponseEntity<User> updateUserPoints(@RequestBody User user, HttpSession session) {
        if (this.checkAuthor(session)) {
            return new ResponseEntity<>(this.usersService.updateUserPoints(user), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    private boolean checkAuthor(HttpSession session) {
        if (session.getAttribute("loggedUser") != null) {
            return true;
        }
        return false;
    }
}
