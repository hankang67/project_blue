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
[내가 구현한 기능]

- API 테스트 도구 Swagger

[주요 로직]

- SwaggerConfig에서 기본 정보, 보안 스키마 등을 구성
- @Schema 사용하여 Sample Date Setting
- ResponseDto를 자동으로 인식할 수 있도록 inner Class 제거

[배경]

- 기존 사용하던 Postman은 HTTP Method, URL, port 등을 모두 직접 입력하여 테스트 하는 방식으로 사용자의 오류로 인한 에러가 발생할 수 있음

[요구사항]

- API 자동 문서화 기능 구현
- Swagger UI를 통해 API를 테스트할 수 있는 환경 제공
- API 명세 업데이트 시, 코드 변경 사항에 따라 문서 자동 갱신

[선택지]

- Swagger
- PostMan

[의사결정/사유]

- Controller에 작성한 RESTful API 자동 문서화
- API 요청에 필요한 파라미터 및 요청 형식을 명확히 표기

[회고]

- PostMan에서 사용했던 API Document를 생성할 수는 없음
- 개발 과정에서 훨씬 더 효율적인 방식으로 API 테스트 가능
- https://everyday-spring.com/625
```

</details>

### [ AWS S3 File Upload ]

<details> <summary>상세보기</summary>  

```java
[내가 구현한 기능]

- 공연 등록 시 포스터 파일을 입력받아 S3 Bucket에 업로드
- 공연 삭제시 S3 Bucket의 포스터 파일도 함께 삭제
- 포스터 수정 시 S3 Bucket의 기존 이미지 삭제 후 새로운 이미지 등록

[주요 로직]

- MultipartFile을 사용하여 클라이언트로부터 파일 입력
- AWS SDK의 AmazonS3 클라이언트를 활용하여 S3 Bucket에 파일 저장
- 파일 이름 충돌 방지를 위해 고유 식별자(UUID)를 파일 이름에 추가

[배경]

- 효율적인 파일 관리: 로컬 서버에 파일을 저장하면 확장성과 보안 측면에서 한계가 있음
- 클라우드 스토리지 활용: AWS S3는 고가용성과 비용 효율적인 스토리지 서비스를 제공

[요구사항]

- 업로드된 파일이 S3에 저장되었음을 확인할 수 있는 URL 반환
- 공연 삭제 시 관련 포스터 파일도 S3에서 삭제
- 파일 사이즈 제한

[선택지]

- AWS S3
- 로컬 파일 시스템
- AWS S3 Presigned URL
        
[의사결정/사유]

- 로컬 저장 방식에 비해 확장성과 데이터 보존성이 뛰어남
- 파일 업로드와 삭제가 비교적 간단하며, AWS SDK로 손쉽게 관리 가능

[회고]

- 소스 코드 내에 AWS IAM 계정의 보안키 정보를 포함하여 push가 불가능한 상황이 발생했음
- 보안 키에 대한 관리의 중요성을 한번더 확인함
```

</details>

### [ Toss payments 결제 API 연동 ]

<details> <summary>상세보기</summary>  



</details>

### [ Jenkins CI/CD ]

<details> <summary>상세보기</summary>  

```java
[내가 구현한 기능]

- Jenkins를 활용한 CI/CD 파이프라인 구축
- GitHub에서 소스 코드 변경사항을 감지하여 자동으로 빌드, 테스트, 배포를 수행하도록 Jenkins 설정
- Docker를 활용하여 Application 컨테이너를 생성하고, EC2 서버에 자동 배포

[주요 로직]

- Jenkins Pipeline 구성 : Clone, Build, Bulid Docker, Push Docker, Deploy to EC2
- Jenkins의 Git Plugin을 사용해 GitHub에서 푸시 이벤트를 감지하도록 Webhook 설정
- Application을 Docker 컨테이너로 빌드 및 배포

[배경]

- 지속적 통합(CI)과 지속적 배포(CD): 팀원들이 동시에 개발하는 환경에서 코드 충돌을 줄이고, 안정적인 배포 프로세스를 구축하기 위함

