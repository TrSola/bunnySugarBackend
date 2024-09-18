package com.EEIT85.bunnySugar.repository;

import com.EEIT85.bunnySugar.entity.Anniversaries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnniversariesRepository extends JpaRepository<Anniversaries, Long> {

    @Modifying
    @Query(value = "INSERT INTO anniversaries (anniversary_name, anniversary_date, mail_sent, create_time, update_time, users_id) " +
            "VALUES (:#{#anniversaries.anniversaryName}, :#{#anniversaries.anniversaryDate}, :#{#anniversaries.mailSent}, " +
            ":#{#anniversaries.createTime}, :#{#anniversaries.updateTime}, :usersId)", nativeQuery = true)
    void saveAnniversariesAndUsersId(@Param("anniversaries") Anniversaries anniversaries, @Param("usersId") Long usersId);

}
