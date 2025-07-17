package kr.hhplus.be.server.reservation.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/reservations")
@Tag(name = "예약 API", description = "좌석 예약 API")
public class reservationController {

    @Operation(summary = "좌석 예약 요청", description = "좌석을 예약하고 임시 배정 5분 안내합니다.")
    @PostMapping
    public ResponseEntity<Map<String, Object>> reserveSeat(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("reservationId", 1234);
        response.put("expires", 300);

        return ResponseEntity.ok(response);
    }
}