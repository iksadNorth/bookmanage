-- 외래 키 검사 비활성화
SET FOREIGN_KEY_CHECKS = 0;

-- 테이블 초기화
TRUNCATE TABLE member;
TRUNCATE TABLE book;
TRUNCATE TABLE history;

-- 외래 키 검사 다시 활성화
SET FOREIGN_KEY_CHECKS = 1;