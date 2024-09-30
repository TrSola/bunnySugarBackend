package com.EEIT85.bunnySugar.controller.user;

import com.EEIT85.bunnySugar.dto.users.front.MemberFrontDto;
import com.EEIT85.bunnySugar.dto.users.front.MemberFrontUpdateDto;
import com.EEIT85.bunnySugar.entity.Users;
import com.EEIT85.bunnySugar.repository.UserRepository;
import com.EEIT85.bunnySugar.service.user.UserService;
import com.EEIT85.bunnySugar.service.user.front.MemberFrontService;
import org.springdoc.core.converters.ResponseSupportConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/memberPage")
public class MemberController {

    @Autowired
    private MemberFrontService memberFrontService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResponseSupportConverter responseSupportConverter;

    @GetMapping("/memberData")
    public Map<String, Object> list() {
        Map<String, Object> res = new HashMap<>();
        res.put("status", "success");
        res.put("message", "JWT驗證成功，會員資料...");
        return res;
    }

    // 查詢會員資料
    @GetMapping("/{id}")
    public ResponseEntity<MemberFrontDto> getMemberById(@PathVariable Long id) {
        MemberFrontDto memberFrontDto = memberFrontService.getMemberById(id);
        return ResponseEntity.ok(memberFrontDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMember(
            @PathVariable Long id, @RequestBody MemberFrontUpdateDto updatedMemberDto) {
        memberFrontService.updateMember(id, updatedMemberDto);
        return ResponseEntity.ok("會員資料更新成功");
    }

}
