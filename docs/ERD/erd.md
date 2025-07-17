erDiagram
USER {
UUID id PK
VARCHAR name
BIGINT balance
}

    CONCERT {
        BIGINT id PK
        VARCHAR title
    }

    CONCERT_DATE {
        BIGINT id PK
        BIGINT concert_id FK
        DATE date
    }

    SEAT {
        BIGINT id PK
        BIGINT concert_date_id FK
        INTEGER seat_number
    }

    RESERVATION {
        BIGINT id PK
        BIGINT seat_id FK
        VARCHAR status
    }
 
    PAYMENT {
        UUID id PK
        UUID user_id FK
        BIGINT reservation_id FK
        BIGINT amount
        VARCHAR status
        DATETIME paid_at
    }

    USER ||--o{ RESERVATION : 1N
    USER ||--o{ PAYMENT: 1N
    CONCERT ||--o{ CONCERT_DATE: 1N
    CONCERT_DATE ||--o{ SEAT: 1N
    SEAT ||--|| RESERVATION: 11
    RESERVATION ||--|| PAYMENT: 11
