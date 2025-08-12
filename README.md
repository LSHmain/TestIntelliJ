# IntelliJ를 통한 Spring Boot 학습
Spring Boot 학습 예제와 GitHub 기능 테스트를 위한 저장소입니다.
JPA, LFS, Release 관리 등 실무에서 자주 쓰이는 GitHub 기능을 학습하며 적용합니다.

GitHub의 다양한 기능을 활용하지 못하고 있으므로 이를 위해 Spring Boot 학습 내용을 가지고 Test 진행

## 목차
1. [Test내용](#Test내용)
2. [기능](#기능)
3. [설치](#설치)
4. [기여](#기여)
5. [개발환경](#개발환경)
6. [라이선스](#라이선스)

## Test내용
- JPA 학습
- LFS 저장소 활용
- Release 학습

## 기능
- JPA 연관관계 매핑 예제 (OneToOne, OneToMany, ManyToOne 등)
- Git LFS를 이용한 대용량 파일 관리
- GitHub Release를 활용한 버전 관리

## 설치
```bash
git clone https://github.com/LSHmain/ImgTestCardGarden.git
cd ImgTestCardGarden
```

## 기여가이드
| 타입  | 설명 |
|-------|------|
| feat  | 새로운 기능 추가 |
| fix   | 버그 수정 |
| docs  | 문서 수정 |
| style | 코드 포맷팅, 세미콜론 누락 등 |
| refactor | 기능 변화 없는 코드 구조 변경 |
| test  | 테스트 코드 추가/수정 |

## 버전 가이드
### 주버전 (Major)
* 형식: X.0.0
* 호환성 깨지는 변화가 있을 때 올립니다.
* 예:
    * API나 데이터 구조 변경
    * 이전 버전과 호환되지 않는 기능 제거
    * 프로젝트 전면 리팩터링
* 예시: 1.5.2 → 2.0.0 (호환성 없는 큰 변화)

### 부버전 (Minor)
* 형식: 1.X.0
* 새로운 기능 추가 (기존 기능과 호환 유지)
* 예:
    * 새로운 API 추가
    * 기존 기능 확장
    * UI 개선
* 예시: 1.5.2 → 1.6.0 (기존 기능은 그대로, 새 기능 추가)

### 수정버전 (Patch)
* 형식: 1.0.X
* 버그 수정, 성능 개선, 테스트 기능, 사소한 변경
* 예:
    * 오타 수정
    * 로직 버그 수정
    * 보안 패치
* 예시: 1.0.2 → 1.0.3 (버그만 수정)


## 개발환경
| 구분         | 버전 |
|--------------|------|
| Java         | 17   |
| Python       | 3.10 |
| Spring Boot  | 3.2.0 |
| Node.js      | 20.5.0 |
| npm          | 10.2.3 |
| Gradle       | 8.5  |
| MySQL        | 8.0.33 |

## 라이선스
XXX License

