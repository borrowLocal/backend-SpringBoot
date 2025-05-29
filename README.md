# backend-SpringBoot

## 1. Eclipse에서 확인해야 할 주요 설정

### 1.1 JDK 설정 확인 (Installed JREs)  
- 경로: `Window > Preferences > Java > Installed JREs`  
- 사용 JDK: `jdk-17`

### 1.2 컴파일러 설정 (Compiler)  
- 경로: `Window > Preferences > Java > Compiler`  
- 설정 값: `Compiler compliance level = 17`

---

## 2. 서버 접속 정보

- URL: [http://localhost:8080/](http://localhost:8080/)  
- 아이디: `user`  
- 비밀번호: 콘솔에 출력되는 비밀번호 확인
- (Spring Security가 기본 설정으로 임시 비밀번호를 생성한 것으로 로그인 기능이나 인증 커스터마이징 하면 사라짐)
  
<img src="https://github.com/user-attachments/assets/0e58a2aa-e4c6-484b-926c-0344d5459ecf" width="400"/>
- 이렇게 뜨는게 정상 !

---

## 3. Swagger 접속 경로

서버 실행 후 다음 중 하나로 접속

- [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)  
- [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
