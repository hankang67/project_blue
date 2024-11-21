![image](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F5Ki7f%2FbtsKOKZlNKD%2FkG8QAQwWBnEToKrhFVRrvK%2Fimg.png)

# 🎫 TICKET BLUE
_프로젝트 개요_

공연 등 다양한 문화 및 엔터테이먼트 이벤트의 티켓을 온라인으로 예매할 수 있는 서비스

# ⚽ Goals
_핵심 목표_

## 사용자 편의기능 최적화
- Elasticsearch 도입으로 검색 속도 50% 이상 향상
- Redis caching 도입으로 검색 속도 50% 이상 향상
## Spring Batch
- Spring Batch로 대용량 데이터 처리
- JDBC Bulk Insert 방식으로 70% 속도 개선
## 이중화 DB
- DB 이중화로 부하 방지 및 데이터 안정성 보장
- 에러율 0%를 목표로 이중화 환경 구성

# 🌟 Key Summary
_핵심 요약_

작성중

# 🚀 Infra Architecture & STACK
_인프라 아키텍처 & 적용기술_

![Infra](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FctMT0I%2FbtsKO6172by%2FCfn9epQ080RRD8gKUKFShk%2Fimg.png)

[ERD diagram](https://www.erdcloud.com/d/5iR9JboxDdHp9rhv4)
![ERD](https://github.com/user-attachments/assets/c8931c5d-13ba-4120-9876-4e0b613e9af6)

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

# 🔍 Main Features

_주요 기능_

Admin

    - 관리자 전용 계정으로 가입할 수 있습니다.
    - 포스터 파일을 첨부하여 공연을 등록하고 수정 삭제가 가능합니다. (이미지는 AWS S3에 저장됩니다.)
    - 공연장, 출연자, 공연에 대한 회차등을 등록하고 관리할 수 있습니다.
    - 쿠폰을 등록하고 관리할 수 있습니다.
    - 공연에 출연하는 출연자를 등록하고 관리할 수 있습니다.
    - 사용자 예매내역을 조건에 따라 검색할 수 있습니다 (특정 공연에 대한 예매 내역 등) - Elasticsearch

Admin - Spring Batch

[Spring Batch Application GitHub 바로가기](https://github.com/everydayspring/project-blue-batch)

    - OldUsers - 휴먼계정 처리
    - OldUsersAlert - 휴먼계정 대상자 알림
    - OldPerformances - 오래된 공연 정보 삭제
    - TimeoutReservation - 기간 만료 예매 취소처리
    - UpcomingReservationAlert - 관람일 알림
    - ReservationReviewAlert - 관람평 작성 유도 알림

Search

    - 키워드를 입력하여 한번의 검색으로 공연장, 출연자, 공연을 모두 조회할 수 있습니다. Elasticsearch로 구현되어 빠른 응답속도를 제공합니다.
    - 사용가능한 쿠폰, 전체 공연, 배우, 공연장 등을 조회할 수 있습니다. Redis Caching 기능을 적용하여 재검색시 빠른 응답속도를 제공합니다.
    - 원하는 공연회차의 잔여좌석을 조회할 수 있습니다.

Reservation

    - 원하는 공연회차의 좌석을 지정하여 예매할 수 있습니다.
    - 예매에 대한 결제, 결제취소가 가능합니다 (토스페이먼츠 API)
    - 예매, 결제 시 Slack 알림과 Mailing 서비스를 제공합니다 (SSE, Async)


# 🔥 Technical Enhancements

_기술적 고도화_

<details> <summary>JIRA</summary>

### Jira

- Jira 플랫폼을 활용하여 프로젝트 관리를 진행하였습니다

![Jira_Timeline](https://g-cbox.pstatic.net/MjAyNDExMjFfMzAw/MDAxNzMyMTYyNTQzODMx.xZDLpCMsB_vk-ReOs_fb2pckQWKXCWDOJhmYmN27W_Eg.YxNraajudnB_nkRHdVToJtELcEThTK424gE-Loj6ocAg.PNG/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7_2024-11-21_131417.png)

- GitHub 연동으로 이슈와 관련된 커밋 기록을 확인할 수 있습니다

![Jira_Github_connection](https://g-cbox.pstatic.net/MjAyNDExMjFfODQg/MDAxNzMyMTYyNjg5NTYy.vl0DYUQdx9ercb8V801IwkQcT3JaHCzwaDSiXTWT43Qg.3cU_sfoN8RgRsDWqh9XCc4aeOAW_R90e9CbrjDal5Ncg.PNG/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7_2024-11-21_131737.png)


[Jira로 프로젝트 관리하기 Blog](https://everyday-spring.com/626)

</details>


<details> <summary>SWAGGER</summary>

### Swagger

- 좀더 편안한 API 테스트를 위해 Swagger를 도입하였습니다
- API 목록이 알파벳 오름차순으로 정렬되도록 추가 설정을 하였습니다

![Swagger_API](https://g-cbox.pstatic.net/MjAyNDExMjFfODYg/MDAxNzMyMTYyODk2ODEw.ENov1JgrjEIMPxkHeYmBpxBVUs9VrZIvttgMTybO0qUg.ghIwmCI8tVSUwNZMreYjq9XtPDOfgCdBFVz_6Zl-hwIg.PNG/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7_2024-11-21_132055.png)

- DTO의 inner class 구조를 삭제하고 Sample data를 설정했습니다.

![Swagger_sample_data](https://g-cbox.pstatic.net/MjAyNDExMjFfMjU2/MDAxNzMyMTYyOTA1Njg3.HSlfdyH4pgD0olN5ixaUY1iFWwvKNPZdkQOnH5kNqyUg.kSxjxT3-X-34iF68FZ6O5Nq0IoW_Wd9sd25p2uTSBwAg.PNG/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7_2024-11-21_132118.png)

[Java SpringBoot Swagger Blog](https://everyday-spring.com/625)

</details>


<details> <summary>AWS S3 File Upload</summary>

- 공연 등록 시 공연 포스터 S3에 업로드 기능
- 공연 삭제 시 포스터 동시 삭제
- 포스터 수정 시 S3에 있던 기존 이미지가 삭제된 후 새로운 이미지로 교체 기능
- S3 이미지 첨부파일 올라간 모습
  ![S3버킷](img.png)

</details>


<details> <summary>toss payments 결제 API 연동</summary>

Toss Payments에서 제공하는 API가 사용하기 쉽게 되어있다.<br>
토스페이의 절차는 아래 사진과 같이 이루어져있다.<br>
![토스 결제 절차](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcNmDms%2FbtsKhpnqeWa%2FcurhwWKSLpzzy4ilgqln21%2Fimg.png)

Toss에서 제공해주는 템플릿을 열면 이렇게 html들과 Controller를 제공해준다.<br>
![템플릿](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcFJhZx%2FbtsKgaEY4Gr%2FdNA3GfsXLbFMCBQM1Xhi10%2Fimg.png)

처음에 결제위젯으로 진입하기 전 전달할 데이터를 세팅해서 Payment 테이블에 기본적인 값들을 저장해주고
결제위젯에 필요한 값들을 Return 값으로 전달해줬다.<br>
![리턴 값 이미지](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FGv9D9%2FbtsKQ6N9VPv%2FeJNmer56J5Zo44QOORu6vK%2Fimg.png)

그리고 Return 값은 Model에 넣은후 Spring의 **Thymleaf**를 이용하여 html에서 값을 불러왔다.<br>
![model로 값 전달](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FG5bSg%2FbtsKQDL7XgY%2F71tYBCz3KUtSZhDSj1W1Yk%2Fimg.png)

아래와 같이 쓰면 Model에 있는 값을 불러올 수 있다.<br>
![Thymleaf로 값 불러오기](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbyVEV8%2FbtsKh01Bu0c%2FDUEoPuX0CAOBUwKBj9Utj0%2Fimg.png)

결제위젯의 **결제하기**를 누르면 checkout.html의 'widgets.requestPayment'를 통해 결제창을 요청한다.<br>
![widget 이미지](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FHn59g%2FbtsKQBt54nU%2FbDokeAO17rEuZVXT9SQVjk%2Fimg.png)

Toss에서는 중간에 결제 가격을 조정하여 악의적으로 이용할 수 있다고 하여 요청을 보낼 당시의 orderId, amount와
Return으로 받은 orderId, amount를 비교하여 일치여부를 확인하는 것을 권장하기 때문에
결제 승인 절차에 진입하기 전에 가격을 검증하였다.
아까 Payment 테이블에 저장해놓은 orderId, amount를 불러와 Return으로 받은 값과 비교를 진행한다.<br>
![검증 이미지](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbb0jdw%2FbtsKQGITmS0%2FN6v40uHIMOa4U6YW40XXwK%2Fimg.png)

그렇게 최종적으로 승인되면 아래와 같이 Json 형식으로 값들을 Return해준다.<br>
![결제 승인 이미지](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FceC3y3%2FbtsKgef7pyp%2F4rX8Uwyctvkr96bb8lSjq0%2Fimg.png)

</details>


<details> <summary>Jenkins CI/CD</summary>

### Window PC에 Docker, jenkins 설치, GitHub 연동하기

- window 환경에서 Jenkins 환경을 구성하여 배포 테스트를 진행했습니다

![터미널에서 도커 컨테이너 실행](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbC3EdK%2FbtsKnFXc1Xl%2FYuAtZ1hBEkKRxcb5km3H10%2Fimg.png)
![정상적으로 연결된 젠킨스](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbjfzW8%2FbtsKl6BKLBs%2FmWzKjOkrxfUEp8lc7jpyr0%2Fimg.png)

### AWS EC2에 Docker Jenkins 설치, Github Spring Boot application CI/CD

- EC2에서 Jenkins CI/CD 환경을 구성하였습니다
- Docker - Jenkins 구조의 최소 요구 성능을 반영하여 t3.small 인스턴스를 생성했습니다
- Docker not found
    - Docker 명령어를 찾지 못해 발생하는 문제
    - Jenkins 내에 Docker를 추가 설치하여 Docker in Docker 구조를 사용했습니다
- 추가적인 리소스를 사용 할 수 있도록 메모리 스왑 설정도 진행하였습니다.

![ec2_instance_type](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FQUUa3%2FbtsKtNvFdDM%2FKCnGUwP01CkzMfyB7P5Me0%2Fimg.jpg)

![jenkins_pipeline_setting](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FrXrF9%2FbtsKtJ70cSY%2FpSMCHvEQ48aUVNAtOgRz71%2Fimg.png)

![jenkins_deploy](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbtzsIf%2FbtsKtFET9jR%2FNlcHtt8PZICWdbmyDmKJj1%2Fimg.png)

### CI/CD 트러블슈팅

#### *Jenkins Spring Boot build 무한로딩*

EC2 Instance에 설치된 Jenkins에서 빌드를 하게 되면 중간에 로딩이 길어지면서 서버 자체가 먹총이 되는 문제가 발생했습니다

Console Output을 살펴보면 GitHub에서 대상 branch를 clone해오는 것 까지는 작동 했으나

application을 build해서 jar 파일을 만들때 무한로딩에 걸리는 현상이었습니다

로딩에 걸려버리면 Jenkins 페이지도 접속이 안되고 터미널로 서버에 연결조차 되지 않았고,

30분이상 기다려도 무한 로딩상태여서 결국 EC2 Instance 자체를 중지하고 재시작 해야했습니다

계속해서 시도를 해봤지만 build 과정에서 계속 먹통이 됐고 Instance를 중지하는 것도 길게는 10분정도 시간이 지체되었습니다

문제는 *리소스 부족으로 인한 빌드실패*로 최소 2v CPU, 2~4 GiB Memory가 필요한 상황이었습니다

*결국 Instance를 t3.small로 새로 만들어 해결했습니다*

#### *Spring Boot Spotless Plugin build resource*

EC2 사양을 올렸는데도 build 과정에서 먹통이 되는 문제가 발생했습니다

build 과정에서 리소스를 많이 잡아먹는 *Spotless 플러그인*이 문제 원인이었습니다

컴파일 시마다 동작하면서 전체 파일을 점검하기 때문에 많은 리소스를 잡아먹는 상황으로,

*플러그인을 사용할 때에만 주석을 해제하는 방식으로 해결하였습니다

Jenkins CI/CD 블로그

[ (1) ](https://everyday-spring.com/628)
[ (2) ](https://everyday-spring.com/629)
[ (3) ](https://everyday-spring.com/630)
[ (4) ](https://everyday-spring.com/631)
[ (5) ](https://subin0522.tistory.com/632)
[ (6) ](https://subin0522.tistory.com/633)
[ (7) ](https://everyday-spring.com/634)

</details>


<details> <summary>Redis - 동시성 제어</summary>

### 동시성 제어를 위한 락 적용

- 선착순 쿠폰을 발급하는 과정에서 동시에 많은 요청이 들어올 수 있기 때문에 동시성 제어의 필요성이 있다고 생각하였고
  이를 위해 다음 세 가지 락 방식을 적용 및 테스트하여 가장 효율적인 방식을 찾고자 한다.
1. 낙관적 락 :


- 낙관적 락은 데이터베이스의 레코드에 버전 정보를 두어 데이터의 충돌이 발생하지 않을 것이라고 가정하는 방식으로 수정 시점에 데이터의
  버전이 일치하는 지를 확인하여 만약 일치하지 않으면 예외를 발생
- 장점으로는 트랜잭션이 짧고 충돌이 적은 환경에서는 성능이 뛰어나며, 락을 사용하지 않기 때문에 높은 동시성을 유지할 수 있다.
-  JPA의 @Version 애너테이션을 사용하여 쿠폰 엔티티에 Version필드를 추가하고, 이를 통해 충돌을 감지한다.


2. 비관적 락 :


- 데이터의 수정이 일어날 가능성이 높다고 가정하여 레코드를 수정할 때 즉시 락을 걸어 다른 트랜잭션이 접근하지 못하도록 하는 방식.
- 장점으로는 데이터 충돌이 발생할 가능성이 있는 경우에 유용하며 트랜잭션의 안전성을 보장.
- PA의 @Lock 애너테이션을 사용하여 레퍼지토리에서 쿠폰 엔티티를 조회할 때 비관적 락 모드를 적용.


3. 분산 락 :


- 분산 락은 여러 서버에서 동시에 접근하는 환경에서 데이터의 일관성을 유지하기 위해 사용하는 락으로 Redis와 같은 외부 시스템을 활용하여 락을 관리
- 장점으로는 여러 인스턴스에서 동시에 실행되는 애플리케이션에서 동시성 문제를 해결할 수 있으며 데이터의 일관성을 유지할 수 있다.
- Redis와 같은 분산 시스템에서 락을 관리하기 위해 Redisson 라이브러리를 사용하여 쿠폰 발급 시 분산 락을 적용.

#### 쿠폰 발급 테스트
특정 쿠폰 발급 시 동시 요청이 발생할 때 발생할 수 있는 문제를 확인하고, 성공적으로 발급된 수량과 실제 최종적으로 반영된 발급 수량의 차이를 점검 하기 위해 테스트를 진행

![테스트 폼](https://blog.kakaocdn.net/dn/z0TQK/btsKydNb8MY/4AJvi7Tk2wEVjCvoKvZhb0/img.png)
1) 테스트 준비:
   testCount는 총 1000개의 동시 요청을 의미
   ExecutorService는 10개의 스레드를 사용하여 동시 요청을 테스트
   CountDownLatch는 모든 스레드가 작업을 완료할 때까지 메인 스레드가 대기하도록 설정.
   AtomicInteger를 사용하여 발급 성공 카운트(atomicInteger)와 예외 발생 카운트(atomicexception)를 기록


2) 동시 요청 생성:
   각 스레드는 couponService.firstCoupon(authUser, couponId)를 호출하여 쿠폰 발급 요청을 수행
   요청이 성공하면 atomicInteger의 값을 증가,
   예외가 발생하면 atomicexception의 값을 증가시키며 예외 메시지를 출력
   finally 블록에서 countDownLatch.countDown()을 호출하여 해당 스레드의 작업 완료를 알린다.


3) 결과 검증:
   countDownLatch.await()로 모든 스레드의 작업이 완료될 때까지 대기
   executorService.shutdown()을 호출하여 스레드 풀을 종료
   데이터베이스에서 최종 쿠폰 발급 수량을 가져와서 atomicInteger와 비교
   성공한 발급 수량이 실제 쿠폰의 currentQuantity와 다른지 확인하여, 동시성 문제로 인한 데이터 불일치를 검증
   ![일반결과](https://blog.kakaocdn.net/dn/bKUwCE/btsKIScgQVl/FLiWoaWX0ZJ4M6hIkYDRQK/img.png)

- 일반 테스트 결과 :
    -  테스트의 결과는 최종 발급된 쿠폰의 수량 111개, 성공한 발급 수량은 1000개로 목표는 1000개의 쿠폰 발급이었지만 실질적으로 111건 밖에 유저들에게
       쿠폰이 발급 되지 않았다. 이유로는 쿠폰 발급 로직이 많은 사용자의 동시 요청을 처리하는 과정에서 동시성 제어가 제대로 이루어지지 않아,
       일부 요청이 충돌하여 실패했을 가능성이 높다. 락이 걸리지 않은 상태에서 여러 트랜잭션이 동시에 같은 쿠폰 데이터를 수정하려고 할 때 데이터 일관성이
       보장되지 않아 발급 실패가 발생할 수 있으며 그 결과로 111건 만이 발급되지 못하였다.

#### 낙관적 락 적용
- 데이터가 충돌하지 않을 것이라는 가정 하에 동작 -> 즉, 여러 스레드가 동시에 데이터를 수정할 가능성이 낮다고 가정하고 수정할 때까지 락을
  사용하지 않는다.
  ![낙관적락](https://blog.kakaocdn.net/dn/UKoVz/btsKxRjoKwK/fQxgieyiAIkdElwkcnooSk/img.png) ![결과](https://blog.kakaocdn.net/dn/oDOBh/btsKxQEOBsS/BsV3JbZSHb75z7tGTpIAj1/img.png)
1) 버전 관리 : 엔티티에 @Version 어노테이션을 사용하여 쿠폰 엔티티에 Version필드를 추가 ->  DB에서 엔티티를 수정할 때, 현재 버전과 데이터베이스의 버전이 일치하는지 확인


2) 충돌 감지 : 다른 스레드가 동시에 해당 데이터를 수정하여 버전이 변경되었다면, 예외가 발생 -> 충돌을 감지한 스레드는 재시도하거나 오류 처리


3) 결과 : 서비스에서 발급된 쿠폰 수량과 고객이 요청한 쿠폰의 수량은 일치 하지만, 867개의 쿠폰이 예외 발생으로 인해 지급되지 못하였다.


#### 비관적 락 적용
- 데이터 충돌이 자주 발생한다고 가정하고 동시성을 관리하는 방법으로 락은 트랜잭션이 완료될 때까지 유지되며, 이를 통해 데이터의 일관성을 보장한다.

![비관적 락](https://blog.kakaocdn.net/dn/ALmJx/btsKKhvjSML/XPPme4stjbn5KcIZ2vjw4K/img.png)

1) 비관적 락 적용 :  Repository 락을 사용하기 위해 특정 메서드에 @Lock(LockModeType.PESSIMISTIC_WRITE) 어노테이션을 적용


2)  비관적 락 의미 : @Lock을 통해 데이터를 조회한 시점에서 다른 트랜잭션이 해당 데이터에 접근하여 변경할 수 없도록 강제로 잠금을 설정


3) 트랜잭션 범위 내 락 적용 : 비관적 락은 트랜잭션 범위 내에서만 유효 -> 트랜잭션이 완료되면 락이 자동으로 해제되며, 다른 트랜잭션이 접근가능


4) 데드락 : 여러 트랜잭션이 서로의 락을 기다리는 데드락 상황발생(대기시간 초과-PessimisticLockException) ->
   적절한 타임아웃 설정 및 재시도 로직 을 통해 해결 가능


5) 결과 : 여러 스레드가 동시에 쿠폰 발급을 시도하면 트랜잭션이 순차적으로 처리되기 때문에 성능 저하가 발생할 수 있으며 특히 1000개의 요청이 한꺼번에 들어올 경우
   락으로 인해 데드락 상황이 발생할 수 있으며 그 결과 많은 요청이 충돌하면서 일부는 대기 중 타임아웃되거나 실패하고  최종 발급 수량과 요청된 수량 간 차이가 아래와 같이 발생 할 수 있다.

![비관적 락 결과](https://blog.kakaocdn.net/dn/dl5NL1/btsKJAJbRPG/LKNqypLVcFNXXXY5kWZk6K/img.png)


#### 분산 락 적용
- 여러 인스턴스에서 동시성 제어를 목적으로 사용되며 분산 환경에서 데이터 일관성을 보장하기 위해 Redis와 같은 외부 시스템을 이용해 락을 관리하는 방식.

![분산 락 aop](https://blog.kakaocdn.net/dn/T8q99/btsKJCGTTd5/Ip42602YYOaRn3oP9mkk8k/img.png)

-  aop를 적용한 락 로직 (어노테이션)
1) Redis 기반 락 구현:

Redis에 락 키를 저장하여 동시성 제어를 수행하며 락의 키는 lock:<key> 형식으로 생성하고 RedissonClient를 사용해 관리



2) 락 설정 및 획득 :

lock.tryLock() 메서드를 통해 지정된 대기 시간(waitTime)과 임대 시간(leaseTime) 내에 락을 획득 시도 한다.

- waitTime: 락을 기다릴 최대 시간.
- leaseTime: 락을 유지할 시간.


3) 락 해제 및 자동 해제 :

락 해제는 lock.unlock()를 호출하며 leaseTime 이 초과되면 자동으로 해제되며, 락이 해제가 되지 않는다면 다른 트랜잭션에서 접근할 수 없으므로
finally 를 통해 항상 락을 해제 할 수 있도록 지정함.

#### 분산 락 적용 :
![분산 락적용 이미지](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fb02Q9C%2FbtsKKrdsWrv%2FwAkpGmB6wFKDYVvFdVkq21%2Fimg.png)
AOP를 통해 지정한 어노테이션 포인트를 통해 특정 서비스 메서드 위에 락 어노테이션과 Key 값을 명시하여 동시성 제어를 수행 할 수 있도록 한다.
이때의 Key 값은 락의 고유 식별자로 사용되며 이를 통하여 Redis와 같은 외부 시스템에서 분산 락을 관리한다.

-> 락을 필요한 메서드에만 간단한 방법으로 적용할 수 있어 코드 중복을 줄이며 설정된 Key를 통해 각 리소스에 대한 락을 독립적으로 관리가 가능하다.

#### 분산락 테스트

1) 테스트 환경 설정 :

- 30개의 스레드가 동시에 쿠폰 발급을 요청하도록 설정.
- 각각의 스레드는 고유한 AuthUser 객체를 생성하여 요청 간 충돌 방지.

2) 테스트 데이터 :
   발급 가능한 쿠폰의 총 수량
   10,000개.

#### 결과
![분산 락 결과](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F9jeuM%2FbtsKIQy0ksH%2FdSgxPB9sRMagK1AA36K2iK%2Fimg.png)
1) 발급 수량 일치 :

- 서비스에서 발급된 쿠폰 수량: 10,000개.
- 고객이 요청한 쿠폰 수량과 정확히 일치하며 중복 발급 없음

2) 데드락 미발생 :

- 30개 스레드의 동시 요청 처리 중에도 데드락이나 타임아웃 발생하지 않음

3) 안전성 확인 :

- 분산 락 적용으로 인해 예상치 못한 쿠폰 발급 초과 문제 해결
- 각 스레드는 순차적으로 락을 획득하여 동시성 문제 없이 안정적으로 요청 처리

</details>


<details>
  <summary>Redis - Caching</summary>

- 배우 단건 조회

   <details>
       <summary>캐싱 전: Average 32, Error 0%</summary>

  ![캐싱 전 이미지](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fcen7X9%2FbtsKPgSiDEL%2FtR8hYcOo2bAaw9slhjcGMK%2Fimg.png)
  ![캐싱 전 그래프](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FArpyG%2FbtsKRxLs9PS%2FPmIG1PPi6KKLRblYhMvdo0%2Fimg.png)

  </details>

  <details>
     <summary>캐싱 후: Average 7, Error 0%</summary>

  ![캐싱 후 이미지](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FW3abg%2FbtsKR3XvRJ2%2FWrsEYChLeRYZljfltkW57K%2Fimg.png)
  ![캐싱 후 그래프](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FpLbxO%2FbtsKPOnnz2S%2F4XK0aGmAb16lBK5CVaPSO0%2Fimg.png)

  </details>


- 공연 단건 조회

   <details>
       <summary>캐싱 전: Average 30, Error 0%  </summary>

  ![캐싱 전 이미지](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FKDaEx%2FbtsKR58R7fm%2Fo9zhFyiET3tWGDqr2v97WK%2Fimg.png)
  ![캐싱 전 그래프](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F5SsZZ%2FbtsKRRJIXSj%2FItWW5d4k79bI22BUiNkEvk%2Fimg.png)

  </details>

  <details>
     <summary> 캐싱 후: Average 14, Error 0%</summary>

  ![캐싱 후 이미지](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fb45hcX%2FbtsKQzwmxs6%2FKcNkUty6dFa1ML4giCsYW1%2Fimg.png)
  ![캐싱 후 그래프](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FP9VJp%2FbtsKRgb706p%2FWhVbTFCRI1yK8XpJ19XvZk%2Fimg.png)
  </details>


- 공연 회차 조회

   <details>
       <summary> 캐싱 전: Average 25, Error 0% </summary>

  ![캐싱 전 이미지](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FZgCuN%2FbtsKPhjfC4j%2Fxn51iJOoh016xTnkjddYxk%2Fimg.png)
  ![캐싱 전 그래프](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbzXDUB%2FbtsKPGJ0Y4s%2Fkt0nbgiP0VQ2wvYn0mRwB1%2Fimg.png)

  </details>

  <details>
     <summary>캐싱 후: Average 12, Error 0%</summary>

  ![캐싱 후 이미지](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FxUYhe%2FbtsKRSojJE2%2FaM7ccg7qg2VbAL7xAkCip1%2Fimg.png)
  ![캐싱 후 그래프](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbuK0AT%2FbtsKQgREgRx%2FEYKSIrrpTVgSjQpRJ5B5o0%2Fimg.png)
  </details>


- 공연 관람평 조회

   <details>
       <summary> 캐싱 전: Average 17369, Error 0% </summary>

  ![캐싱 전 이미지](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbgBZgh%2FbtsKPBhCTDv%2FUoM0OKH3hkD62TXgDgLbCk%2Fimg.png)
  ![캐싱 전 그래프](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fze7lA%2FbtsKRogMbQA%2F6ZkRNnhWV2uTKpXcRKOxF1%2Fimg.png)

  </details>

  <details>
     <summary> 캐싱 후: Average 19, Error 0%</summary>

  ![캐싱 후 이미지](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fc6z6xL%2FbtsKQLpXFWe%2FqoFIEGykvW7WKo9k6jhCN1%2Fimg.png)
  ![캐싱 후 그래프](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FoCcm7%2FbtsKPUOPkct%2FVoaRKuROYppMp4nGLEONpK%2Fimg.png)
  </details>


- 공연 출연자 조회

   <details>
       <summary>  캐싱 전: Average 26, Error 1.04%   </summary>

  ![캐싱 전 이미지](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FwmBUB%2FbtsKPyFuHDp%2FdIK6JSYhZZl98UpmgYfQhk%2Fimg.png)
  ![캐싱 전 그래프](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Flt3CP%2FbtsKQrL3Des%2F4BeU9pyU38jlZ3R9JSymsk%2Fimg.png)

  </details>

  <details>
     <summary>캐싱 후: Average 14, Error 0%</summary>

  ![캐싱 후 이미지](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FetO397%2FbtsKRdmaiAf%2Fcye4DnT5kKo8FBABuAJWyK%2Fimg.png)
  ![캐싱 후 그래프](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcuuDXS%2FbtsKQ8kYjgj%2F4dpSu9r3wKLB93gAOR7WJk%2Fimg.png)
  </details>


- 공연장 단건 조회

   <details>
       <summary> 캐싱 전: Average 148, Error 9.55%   </summary>

  ![캐싱 전 이미지](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FETGnP%2FbtsKQ4iBAe9%2FB4gZhj8jsUMrW0AMjYXdZK%2Fimg.png)
  ![캐싱 전 그래프](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FUe0l1%2FbtsKRoujH5T%2FmjdE7qUlVMghiM67heaxo1%2Fimg.png)

  </details>

  <details>
     <summary> 캐싱 후: Average 12, Error 0%</summary>

  ![캐싱 후 이미지](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbS4HZy%2FbtsKRS2WMt8%2FsfKTLrMnXHUMkkGkyEIejK%2Fimg.png)
  ![캐싱 후 그래프](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fcs9Hb0%2FbtsKPBaU7ow%2FtO0Qx4RqO0OoPjqckRQvHk%2Fimg.png)
  </details>

</details>


<details> <summary>Elasticsearch</summary>

### elastic 환경설정 및 검색 api구현과정
#### elastic 도입 배경
ElasticSearch는 빠르고 정확한 검색이 필요한 다양한 서비스에서 사용됩니다.
저희 프로젝트에서는 검색 속도와 정확성을 최적화하기 위해 도입했으며, 주요 도입 이유는 다음과 같습니다:

- 예약 검색 최적화: 사용자 예약 정보를 효율적으로 검색하기 위한 전용 검색 엔진 필요.
- 데이터 분석 확장성: 단순 검색뿐만 아니라, 예약 데이터의 다양한 분석이 가능하도록 설계.
- 확장 가능성: 프로젝트가 확장되더라도 서버를 손쉽게 추가하고 데이터를 효과적으로 분산 처리할 수 있는 구조..

#### elasticsearch API 구현
검색 API
예약 검색 API를 구현해 사용자 이름, 공연 제목, 날짜, 상태 등 다양한 조건으로 검색 가능하도록 개발.

- 사용한 기술: Criteria API를 이용한 정적 쿼리 생성.
- 구현 방식:
  예약 정보 필터링.
  데이터 동기화 기능 구현.

- api
  ![image](https://github.com/user-attachments/assets/223ffbba-7c80-4622-b044-19d49159017d)
  ![image](https://github.com/user-attachments/assets/d1ba2880-ee51-49f6-aedd-2c5d1102c666)

- index
  ![image](https://github.com/user-attachments/assets/059969de-6a28-4397-ba2e-380ca11beedb)
  ![image](https://github.com/user-attachments/assets/5631fd1e-7a10-4cbf-91f4-ca0a20efb5c0)

- data
  ![image](https://github.com/user-attachments/assets/a4163210-e8d3-4330-818f-ba7fb2d5846c)

#### 성능 테스트 및 개선 방향
Elasticsearch를 도입한 뒤, 실제 환경에서의 성능을 점검했습니다.
다양한 조건으로 쿼리를 실행하며 MySQL과 속도를 비교했고, 성능 병목 현상을 식별해 최적화했습니다.

- 성능 이슈 및 대응
  이슈: MySQL보다 Elasticsearch의 속도가 느린 문제 발견.
  원인:
  인덱스 설정 최적화 부족.
  데이터가 분산되지 않은 단일 노드 구성.
  대응 방안:
  역색인 최적화.
  샤드 분산 구성 및 클러스터 확장.
- admin/search 성능
  ![image](https://github.com/user-attachments/assets/ef56629d-5ed4-4132-aab1-a8f4b428e038)
- search/keyword 성능
  ![image](https://github.com/user-attachments/assets/bc54aea2-6600-4737-9525-efc4077bb30c)


### elastic api 성능비교 및 grafna 이용한 모니터링
Elasticsearch(ES) 기반 API와 MySQL 기반 API의 성능을 비교하고, 높은 부하 조건에서의 안정성을 평가하기 위해 진행했습니다.
성능 평가는 아래 지표를 기준으로 이루어졌습니다:
- 평균 응답 시간: 각 API의 요청 처리 속도 비교.
- 초당 처리량(QPS): 부하 증가 시 API가 처리 가능한 요청 수.
- CPU 사용량: 자원 효율성을 평가.
- 안정성 평가: 동시 사용자가 많아질수록 성능 변화 확인.
#### 테스트 결과
![image](https://github.com/user-attachments/assets/4c01bad4-1aa6-4c6f-af22-6346b02366c9)
![image](https://github.com/user-attachments/assets/13cd51c3-246c-42e1-9b90-07d791d9c815)
![image](https://github.com/user-attachments/assets/1e72e000-158d-4eee-a6fc-6978d9e5dc4d)

#### 테스트 결과 분석
- 평균 응답 시간
  API별 평균 응답 시간을 비교한 결과, 아래와 같은 차이가 나타났습니다:

/search/filter와 같은 단순 쿼리는 MySQL 기반 API가 더 빠른 응답 시간을 기록.
/admin/search와 같은 복합 쿼리에서는 Elasticsearch의 성능이 더 우수할 것으로 기대했지만, 테스트 데이터의 영향으로 차이가 크지 않았음.
- 초당 처리량(Throughput)
  동시 사용자가 증가했을 때 QPS는 일정 수준 유지되었으나, Elasticsearch는 특정 시점에서 약간의 성능 저하가 발생.
  MySQL은 데이터 크기가 작을 경우 Elasticsearch보다 높은 QPS를 기록.
- 호출 트래픽 분포
  단순 쿼리(/search/filter, /search/reservations)와 복합 쿼리(/admin/search)의 호출 비율을 분석한 결과, 복합 쿼리 API에서 부하가 더 집중되는 경향이 나타남.
- CPU 사용량
  Elasticsearch는 복합 쿼리를 처리할 때 CPU 자원을 더 많이 사용.
  네트워크 오버헤드와 데이터 직렬화/역직렬화로 인한 자원 사용량 증가 가능성 확인.

#### Elasticsearch 속도 저하 원인 분석
1. 데이터 크기
   테스트 데이터 크기: 작은 데이터셋에서는 MySQL이 Elasticsearch보다 효율적.
   실제 운영 데이터: 대규모 데이터에서는 Elasticsearch의 장점이 발휘될 가능성이 높음.
2. Elasticsearch 쿼리 복잡도
   복잡한 조합의 쿼리(예: filter, match, sort, aggregation)에서 성능 저하가 발생 가능.
   쿼리 최적화가 미흡했을 가능성 존재.
3. 네트워크 오버헤드
   Elasticsearch는 HTTP 통신을 사용하므로, 네트워크 지연이 발생.
   Docker 기반 로컬 환경에서도 이로 인한 지연 발생 가능.
4. Elasticsearch 인덱스 설정
   refresh_interval, number_of_shards, number_of_replicas 등의 설정이 기본값으로 되어 있어 최적화되지 않았을 가능성.

#### 개선 방향 및 추가 테스트 계획
1. 데이터셋 확장
   운영 환경과 유사한 대규모 데이터셋으로 테스트하여 실제 성능을 확인.
2. 쿼리 최적화
   불필요한 aggregation과 sorting 제거.
   필터링 위주의 쿼리 구조로 변경하여 성능 향상.
3. Elasticsearch 인덱스 설정 최적화
   number_of_shards와 number_of_replicas를 환경에 맞게 재설정.
   refresh_interval을 조정해 인덱싱 성능 개선.
4. 네트워크 환경 개선
   로컬 테스트 환경을 벗어나 실제 운영 서버 환경에서 성능 테스트.
   결론
   테스트 결과, MySQL은 작은 데이터셋에서 더 나은 성능을 보였지만,
   Elasticsearch는 대규모 데이터 환경에서의 장점을 살릴 가능성이 높습니다.
   향후 쿼리와 인덱스 설정을 최적화하고, 대규모 데이터셋으로 추가 테스트를 진행할 예정입니다.

</details>


<details> <summary>Alert - AOP</summary>

### AOP 를 사용하여 알림설정

- [ (1) spring 과 slack 알림 연동 - 1](https://k-chongchong.tistory.com/40)
- [ (2) AOP를 사용한 알림 연동 - 2 ](https://k-chongchong.tistory.com/41)
- [ (3) 이벤트 기반 아키텍처(EDA)와 AOP의 비교 -3 ](https://k-chongchong.tistory.com/42)

</details>


<details> <summary>Alert - SSE</summary>

위아래를 띄우고 여기에 내용을 작성하세요
마크다운 문법으로 작성하시면 됩니다

</details>


<details> <summary>Alert - Mail</summary>

메일 서버가 여러가지 있지만 네이버와 구글을 고민하던 중<br>
글로벌 시장을 겨냥한(?) 구글 서버를 사용했다.

우선 Config를 작성하기 전에 해야할 것.
1. Google 로그인 > 보안 > 2단계 인증
2. 앱 비밀번호 생성
3. 앱 비밀번호 16자리 저장하기

![환경변수](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fby1TXS%2FbtsKQaqhrCs%2FCVKr9JshJ6QKHuAaHUk7Q0%2Fimg.png)

## 1. build.gradle
implementation 'org.springframework.boot:spring-boot-starter-mail'

## 2. MailConfig
환경변수를 설정했으면 아래와 같이 Config 파일을 작성해준다.<br>
![Config](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FIqsAp%2FbtsKQ7GkwLn%2FSFXQkrpTuC2ADXi0cdCzl0%2Fimg.png)

## 3. AsyncConfig
예매나 결제에 대한 결과를 메일로 알려주려하는데, 이 메일은 사실 부가적인 요소이기 때문에
메일 발송이 실패하더라도 예매나 결제 결과에 영향을 끼쳐선 안된다.

비동기처리를 하게되면 메인 쓰레드가 아닌 별도 쓰레드에서 동작하는데,
일반적으로 Spring에서는 트랜잭션이 쓰레드간 전파를 하지 않기 때문에 메일에서 롤백이 일어나도 메인 쓰레드에는 영향이 없다.

그리고 동기식으로 처리하게 되면 메일 전송이 완료될 때까지 메인 쓰레드는 대기를 하게 되는데,
그렇게 되면 메인 쓰레드는 다른 작업을 할 수 없기에 메일 발송에서 비동기처리는 사실상 **필수**인 기능인 셈이다.

메일 발송을 비동기식으로 처리하기 위해 Config 파일을 작성 후 사용하고 싶은 메서드에 **@Asnyc** 어노테이션을 달아주면 된다.<br>
![AsyncConfig](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FeuTxOp%2FbtsKRDEIPk7%2F5GlYX1XzmrFxxTkhKcOAM1%2Fimg.png)

처음에는 쓰레드 갯수와 Queue 용량을 작게 설정했었는데 Jmeter로 테스트를 하다보니
쓰레드도 작고 용량도 작아서 에러율이 98% 가량 됐었다.

Queue 용량이 크면 응답 지연이 발생하게 되지만, 지연이 발생하더라도 에러를 잡는게 우선이라고 생각해서
용량을 크게 잡았다.

결과적으론 에러율이 15%정도가 되었는데 이것도 로직에 대한 문제보단 컴퓨터 사양, 인터넷 문제로 판단된다.

### **쓰레드 수정 전**<br>
![쓰레드 수정 전](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fdh8FU0%2FbtsKRbWjpKa%2FnMksePGOTN1xHwj33hutlk%2Fimg.webp)

### **쓰레드 수정 후**<br>
![쓰레드 수정 후](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FRMRYs%2FbtsKPFj0Z2Z%2F48sbDJPHJIJkklnLuywK61%2Fimg.webp)

## 4. EmailService
3번에서 설정한 비동기는 아래 이미지와 같이 사용하려는 메서드에 @Async와 Bean에서 설정한 이름을 넣어주면 된다.

JavaMailSender를 이용해 간편하게 메일을 전송할 수 있다.<br>
![EmailService](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fc009Zg%2FbtsKQajpUwi%2FQaPQvDs4kFumbI8DNnMw81%2Fimg.png)

메일에 관련된 예외처리는 구현하려고 했었으나 실패로직을 구현하는 방법을 몰라서 시도하다가
마감 시간 이슈로인해 적용하지 못해서 시간 여유가 생기면 따로 구현해보려 한다.

</details>


<details> <summary>Spring Batch</summary>

위아래를 띄우고 여기에 내용을 작성하세요
마크다운 문법으로 작성하시면 됩니다

</details>


<details> <summary>Logstash</summary>

### Logstash 환경설정
- https://velog.io/@uara67/Logstash-springboot-환경-구현하기로그수집하기-1일차
- https://velog.io/@uara67/logstash-AOP로-로그-수집하기


## 1. build.gradle
logback을 이용하여 logstash에 로그를 전달할 것이기에 의존성을 추가해준다. <br>

implementation 'net.logstash.logback:logstash-logback-encoder:7.4'

## 2. logback-spring.xml
### 로그 관련 고려한 점
1. 예매, 쿠폰, 결제 관련 내역의 로그를 남길 것
2. ELK 서버 외의 LOCAL에도 로그를 남길 것
3. 쿼리문도 로그로 남길 것

xml 파일을 보면 local파일을 생성하여 저장하는 부분과 쿼리문 전송하는 부분,
aop를 사용해서 해당 클래스에 logstash로 로그를 전송하는 부분이 있다.

<details> <summary> logback-spring.xml </summary>

``` Java
<configuration scan="true" scanPeriod="30 seconds">
    <property resource="application.properties"/>
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <!-- 로컬 파일에 로그 저장 설정 -->
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern> ${LOGSTASH_FILE_PATH} </fileNamePattern>
            <maxHistory>14</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern>%date %level [%thread] %logger{10} [%file:%line] %msg%n%xThrowable{5}</pattern>
        </encoder>
    </appender>

    <!--  logstash setting  -->
    <appender name="LOGSTASH" class="net.logstash.logback.appender.LogstashTcpSocketAppender">
        <destination> ${LOGSTASH_DESTINATION} </destination>
        <encoder class="net.logstash.logback.encoder.LogstashEncoder" />
    </appender>

    <logger name="com.sparta.projectblue.aop.LogstashAspect" level="DEBUG">
        <appender-ref ref="LOGSTASH" />
        <appender-ref ref="CONSOLE" />
    </logger>

    <logger name="org.hibernate.SQL" level="DEBUG" additivity="false">
        <appender-ref ref="LOGSTASH" />
    </logger>

    <!-- 전체 애플리케이션 로깅 설정 -->
    <root level="INFO">
        <appender-ref ref="CONSOLE" />  <!-- INFO레벨 이상 로그를 CONSOLE에 출력 -->
        <appender-ref ref="FILE" />  <!-- INFO레벨 이상 로그를 File에 기록 -->
    </root>
```
</details>

## 3. LogstashAspect
AOP방식을 사용하였고, 어노테이션 방식의 포인트컷을 사용해
로그를 남기고자 하는 메서드에 어노테이션을 달아주었다.

logstash.conf 파일을 보면 알겠지만, "ReservationEvent" 이라는 글자를 필터해서
해당 index에 로그를 저장한다.

<details> <summary> LogstashAspect.java </summary>

```Java
    @Pointcut("@annotation(com.sparta.projectblue.aop.annotation.ReservationLogstash)")
    private void reservationLog() {}

    @Around("reservationLog()")
    public Object reservationLogstash(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            log.error(
                    "ReservationEvent: 예매 실패 - 메서드: {}, 이유: {}",
                    joinPoint.getSignature().getName(),
                    e.getMessage());
            throw e;
        }

        // 예매 완료
        // 패턴 매칭을 적용한 코드
        if (result instanceof CreateReservationResponseDto reservation) {
            log.info(
                    "ReservationEvent: 예매 완료 - 예매 ID: {}, 공연명: {}, 날짜: {}, 좌석: {}, 총 가격: {}, 예약상태: {}",
                    reservation.getId(),
                    reservation.getPerformanceTitle(),
                    reservation.getRoundDate(),
                    reservation.getSeats(),
                    reservation.getPrice(),
                    reservation.getStatus());
        }

        // 예매 취소
        else if ("delete".equals(joinPoint.getSignature().getName())) {
            Object[] args = joinPoint.getArgs();
            Long reservationId = (Long) args[0];
            log.info("ReservationEvent: 예매 취소 - 유저 ID: {}", reservationId);
        } else {
            log.warn("ReservationEvent: 예상치 못한 결과 형식 - {}", result);
        }

        return result;
    }
```
</details>

## 4. docekr-compose.yml
프로젝트 세팅은 끝났고 docker에 elk를 쉽게 설치하는 방법으로 docker-compose.yml 파일을 세팅했다.

docker에 설치하는 방법은 간단하다. 나는 window를 사용하기 때문에
1. PowerShell을 관리자 모드로 실행
2. docker-compose.yml 파일이 있는 경로로 이동
3. "docker compose up -d" 명령어를 입력해서 image를 다운받고 container를 실행

<details> <summary> docekr-compose.yml </summary>

```yaml
services:
  es01:
    image: docker.elastic.co/elasticsearch/elasticsearch:7.17.3
    container_name: es01
    environment:
      - node.name=es01
      - cluster.name=search-cluster
      - discovery.seed_hosts=es02,es03
      - cluster.initial_master_nodes=es01,es02,es03
      - xpack.security.enabled=false
      - xpack.security.http.ssl.enabled=false
      - xpack.security.transport.ssl.enabled=false
      - "ES_JAVA_OPTS=-Xms256m -Xmx256m"
    ports:
      - "9200:9200" # https
      - "9300:9300" #tcp
    networks:
      - es-bridge
  es02:
    image: docker.elastic.co/elasticsearch/elasticsearch:7.17.3
    container_name: es02
    environment:
      - node.name=es02
      - cluster.name=search-cluster
      - discovery.seed_hosts=es01,es03
      - cluster.initial_master_nodes=es01,es02,es03
      - xpack.security.enabled=false
      - xpack.security.http.ssl.enabled=false
      - xpack.security.transport.ssl.enabled=false
      - "ES_JAVA_OPTS=-Xms256m -Xmx256m"
    ports:
      - "9201:9200" # https
      - "9301:9300" #tcp
    networks:
      - es-bridge
  es03:
    image: docker.elastic.co/elasticsearch/elasticsearch:7.17.3
    container_name: es03
    environment:
      - node.name=es03
      - cluster.name=search-cluster
      - discovery.seed_hosts=es01,es02
      - cluster.initial_master_nodes=es01,es02,es03
      - xpack.security.enabled=false
      - xpack.security.http.ssl.enabled=false
      - xpack.security.transport.ssl.enabled=false
      - "ES_JAVA_OPTS=-Xms256m -Xmx256m"
    ports:
      - "9202:9200" # https
      - "9302:9300" #tcp
    networks:
      - es-bridge
  logstash:
    image: docker.elastic.co/logstash/logstash:7.17.3
    container_name: logstash
    environment:
      - xpack.monitoring.enabled=false
    ports:
      - 5000:5000
      - 9600:9600
    volumes:
      - ./logstash.conf:/usr/share/logstash/pipeline/logstash.conf # local file mapping
    depends_on:
      - es01
      - es02
      - es03
    networks:
      - es-bridge
  kibana:
    image: docker.elastic.co/kibana/kibana:7.17.3
    container_name: kibana
    environment:
      SERVER_NAME: kibana
      ELASTICSEARCH_HOSTS: http://es01:9200
    ports:
      - 5601:5601
    # Elasticsearch Start Dependency
    depends_on:
      - es01
    networks:
      - es-bridge
networks:
  es-bridge:
    driver: bridge
```

</details>

ElasticSearch 노드는 3개 이상, 홀수 단위로 설정해놓는 것이 장애 발생 시
후보 마스터노드를 선출할 때 좋다고 해서 3개로 구성했다.

사용하는 메모리는 AWS 4GB 짜리 medium 서버를 이용하기 때문에 256MB로 설정해주었다.

logstash의 volumes를 통해 현재 경로에 있는 logstash.conf 와 docker 서버에 있는 logstash.conf를 매핑시켰다.
이러면 logstash 내용들을 굳이 docker 서버에 접속하지 않고 window에서 파일을 수정할 수 있어서 간편하다.

## 5. logstash.conf
logstash로 수집되는 로그들을 filter도 해주고 로그 형식 변환 등을 해서 es로 보내주는 설정파일이다.

<details> <summary> logstash.conf </summary>

```
input {
  tcp {
    port => 5000	// 5000번 포트로 log를 받겠다.
    codec => json	// json 형식으로
  }
}

filter {
 grok {	// timestamp는 ISO8601 형식으로.... loglevel도... 나머지데이터는 message에~
  match => { "message" => "%{TIMESTAMP_ISO8601:timestamp} %{LOGLEVEL:loglevel} %{GREEDYDATA:message}" }
 }

 date {
  match => [ "timestamp", "ISO8601" ]
 }

 # 이벤트별 태그 추가
 # ReservationEvent 가 포함되어 있으면 reservation_event 태그 추가
 if [message] =~ "ReservationEvent" {
  mutate { add_tag => ["reservation_event"] }
 }

 if [message] =~ "PaymentEvent" {
  mutate { add_tag => ["payment_event"] }
 }
 
 if [message] =~ "CouponEvent" {
  mutate { add_tag => ["coupon_event"] }
 }

 # SQL 관련 테이블 이름으로 쿼리 태그 추가
 # select가 포함된 쿼리는 제외한다.
 if [logger_name] == "org.hibernate.SQL" {
  if [message] =~ /select/ {
   drop {}
  }
  if [message] =~ /(reservations|reserved_seats|rounds)/ {
    mutate { add_tag => ["reservation_query"] }
  } else if [message] =~ /payments/ {
    mutate { add_tag => ["payment_query"] }
  } else if [message] =~ /coupon/ {
    mutate { add_tag => ["coupon_query"] }
  }
 }
}

output {
 # 전체 이벤트 로그 전송
 if "reservation_event" in [tags] or "payment_event" in [tags] or "coupon_event" in [tags]
   or "reservation_query" in [tags] or "payment_query" in [tags] or "coupon_query" in [tags] {
  elasticsearch {
   hosts => ["http://es01:9200", "http://es02:9200", "http://es03:9200"]
   index => "logstash-%{+YYYY.MM.dd}"
  }
  stdout { codec => rubydebug }
 }
 # Reservation 관련 이벤트와 쿼리를 Elasticsearch로 전송
 if "reservation_event" in [tags] or "reservation_query" in [tags] {
   elasticsearch {
     hosts => ["http://es01:9200", "http://es02:9200", "http://es03:9200"]
     index => "reservation-logs-%{+YYYY.MM.dd}"
   }
   stdout { codec => rubydebug }
 }

 # Payment 관련 이벤트와 쿼리를 Elasticsearch로 전송
 if "payment_event" in [tags] or "payment_query" in [tags] {
   elasticsearch {
     hosts => ["http://es01:9200", "http://es02:9200", "http://es03:9200"]
     index => "payment-logs-%{+YYYY.MM.dd}"
   }
   stdout { codec => rubydebug }
 }

 # Coupon 관련 이벤트와 쿼리를 Elasticsearch로 전송
 if "coupon_event" in [tags] or "coupon_query" in [tags] {
   elasticsearch {
     hosts => ["http://es01:9200", "http://es02:9200", "http://es03:9200"]
     index => "coupon-logs-%{+YYYY.MM.dd}"
   }
   stdout { codec => rubydebug }
 }
}
```

우리는 5000번 포트를 통해 json 형식으로 logstash로 데이터를 전달받기로 했다.

log메시지 중 특정 단어가 포함되어 있을 때 태그를 추가하여
해당 index로 로그를 전송하고, "select"가 포함된 쿼리는 전송되지 않도록 필터를 설정했다.

</details>

#### Logstash 이미지
- logstash 로그 수집화면<br>
  ![image](https://github.com/user-attachments/assets/5ec8ba74-3397-49e4-8aea-6ff67cf41ae2)
- 매일 채워지는 로그<br>
  ![image](https://github.com/user-attachments/assets/2a7c2061-856d-46dc-a85b-409ceae39a94)
- 매일 백업되는 로그<br>
  ![image](https://github.com/user-attachments/assets/7089d337-2896-4b9f-8c1e-96a9273e362c)

</details>


<details> <summary>Monitoring - Prometheus, Grafana</summary>

위아래를 띄우고 여기에 내용을 작성하세요
마크다운 문법으로 작성하시면 됩니다

</details>


<details> <summary>Code Convention</summary>

### Code Convention

프로젝트에서 Code Convention은 이력서를 작성할때의 맞춤법 만큼 중요하다고 생각합니다

온라인 협업 프로젝트에서 일관성있는 Code를 작성하는 것은 쉽지 않았지만 최대한 정돈된 Code를 배포할 수 있도록 다양한 시도를 했습니다

### SonarQube

서버 기반의 정적 코드 분석 도구입니다

Docker 컨테이너로 실행하였고 프로젝트 경로등의 옵션을 넣어 실행할 수 있습니다

Bugs와 Code Smells를 최소화 하였습니다

![SonarQube_Docker](https://g-cbox.pstatic.net/MjAyNDExMjFfMjY4/MDAxNzMyMTc4NjcwOTA4.YhTuUHPhH6Pri2N91rY_hxS60A5UyJwNU7ptVh-eJ5Yg.ZVC0BMyrgVBXZWPEfF1QO-3TKoh459SX22L7Ct8IZEkg.PNG/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7_2024-11-21_174356.png)
![SonarQube_before_after](https://g-cbox.pstatic.net/MjAyNDExMjFfNTMg/MDAxNzMyMTgwMTk2NDM1.KhQnItiQ0dJCO-zNcgm5KJO7BXhs9BNx53oFckAgUkcg.GIKsZMuHdeAACxLPoAOdA6DOMkuYmB-4vOqAVXOh228g.PNG/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7_2024-11-17_200758.png)

### SonarLint

IDE에서 Plugin 형태로 사용할 수 있는 정적 코드 분석 도구입니다

![SonarLint_155_issues](https://g-cbox.pstatic.net/MjAyNDExMjFfMjAw/MDAxNzMyMTc5MTQwNjM4.5GpMaVU1aU-zaQo8ZZTCz5CRKL2CW4bM-2HivrjIAbIg.Dh2334UZroiQZmYnL-ZAgGwHkWEhSs3K7diHPJkM2iUg.PNG/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7_2024-11-18_234428.png)
![SonarLint_15_issues](https://g-cbox.pstatic.net/MjAyNDExMjFfMTk1/MDAxNzMyMTc5MDc1NDkx.KcNLJ245GOcrR57C5fh6jUgqkc1NstZBXXlLmzbptbcg.qnMXJ3hMx66W_IpSetFGjIndLTLEWdCoLOW7KI5amAEg.PNG/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7_2024-11-19_100950.png)

### Spotless

IDE에서 Plugin 형태로 사용할 수 있는 자동정렬 도구 입니다.

build.gradle에 추가하면 컴파일 과정에서 전체 코드를 설정에 맞게 정렬해줍니다

다만, 전체 코드를 처리하기 때문에 배포 성능을 저하시키는 원인이 될 수 있습니다

본 프로젝트에서는 주석처리를 하여 필요할때만 한번씩 전체 코드에 적용하는 방식으로 사용했습니다

![](https://g-cbox.pstatic.net/MjAyNDExMjFfMyAg/MDAxNzMyMTc5ODQzODky._s-wo4smBn4FRxzDa3_pkY8MVrEOxkgnzLwCOnMyb5cg.5o3fPHUPCvVwTmF7hBclvFiZMIaA4vHwwGqNbyLvdGUg.PNG/carbon_%2816%29.png)

</details>

# 💻 Contributors

<details> <summary>강은총</summary>

### [강은총](https://github.com/eunchongkang)

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

<details> <summary>김태주 (LEADER)</summary>

### [김태주 (LEADER)](https://github.com/mylotto0626)

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

<details> <summary>이봄</summary>

### [이봄](https://github.com/everydayspring)

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

<details> <summary>이봉원 (SUB-LEADER)</summary>

### [이봉원 (SUB-LEADER)](https://github.com/LeeBongwon94)

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

<details> <summary>한강</summary>

### [한강](https://github.com/hankang67)

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

# 🤝 Development Guide

![team guide](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcOdXdA%2FbtsKR3QWsg0%2Fk46EZZvQcx6O09QeozDJhk%2Fimg.png)
