package kr.hhplus.be.server.seat.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/seats")
@Tag(name = "좌석 API", description = "좌석 조회 API")
public class seatController {

    @Operation(summary = "예약 가능 좌석 조회", description = "concertDateId로 예약 가능한 좌석 목록을 조회합니다.")
    @GetMapping("/{concertDateId}")
    public ResponseEntity<List<Map<String, Object>>> getAvailableSeats(@PathVariable Long concertDateId) {
        List<Map<String, Object>> mockSeats = List.of(
                Map.of("seatNumber", 1, "status", "AVAILABLE"),
                Map.of("seatNumber", 2, "status", "RESERVED")
        );

        return ResponseEntity.ok(mockSeats);
    }
}