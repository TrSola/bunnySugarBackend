package com.EEIT85.bunnySugar.service.game;

import com.EEIT85.bunnySugar.dto.GameDetailsDto;
import com.EEIT85.bunnySugar.entity.Users;
import com.EEIT85.bunnySugar.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public boolean startGame(Long id) {
        Users user = gameRepository.findById(id).orElse(null);

        if (user == null || user.getGameTimes() == null || user.getGameTimes() == 0) {
            return false;
        } else {
            int updatedGameTimes = user.getGameTimes() - 1;
            user.setGameTimes(updatedGameTimes);
            gameRepository.save(user);
            return true;
        }
    }

    public Integer endGame(Long id, GameDetailsDto result) {
        Users user = gameRepository.findById(id).orElse(null);
        if (user != null) {
            user.setBunnyCoin(user.getBunnyCoin() + result.getEarnedCoins());
            gameRepository.save(user);
            return user.getGameTimes();
        }
        return null;
    }
}
