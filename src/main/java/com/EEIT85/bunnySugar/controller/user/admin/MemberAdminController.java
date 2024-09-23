package com.EEIT85.bunnySugar.controller.user.admin;

import com.EEIT85.bunnySugar.dto.users.admin.MembeAdminUpdateDto;
import com.EEIT85.bunnySugar.dto.users.admin.MemberAdminSelectDto;
import com.EEIT85.bunnySugar.service.user.admin.MemberAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/members")
public class MemberAdminController {

    @Autowired
    private MemberAdminService memberAdminService;

    // 查詢所有會員並分頁
    @GetMapping
    public ResponseEntity<Page<MemberAdminSelectDto>> getAllMembers(
            @RequestParam(defaultValue = "1") int page,   // 當前的頁碼，預設為第1頁（索引從0開始）
            @RequestParam(defaultValue = "10") int size   // 每頁顯示的資料數量，默認為10條
    ) {
        Pageable pageable = PageRequest.of(page - 1, size); // springboot索引起始為0，實際頁碼起始為1
        Page<MemberAdminSelectDto> membersPage = memberAdminService.getAllMembers(pageable);
        return ResponseEntity.ok(membersPage);
    }

    // 根據ID查詢會員
    @GetMapping("/{id}")
    public ResponseEntity<MemberAdminSelectDto> getMemberById(@PathVariable Long id) {
        MemberAdminSelectDto member = memberAdminService.getMemberById(id);
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
        boolean success = memberAdminService.updateMember(id, updatedMemberDto);
        if (success) {
            return ResponseEntity.ok("會員資料更新成功");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("會員不存在");
        }
    }
}

