
# LINKLE_Backend
Spring Boot 기반 백엔드 서버

## 동네 친구 SNS (Local Friends SNS)


주변 사람들과 일상을 공유하고, 다양한 활동을 함께 즐길 수 있는 **SNS / 채팅 / 실시간 지도 / 결제 및 구독 / TTS 음성 안내 봇** 통합 플랫폼입니다.  
Spring Boot와 React를 기반으로, 백엔드-프론트엔드-데이터베이스-배포까지 풀스택 개발과 최신 기술 스택을 활용한 프로젝트입니다.

---

## 📌 프로젝트 개요

- **프로젝트 주제**: 동네 친구 SNS
- **목표**: 주변 사람들과의 소통과 활동 참여를 촉진하고, 실시간 정보와 다양한 부가 기능을 제공
- **전략**: Divide and Conquer (모듈화 및 기능별 병렬 개발)
- **성과 목표**: 개인 역량 강화 + 실사용 가능한 SNS 완성

---

## 📋 주요 기능

| 기능 카테고리 | 세부 기능 |
|--------------|----------|
| **피드** | 개인 판매 피드 작성, 일반 피드 CRUD, 하이라이트 게시글(스토리), 피드 공유, 해시태그 검색, 포스트 공개 범위 설정, 좋아요/북마크/좋아요 숨김, 판매글 숨김 |
| **댓글** | 댓글 작성/수정/삭제, @언급(인물·위치) |
| **검색** | #해시태그 검색, 친구 검색, 친구 추천(알 수도 있는 사람) |
| **채팅** | 1:1 DM, 랜덤 채팅, 채팅 이모티콘, 채팅 목록/기록 CRUD, 채팅 알림 |
| **지도·위치** | 내 위치 지도, 주변 명소 추천, 근처 사람 보기, 위치 공유 범위 설정, 위치 공개/비공개 |
| **결제·구독** | Toss Payments API 연동, 코인 충전, 구독 상품 결제/중단/해지 |
| **AI/음성** | TTS 화면 읽기, 화면 요약 |
| **관리·보안** | 신고/차단 관리, 사용자 차단, 판매게시글 작성 권한, 아이디/비밀번호 찾기, 개인정보 변경, 로그인/로그아웃, 회원가입 |

---

## 🛠 개발 환경

| 구분 | 상세 |
|------|------|
| **언어** | Java 17.0.6, Python 3.11 |
| **Framework** | Spring Boot 3.5.4, React 19.1.0 |
| **DB** | MariaDB 11.8.2 |
| **IDE** | IntelliJ 2025.2, VSCode 1.102.3, DBeaver 25.1.0, Anaconda |
| **패키지 관리자** | Gradle 8.14.3 |
| **버전 관리** | Git, GitHub |
| **배포 환경** | Podman, Red Hat Quay.io |

---

## ⚙ 사용 기술 스택
| 영역       | 기술                                      |
|------------|-------------------------------------------|
| 백엔드     | Spring Boot, Spring Security, JPA, QueryDSL |
| 프론트엔드 | React, Axios, Tailwind (or styled-components) |
| DB         | MariaDB                                   |
| 인증       | JWT                                       |
| 배포       | Podman, Red Hat Quay                      |
| 기타       | TTS API, Toss Payments                    |

---


## 📦 설치 방법

```bash
# 저장소 클론
git clone https://github.com/LINKLE-2025/LINKLE_Backend.git
cd TestIntelliJ
```

# 백엔드 실행 (Spring Boot)
```
./gradlew bootRun
```

# 프론트엔드 실행 (React)
```
cd frontend
npm install
npm start
```
---

## 🧱 아키텍처
```
plaintext
User → React → Spring Boot REST API → MariaDB
                    ↳ JWT Auth
                    ↳ TTS/Payment API
```
---
## 🪪 라이선스
이 프로젝트는 MIT 라이선스를 따릅니다. [LICENSE](./LICENSE) 파일을 확인해주세요.

