-- 테이블 초기화
INSERT INTO book (title, author, publisher, isbn)
VALUES ('이것이 자바다', '신용권', '한빛미디어', '123456789');
INSERT INTO book (title, author, publisher, isbn)
VALUES ('이것이 파이썬이다', '신용권', '한빛미디어', '987654321');
INSERT INTO book (title, author, publisher, isbn)
VALUES ('이것이 C언어다', '신용권', '한빛미디어', '11111');

-- 'asdf' ==encoding==> '$2a$10$O3Bb8418o8KVTyi4zBU0QuSWgCizyEF9WyT.KlsAaN4AbGOxbxMwy'
INSERT INTO member (username, password)
VALUES ('username 1', '$2a$10$O3Bb8418o8KVTyi4zBU0QuSWgCizyEF9WyT.KlsAaN4AbGOxbxMwy');
INSERT INTO member (username, password)
VALUES ('username 2', '$2a$10$O3Bb8418o8KVTyi4zBU0QuSWgCizyEF9WyT.KlsAaN4AbGOxbxMwy');
INSERT INTO member (username, password)
VALUES ('username 3', '$2a$10$O3Bb8418o8KVTyi4zBU0QuSWgCizyEF9WyT.KlsAaN4AbGOxbxMwy');
INSERT INTO member (username, password, role_type)
VALUES ('username 4', '$2a$10$O3Bb8418o8KVTyi4zBU0QuSWgCizyEF9WyT.KlsAaN4AbGOxbxMwy', 'ROLE_ADMIN');

-- bookId : 1
INSERT INTO history (created_at, type, book_id, member_id)
VALUES ('2023-12-18 2:56:49', 'RETURN_COMPLETED', 1, 1);
INSERT INTO history (created_at, type, book_id, member_id)
VALUES ('2023-12-18 14:56:49', 'BORROWING', 1, 2);

-- bookId : 2
INSERT INTO history (created_at, type, book_id, member_id)
VALUES ('2023-12-19 2:56:49', 'BORROWING', 2, 1);
INSERT INTO history (created_at, type, book_id, member_id)
VALUES ('2023-12-19 14:56:49', 'RETURN_COMPLETED', 2, 1);
