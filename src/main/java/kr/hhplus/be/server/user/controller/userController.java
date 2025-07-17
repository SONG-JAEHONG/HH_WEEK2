package kr.hhplus.be.server.user.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@Tag(name = "유저 API", description = "포인트 조회 및 충전 API")
public class userController {

    @Operation(summary = "포인트 조회", description = "포인트 잔액를 조회합니다.")
    @GetMapping("/{userId}/balance")
    public ResponseEntity<Map<String, Object>> getBalance(@PathVariable String userId) {
        return ResponseEntity.ok(Map.of("balance", 25000));
    }

    @Operation(summary = "포인트 충전", description = "포인트를 충전합니다.")
    @PostMapping("/{userId}/balance/charge")
    public ResponseEntity<Map<String, Object>> chargeBalance(@PathVariable String userId, @RequestBody Map<String, Integer> request) {
        return ResponseEntity.ok(Map.of("balance", 75000));
    }
}