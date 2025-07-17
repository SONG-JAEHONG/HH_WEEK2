sequenceDiagram
participant 스케쥴러
participant WaitingService
participant Redis
participant NotificationService
participant 알림서버
 
    loop 주기적 실행 (예: 1초 간격)
        스케쥴러->>WaitingService: 예약열 상태 점검 요청

        WaitingService->>Redis: 예약열 인원 수 확인 (working:concert)

        alt 예약열 여유 있음
            WaitingService->>Redis: 대기열에서 다음 토큰 조회 (waiting:concert)
            WaitingService->>Redis: 대기열에서 해당 토큰 삭제 (waiting:concert)
            WaitingService->>Redis: 예약열에 토큰 추가 (working:concert)

            WaitingService->>NotificationService: 예약 가능 알림 발송 요청
            NotificationService->>알림서버: 사용자에게 예약 가능 안내 메시지 전송

        else 예약열 가득 참
            note over WaitingService: 대기열 유지 (변경 없음)
        end
    end
