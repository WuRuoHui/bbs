create table USER
(
    id int auto_increment,
    account_id varchar(100) null,
    name varchar(50) null,
    token varchar(36) null,
    gmt_create bigint null,
    gmt_modified bigint null,
    constraint USER_pk
        primary key (id)
);