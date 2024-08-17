# <center>PokeRanger</center>

![image](https://github.com/user-attachments/assets/a5d2f70d-f9ba-48a7-80ee-0af34bbd9cd7)
<br></br>
 - 반려 동물의 손과 발을 의미하는 PAW, 돌봄의 뜻 CARE, 다양한 반려동물을 지키고 수비하는 RANGER.
 - PAW + CARE => POKE , 다양한 재료가 섞인 포케처럼 모든 종류의 반려동물들의 의미


## **서비스 목적**
**유기 동물 후원 물품들을 기부, 판매 받아 보호 활동에 앞장선다.**
<br></br>

## 서비스 목표
- 유저가 직접 참여하여 다양한 카테고리의 모임을 생성/참여하여 커뮤니티를 이룸
- 사용자가 선호 카테고리를 선택하여 선호 카테고리별 모임 조회를 통한 모임 선택의 어려움 감소
- 생성된 다양한 챌린지를 통해 사용자들이 사이트를 이용함에 있어 동기부여 제공
- 랭킹 시스템(유저가 완료한 업적 + 유저가 등반한 산의 누적 높이)를 통한 등산에 대한 동기부여 제공
<br></br>

## ERD

![image](https://github.com/user-attachments/assets/84b8ed3b-7267-4303-9ef8-098a2de7b253)

<br></br>

## 시연영상
https://www.youtube.com/watch?v=UP-HalHFP8U


<br></br>
## 화면 구성

- **메인 화면**

![image](https://github.com/user-attachments/assets/6a57ea24-20f1-4286-8cbc-0329b3755ec2)
<br></br>

- **관리자 페이지**
![image](https://github.com/user-attachments/assets/4ea74438-8249-4d41-a588-a016b0cb3b34)
<br></br>

- **카테고리 추가**
![image](https://github.com/user-attachments/assets/43c30a28-fc37-4db8-b997-1908fd3bb49e)
<br></br>

- **게시판 메인화면**
![image](https://github.com/user-attachments/assets/dee0b98b-42a7-4c35-b03d-fb3dd406906f)

<br></br>
- **회원 가입**
![image](https://github.com/user-attachments/assets/013e5217-dac3-42a4-875b-266f5a8924c7)
<br></br>

- **회원 관리**
![image](https://github.com/user-attachments/assets/d2c7882b-a69b-4f8f-b64f-76f0e71bb6bd)
<br></br>

- **제품 등록**
- ![image](https://github.com/user-attachments/assets/4675f57b-d6f8-4ef7-af69-7231e39575ce)

<br></br>
- **제품 상세**
![image](https://github.com/user-attachments/assets/535251a0-85c4-4a26-921d-f500bdcfc276)

<br></br>

<br></br>
- **장바구니**
![image](https://github.com/user-attachments/assets/4429f556-08b3-4ad2-a7a0-a1767a366e03)

<br></br>

- **주문/결제**
![image](https://github.com/user-attachments/assets/c8dc423c-c3c0-4504-9a46-9b6fc7729969)
<br></br>

- **주문 조회**
![image](https://github.com/user-attachments/assets/aaaa0b42-e41c-4dba-adf5-4e6692f94b67)
<br></br>

- **주문 관리**
![image](https://github.com/user-attachments/assets/0a2e438a-aa5f-43dc-a1aa-4287bb457e9c)
<br></br>

## 기술 스택

> 프론트엔드

<img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white"> <img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=HTML5&logoColor=white"> <img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=CSS3&logoColor=white">



> 백엔드

<img alt="springboot" src ="https://img.shields.io/badge/springboot-6DB33F.svg?&style=for-the-badge&logo=springboot&logoColor=white"/> <img alt="springsecurity" src ="https://img.shields.io/badge/springsecurity-6DB33F.svg?&style=for-the-badge&logo=springsecurity&logoColor=white"/>
<img alt="mysql" src ="https://img.shields.io/badge/mysql-4479A1.svg?&style=for-the-badge&logo=mysql&logoColor=white"/> 

> 서버

<img alt="nginx" src ="https://img.shields.io/badge/nginx-009639.svg?&style=for-the-badge&logo=nginx&logoColor=white"/> <img alt="amazon aws" src ="https://img.shields.io/badge/amazon aws-232F3E.svg?&style=for-the-badge&logo=amazonaws&logoColor=white"/>
<img alt="amazonrds" src ="https://img.shields.io/badge/amazonrds-527FFF.svg?&style=for-the-badge&logo=amazonrds&logoColor=white"/>
<br></br>


## 서비스 기능 명세
### 1. 유저 기능
> - Spring Security + JWT 기반 사용자 인증/인가
> - 회원
>    - 회원가입시 비밀번호 인코딩
>    - 이메일 인증(비밀번호 재발급)
> - 로그인
>    - AccessToken 과 RefreshToken 발급
>    - AccessToken 만료되면 RefreshToken으로 newAccessToken 발급
>    - 카카오 소셜로그인 성공 후 자체 JWT 발급
> - 선호 키워드 등록

### 2. 모임 기능
> - 회원
>    - 모임 생성
>        - 생성한 회원은 모임장(id) 부여
>        - 생성시 모임카테고리, 선호태그 등 설정
>    - 조회
>        - 선호 카테고리 및 태그 사용자 검색어 기반 모임&검색어 조회
>        - 최신순, 인기순 기반 필터링
>        - 모임 참여인원 조회 가능
>    - 유저 신고 기능
> - 모임장
>    - 모임 수정 가능
>    - 모임페이지 삭제 가능

### 3.  챌린지 기능
> - 유저가 챌린지 시작버튼을 통해 시작하는 방식이 아닌, 챌린지 시작 조건 만족 시 자동으로 시작되는 방식
> - 유저의 챌린지 진행도가 챌린지 생성시 설정한 성공기준에 부합하면 성공 여부를 포함하는 컬럼 값인 isCompleted가 true로 바뀌게 되고 업적 트로피를 획득

### 4.  인증 기능
> - 산 정상 좌표값 기반으로 정상 및 등반높이 인증
> - 등반한 정상 개수와 누적 높이를 기록

### 5. 랭킹
> - 사용자가 인증한 데이터(높이, 완료한 챌린지)를 기반으로  점수를 산출하여 랭킹시스템 생성

### 6. 관리자(Admin)
> - 회원관리 : 전체 회원 정보 조회(신고 내역 등) 및 회원 정보 삭제 기능
> - 챌린지 관리 : 챌린지 업로드 및 삭제
> - 카테고리 관리 : 카테고리 수정 및 삭제 기능
<br></br>
## 역할분담
---
| 훈련생 | 역할  | 담당업무 |
| --- | --- | ---- |
| 옥찬혁 | 팀장  | 백엔드  |
| 나정균 | 팀원  | 백엔드  |
| 민지원 | 팀원  | 백엔드  |
| 김경혜 | 팀원  | 프론트  |
| 윤혜원 | 팀원  | 프론트  |
| 진채영 | 팀원  | 프론트  |

