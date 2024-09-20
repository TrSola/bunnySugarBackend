package com.EEIT85.bunnySugar.controller.user;

import com.EEIT85.bunnySugar.dto.MemberUpdateDto;
import com.EEIT85.bunnySugar.service.user.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/memberPage")
public class MemberController {

    @GetMapping("/memberData")
    public Map<String, Object> list() {
        Map<String, Object> res = new HashMap<>();
        res.put("status", "success");
        res.put("message", "JWT驗證成功，會員資料...");
        return res;
    }

    @Autowired
    private MemberService memberService;

    @PatchMapping("/memberData/{id}")
    public ResponseEntity<String> partialUpdateMember(
            @PathVariable Long id,
            @RequestBody MemberUpdateDto memberUpdateDto) {

        boolean success = memberService.partialUpdateUser(id, memberUpdateDto);

        if (success) {
            return ResponseEntity.ok("會員資料部分更新成功");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("會員不存在");
        }
    }

}
