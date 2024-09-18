package com.EEIT85.bunnySugar.dto.anniversaries;

import java.time.LocalDate;

public abstract class AnniversariesBaseDto {


    private String anniversaryName;
    private LocalDate anniversaryDate;
    private Long usersId;

    public AnniversariesBaseDto() {
    }

    public AnniversariesBaseDto(String anniversaryName, LocalDate anniversaryDate, Long usersId) {
        this.anniversaryName = anniversaryName;
        this.anniversaryDate = anniversaryDate;
        this.usersId = usersId;
    }

    public String getAnniversaryName() {
        return anniversaryName;
    }

    public void setAnniversaryName(String anniversaryName) {
        this.anniversaryName = anniversaryName;
    }

    public LocalDate getAnniversaryDate() {
        return anniversaryDate;
    }

    public void setAnniversaryDate(LocalDate anniversaryDate) {
        this.anniversaryDate = anniversaryDate;
    }

    public Long getUsersId() {
        return usersId;
    }

    public void setUsersId(Long usersId) {
        this.usersId = usersId;
    }
}