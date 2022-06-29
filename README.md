# 파일 확장자 차단

### 개발환경
* Language - JAVA 11
* Framework - spring boot 2.7.1
* DBMS - h2 or mariaDB
* WAS - embedded Tomcat
* Template Engine - Thymeleaf
----

### 요건
1. 요건1
    1. 고정 확장자는 차단을 자주하는 확장자 리스트이며, default는 unCheck되어져 있습니다.
    2. 고정 확장자를 check or uncheck를 할 경우 db에 저장됩니다. - 새로고침시 유지되어야 합니다.
2. 요건2
    1. 확장자 최대 입력 길이는 20자리
    2. 추가버튼 클릭시 db 저장되며, 아래쪽 영역에 표현됩니다.
3. 요건3
    1. 커스텀 확장자는 최대 200개까지 추가가 가능
    2. 확장자 옆 X를 클릭시 db에서 삭제

### 추가고려 요건
1. 입력값 validation
    1. 화면에서 입력값을 검증해서 값이 입력되지 않았을 경우 alert 호출
    2. 확장자의가 등록또는 삭제가 정상적으로 되었을경우 사용자에게 alert를 통한 안내 표시
2. 중복 체크
    2. 커스텀 확장자 입력 후 추가버튼 클릭시 기 등록된 커스텀 확장자 여부 체크
3. XSS 보안
    1. 사용자가 입력한 값에 대해 이스케이프 처리 필요
4. 동시 작업 체크
   1. 다른 작업자가 다른 화면에서 추가 또는 삭제 가능성 체크 필요 (고려는 했으나 기능은 미구현)

---
### 기능 요건 정리
* 제약사항
    * 선택한 고정 확장자는 새로고침 시에도 유지되야 함
    * 커스텀 확장자 최대 200개까지 가능 함
* 기능 요구사항
    * 고정확장자 관리
        * 사용여부 (복수개 선택 가능)
        * 고정확장자는 check or uncheck시 DB에 저장됨
    * 커스텀 확장자 관리
        * 확장자 입력 후 추가 클릭시 DB에 추가됨
          * 고정확장자에 등록되어 있는지 체크
          * 기등록여부 및 200개 제한 체크 필요
        * 커스텀 확장자 옆 X 클릭시 DB에서 삭제됨
---
### 기능 목록
1. FE
    1. 고정확장자 체크박스 check/uncheck 시
        1. 고정확장자 수정기능 호출
    2. 커스텀 확장자 입력 후 추가 클릭 시
        1. 커스텀확장자 추가기능 호출
            1. 호출이 성공하면 box안에 버튼 추가
            2. 실패하면 실패 메시지 alert
                1. API 호출 실패
                2. 200개 상한선 도달 메시지
                3. 기 등록된 알림
    3. 커스텀 확장자 X 클릭 시
        1. 커스텀확장자 삭제기능 호출
2. BE
    1. 고정확장자 관리
        1. 등록/선택한 확장자 정보 조회
            1. DB에 저정된 고정/커스텀 확장자 정보를 반환합니다.
        2. 고정 확장자 수정
            1. check or uncheck시에 호출되며 기존 정보 upsert
    2. 커스텀 확장자 관리
        1. 커스텀 확장자 추가
            1. 확장자명 입력 후 추가버튼 클릭시 호출
        2. 커스텀 확장자 삭제
            1. 커스텀 확장자의 "X" 클릭시 호출
----
* report 실행방법
  1. H2 DB를 설치하고 프로젝트를 run 하거나
  2. MariaDB를 설치 후 application.yml의 해당 설정 주석 해제 후 build.gradle의 해당 부분 의존성 주석 해제 후 run
  3. http://localhost:8080/ 으로 접속하면 report 화면이 표시됩니다.
----
### TABLE DDL
```sql
CREATE TABLE `tb_favi_ext` (
`favi_ext` varchar(200) NOT NULL,
`favi_ext_use_yn` char(1) NOT NULL DEFAULT 'N',
`chg_dt` datetime DEFAULT CURRENT_TIMESTAMP,
`chg_id` varchar(100) DEFAULT 'SYSTEM',
PRIMARY KEY (`favi_ext`)
) ;
```

```sql
CREATE TABLE `tb_cust_ext` (
`cust_ext` varchar(200) NOT NULL,
`chg_dt` datetime DEFAULT CURRENT_TIMESTAMP,
`chg_id` varchar(100) DEFAULT 'SYSTEM',
PRIMARY KEY (`cust_ext`)
) ;
```

### INIT DATA DML
```sql
INSERT INTO `tb_favi_ext` (`favi_ext`, `favi_ext_use_yn`, `chg_id`) VALUES ('bat', 'N', 'INIT');
INSERT INTO `tb_favi_ext` (`favi_ext`, `favi_ext_use_yn`, `chg_id`) VALUES ('cmd', 'N', 'INIT');
INSERT INTO `tb_favi_ext` (`favi_ext`, `favi_ext_use_yn`, `chg_id`) VALUES ('com', 'N', 'INIT');
INSERT INTO `tb_favi_ext` (`favi_ext`, `favi_ext_use_yn`, `chg_id`) VALUES ('cpl', 'N', 'INIT');
INSERT INTO `tb_favi_ext` (`favi_ext`, `favi_ext_use_yn`, `chg_id`) VALUES ('exe', 'N', 'INIT');
INSERT INTO `tb_favi_ext` (`favi_ext`, `favi_ext_use_yn`, `chg_id`) VALUES ('scr', 'N', 'INIT');
INSERT INTO `tb_favi_ext` (`favi_ext`, `favi_ext_use_yn`, `chg_id`) VALUES ('js', 'N', 'INIT');
```
----
