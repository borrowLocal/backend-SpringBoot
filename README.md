# 📌 Borolo Backend

> Spring Boot 기반 백엔드 애플리케이션  
> 사용자 관리, 아이템 대여, 결제 등 주요 도메인을 다루는 프로젝트입니다.  

---

## 👩🏻‍🏫 프로젝트 소개
- Java 17 & Spring Boot 3.4.5 기반 백엔드 서비스
- Oracle DB와 MyBatis를 활용한 안정적인 데이터 관리
- RESTful API 아키텍처
- Spring Mail을 이용한 알림/메일 발송 기능
- OpenAPI(Swagger UI) 기반 API 문서화 제공

---

## 🛠 Tech Stack

### 💻 Backend
![Java](https://img.shields.io/badge/Java-17-007396?logo=java&logoColor=white)  
- 프로젝트 메인 언어  

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.5-6DB33F?logo=springboot)  
- 백엔드 프레임워크, 내장 서버 기반 빠른 개발 환경 제공  

![MyBatis](https://img.shields.io/badge/MyBatis-000000?logo=java&logoColor=white)  
- SQL 매퍼 프레임워크, 세밀한 쿼리 제어  

---

### 🗄 Database
![Oracle](https://img.shields.io/badge/Oracle%20DB-F80000?logo=oracle&logoColor=white)  
- 메인 데이터베이스, 안정적인 데이터 관리  

---

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

---

## 🏗 시스템 아키텍처
```plaintext
[Client] ⇄ [Spring Boot Backend] ⇄ [MyBatis] ⇄ [Oracle DB]
                           ⇂
                       [Spring Mail]
                           ⇂
                      [OpenAPI/Swagger]
