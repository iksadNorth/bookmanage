-- member
create table member (
    id bigint primary key auto_increment,
    username varchar(256) not null unique,
    password varchar(256) not null,
    role_type varchar(256) default 'ROLE_USER'
);

ALTER TABLE member ADD INDEX idx_member_username (username);

-- book
create table book (
    id bigint primary key auto_increment,
    title varchar(256) not null,
    author varchar(256),
    publisher varchar(256),
    isbn varchar(13) not null unique
);

ALTER TABLE book ADD INDEX idx_book_isbn (isbn);

-- history
create table history (
    id bigint primary key auto_increment,
    created_at datetime not null,
    type varchar(256) not null,
    book_id bigint not null,
    member_id bigint not null
);

ALTER TABLE history ADD FOREIGN KEY(member_id) REFERENCES member(id);
ALTER TABLE history ADD FOREIGN KEY(book_id) REFERENCES book(id);

ALTER TABLE history ADD INDEX idx_history_created_at (created_at);
