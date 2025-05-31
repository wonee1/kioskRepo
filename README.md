# 키오스크 시스템

Java Swing을 사용한 키오스크 애플리케이션입니다.

## 개발 환경
- Java 11
- Maven
- Swing GUI Framework

## 프로젝트 구조
```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── kiosk/
│   │           └── app/
│   │               └── KioskApplication.java
│   └── resources/
```

## 빌드 및 실행 방법

1. Maven 빌드:
```bash
mvn clean package
```

2. 애플리케이션 실행:
```bash
java -jar target/kiosk-app-1.0-SNAPSHOT.jar
```

## 주요 기능
- 기본 GUI 프레임워크 설정
- 메인 화면 구성 