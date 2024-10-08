package com.EEIT85.bunnySugar.service.game;

import com.EEIT85.bunnySugar.dto.game.GameDetailsDto;
import com.EEIT85.bunnySugar.entity.Users;
import com.EEIT85.bunnySugar.repository.GameRepository;
import com.EEIT85.bunnySugar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    // 開始遊戲邏輯
    public GameDetailsDto startGame(Long id) {
        Users user = gameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found for id: " + id));

        GameDetailsDto gameDetailsDto = new GameDetailsDto();

        // 檢查用戶的遊戲次數是否有效
        if (user.getGameTimes() == null || user.getGameTimes() == 0) {
            gameDetailsDto.setGameTimes(0);
            return gameDetailsDto;
        } else {
            // 減少遊戲次數
            int updatedGameTimes = user.getGameTimes() - 1;
            user.setGameTimes(updatedGameTimes);
            gameRepository.save(user);

            // 返回遊戲次數
            gameDetailsDto.setGameTimes(updatedGameTimes);
            return gameDetailsDto;
        }
    }

    // 結束遊戲邏輯
    public GameDetailsDto endGame(Long id, GameDetailsDto result) {
        // 查找用戶
        Users user = gameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found for id: " + id));

        // 檢查 result 是否有效
        if (result == null || result.getEarnedCoins() == null) {
            System.out.println("Invalid result data.");
            return null;
        }

        // 從數據庫中獲取並更新用戶的遊戲次數
        int updatedGameTimes = user.getGameTimes() - 1;

        // 更新用戶的 bunnyCoin
        int updatedBunnyCoins = user.getBunnyCoin() + result.getEarnedCoins();

        // 保存更新
        user.setGameTimes(updatedGameTimes);
        user.setBunnyCoin(updatedBunnyCoins);
        gameRepository.save(user);

        // 返回更新後的 GameDetailsDto
        GameDetailsDto gameDetailsDto = new GameDetailsDto();
        gameDetailsDto.setGameTimes(updatedGameTimes); // 這裡的遊戲次數來自數據庫
        gameDetailsDto.setEarnedCoins(result.getEarnedCoins()); // 這裡的金幣來自前端
        return gameDetailsDto;
    }

}


