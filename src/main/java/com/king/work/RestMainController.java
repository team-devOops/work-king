package com.king.work;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestMainController {
    @GetMapping("/main")
    public ResponseEntity<String> getMain() {
        return ResponseEntity.ok("gg");
    }
}
