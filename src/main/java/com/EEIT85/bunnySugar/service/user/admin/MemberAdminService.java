package com.EEIT85.bunnySugar.service.user.admin;

import com.EEIT85.bunnySugar.dto.users.admin.MembeAdminUpdateDto;
import com.EEIT85.bunnySugar.dto.users.admin.MemberAdminSelectDto;
import com.EEIT85.bunnySugar.entity.Users;
import com.EEIT85.bunnySugar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MemberAdminService {
    @Autowired
    private UserRepository userRepository;

    // 使用jpql查詢所有會員並返回分頁結果
    public Page<MemberAdminSelectDto> getAllMembers(Pageable pageable) {
        return userRepository.findAllMemberAdminSelectDto(pageable);
    }

    // 使用jpql根據ID查詢會員
    public MemberAdminSelectDto getMemberById(Long id) {
        return userRepository.findMemberAdminSelectDtoById(id);
    }

    // 更新會員資料
    public boolean updateMember(Long id, MembeAdminUpdateDto updatedMemberDto) {
        Optional<Users> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            Users user = existingUser.get();

            // 根據DTO的資料更新會員資料
            user.setEmail(updatedMemberDto.getEmail() != null ? updatedMemberDto.getEmail() : user.getEmail());
            user.setName(updatedMemberDto.getName() != null ? updatedMemberDto.getName() : user.getName());
            user.setPhone(updatedMemberDto.getPhone() != null ? updatedMemberDto.getPhone() : user.getPhone());
            user.setGender(updatedMemberDto.getGender() != null ? updatedMemberDto.getGender() : user.getGender());
            user.setBirthday(updatedMemberDto.getBirthday() != null ? updatedMemberDto.getBirthday() : user.getBirthday());


            // 更新時間
            user.setUpdateTime(LocalDateTime.now());

            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

}