[요구사항]

- GitHub에서 변경사항 발생 시 자동으로 빌드 및 테스트 수행
- Jenkins를 통해 EC2 서버로 Docker 이미지를 배포
- 배포 실패 시 롤백 가능
- Jenkins Pipeline으로 CI/CD 단계를 시각적으로 확인 가능

[선택지]

- GitHub Actions
- Jenkins
- AWS

[의사결정/사유]

- 플러그인 생태계가 풍부하여 참고 레퍼런스 자료가 다양함

[회고]

- CI/CD 자동화로 개발부터 배포까지의 시간이 크게 단축됨
- 초기 환경 설정(SSH 연결, Docker 설치 등)에 많은 시간이 소요됨
- Docker 이미지 저장소(ECR)와 통합하여 이미지 관리 최적화를 추후 도입함
- 추후 병렬 처리로 배포 성능 최적화가 목표
- https://everyday-spring.com/628
```

```java
[성능 개선 / 코드 개선 요약]

- 빌드 및 테스트 단계에서 메모리 부족으로 인한 비정상 종료 빈도 증가

[문제 정의]

- Jenkins CI/CD 파이프라인 실행 중, EC2 인스턴스의 메모리 용량 부족으로 인해 빌드가 중단되고 작업 실패 발생
- 사용 중인 EC2 인스턴스: t2-micro (메모리 1GB)

[가설] 

- Jenkins 빌드 작업에서 Gradle 빌드, 테스트, Docker 이미지 빌드 등 메모리를 과도하게 사용
- t2-micro 인스턴스의 1GB 메모리가 Jenkins의 동시 처리 요구를 충족하지 못함

[해결 방안]

- EC2 인스턴스 업그레이드 : 인스턴스를 t3-small (메모리 2GB)로 업그레이드
- 스왑 메모리 추가 설정 : 인스턴스의 메모리 부족 문제를 보완하기 위해 스왑 공간을 생성
- 리소스를 많이 소모하는 정렬 플러그인 제거

[해결 완료]

- EC2 인스턴스를 t3-small로 업그레이드하여 2GB 메모리 확보
- 스왑 공간 추가로 Jenkins가 빌드 작업 중 메모리 부족 문제를 겪지 않도록 설정
- Jenkins와 빌드 도구(Gradle)의 메모리 사용량 최적화를 통해 안정적인 파이프라인 실행 환경 마련
- 문제 발생 후 실행한 파이프라인 모두 정상 작동 확인

[회고]

- 현재와 같은 Docker in Docker 구조가 아닌 Jenkins를 단독 설치하여 리소스 효율성을 높일 수 있음
- 하나의 인스턴스가 아닌 여러개의 인스턴스로 Application을 분리해야 함
- https://everyday-spring.com/634
```

</details>

### [ Redis - 동시성 제어 ]

<details> <summary>상세보기</summary>  

```java
[내가 구현한 기능]

- 동시성 제어를 위한 락 적용 : 선착순 쿠폰 발급 로직에 동시 요청이 발생하는 상황에서 데이터 일관성을 보장하기 위해 낙관적 락, 비관적 락, 분산 락 세 가지 방식을 비교 적용
- 각 락 방식의 장단점과 결과를 분석하여 최적의 해결 방안을 도출

[주요 로직]

- 낙관적 락 : JPA의 @Version 어노테이션을 사용하여 쿠폰 엔티티의 버전을 관리, 데이터 충돌이 발생할 경우 예외를 던지고, 예외 처리 로직을 통해 재시도 또는 오류 처리
- 비관적 락 : JPA의 @Lock(LockModeType.PESSIMISTIC_WRITE) 어노테이션을 사용하여 트랜잭션 내에서 데이터 수정 시 락을 강제로 설정, 트랜잭션 완료 시까지 다른 트랜잭션의 접근을 차단하여 데이터 충돌 방지
- 분산 락 : Redis와 Redisson 라이브러리를 활용하여 여러 서버 간 락을 관리, 락 키 생성, 대기 시간 및 임대 시간 설정을 통해 안정적인 락 해제 및 관리 구현, AOP를 통해 락을 간편하게 적용 가능하도록 어노테이션 방식 도입

