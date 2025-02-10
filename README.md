# Take a picture (TAP) - Backend 📸

## 📌 프로젝트 소개
이 프로젝트는 사진 공유 플랫폼의 백엔드 부분으로, Spring Boot를 기반으로 개발되었습니다.

### 🔗 관련 링크
- [프론트엔드 Repository](https://github.com/lhg1006/Take-a-picture-TAP_js)

## 🛠 기술 스택

### Backend
- Spring Boot 2.4.2
- Java
- MySQL
- MyBatis

### 주요 의존성
- Spring Boot Starter Web
- MySQL Connector Java
- MyBatis Spring Boot Starter
- Log4jdbc-log4j2-jdbc4
- Gson
- Lombok

## 📊 프로젝트 구성
- Java: 98.7%
- Dockerfile: 1.3%

## 🌟 주요 기능
- RESTful API 제공
- 사진 업로드 및 저장
- 사용자 인증 및 권한 관리
- 데이터베이스 연동
- 로깅 시스템

## 🚀 시작하기

### 1. Repository Fork
- 우측 상단의 'Fork' 버튼을 클릭하여 이 Repository를 자신의 GitHub 계정으로 Fork합니다.
- Fork한 Repository를 로컬 환경으로 클론합니다
- git clone https://github.com/[your-username]/Take-a-picture-TAP_java.git

### 2. 데이터베이스 설정
- MySQL 데이터베이스 생성
- `application.properties` 또는 `application.yml` 파일에서 데이터베이스 연결 정보 설정

### 3. 애플리케이션 실행
./gradlew bootRun

### 4. 빌드
./gradlew build

## 🐳 Docker 지원
<p>이미지 빌드</p>
<p>docker build -t tap-backend .</p>
<p>컨테이너 실행</p>
<p>docker run -p 8080:8080 tap-backend</p>

## 📂 프로젝트 구조
<p>tap-backend/</p>
<p>├── src/</p>
<p>│ ├── main/</p>
<p>│ │ ├── java/</p>
<p>│ │ │ └── com/</p>
<p>│ │ │ └── tap/</p>
<p>│ │ │ ├── controller/</p>
<p>│ │ │ ├── service/</p>
<p>│ │ │ ├── repository/</p>
<p>│ │ │ └── domain/</p>
<p>│ │ └── resources/</p>
<p>│ └── test/</p>
<p>├── gradle/</p>
<p>├── Dockerfile</p>
<p>└── build.gradle</p>


## 🔧 개발 환경
- JDK 11 이상
- Gradle 6.7 이상
- MySQL 8.0 이상

## 🔒 라이센스
이 프로젝트는 개인 포트폴리오용으로 제작되었습니다.

## 📫 연락처
- Email: lhg961006@gmail.com
- Portfolio: https://lhg1006.github.io/
