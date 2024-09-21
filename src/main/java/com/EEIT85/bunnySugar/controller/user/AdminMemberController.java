package com.EEIT85.bunnySugar.controller.user;

import com.EEIT85.bunnySugar.dto.users.MembeAdminUpdateDto;
import com.EEIT85.bunnySugar.dto.users.MemberAdminDto;
import com.EEIT85.bunnySugar.service.user.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/members")
public class AdminMemberController {

    @Autowired
    private MemberService memberService;

    // 查詢所有會員
    @GetMapping
    public ResponseEntity<List<MemberAdminDto>> getAllMembers() {
        List<MemberAdminDto> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }

    // 根據ID查詢會員
    @GetMapping("/{id}")
    public ResponseEntity<MemberAdminDto> getMemberById(@PathVariable Long id) {
        MemberAdminDto member = memberService.getMemberById(id);
        if (member != null) {
            return ResponseEntity.ok(member);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // 更新會員資料
    @PutMapping("/{id}")
    public ResponseEntity<String> updateMember(
            @PathVariable Long id, @RequestBody MembeAdminUpdateDto updatedMemberDto) {
        boolean success = memberService.updateMember(id, updatedMemberDto);
        if (success) {
            return ResponseEntity.ok("會員資料更新成功");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("會員不存在");
        }
    }
}

