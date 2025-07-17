package kr.hhplus.be.server.concert.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/concerts")
@Tag(name = "공연 API", description = "공연 및 날짜 조회 API")
public class concertController {

    @Operation(summary = "공연 목록 조회", description = "공연 목록을 조회합니다.")
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAvailableConcerts() {
        List<Map<String, Object>> mockConcerts = List.of(
                Map.of("concertId", 1, "title", "공연A"),
                Map.of("concertId", 2, "title", "공연B")
        );

        return ResponseEntity.ok(mockConcerts);
    }

    @Operation(summary = "예약 가능 날짜 조회", description = "concertId로 예약 가능한 날짜 목록을 조회합니다.")
    @GetMapping("/{concertId}/dates")
    public ResponseEntity<List<Map<String, Object>>> getAvailableDates(@PathVariable Long concertId) {
        List<Map<String, Object>> mockDates = List.of(
                Map.of("concertDateId", 1, "date", "2025-07-18"),
                Map.of("concertDateId", 2, "date", "2025-07-19")
        );

        return ResponseEntity.ok(mockDates);
    }
}