# RefreshToken

## RefreshToken 도입 배경
Access Token 만을 사용하여 사용자를 인증 시 로그인을 한 이후 토큰 만료시간이 지나면 사용자는 로그아웃되는 경험을 함. 그럼 유효기간을 한 1년 가지고 가면 해결이 가능하지 않을까? 라고 생각할 수 있지만.. 유효기간이 너무 길어지면, 토큰 탈취 시 계정의 제어권을 뻇기게 되는 보안 문제가 발생

## 목적
Access Toekn의 유효기간을 짧게 처리 하여 보안을 강화 하며 잦은 로그아웃의 유저 불편함 경험을 해결하기 위해 Refresh Token을 사용한 인증 방법을 도입

## 구현 계획
1. RefreshToken을 어디에 저장 할 것 인가? 
    - Refresh Token은 Access Token을 재발급하기 위한 용도입니다. Refresh Token을 Redis에 저장하는 방식을 채택
    1) Key - Value 방식, 인메모리 DB 방식으로 빠르게 접근할 수 있습니다.

    2) 브라우저에 비해 탈취 가능성이 낮다고 생각하는 redis 서버에 저장하는 방식입니다.

    3) Refresh Token은 영구적으로 저장되는 데이터가 아님.

    Refresh Token은 영구적으로 저장될 필요가 없기 때문에 In-Memory DB를 사용해도 충분하며, 성능 이점을 챙길 수 있음

2. Refresh Token에는 어떤 정보를 담고 있을 것인가?

    - 현재 특정 정보를 담지 않음. (refreshToken을 발급 받은 IP정보와 같이 보안을 강화하는 방법 고민 중) 

    - UUID를 사용하여 난수로 생성되는 UUID를 토큰 값으로 설정

3. Redis에 key:value 구조에 대해

    1) Key : Value = RrefreshToken : AccessToken
        
        - 사용자의 정보가 필요가 없음으로 jwt페이로더의 디코딩 과정이 필요가 없어짐

        - 사용자의 쿠키에 refreshToken이 존재함으로 탈취 위험이 존재. 

        - 두개의 토큰이 모두 탈취 당한 경우, 부적합한 사용자가 먼저 토큰을 재발급 받으면 서버에서는 이를 알 수 없음. 


    2) Key : Value = AccessToken : RrefreshToken

        - RefreshToken을 서버만 가지고 있어 탈취 위험이 줄어듬

        - 사용자가 AccessToken을 탈취 당했을 경우, RefreshToken에 어떤 정보를 담지 않고 있는 이상 요청에 대한 검증이 어려움. 

        - 경남님은 위와 같은 문제를 해결하기 위해 refreshToken에 첫 발급 IP를 넣어줘서 해결.

    3) Key : Value = UserInfo : RrefreshToken
        
        - email을 key값으로 가짐, accessToken의 패이로더를 디코딩 하는 작업이 필요. 

        - 이 구조의 장점은, 두 가지 토큰이 탈취당한 경우 서버에서 이를 감지 할 수 있어 재로그인 가능. 

## 보안할 점

1. 사용자의 통신 간의 효율성을 증가 시킬 수 있는 방법

    현재 우리 아키텍처는 불필요한 서버 통신 과정이 존재.

    - 현재 대략적인 서버 통신 과정

    인증이 필요한 API A요청 -> filter단에서 토큰 검증 -> 만료된 토큰에 대해 특정 에러(상태코드를 던짐) -> 프론트에 인터셉터가 이를 잡아서 토큰 재발행 API B를 요청 -> 서버(application)의 RT검증 -> 토큰을 사용자에게 재발행 -> 사용자는 새로운 토큰으로 인증이 필요한 API A 재 요청