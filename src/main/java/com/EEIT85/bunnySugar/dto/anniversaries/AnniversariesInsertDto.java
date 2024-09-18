package com.EEIT85.bunnySugar.dto.anniversaries;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AnniversariesInsertDto extends AnniversariesBaseDto{
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public AnniversariesInsertDto(String anniversaryName, LocalDate anniversaryDate, Long usersId, LocalDateTime createTime, LocalDateTime updateTime) {
        super(anniversaryName, anniversaryDate, usersId);
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
