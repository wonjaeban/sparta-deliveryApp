# sparta-deliveryApp

* 앞단은 만들지 않고 주어진 테스트 코드를 성공적으로 통과시키기 위한 프로젝트입니다.

1. 음식점 등록 및 조회
    - 음식점 정보 입력받아 등록
        1. 음식점 이름 (name)
        2. 최소주문 가격 (minOrderPrice)
            1. 허용값: 1,000원 ~ 100,000원 입력
            2. 100 원 단위로만 입력 가능 (예. 2,220원 입력 시 에러발생. 2,300원은 입력 가능)
            3. 허용값이 아니거나 100원 단위 입력이 아닌 경우 에러 발생시킴
        3. 기본 배달비 (deliveryFee)
            1. 허용값: 0원 ~ 10,000원
            2. 500 원 단위로만 입력 가능 (예. 2,200원 입력 시 에러발생. 2,500원 입력 가능) 
            3. 허용값이 아니거나 1,000원 단위 입력이 아닌 경우 에러 발생시킴
            
    - 음식점 조회
        - 등록된 모든 음식점 정보 조회 가능
            1. 등록 시 입력한 음식점 정보 (name, minOrderPrice, deliveryFee)
            2. DB 테이블 ID (id)  
            
## 주요 API 설계

| 기능          | URL             | Method    |Request      |Response|
| ----------- | --------------- | --------- | ----------- | ------ |
| 음식점 등록     | /restaurant/register          | POST      | {name: "쉐이크쉑 청담점", minOrderPrice: 5000, deliveryFee: 2000}      | {} |
| 음식점 조회      | /restaurants | GET | {} | [{id: 1, name: "쉐이크쉑 청담점", minOrderPrice: 5000, deliveryFee: 2000}] |

    
2. 음식 등록 및 메뉴판 조회
    - 음식점 ID 및 음식 정보 입력받아 등록
        1. 음식점 ID (restaurantId)
            1. 음식점 DB 테이블 ID
        2. 음식명 (name)
            1. 같은 음식점 내에서는 음식 이름이 중복될 수 없음 (예. '자담치킨 강남점'에 '후라이드치킨' 이 이미 등록되어 있다면 중복하여 등록할 수 없지만, 다른 음식점인 '맘스터치 강남점'에는 '후라이드치킨' 을 등록 가능)
        3. 가격 (price)
            1. 허용값: 100원 ~ 1,000,000원
            2. 100 원 단위로만 입력 가능 (예. 2,220원 입력 시 에러발생. 2,300원 입력 가능)
            3. 허용값이 아니거나 100원 단위 입력이 아닌 경우 에러 발생시킴
            
    - 메뉴판 조회
        - 하나의 음식점에 등록된 모든 음식 정보 조회
            1. 등록 시 입력한 음식 정보 (name, price)
            2. DB 테이블 ID (id)
            
## 주요 API 설계

| 기능          | URL             | Method    |Request      |Response|
| ----------- | --------------- | --------- | ----------- | ------ |
| 음식 등록     | /restaurant/{restaurantId}/food/register          | POST      | [{name: "쉑버거 더블", price: 10900}, {name: "치즈 감자튀김", price: 4900}, {name: "쉐이크",  price: 5900}]      | {} |
| 메뉴판 조회      | /restaurant/{restaurantId}/foods | GET | {} | [{id: 1,  name: "쉑버거 더블", price: 10900}, {id: 2,  name: "치즈 감자튀김", price: 4900}, {id: 3, name: "쉐이크", price: 5900}] |
    
    
    
3. 주문 요청하기 및 주문 조회
    - 주문 요청 시 배달 음식점 및 음식 정보 입력받음
        1. 음식점 ID (restaurantId)
        2. 음식 주문 정보 (foods)
            1. 음식 ID (id)
            2. 음식을 주문할 수량 (quantity)
                1. 허용값: 1 ~ 100
                2. 허용값이 아니면 에러 발생시킴
                
    - 주문 요청에 대한 응답으로 다음 정보를 포함시킴
        1. 주문 음식점 이름 (restaurantName)
        2. 주문 음식 정보 (foods)
            - 주문 음식명 (name)
            - 주문 수량 (quantity)
            - 주문 음식의 가격 (price)
                - 계산방법
                    - 주문 음식 1개의 가격 * 주문 수량
        3. 배달비 (deliveryFee)
            - 주문 음식점의 기본 배달비
        4. 최종 결제 금액 (totalPrice)
            - 계산방법
                - 주문 음식 가격들의 총 합 + 배달비
            - "주문 음식 가격들의 총 합" 이 주문 음식점의 "최소주문 가격" 을 넘지 않을 시 에러 발생시킴
            
    - 주문 조회
        - 그동안 성공한 모든 주문 요청을 조회 가능
    
 ## 주요 API 설계

