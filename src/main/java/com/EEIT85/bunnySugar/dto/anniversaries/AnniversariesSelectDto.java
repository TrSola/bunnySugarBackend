package com.EEIT85.bunnySugar.dto.anniversaries;

import java.time.LocalDate;

public class AnniversariesSelectDto extends AnniversariesBaseDto{
    public AnniversariesSelectDto(String anniversaryName, LocalDate anniversaryDate, Long usersId) {
        super(anniversaryName, anniversaryDate, usersId);
    }
}
