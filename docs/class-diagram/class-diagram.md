classDiagram
class TokenAuthFilter {
+checkToken(token) Token 유효성 검사
}

    class WaitingController {
        +issueToken(userId) 대기열 토큰 발급
    }

    class WaitingService {
        +generateToken(userId) UUID 기반 토큰 생성
        +storeToken(token) Redis에 대기열 등록
        +tryFillWorkingQueue() 예약열로 이동 시도
        +fillWorkingQueue() 예약열 채우기
        +completeWork(token) 작업 완료 시 예약열에서 토큰 제거
    }

    class ConcertController {
        +getAvailableConcerts() 공연 목록 조회
        +getAvailableDates(concertId) 해당 공연의 날짜 목록 조회
    }

    class ConcertService {
        +getAvailableConcerts() 공연 목록 조회
        +getAvailableDates(concertId) 해당 공연의 날짜 목록 조회
    }

    class SeatController {
        +getAvailableSeats(concertDateId) 좌석 목록 조회
    }

    class SeatService {
        +getAvailableSeats(concertDateId) 해당 날짜의 좌석 조회
    }

    class ReservationController {
        +reserveSeat(date, seatNo, userId) 좌석 예약 시도
    }

    class ReservationService {
        +reserveSeat(concertDateId, seatNumber, userId) 좌석 예약 처리
    }

    class PaymentController {
        +payment(userId, dateId, seatNo) 결제 요청 처리
    }

    class PaymentService {
        +processPayment(userId, dateId, seatNo) 결제 처리 전체 로직
        +deductBalance(userId, amount) 포인트 차감
        +markReservationCompleted(reservationId) 예약 상태 변경
        +savePaymentHistory(userId, dateId, seatNo) 결제내역 저장
    }

    class ConcertRepository {
        +findAllConcerts() 공연 목록 반환
        +findConcertDates(concertId) 해당 공연 날짜 반환
    }

    class SeatRepository {
        +findReservedSeats(dateId) 예약된 좌석 번호 조회
    }

    class ReservationRepository {
        +findByConcertDateIdAndSeatNumber() 예약 정보 조회
    }

    class UserController {
        +getBalance(userId) 잔액 조회
        +chargeBalance(userId, amount) 잔액 충전
    }

    class UserService {
        +getBalance(userId) 사용자 잔액 조회
        +chargeBalance(userId, amount) 사용자 잔액 충전
    }

    class UserRepository {
        +findById() 사용자 조회
        +save() 사용자 정보 저장
    }

    class Redis {
    }

    WaitingController --> WaitingService
    WaitingService --> Redis
    TokenAuthFilter --> Redis

    ConcertController --> ConcertService
    SeatController --> SeatService
    ReservationController --> ReservationService
    PaymentController --> PaymentService

    ConcertService --> ConcertRepository
    SeatService --> SeatRepository
    ReservationService --> ReservationRepository
    PaymentService --> ReservationRepository
    PaymentService --> UserRepository

    UserController --> UserService
    UserService --> UserRepository