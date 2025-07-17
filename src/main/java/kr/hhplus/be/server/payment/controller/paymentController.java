package kr.hhplus.be.server.payment.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@Tag(name = "결제 API", description = "좌석 결제 API")
public class paymentController {

    @Operation(summary = "결제 요청", description = "결제를 진행합니다.")
    @PostMapping
    public ResponseEntity<Map<String, Object>> processPayment(@RequestBody Map<String, Object> request) {
        return ResponseEntity.ok(Map.of(
                "paymentId", 5678,
                "amount", 30000,
                "status", "SUCCESS"
        ));
    }
}