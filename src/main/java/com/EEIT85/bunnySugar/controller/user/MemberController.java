package com.EEIT85.bunnySugar.controller.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
