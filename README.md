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

![](https://g-cbox.pstatic.net/MjAyNDExMjFfMzAw/MDAxNzMyMTYyNTQzODMx.xZDLpCMsB_vk-ReOs_fb2pckQWKXCWDOJhmYmN27W_Eg.YxNraajudnB_nkRHdVToJtELcEThTK424gE-Loj6ocAg.PNG/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7_2024-11-21_131417.png)

- GitHub 연동으로 이슈와 관련된 커밋 기록을 확인할 수 있습니다

![](https://g-cbox.pstatic.net/MjAyNDExMjFfODQg/MDAxNzMyMTYyNjg5NTYy.vl0DYUQdx9ercb8V801IwkQcT3JaHCzwaDSiXTWT43Qg.3cU_sfoN8RgRsDWqh9XCc4aeOAW_R90e9CbrjDal5Ncg.PNG/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7_2024-11-21_131737.png)


[Jira로 프로젝트 관리하기 Blog](https://everyday-spring.com/626)

</details>

<details> <summary>SWAGGER</summary>

### Swagger

- 좀더 편안한 API 테스트를 위해 Swagger를 도입하였습니다
- API 목록이 알파벳 오름차순으로 정렬되도록 추가 설정을 하였습니다

![](https://g-cbox.pstatic.net/MjAyNDExMjFfODYg/MDAxNzMyMTYyODk2ODEw.ENov1JgrjEIMPxkHeYmBpxBVUs9VrZIvttgMTybO0qUg.ghIwmCI8tVSUwNZMreYjq9XtPDOfgCdBFVz_6Zl-hwIg.PNG/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7_2024-11-21_132055.png)

- DTO의 inner class 구조를 삭제하고 Sample data를 설정했습니다.

![](https://g-cbox.pstatic.net/MjAyNDExMjFfMjU2/MDAxNzMyMTYyOTA1Njg3.HSlfdyH4pgD0olN5ixaUY1iFWwvKNPZdkQOnH5kNqyUg.kSxjxT3-X-34iF68FZ6O5Nq0IoW_Wd9sd25p2uTSBwAg.PNG/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7_2024-11-21_132118.png)

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

위아래를 띄우고 여기에 내용을 작성하세요
마크다운 문법으로 작성하시면 됩니다

</details>

<details> <summary>Jenkins CI/CD</summary>

### Window PC에 Docker, jenkins 설치, GitHub 연동하기

- window 환경에서 Jenkins 환경을 구성하여 배포 테스트를 진행했습니다

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbC3EdK%2FbtsKnFXc1Xl%2FYuAtZ1hBEkKRxcb5km3H10%2Fimg.png)
![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbjfzW8%2FbtsKl6BKLBs%2FmWzKjOkrxfUEp8lc7jpyr0%2Fimg.png)

### AWS EC2에 Docker Jenkins 설치, Github Spring Boot application CI/CD

- EC2에서 Jenkins CI/CD 환경을 구성하였습니다
- Docker - Jenkins 구조의 최소 요구 성능을 반영하여 t3.small 인스턴스를 생성했습니다
- Docker not found
  - Docker 명령어를 찾지 못해 발생하는 문제
  - Jenkins 내에 Docker를 추가 설치하여 Docker in Docker 구조를 사용했습니다
- 추가적인 리소스를 사용 할 수 있도록 메모리 스왑 설정도 진행하였습니다.

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FQUUa3%2FbtsKtNvFdDM%2FKCnGUwP01CkzMfyB7P5Me0%2Fimg.jpg)

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FrXrF9%2FbtsKtJ70cSY%2FpSMCHvEQ48aUVNAtOgRz71%2Fimg.png)

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbtzsIf%2FbtsKtFET9jR%2FNlcHtt8PZICWdbmyDmKJj1%2Fimg.png)

Jenkins CI/CD 블로그

[ (1) ](https://everyday-spring.com/628)
[ (2) ](https://everyday-spring.com/629)
[ (3) ](https://everyday-spring.com/630)
[ (4) ](https://everyday-spring.com/631)
[ (5) ](https://subin0522.tistory.com/632)
[ (6) ](https://subin0522.tistory.com/633)
[Jenkins CI/CD 트러블 슈팅](https://everyday-spring.com/634)

</details>


<details> <summary>Redis - 동시성 제어</summary>

위아래를 띄우고 여기에 내용을 작성하세요
마크다운 문법으로 작성하시면 됩니다

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

- https://velog.io/@uara67/Spring-ELK-1-엘라스틱-서치-그게-뭔데
- https://velog.io/@uara67/Spring-ELK-Docker-Elastic-Search-Kibana를-설치해서-springboot와-연결하자-1
- https://velog.io/@uara67/Spring-ELK-Docker-Elastic-Search-Kibana를-설치해서-springboot와-연결하자-2
- https://velog.io/@uara67/Spring-ELK-es로-구현한-예매검색-api
### elastic api 성능체크 및 grafna 이용한 모니터링
- https://velog.io/@uara67/Elastic-성능-체크를-promethus와-grafna로-해보자
- https://velog.io/@uara67/Elasticsearch-성능-테스트-보고서-1
- https://velog.io/@uara67/elastic-모니터링과-속도측정테스트-2
- ![image](https://github.com/user-attachments/assets/fab66816-51b3-4293-a785-e2d8fc4158c6)
  [성능테스트 결과 요약]
  - https://velog.io/@uara67/Elasticsearch-vs-MySQL-왜-MySQL이-더-빠를까
  - ![image](https://github.com/user-attachments/assets/244d4bcd-e97d-4fa3-ad7a-5f8841e3cb92)
  
  
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

위아래를 띄우고 여기에 내용을 작성하세요
마크다운 문법으로 작성하시면 됩니다

</details>

<details> <summary>Spring Batch</summary>

위아래를 띄우고 여기에 내용을 작성하세요
마크다운 문법으로 작성하시면 됩니다

</details>

<details> <summary>Logstash</summary>

### Logstash 환경설정
- https://velog.io/@uara67/Logstash-springboot-환경-구현하기로그수집하기-1일차
- https://velog.io/@uara67/logstash-AOP로-로그-수집하기
#### Logstash 이미지
- logstash 로그 수집화면
- ![image](https://github.com/user-attachments/assets/5ec8ba74-3397-49e4-8aea-6ff67cf41ae2)
- 매일 채워지는 로그
- ![image](https://github.com/user-attachments/assets/2a7c2061-856d-46dc-a85b-409ceae39a94)
- 매일 백업되는 로그
- ![image](https://github.com/user-attachments/assets/7089d337-2896-4b9f-8c1e-96a9273e362c)

</details>

<details> <summary>Monitoring - Prometheus, Grafana</summary>

위아래를 띄우고 여기에 내용을 작성하세요
마크다운 문법으로 작성하시면 됩니다

</details>

<details> <summary>SonarQube, SonarLint</summary>

위아래를 띄우고 여기에 내용을 작성하세요
마크다운 문법으로 작성하시면 됩니다

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
