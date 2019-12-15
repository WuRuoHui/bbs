create table COMMENT
(
	id bigint auto_increment primary key not null ,
	parent_id bigint null,
	type int not null,
	commentator int not null,
	gmt_create bigint null,
	gmt_modified bigint null,
	like_count bigint default 0 null
);