| 기능          | URL             | Method    |Request      |Response|
| ----------- | --------------- | --------- | ----------- | ------ |
| 주문     | /order/request          | POST      | {restaurantId: 1, foods: [{ id: 1, quantity: 1 }, { id: 2, quantity: 2 }, { id: 3, quantity: 3 }]}      | {restaurantName:"쉐이크쉑 청담점", foods: [{ name: "쉑버거 더블", quantity: 1, price: 10900 }, {  name: "치즈 감자튀김", quantity: 2, price: 9800}, {name: "쉐이크",  quantity: 3, price: 17700}], deliveryFee: 2000, totalPrice: 40400} |
| 주문 조회      | /orders | GET | {} | {restaurantName:"쉐이크쉑 청담점", foods: [{ name: "쉑버거 더블", quantity: 1, price: 10900 }, {  name: "치즈 감자튀김", quantity: 2, price: 9800}, {name: "쉐이크",  quantity: 3, price: 17700}], deliveryFee: 2000, totalPrice: 40400} |


# 위 사항들에 아래 사항들을 더했습니다.
    
   
    
    1. 배달 가능한 음식점 조회
    2. 거리별 배달비 산정
    3. 역할 별 (음식점 사장님, 사용자) API 인가
    
    - 매운 맛 과제에서는 API 상세스펙과 테스트 코드는 제공되지 않습니다.
    - 직접 API 를 설계 및 구현해 보세요.
    - 가능하다면 테스트 코드까지 구현해서 기능을 검증해 보세요.
    
    **[배달 가능한 음식점 조회]**
    
    실제 주소 정보를 사용하지 않고, 가상의 X, Y 축 공간을 주소로 사용
    
    "**(X축 값, Y축 값)" 으로 주소 표시**
    
    - X축 값: 0 ~ 99
    - Y축 값: 0 ~ 99
    
    예를 들어, 주소 **"(4, 3)"** 은 **"X: 4, Y: 3"** 을 나타냄
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/25f7998a-cfe1-434e-b694-a7ef22119b74/Untitled.png)
    
    1. 음식점 등록 시 "음식점 주소" 를 추가로 입력 받음
        
        예) 요청 Body 를 통해 주소 **"(4, 3)"**을 입력 받음
        
        **"POST /restaurant/register"**
        
        요청 Body
        
        ```json
        {
          name: "쉐이크쉑 청담점",
          minOrderPrice: 5000,
          deliveryFee: 2000,
        	x: 4,
          y: 3
        }
        ```
        
    2. 음식점 조회 시 "배달받을 주소" 를 추가로 입력 받음
        
        예) API 파라미터를 통해 **"(6, 2)"** 을 입력받음
        
        **"GET /restaurants?x=6&y=2"**
        
    
    1. "배달받을 주소" 에서 최대 3 km 내에 있는 음식점들만 조회되어야 함
    - x축 1칸 이동을 1km 로 판단
    - y축 1칸 이동을 1km 로 판단
        
        
    
    예) "배달받을 주소"가 **"(4, 3)"** 이라면 최대 배달 가능한 범위인 3 km 로 계산해보면 아래와 같이 마름모가 그려지고, 마름모 내에 있는 음식점들만 배달이 가능하다고 판단할 수 있음
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/c34ffed7-53ce-4186-b64f-ef88f4a62ba6/Untitled.png)
    
    아래와 같이 A, B, C, D, E 음식점이 있다고 가정한다면, (4, 3) 기준으로 배달이 가능한 음식점 목록은 "B, C, E" 가 되어야 함
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/0aa76f4b-1f75-4caf-a460-29bc78d1974f/Untitled.png)
    
    **[거리별 배달비 산정]**
    
    1. 배달 받을 주소와 음식점 간의 거리 1km 당 500원씩 "배달비 할증"가 추가됨
        1. 총 배달비 = 음식점의 기본 배달비 + 거리별 "배달비 할증"
    
    예를 들어, (4, 3) 기준으로 각 음식점 거리별 "배달비 할증"
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e8b3984e-e013-43f7-96b8-3e36801d0280/Untitled.png)
    
    **[역할 별 API 인가 처리]**
    
    스프링 시큐리티를 사용해 현재까지 구현한 API 를 "음식점 사장님, 사용자"로 역할 별 사용 가능한 API 로 나누어 제공
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/10fd0288-3bb9-4d94-9ab1-80bc352d43cb/Untitled.png)
    
    1. 회원 관리 기능
        - 회원 가입 및 로그인
            - 사장님, 사용자 구분
            - 사장님 (음식점 사장님)
                - 아이디
                - 비밀번호
                - 사장님 이름
            - 사용자
                - 아이디
                - 비밀번호
                - 닉네임
                
    2. 역할 별 API 인가처리
        - 사장님
            - 음식점 등록 및 음식 등록만 가능
        - 사용자
            - 음식점 등록 및 음식 등록 불가
            - 나머지 API 모두 허용