[배경]

- 문제 상황: 선착순 쿠폰 발급 과정에서 동시 요청이 발생하면 데이터 일관성이 깨지고, 중복 발급 또는 발급 실패가 빈번하게 발생
- 목표: 데이터 일관성을 유지하면서 동시에 가능한 많은 요청을 처리할 수 있는 최적의 동시성 제어 방식을 도입

[요구사항]

- 동시성 제어를 통해 쿠폰 발급 중 데이터 일관성 유지
- 여러 서버에서 요청이 발생하는 환경에서도 안정적으로 처리 가능
- 락 적용 방식에 따라 성능(처리 속도)과 안전성 간의 균형을 유지

[선택지]

- 낙관적 락 : 충돌 가능성이 낮은 환경에서 높은 성능 제공, 하지만 충돌이 잦을 경우 성능 저하와 높은 예외 처리 비용 발생
- 비관적 락 : 데이터 충돌 가능성이 높은 경우 적합하며, 안전성이 뛰어남, 트랜잭션 대기 시간이 길어질 수 있고 데드락 발생 가능성 존재
- 분산 락 : 여러 서버에서 동시 요청을 처리하는 분산 환경에 적합, Redis와 같은 외부 시스템 의존으로 인해 네트워크 장애가 발생하면 성능 저하 가능

[의사결정/사유]

- 분산 락 적용
- 단일 서버를 넘어서는 분산 환경에서도 안정적으로 동시성 제어가 가능
- AOP 기반의 어노테이션 방식으로 유지보수와 코드 재사용성이 높음
- 테스트 결과, 쿠폰 발급 수량과 고객 요청 수량 간의 일치 확인
- 데드락 발생 없이 요청이 안전하게 처리됨
```

![일반결과](https://blog.kakaocdn.net/dn/bKUwCE/btsKIScgQVl/FLiWoaWX0ZJ4M6hIkYDRQK/img.png)

![결과](https://blog.kakaocdn.net/dn/oDOBh/btsKxQEOBsS/BsV3JbZSHb75z7tGTpIAj1/img.png)

![비관적 락 결과](https://blog.kakaocdn.net/dn/dl5NL1/btsKJAJbRPG/LKNqypLVcFNXXXY5kWZk6K/img.png)

![분산 락 결과](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F9jeuM%2FbtsKIQy0ksH%2FdSgxPB9sRMagK1AA36K2iK%2Fimg.png)

</details>

### [ Redis - Caching ]

<details> <summary>상세보기</summary>  

```java
[내가 구현한 기능]

- Redis를 활용한 캐싱 시스템 구현
- 자주 요청되는 데이터를 Redis 캐시로 저장하여 애플리케이션 성능 향상
- DB 부하를 줄이고 데이터 조회 속도를 개선

[주요 로직]

- 캐싱 적용 데이터 선정
  - 데이터베이스에서 자주 조회되는 데이터를 분석
  - 예: 사용자 정보, 게시물 목록, 상품 데이터 등
- Redis 캐시 저장
  - 데이터 조회 시 Redis에서 먼저 캐시 데이터 확인
  - Redis에 데이터가 없는 경우(DB 조회 발생) 데이터베이스에서 조회한 후 Redis에 캐싱

[배경]

- 데이터베이스에 자주 동일한 조회 요청이 들어와 성능 저하 문제 발생
- 대규모 트래픽에서 데이터베이스 부하를 줄이고 사용자 응답 속도를 개선하기 위해 캐싱 필요

[요구사항]

- 데이터 조회 성능을 개선하여 사용자 응답 시간을 단축
- 데이터 변경 시 캐시와 데이터베이스의 일관성을 유지
- 캐시 데이터의 유효 기간을 설정하여 오래된 데이터를 자동 삭제
- 트래픽이 많은 환경에서도 안정적으로 동작할 수 있도록 확장성 확보

