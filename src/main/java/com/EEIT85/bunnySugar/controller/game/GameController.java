package com.EEIT85.bunnySugar.controller.game;

import com.EEIT85.bunnySugar.dto.GameDetailsDto;
import com.EEIT85.bunnySugar.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "http://localhost:5173")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/test")
    public String test() {
        return "GET request successful!";
    }

    @PostMapping("/start/{id}")
    public ResponseEntity<String> startGame(@PathVariable Long id) {
        boolean canPlay = gameService.startGame(id);
        if (canPlay) {
            return ResponseEntity.ok("User has enough game times!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not enough game times!");
        }
    }

    @PostMapping("/end/{id}")
    public ResponseEntity<Integer> endGame(@PathVariable Long id, @RequestBody GameDetailsDto result) {
        Integer gameTimes = gameService.endGame(id, result);
        return ResponseEntity.ok(gameTimes);
    }
}
