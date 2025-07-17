classDiagram
direction LR

%% 공통 인증
class TokenAuthFilter {
+checkToken(token) : 요청에 포함된 토큰의 유효성을 검사
}

%% 대기열 관리
class WaitingController {
+issueToken(userId) : 대기열 토큰 발급 API
}

class WaitingService {
+generateToken(userId) : UUID 기반 대기열 토큰 생성
+storeToken(token) : Redis 대기열에 토큰 저장
+tryFillWorkingQueue() : 예약열로 이동 시도
+fillWorkingQueue() : 예약열 자리 채우기
+completeWork(token) : 결제 완료 후 예약열 토큰 제거
}

class Redis

%% 공연 조회
class ConcertController {
+getAvailableConcerts() : 공연 목록 조회 API
+getAvailableDates(concertId) : 해당 공연의 날짜 조회 API
}

class ConcertService {
+getAvailableConcerts() : 공연 목록 조회
+getAvailableDates(concertId) : 해당 공연의 날짜 조회
}

class ConcertRepository {
+findAllConcerts() : DB에서 공연 목록 반환
+findConcertDates(concertId) : DB에서 해당 공연의 날짜 반환
}

%% 좌석 조회
class SeatController {
+getAvailableSeats(concertDateId) : 좌석 목록 조회 API
}

class SeatService {
+getAvailableSeats(concertDateId) : 예약 가능한 좌석 목록 조회
}

class SeatRepository {
+findSeatsByConcertDate(concertDateId) : 좌석 전체 조회 후 status 필터링
}

%% 좌석 예약
class ReservationController {
+reserveSeat(date, seatNo, userId) : 좌석 예약 API
}

class ReservationService {
+reserveSeat(concertDateId, seatNumber, userId) : 좌석 예약 처리
+markReservationCompleted(reservationId) : 결제 완료 시 예약 상태 변경
}

class ReservationRepository {
+findByConcertDateIdAndSeatNumber() : 좌석별 예약 정보 조회
+save() : 예약 정보 저장
}

%% 결제 처리
class PaymentController {
+payment(userId, dateId, seatNo) : 결제 요청 API
}

class PaymentService {
+processPayment(userId, dateId, seatNo) : 결제 프로세스 관리
+savePaymentHistory(userId, reservationId, amount) : 결제내역 저장
}

%% 잔액 관리
class UserController {
+getBalance(userId) : 잔액 조회 API
+chargeBalance(userId, amount) : 잔액 충전 API
}

class UserService {
+getBalance(userId) : 유저 잔액 조회
+chargeBalance(userId, amount) : 유저 잔액 충전
+deductBalance(userId, amount) : 결제 시 잔액 차감
}

class UserRepository {
+findById() : 유저 조회
+save() : 유저 정보 저장
} 

%% 관계
WaitingController --> WaitingService
WaitingService --> Redis
TokenAuthFilter --> Redis

ConcertController --> ConcertService
ConcertService --> ConcertRepository

SeatController --> SeatService
SeatService --> SeatRepository

ReservationController --> ReservationService
ReservationService --> ReservationRepository

PaymentController --> PaymentService


UserController --> UserService
UserService --> UserRepository
