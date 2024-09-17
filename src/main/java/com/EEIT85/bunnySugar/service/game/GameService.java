package com.EEIT85.bunnySugar.service.game;

import com.EEIT85.bunnySugar.dto.GameDetailsDto;

public interface GameService {
    boolean startGame(Integer id);
    Integer endGame(Integer id, GameDetailsDto result);
}
