sequenceDiagram
participant 클라이언트
participant ReservationController
participant ReservationService
participant SeatRepository
participant ReservationRepository
participant DB
 
    클라이언트->>ReservationController: 좌석 예약 요청 
    note over ReservationController: (공통 토큰 인증)
    ReservationController->>ReservationService: 좌석 예약 시도

    %% 좌석 존재 및 상태 확인
    ReservationService->>SeatRepository: 좌석 ID로 좌석 조회
    SeatRepository->>DB: seat 테이블에서 좌석 정보 조회
    DB-->>SeatRepository: 좌석 정보 반환
    SeatRepository-->>ReservationService: 좌석 정보 반환

    ReservationService->>ReservationRepository: 좌석 ID로 예약 정보 조회
    ReservationRepository->>DB: reservation 테이블에서 해당 좌석 예약 조회
    DB-->>ReservationRepository: 예약 정보 반환
    ReservationRepository-->>ReservationService: 예약 정보 반환

    alt 예약 가능 상태 (status = '예약가능')
        ReservationService->>ReservationRepository: 상태를 '결제중'으로 변경
        ReservationRepository->>DB: 예약 상태 업데이트 (status = '결제중')
        ReservationService-->>ReservationController: 임시 예약 성공
        ReservationController-->>클라이언트: 좌석이 임시로 예약되었습니다. 5분 내 결제하세요.
    else 예약 불가 상태 (status = '결제중' 또는 '결제완료')
        ReservationService-->>ReservationController: 예외 발생 (이미 예약된 좌석입니다.)
        ReservationController-->>클라이언트: 실패 응답
    end
