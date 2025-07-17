sequenceDiagram
participant 클라이언트
participant ReservationController
participant ReservationService
participant PaymentService
participant ReservationRepository
participant UserRepository
participant PaymentRepository
participant WaitingService
participant Redis
participant DB 

    클라이언트->>ReservationController: 결제 요청 
    note over ReservationController : 공통 토큰 인증
    ReservationController->>ReservationService: 결제 시도 요청
    ReservationService->>PaymentService: 결제 처리 요청

    PaymentService->>ReservationRepository: 예약 상태 확인 (status = '결제중')
    ReservationRepository->>DB: reservation 조회 
    DB-->>ReservationRepository: 예약 데이터 반환
    ReservationRepository-->>PaymentService: 예약 데이터 반환

    alt 예약 불가 (status != '결제중')
        PaymentService-->>ReservationService: 결제 불가 예외
        ReservationService-->>ReservationController: 실패 응답
        ReservationController-->>클라이언트: 결제 실패 응답
    else 결제 가능
        PaymentService->>UserRepository: 유저 조회
        UserRepository->>DB: user 테이블 조회
        DB-->>UserRepository: 유저 데이터 반환
        UserRepository-->>PaymentService: 유저 데이터 반환

        alt 잔액 부족
            PaymentService-->>ReservationService: 잔액 부족 예외
            ReservationService-->>ReservationController: 실패 응답
            ReservationController-->>클라이언트: 잔액 부족 응답
        else 잔액 충분
            PaymentService->>UserRepository: 유저 잔액 차감 후 저장
            UserRepository->>DB: user 테이블 업데이트

            PaymentService->>ReservationRepository: 예약 상태 '결제완료'로 변경 후 저장
            ReservationRepository->>DB: reservation 테이블 업데이트

            PaymentService->>PaymentRepository: 결제내역 저장
            PaymentRepository->>DB: payment 테이블 저장

            PaymentService->>WaitingService: 예약열 토큰 삭제 요청
            WaitingService->>Redis: 예약열에서 토큰 삭제 (working:concert)

            PaymentService-->>ReservationService: 결제 성공
            ReservationService-->>ReservationController: 결제 성공
            ReservationController-->>클라이언트: 결제 성공 응답
        end
    end
