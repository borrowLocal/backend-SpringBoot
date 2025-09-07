# Borolo Backend

> Spring Boot 기반 백엔드 웹서비스  
> 대여인과 대여자의 아이템 거래를 지원하며, 회원과 상태관리, 알림 기능을 갖춘 백엔드 서비스입니다.


## 🙌🏻 프로젝트 소개
- Java 17 & Spring Boot 3.4.5 기반 백엔드 서비스
- Oracle DB와 MyBatis를 활용한 안정적인 데이터 관리
- RESTful API 아키텍처
- Spring Mail을 이용한 알림/메일 발송 기능
- OpenAPI(Swagger UI) 기반 API 문서화 제공


## 📑 주요 기능
- 회원 관리: 회원가입, 로그인, 탈퇴, 이메일 인증  
- 대여인(Borrower): 물품 조회·검색, 대여 신청 및 결제, 반납, 리뷰 작성  
- 대여자(Renter): 물품 등록·관리, 대여 신청 수락/거절, 상태 관리  
- 공통: 이메일 발송, REST API 문서화 (Swagger UI)  


## 🛠 Tech Stack

### 💻 Backend
![Java](https://img.shields.io/badge/Java-17-007396?logo=java&logoColor=white)  
- 프로젝트 메인 언어  

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.5-6DB33F?logo=springboot)  
- 백엔드 프레임워크, 내장 서버 기반 빠른 개발 환경 제공  

![MyBatis](https://img.shields.io/badge/MyBatis-000000?logo=java&logoColor=white)  
- SQL 매퍼 프레임워크, 세밀한 쿼리 제어  

### 🗄 Database
![Oracle](https://img.shields.io/badge/Oracle%20DB-F80000?logo=oracle&logoColor=white)  
- 메인 데이터베이스, 안정적인 데이터 관리  


### 📦 Build & Dependency Management
![Maven](https://img.shields.io/badge/Maven-3.9.0-C71A36?logo=apachemaven&logoColor=white)  
- 빌드 및 의존성 관리 도구

---

### 🌐 Web & API
![Spring Web](https://img.shields.io/badge/Spring%20Web-6DB33F?logo=spring&logoColor=white)  
- RESTful API 설계 및 구현  

![Validation](https://img.shields.io/badge/Validation-FF6F00?logo=checkmarx&logoColor=white)  
- 입력값 검증 및 유효성 체크  

![Spring Mail](https://img.shields.io/badge/Spring%20Mail-007396?logo=gmail&logoColor=white)  
- 회원가입, 알림 등 메일 발송 기능  

![OpenAPI](https://img.shields.io/badge/OpenAPI-6BA539?logo=openapiinitiative&logoColor=white)  
- Swagger 기반 API 문서화  

<hr style="border: 0.1px solid #ccc">

## 🏗 시스템 아키텍처
```plaintext
[Client] ⇄ [Spring Boot Backend] ⇄ [MyBatis] ⇄ [Oracle DB]
                           ⇂
                       [Spring Mail]
                           ⇂
                      [OpenAPI/Swagger]

``` 

## 🗂 추가 자료
- ERD: ![ERD](docs/ERD.png) [전체 ERD 보기](docs/ERD.png)  
- Use Case: ![Use Case](docs/usecase.png) [자세히 보기](docs/usecase.png)  
- 파일 구조: ![File Structure](docs/file-structure.png) [전체 보기](docs/file-structure.png)
