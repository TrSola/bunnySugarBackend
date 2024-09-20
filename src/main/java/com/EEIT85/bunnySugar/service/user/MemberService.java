package com.EEIT85.bunnySugar.service.user;

import com.EEIT85.bunnySugar.dto.MemberUpdateDto;
import com.EEIT85.bunnySugar.entity.Users;
import com.EEIT85.bunnySugar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private UserRepository userRepository;

    public boolean partialUpdateUser(Long id, MemberUpdateDto memberUpdateDto) {
        Optional<Users> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            Users user = existingUser.get();

            // 檢查 DTO 是否有新的數據，沒有的話保留原本的值
            if (memberUpdateDto.getEmail() != null) {
                user.setEmail(memberUpdateDto.getEmail());
            }
            if (memberUpdateDto.getName() != null) {
                user.setName(memberUpdateDto.getName());
            }
            if (memberUpdateDto.getGender() != null) {
                user.setGender(memberUpdateDto.getGender());
            }
            if (memberUpdateDto.getPhone() != null) {
                user.setPhone(memberUpdateDto.getPhone());
            }
            if (memberUpdateDto.getBirthday() != null) {
                user.setBirthday(memberUpdateDto.getBirthday());
            }

            // 保存修改過的會員資料
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }
}
