package com.EEIT85.bunnySugar.dto.anniversaries;

import java.time.LocalDate;

public class AnniversariesSelectDto extends AnniversariesBaseDto{
    private final Long id;

    public AnniversariesSelectDto(LocalDate anniversaryDate, String anniversaryName, Long id) {
        super(anniversaryDate, anniversaryName);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
