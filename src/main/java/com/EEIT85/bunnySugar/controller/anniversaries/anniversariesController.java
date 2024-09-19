package com.EEIT85.bunnySugar.controller.anniversaries;

import com.EEIT85.bunnySugar.dto.anniversaries.AnniversariesInsertDto;
import com.EEIT85.bunnySugar.dto.anniversaries.AnniversariesSelectDto;
import com.EEIT85.bunnySugar.dto.anniversaries.AnniversariesUpdateDto;
import com.EEIT85.bunnySugar.service.AnniversariesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/anniversaries")
@RestController
public class anniversariesController {
    Long usersId = 1L;
    @Autowired
    AnniversariesService anniversariesService;

    @GetMapping
    public List<AnniversariesSelectDto> getAllById() {
        return anniversariesService.getAllById(usersId);
    }

    @PostMapping
    public ResponseEntity<String> insertAnniversaries(@RequestBody AnniversariesInsertDto anniversariesInsertDto) {
        anniversariesService.insertAnniversaries(anniversariesInsertDto, usersId);
        return ResponseEntity.ok("成功新增");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnniversaries(@PathVariable Long id) {
        anniversariesService.deleteAnniversaries(id);
        return ResponseEntity.ok("成功刪除");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAnniversaries(@PathVariable Long id ,
                                                      @RequestBody AnniversariesUpdateDto anniversariesUpdateDto) {
        anniversariesService.updateAnniversaries(id, anniversariesUpdateDto);
        return ResponseEntity.ok("成功更新");
    }
}
