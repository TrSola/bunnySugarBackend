package com.EEIT85.bunnySugar.dto.anniversaries;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AnniversariesUpdateDto extends AnniversariesBaseDto{
    private Long id;

    public AnniversariesUpdateDto(String anniversaryName, LocalDate anniversaryDate, Long usersId, Long id) {
        super(anniversaryName, anniversaryDate, usersId);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
