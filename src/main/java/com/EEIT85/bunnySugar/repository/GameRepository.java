package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.entity.UserForGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<UserForGame, Integer> {
    // Custom query methods can be added here if necessary
}
