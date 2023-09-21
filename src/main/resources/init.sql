create table books
(
    book_Id   serial primary key,
    book_name varchar(255) not null
);
create table authors
(
    author_Id        serial primary key,
    author_full_name varchar(255) not null,
    books_qty        int
);
create table author_book
(
    id        serial primary key,
    author_id int not null,
    book_id   int not null,
    foreign key (author_id) references authors (author_id),
    foreign key (book_id) references books (book_id)
);

insert into books (book_name)
values ('Мастер и Маргарита'); --1
insert into books (book_name)
values ('Двенадцать стульев'); --2
insert into books (book_name)
values ('Собачье сердце'); --3
insert into books (book_name)
values ('Белая гвардия'); --4
insert into authors(author_full_name, books_qty)
values ('Михаил Афанасьевич Булгаков', 3);--1
insert into authors(author_full_name, books_qty)
values ('Илья́ Арно́льдович Ильф', 1);--2
insert into authors(author_full_name, books_qty)
values ('Евге́ний Петро́вич Петро́в', 1);--3
insert into author_book(author_id, book_id)
values (1, 1)
    insert into author_book(author_id, book_id)
values (1, 3)
insert into author_book(author_id, book_id)
values (1, 4)
insert into author_book(author_id, book_id)
values (2, 2)
insert into author_book(author_id, book_id)
values (3, 2)