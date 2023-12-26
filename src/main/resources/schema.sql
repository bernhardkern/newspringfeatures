create table if not exists todo
(
    id int not null,
    user_id   int not null,
    title text,
    completed boolean,
    PRIMARY KEY (id)
);