[선택지]

- Redis 캐싱
- Ehcache 사용
- 데이터베이스 쿼리 최적화

[의사결정/사유]

- Redis 캐싱을 선택한 이유
  - 대규모 트래픽에서도 빠른 응답 시간 보장
  - TTL 설정을 통해 캐시 데이터의 유효성을 유지
  - 확장성과 안정성이 높아 분산 환경에 적합

[회고]

- 적용하지 못한 TTL 구현 예정
```

![레디스_캐싱_성능개선_결과](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbWNKr8%2FbtsKRovmnC2%2FkwsxKFqfcX5Q28tf0DBZkk%2Fimg.png)

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

```java
[내가 구현한 기능]

- 예매 및 결제내역 이메일 발송

[주요 로직]

- JavaMailSender를 사용해 SMTP 서버를 통해 메일 발송

[배경]

- 사용자가 예매 및 결제를 완료한 후 확인 메일을 발송하여 신뢰도 향상

[요구사항]

- 글로벌 및 한국 사용자 모두 사용 가능한 메일 서버 선택
- 메일 발송이 암호화로 보호될 것

[선택지]

- Google
- Naver

[의사결정/사유]

- Google은 글로벌 사용자, Naver는 한국 사용자 기반
- 비교적 더 간단하게 설정할 수 있는 "Google"을 선택

[회고]

- 간단한 설정과 높은 안정성을 통해 예상보다 빠른 시간에 기능 구현
- 메일 발송이 실패했을 경우 별도의 재시도 로직, 예외 처리 추가 필요
```

```java
[성능 개선 / 코드 개선 요약]

[문제 정의]

- 메일 발송 시 동기식 처리로 인해 응답 지연 발생
- 대용량 트래픽 처리 시 병목 현상 초래 가능

[가설]

- 메일 발송 구현 시 별도 처리를 하지 않아 동기식으로 동작
- 메일 발송이 완료 될 때까지 메인 쓰레드가 대기하는 현상

[해결 방안]

- 비동기 처리
  - Spring의 @Async를 활용하여 간단하게 비동기 처리 구현
  - 비동기 처리 시 메인 쓰레드가 아닌 별도 쓰레드에서 처리
- 스프링 배치
  - 일정 주기로 모아 한 번에 처리
- 사용자 만족도를 높이기 위해 메일 발송 로직을 비동기 처리로 구현하여
  - 즉시성 확보를 우선 적용

[해결 완료]

- @Async을 사용하여 메일 발송 메서드를 비동기 처리로 전환
- 예매 완료 후 응답시간 5.25s -> 0.8s로 약 84% 개선

[회고]

- 간단한 설정으로 비동기 처리를 구현 가능
```

```java
[성능 개선 / 코드 개선 요약]

[문제 정의]

- Jmeter를 사용해 예매 기능 부하테스트 시 에러율 98% 발생

[가설]

- Async 관련 쓰레드풀이 낮게 설정되어 요청 처리 한계 발생
- 대기 큐 저장공간이 낮게 설정되어 용량을 초과한 요청 에러 발생

[해결 방안]

- 동시 요청 처리 능력 확장을 위해 최소/최대 쓰레드 수를 늘림
- 대기 큐 저장공간이 크면 응답 지연 발생 가능성이 있지만 에러율을 낮추는 것이 우선이라고 판단

[해결 완료]

- 최소 쓰레드 수 : 5 -> 50
- 최대 쓰레드 수 : 10 -> 200
- 큐 저장공간 : 100 -> 1000
- 적용 결과
  - 기존 에러율 98% -> 15%로 대폭 감소
  - 15%도 테스트 PC 성능과 인터넷 문제로 판단

[회고]

- Jmeter로 테스트하며 쓰레드 풀 설정을 임의의 값으로 설정했으나
  보다 정확한 계산법으로 설정할 필요가 있다고 생각됨
```

![mail troubleshooting](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FIczA5%2FbtsKSinD35T%2FZlDWKmtQFF9rRopx4pwAK1%2Fimg.png)

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