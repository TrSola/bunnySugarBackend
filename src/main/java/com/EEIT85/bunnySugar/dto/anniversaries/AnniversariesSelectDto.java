package com.EEIT85.bunnySugar.dto.anniversaries;

import java.time.LocalDate;

public class AnniversariesSelectDto extends AnniversariesBaseDto{
    private final Long id;
    public AnniversariesSelectDto(Long id, String anniversaryName,
                                  LocalDate anniversaryDate, Long usersId) {
        super(anniversaryName, anniversaryDate, usersId);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
