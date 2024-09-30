package com.EEIT85.bunnySugar.controller.game;

import com.EEIT85.bunnySugar.dto.game.GameDetailsDto;
import com.EEIT85.bunnySugar.service.game.GameService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/game")
@CrossOrigin(origins = "http://localhost:5173")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/start")
    public ResponseEntity<String> startGame(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        boolean canPlay = gameService.startGame(userId);
        if (canPlay) {
            return ResponseEntity.ok("User has enough game times!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not enough game times!");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Integer> endGame(@PathVariable Long id, @RequestBody GameDetailsDto result) {
        Integer gameTimes = gameService.endGame(id, result);
        return ResponseEntity.ok(gameTimes);
    }
}

