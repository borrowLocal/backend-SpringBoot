# Borolo Backend

> Spring Boot 기반 백엔드 웹서비스  
> 대여인과 대여자의 아이템 거래를 지원하며, 회원과 상태관리, 알림 기능을 갖춘 백엔드 서비스입니다.

<br>

## 🙌🏻 프로젝트 소개
- Java 17 & Spring Boot 3.4.5 기반 백엔드 서비스
- Oracle DB와 MyBatis를 활용한 안정적인 데이터 관리
- RESTful API 아키텍처
- Spring Mail을 이용한 알림/메일 발송 기능
- OpenAPI(Swagger UI) 기반 API 문서화 제공

<br>

## 📑 주요 기능
- 회원 관리: 회원가입, 로그인, 탈퇴, 이메일 인증  
- 대여인(Borrower): 물품 조회·검색, 대여 신청 및 결제, 반납, 리뷰 작성  
- 대여자(Renter): 물품 등록·관리, 대여 신청 수락/거절, 상태 관리  
- 공통: 이메일 발송, REST API 문서화 (Swagger UI)  

<br>

## 🛠 Tech Stack

### Backend
&nbsp;&nbsp;&nbsp;![Java](https://img.shields.io/badge/Java-17-007396?logo=java&logoColor=white)  
&nbsp;&nbsp;&nbsp;- 프로젝트 메인 언어  

&nbsp;&nbsp;&nbsp;![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.5-6DB33F?logo=springboot)  
&nbsp;&nbsp;&nbsp;- 백엔드 프레임워크, 내장 서버 기반 빠른 개발 환경 제공  

&nbsp;&nbsp;&nbsp;![MyBatis](https://img.shields.io/badge/MyBatis-000000?logo=java&logoColor=white)  
&nbsp;&nbsp;&nbsp;- SQL 매퍼 프레임워크, 세밀한 쿼리 제어  


### Database
&nbsp;&nbsp;&nbsp;![Oracle](https://img.shields.io/badge/Oracle%20DB-F80000?logo=oracle&logoColor=white)  
&nbsp;&nbsp;&nbsp;- 메인 데이터베이스, 안정적인 데이터 관리  


### Build & Dependency Management
&nbsp;&nbsp;&nbsp;![Maven](https://img.shields.io/badge/Maven-3.9.0-C71A36?logo=apachemaven&logoColor=white)  
&nbsp;&nbsp;&nbsp;- 빌드 및 의존성 관리 도구


### Web & API
&nbsp;&nbsp;&nbsp;![Spring Web](https://img.shields.io/badge/Spring%20Web-6DB33F?logo=spring&logoColor=white)  
&nbsp;&nbsp;&nbsp;- RESTful API 설계 및 구현  

&nbsp;&nbsp;&nbsp;![Validation](https://img.shields.io/badge/Validation-FF6F00?logo=checkmarx&logoColor=white)  
&nbsp;&nbsp;&nbsp;- 입력값 검증 및 유효성 체크  

&nbsp;&nbsp;&nbsp;![Spring Mail](https://img.shields.io/badge/Spring%20Mail-007396?logo=gmail&logoColor=white)  
&nbsp;&nbsp;&nbsp;- 회원가입, 알림 등 메일 발송 기능  

&nbsp;&nbsp;&nbsp;![OpenAPI](https://img.shields.io/badge/OpenAPI-6BA539?logo=openapiinitiative&logoColor=white)  
&nbsp;&nbsp;&nbsp;- Swagger 기반 API 문서화  

<br>

## 🏗 시스템 아키텍처
```plaintext
[Client] ⇄ [Spring Boot Backend] ⇄ [MyBatis] ⇄ [Oracle DB]
                           ⇂
                       [Spring Mail]
                           ⇂
                      [OpenAPI/Swagger]

``` 
