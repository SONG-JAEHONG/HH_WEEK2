sequenceDiagram
participant 클라이언트 
participant 인증필터 (TokenAuthFilter)
participant Redis

    클라이언트->>인증필터 (TokenAuthFilter): 요청 (토큰 포함)

    인증필터 (TokenAuthFilter)->>Redis: 토큰 유효성 확인 (working:concert 또는 waiting:concert 내 존재 여부)

    alt 유효한 토큰
        Redis-->>인증필터 (TokenAuthFilter): 유효함
        인증필터 (TokenAuthFilter)-->>클라이언트: 요청 처리 계속 진행 (Controller로 전달)
    else 유효하지 않은 토큰
        Redis-->>인증필터 (TokenAuthFilter): 무효
        인증필터 (TokenAuthFilter)-->>클라이언트: 401 Unauthorized 응답
    end
