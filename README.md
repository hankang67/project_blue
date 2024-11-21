![image](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F5Ki7f%2FbtsKOKZlNKD%2FkG8QAQwWBnEToKrhFVRrvK%2Fimg.png)

# 🎫 TICKET BLUE

공연 등 다양한 문화 및 엔터테이먼트 이벤트의 티켓을 온라인으로 예매할 수 있는 서비스

## 🚀 Infra Architecture & STACK

### [ STACK ]

Development & Communication

![IntelliJ](https://img.shields.io/badge/IntelliJ_IDEA-222326.svg?style=for-the-badge&logo=intellij-idea&logoColor=white) 
![Gradle](https://img.shields.io/badge/Gradle-02303a?style=for-the-badge&logo=gradle&logoColor=white) 
![GIT](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white) 
![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white) 
![gitignore](https://img.shields.io/badge/gitignore.io-204ECF?style=for-the-badge&logo=gitignore.io&logoColor=white)

![Jira](https://img.shields.io/badge/Jira-0052CC?style=for-the-badge&logo=jira&logoColor=white) 
![zoom](https://img.shields.io/badge/Zoom-0B5CFF?style=for-the-badge&logo=zoom&logoColor=white) 
![Slack](https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=slack&logoColor=white) 
![Notion](https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=notion&logoColor=white)

Frameworks & Languages

![SpringBoot](https://img.shields.io/badge/SpringBoot-6db33f?style=for-the-badge&logo=springboot&logoColor=white) 
![SpringBatch](https://img.shields.io/badge/SpringBatch-6db33f?style=for-the-badge&logo=spring&logoColor=white) 
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)

Data & Monitoring

![MySQL](https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Redis](https://img.shields.io/badge/redis-FF4438?style=for-the-badge&logo=redis&logoColor=white)
![Elasticsearch](https://img.shields.io/badge/Elasticsearch-005571?style=for-the-badge&logo=Elasticsearch&logoColor=white)
![prometheus](https://img.shields.io/badge/prometheus-E6522C?style=for-the-badge&logo=prometheus&logoColor=white)
![grafana](https://img.shields.io/badge/grafana-F46800?style=for-the-badge&logo=grafana&logoColor=white)

Deployment & Operations

![AWS](https://img.shields.io/badge/aws-232F3E?style=for-the-badge&logo=amazonwebservices&logoColor=white)
![docker](https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![jenkins](https://img.shields.io/badge/jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=white)
![logstash](https://img.shields.io/badge/logstash-005571?style=for-the-badge&logo=logstash&logoColor=white)
![kibana](https://img.shields.io/badge/kibana-005571?style=for-the-badge&logo=kibana&logoColor=white)

API & Testing

![swagger](https://img.shields.io/badge/swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-ff6c37?style=for-the-badge&logo=postman&logoColor=white)
![sonarqube](https://img.shields.io/badge/sonarqube-4E9BCD?style=for-the-badge&logo=sonarqube&logoColor=white)
![sonarLint](https://img.shields.io/badge/sonarlint-CB2029?style=for-the-badge&logo=sonarlint&logoColor=white)

### [ ERD ]

![ERD](https://github.com/user-attachments/assets/c8931c5d-13ba-4120-9876-4e0b613e9af6)

### [ Infra ]

![Infra](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FctMT0I%2FbtsKO6172by%2FCfn9epQ080RRD8gKUKFShk%2Fimg.png)

## ⚽ Goals

### [ 사용자 편의기능 최적화 ]

- Elasticsearch 도입으로 검색 속도 50% 이상 향상
- Redis caching 도입으로 검색 속도 50% 이상 향상

### [ Spring Batch ]

- Spring Batch로 대용량 데이터 처리
- JDBC Bulk Insert 방식으로 70% 속도 개선

### [ 이중화 DB ]

- DB 이중화로 부하 방지 및 데이터 안정성 보장
- 에러율 0%를 목표로 이중화 환경 구성

## 🔍 Main Features

### [ Admin ]

- 관리자 전용 계정으로 가입
- 포스터 파일을 첨부하여 공연을 등록하고 관리 (이미지는 AWS S3에 저장)
- 공연장, 출연자, 공연에 대한 회차, 쿠폰 등록 및 관리
- 공연에 출연하는 출연자 등록 및 관리
- 조건에 따라 사용자 예매내역을 검색 (특정 공연에 대한 예매 내역 등) - Elasticsearch

### [ Admin - Spring Batch ]

- OldUsers - 휴먼계정 처리
- OldUsersAlert - 휴먼계정 대상자 알림
- OldPerformances - 오래된 공연 정보 삭제
- TimeoutReservation - 기간 만료 예매 취소처리
- UpcomingReservationAlert - 관람일 알림
- ReservationReviewAlert - 관람평 작성 유도 알림
- [Spring Batch Application GitHub 바로가기](https://github.com/everydayspring/project-blue-batch)

### [ Search ]

- 키워드를 입력하여 한번의 검색으로 공연장, 출연자, 공연 모두 조회 가능 - Elasticsearch
- 사용가능한 쿠폰, 전체 공연, 배우, 공연장 등 조회
- Redis Caching 기능을 적용하여 재검색시 빠른 응답속도를 제공
- 원하는 공연회차의 잔여좌석을 조회

### [ Reservation ]

- 원하는 공연회차의 좌석을 지정하여 예매
- 예매에 대한 결제, 결제취소 기능 (토스페이먼츠 API)
- 예매, 결제 시 Slack, SSE 알림과 Mailing 서비스 제공

## 🌟 Key Summary

### [ JPA → Elasticsearch 검색 속도 개선 ]

<details> <summary>상세보기</summary>  

![JPA → Elasticsearch 검색 속도 개선](https://g-cbox.pstatic.net/MjAyNDExMjFfMjcy/MDAxNzMyMTgxMDUwOTMx.zxp_80lyUXBhQ4HtpmH1IuUy8KufT7mDp13AV6-nE-cg.H-tE1kGBVGwYljZyPPF_72y_VLPMhCATGPoSE239AZEg.PNG/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7_2024-11-21_182400.png)

</details>

### [ Spring Batch JPA -> JDBC ]

<details> <summary>상세보기</summary>  

![Spring Batch JPA -> JDBC](https://g-cbox.pstatic.net/MjAyNDExMjFfMTMg/MDAxNzMyMTk3NzQ1MDcx.3252H7VpHviRhwflcKeYfxhbKH7YErquClWXSAp_gi0g.0H0iEmfUFMGblwvAiqwdBUsJuh42dPeH2RWn0uV-f2kg.PNG/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7_2024-11-21_182916.png)

</details>

## 🔥 Technical Enhancements

### [ Swagger ]

<details> <summary>상세보기</summary>  

```java
**[내가 구현한 기능]**



[주요 로직]

[배경]

[요구사항]

[선택지]

[의사결정/사유]

[회고]

```

</details>

### [ AWS S3 File Upload ]

<details> <summary>상세보기</summary>  



</details>

### [ Toss payments 결제 API 연동 ]

<details> <summary>상세보기</summary>  



</details>

### [ Jenkins CI/CD ]

<details> <summary>상세보기</summary>  



</details>

### [ Redis - 동시성 제어 ]

<details> <summary>상세보기</summary>  



</details>

### [ Redis - Caching ]

<details> <summary>상세보기</summary>  



</details>

### [ Elasticsearch ]

<details> <summary>상세보기</summary>  



</details>

### [ Logstash ]

<details> <summary>상세보기</summary>  



</details>

### [ Alert - SSE ]

<details> <summary>상세보기</summary>  



</details>

### [ Alert - Mail ]

<details> <summary>상세보기</summary>  



</details>

### [ Spring Batch ]

<details> <summary>상세보기</summary>  



</details>

### [ Monitoring - Prometheus, Grafana ]

<details> <summary>상세보기</summary>  



</details>

### [ Code Convention ]

<details> <summary>상세보기</summary>  



</details>

## 💻 Contributors

### [ 강은총 ] - [GitHub](https://github.com/eunchongkang)

<details> <summary>상세보기</summary>

- CRUD
    - 공연장
- 쿠폰
- 동시성 제어
- 쿠폰 발급시 동시성 제어 필요성
- Redis의 Redisson 라이브러리 사용하여 분산 락 적용
- 결제 시스템 쿠폰 적용
- 토스페이먼츠에서 결제 시 쿠폰 적용 가능
- 알림 시스템
- 예매 성공/취소 시 슬랙 알림 전송
- AOP 방식에서 SSE로 변경하여 비동기, 실시간 처리 기능 추가 및 향상
- Redis pub/sub을 적용하여 서버 인스턴스 간 실시간 알림을 전파 할 수 있도록 구현
- TEST
    - Jmeter 활용하여 쿠폰, 공연장 관련 성능 테스트 및 응답속도 확인

</details>

### [ 김태주 (Leader) ] - [GitHub](https://github.com/mylotto0626)

<details> <summary>상세보기</summary>

- CRUD
    - 관람평
- s3 첨부파일 CRUD
    - 공연을 등록 시 s3에 포스터를 저장할 수 있음
- 레디스 캐싱
- Redis를 이용한 단순 조회 api 조회 속도 개선
- 동시성 제어
- 티켓 예매 시 동시성 제어 필요성
- Redis의 Redisson 라이브러리 사용하여 분산 락 적용
- TEST
    - Junit 테스트 코드 작성

</details>

### [ 이봄 ] - [GitHub](https://github.com/everydayspring)

<details> <summary>상세보기</summary>

- 프로젝트 기본구조 생성
- ERD기반 entity 설계
- Test데이터 생성 domain 설계
- CRUD
    - 예매 관련 기능 구현
- 회원가입, 로그인 기능 구현
- Swagger
    - API 테스트 환경 구성
- 샘플 데이터 세팅
- Jira
    - 프로젝트 관리 환경 구성
- 결제 시스템 연동
- 토스 페이먼츠 결제 DB 관련 기능 구현
- CICD
    - 배포 환경 구성
- Jenkins - github webhook 환경 구성
- Elasticsearch
    - 공연 키워드 검색 기능 구현
- JPA → ES 검색 성능 개선
- Spring Batch
    - Batch 전용 프로젝트 구성
- Jenkins Batch 자동화 환경 구성
- TEST
    - Prometheus - Grafana 환경 구성
- Jmeter 활용 성능 테스트
- Junit 테스트 코드 작성  
  -Refactoring
- SonarQube 정적 코드 검증 및 개선
- SonarLint 정적 코드 검증 및 개선
- code convention 점검 및 수정
- Spotless 활용

</details>  

### [ 이봉원 (Sub-Leader) ] - [GitHub](https://github.com/LeeBongwon94)

<details> <summary>상세보기</summary>

- 공연 관리 시스템
- CRUD : 생성, 전체 조회, 키워드 검색, 수정, 삭제, 출연자 등록, 삭제, 포스터 수정 기능 구현
- 결제시스템 연동
- 토스페이먼츠 API 연동 결제
- OAuth2 소셜 로그인
- 카카오 계정을 활용하여 사용자 인증 및 로그인 구현
- 메일 발송 기능
- SMTP 프로토콜 사용
- 비동기 처리 : @Async를 활용
- DB 이중화 및 분산 처리
- AWS기반 이중화 DB 구성 : EC2를 활용
- Master-Slave 역할 분리
- Master 노드 : 데이터 쓰기 작업(Insert)
    - Slave 노드 : 데이터 읽기 작업(Read)
- ELK 스택 통합 로그 관리
- Logstash 필터 적용
- AWS 환경 적용 : ELK 기반 로그 모니터링
- 코드 리팩토링
- Jmeter를 활용하여 성능테스트 및 응답속도 감소를  
  위한 코드 리팩토링
</details>  


### [ 한강 ] - [GitHub](https://github.com/hankang67)

<details> <summary>상세보기</summary>

- CRUD
    - 공연당 배우 등록, 삭제
- 배우, 회차
- 검색 기능 추가
- 관리자 중심 예매, 결제 검색 기능 구현
- ElasticSearch & kibana
    - elasticsearch 및 kibana 환경 구성
- 인덱싱 설계 및 검색환경 구현
- Logstash
    - 서비스 로그 수집, 백업로그파일 통합
- kibana로 수집한 로그에 대한 모니터링

</details>  

## 🤝 Development Guide

![team guide](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F66hIe%2FbtsKPBHmjH7%2FaI4x4kDG4Dzey5u1BTglpK%2Fimg.png)