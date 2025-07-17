sequenceDiagram
participant 클라이언트
participant WaitingController
participant WaitingService
participant Redis
 
    %% 대기열 입장 요청
    클라이언트->>WaitingController: 대기열 토큰 발급 요청 
    WaitingController->>WaitingService: 대기열에 토큰 추가 요청
    WaitingService->>Redis: 예약열 인원 수 확인 (working:concert)

    alt 예약열 여유 있음
        WaitingService->>Redis: 예약열에 토큰 추가 (working:concert)
    else 예약열 FULL 상태
        WaitingService->>Redis: 대기열에 토큰 추가 (waiting:concert)
    end

    WaitingService-->>WaitingController: 토큰, 상태 반환 (예약열 입장 / 대기중)
    WaitingController-->>클라이언트: 토큰, 상태 응답

    note over 클라이언트: 예약 가능 알림을 받을 때까지 대기
