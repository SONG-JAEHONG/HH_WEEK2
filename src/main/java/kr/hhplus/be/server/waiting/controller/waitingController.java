package kr.hhplus.be.server.waiting.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/waiting")
@Tag(name = "대기열 API", description = "유저 대기열 토큰 관리 API")
public class waitingController {

    @Operation(summary = "대기열 토큰 발급", description = "유저에게 대기열 토큰을 발급합니다.")
    @PostMapping("/token")
    public ResponseEntity<Map<String, Object>> issueToken(@RequestBody Map<String, String> request) {
        String token = UUID.randomUUID().toString();

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("waitingNumber", 42);
        response.put("estimatedWaitTime", 180);

        return ResponseEntity.ok(response);
    }

}