package com.EEIT85.bunnySugar.service.user.front;

import com.EEIT85.bunnySugar.dto.users.admin.MemberAdminUpdateDto;
import com.EEIT85.bunnySugar.dto.users.front.MemberFrontDto;
import com.EEIT85.bunnySugar.dto.users.front.MemberFrontUpdateDto;
import com.EEIT85.bunnySugar.entity.Users;
import com.EEIT85.bunnySugar.exception.MemberNotFoundException;
import com.EEIT85.bunnySugar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MemberFrontService {
    @Autowired
    private UserRepository userRepository;


//    // 根據 ID查詢會員資料
//    public MemberFrontDto getMemberById(Long id) {
//        return userRepository.findById(id)
//                .map(user -> {
//                    MemberFrontDto memberFrontDto = new MemberFrontDto();
//                    memberFrontDto.setName(user.getName());
//                    memberFrontDto.setGender(user.getGender());
//                    memberFrontDto.setPhone(user.getPhone());
//                    memberFrontDto.setBirthday(user.getBirthday());
//                    memberFrontDto.setEmail(user.getEmail());
//                    memberFrontDto.setGameTimes(user.getGameTimes());
//                    memberFrontDto.setBunnyCoin(user.getBunnyCoin());
//                    return memberFrontDto;
//                })
//                .orElseThrow(() -> new MemberNotFoundException("會員不存在"));
//    }

    // 根據ID查詢會員並返回DTO
    public Optional<Users> getMemberById(Long userId) {
        return userRepository.findById(userId);
    }


    // 更新會員資料
    public void updateMember(Long id, MemberFrontUpdateDto updatedMemberDto) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("會員不存在"));

        // 前端允許更改的字段
        user.setName(updatedMemberDto.getName() != null ? updatedMemberDto.getName() : user.getName());
        user.setPhone(updatedMemberDto.getPhone() != null ? updatedMemberDto.getPhone() : user.getPhone());
        user.setGender(updatedMemberDto.getGender() != null ? updatedMemberDto.getGender() : user.getGender());
        user.setBirthday(updatedMemberDto.getBirthday() != null ? updatedMemberDto.getBirthday() : user.getBirthday());

        user.setUpdateTime(LocalDateTime.now());

        userRepository.save(user);
    }
}
