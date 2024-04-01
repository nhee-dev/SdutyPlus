<div align="center">

# Sduty+ 
<img src="https://user-images.githubusercontent.com/49026286/202903659-84b39720-96f9-4a7c-8ea8-80c8e299ad35.png" width="200" height="200"/>

**공부 인증 스터디앱**

**[1. Explore Front Repository](./AOS)**

**[2. 기획 배경](https://github.com/SdutyPlus/SdutyPlus?tab=readme-ov-file#%EA%B8%B0%ED%9A%8D-%EB%B0%B0%EA%B2%BD)**

**[3. 기능](https://github.com/SdutyPlus/SdutyPlus?tab=readme-ov-file#%EA%B8%B0%EB%8A%A5)**

</div>

<br/>

## 시스템 구조도
<div align="center">

<img src="https://user-images.githubusercontent.com/49026286/202906377-f1317e67-ebb5-4aef-a444-806d1eee1bab.png" width="720" height="480"/>

</div>
<br>

## 기술 스택

<table>
	<tr><th rowspan="1">🐬Front-end</th><td>Android, Kotlin, Jetpack, Figma, Clean Architecture</td></tr>
	<tr><th rowspan="1">🍀Back-end</th><td>Spring Boot, Spring Security, JPA, Querydsl, JWT, MariaDB, Firebase, JUnit, JMeter</td></tr>
    <tr><th rowspan="1">🛠Server</th><td>EC2, Docker, Jenkins, Nginx, Certbot</td></tr>
	<tr><th rowspan="1">👨‍👩‍👦‍👦Collaboration</th><td>Gitlab, Jira Software, Mattermost, Notion</td></tr>

</table>

<br>

## TEAM MEMBER
<table>
  <tr>
    <td align="center"><a href="https://github.com/nhee-dev"><img src="https://avatars.githubusercontent.com/nhee-dev" width=80px;" alt=""></td>
    <td align="center"><a href="https://github.com/forlivd"><img src="https://avatars.githubusercontent.com/forlivd" width=80px;" alt=""></td>
    <td align="center"><a href="https://github.com/YunsHub"><img src="https://avatars.githubusercontent.com/YunsHub" width=80px;" alt=""></td>
    <td align="center"><a href="https://github.com/toy9910"><img src="https://avatars.githubusercontent.com/toy9910" width=80px;" alt=""></td>
    <td align="center"><a href="https://github.com/Sicoree"><img src="https://avatars.githubusercontent.com/Sicoree" width=80px;" alt=""></td>
    <td align="center"><a href="https://github.com/pmi4202"><img src="https://avatars.githubusercontent.com/pmi4202" width=80px;" alt=""></td>
  </tr>
  <tr>
    <td align="center"><a href="https://github.com/nhee-dev"><sub><b>김남희</b></td>
    <td align="center"><a href="https://github.com/forlivd"><sub><b>서재형</b></td>
    <td align="center"><a href="https://github.com/YunsHub"><sub><b>김정윤</b></td>
    <td align="center"><a href="https://github.com/toy9910"><sub><b>배한용</b></td>
    <td align="center"><a href="https://github.com/Sicoree"><sub><b>배시현</b></td>
    <td align="center"><a href="https://github.com/pmi4202"><sub><b>편예린</b></td>
  </tr>
  <tr>
    <td align="center"><img src="https://img.shields.io/badge/Android-3DDC84?style=flat&logo=Android&logoColor=white"/></td>
    <td align="center"><img src="https://img.shields.io/badge/Android-3DDC84?style=flat&logo=Android&logoColor=white"/></td>
    <td align="center"><img src="https://img.shields.io/badge/Android-3DDC84?style=flat&logo=Android&logoColor=white"/></td>
    <td align="center"><img src="https://img.shields.io/badge/Android-3DDC84?style=flat&logo=Android&logoColor=white"/></td>
    <td align="center"><img src="https://img.shields.io/badge/Spring-6DB33F?style=flat&logo=Spring&logoColor=white"/></td>
    <td align="center"><img src="https://img.shields.io/badge/Spring-6DB33F?style=flat&logo=Spring&logoColor=white"/></td>	
  </tr>  
</table>


## 규칙

🌐 Git Flow
```
master : 제품으로 출시될 수 있는 브랜치
release : 이번 출시 버전을 준비하는 브랜치
develop : 다음 출시 버전을 개발하는 브랜치
feature : 기능을 개발하는 브랜치
hotfix : 출시 버전에서 발생한 버그를 수정 하는 브랜치
```

🌐 Git branch 생성 규칙

master ← develop ←  (release) ← be/fe ← 작업 브랜치 **순서대로 머지** 한다.

작업 브랜치 이름은 **FE/BE-대작업분류**로 . UI는 따로 가장 뒤에 UI를 붙인다.
```
FE-report
FE-report-UI
FE-pomodoro-timer
BE-report
```

🌐 Git 커밋 타입
```
feat : 기능 추가
fix : 버그 수정 (안 돌아간다)
refactor : 가독성, 유지보수의 목적이 있는 코드 수정
pref : 성능 향상
rename : 단순 파일 이름 수정 및 이동, 추가
style : 코드 스타일 변경 (뉴라인, 세미콜론 등)
docs : 문서 수정
test : 테스트 관련 코드 추가
ui : ui 변경
```

🌐 Git 커밋 형식
```
커밋 타입: 대작업분류(Epic) | 작업 단위, 변경 사항
```
```
feat: 타이머 | 용이 나타나면서 승천하는 애니메이션 기능 추가
```

<details>
<summary>ver.2022</summary>
<div markdown="1">

</br>
🌐 Git branch 생성 규칙

master ← develop ←  (release) ← be/fe ← feat  **순서대로 머지** 한다.


개발 시 **feat-기능 이름** 으로 브랜치를 만들어 상위에 머지한다.

- BE/**feat/naver-login**
- FE/**feat/naver-login**

UI만 작업을 할때는 가장 뒤에 UI를 작성한다.

- FE/**feat/naver-login-UI**


🌐 Git 커밋 컨벤션 생성 규칙
```
feat : 기능 추가
fix : 버그 수정
docs : 문서 수정
style : 단순 수정 (세미콜론, 코드 이동, 띄어 쓰기, 이름 변경)
rename: 추가된 기능, 별 내용 없이 폴더 및 파일만 추가, 폴더 및 파일 이름 수정, 옮기기
refactor : 코드 리팩토링
test : 테스트 추가
comment : 필요한 주석 추가 및 변경
release : 배포 관련 수정, 버전 관리
study : 학습 내용
```
**ver01(간략) : commit type: Epic/대분류 | 작업 단위 [Jira 이슈 번호]**


feat: 회원관리 | 네이버 로그인 기능 추가  [Jira 이슈번호]

**ver02(설명) : ver01양식(Jira 번호 빼고) - 본문 - [Jira 이슈 번호]**

feat: 회원관리 | 네이버 로그인 기능 추가

본문은 위, 아래 한 줄 띄우고 원하는 대로 작성한다.

 => 이런 식으로 특수기호를 쓰거나 이모티콘을 쓰는 것도 가능하다. 😄

단, 한 줄에 너무 길지 않도록 작성하자.

[Jira 이슈 번호]

<br/>



</div>
</details>

