sequenceDiagram
participant 사용자
participant ConcertController
participant ConcertService
participant ConcertRepository
participant SeatController
participant SeatService
participant SeatRepository
participant DB
 
    %% 공연 목록 조회
    사용자->>ConcertController: 공연 목록 요청
    note over ConcertController: (공통 토큰 인증)
    ConcertController->>ConcertService: 공연 목록 조회 요청
    ConcertService->>ConcertRepository: 공연 목록 조회
    ConcertRepository->>DB: concert 테이블 조회
    DB-->>ConcertRepository: 공연 목록 반환
    ConcertRepository-->>ConcertService: 공연 목록 반환
    ConcertService-->>ConcertController: 공연 목록 반환
    ConcertController-->>사용자: 공연 목록 응답

    %% 공연 날짜 조회
    사용자->>ConcertController: 해당 공연 날짜 목록 요청
    note over ConcertController: (공통 토큰 인증)
    ConcertController->>ConcertService: 공연 날짜 조회 요청
    ConcertService->>ConcertRepository: 해당 공연 날짜 조회
    ConcertRepository->>DB: concert_date 테이블 조회
    DB-->>ConcertRepository: 날짜 목록 반환
    ConcertRepository-->>ConcertService: 날짜 목록 반환
    ConcertService-->>ConcertController: 날짜 목록 반환
    ConcertController-->>사용자: 날짜 목록 응답

    %% 예약 가능한 좌석 조회
    사용자->>SeatController: 예약 가능한 좌석 목록 요청 
    note over SeatController: (공통 토큰 인증)
    SeatController->>SeatService: 좌석 목록 조회 요청
    SeatService->>SeatRepository: 좌석 상태 조회 (예약가능)
    SeatRepository->>DB: seat 테이블 조회 (status = '예약가능')
    DB-->>SeatRepository: 예약 가능한 좌석 목록 반환
    SeatRepository-->>SeatService: 좌석 목록 반환
    SeatService-->>SeatController: 좌석 목록 반환
    SeatController-->>사용자: 좌석 목록 응답