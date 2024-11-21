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

[ Admin ]

- 관리자 전용 계정으로 가입
- 포스터 파일을 첨부하여 공연을 등록하고 관리 (이미지는 AWS S3에 저장)
- 공연장, 출연자, 공연에 대한 회차, 쿠폰 등록 및 관리
- 공연에 출연하는 출연자 등록 및 관리
- 조건에 따라 사용자 예매내역을 검색 (특정 공연에 대한 예매 내역 등) - Elasticsearch

[ Admin - Spring Batch ]

- OldUsers - 휴먼계정 처리
- OldUsersAlert - 휴먼계정 대상자 알림
- OldPerformances - 오래된 공연 정보 삭제
- TimeoutReservation - 기간 만료 예매 취소처리
- UpcomingReservationAlert - 관람일 알림
- ReservationReviewAlert - 관람평 작성 유도 알림
- [Spring Batch Application GitHub 바로가기](https://github.com/everydayspring/project-blue-batch)

[ Search ]

- 키워드를 입력하여 한번의 검색으로 공연장, 출연자, 공연 모두 조회 가능 - Elasticsearch
- 사용가능한 쿠폰, 전체 공연, 배우, 공연장 등 조회
- Redis Caching 기능을 적용하여 재검색시 빠른 응답속도를 제공
- 원하는 공연회차의 잔여좌석을 조회

[ Reservation ]

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