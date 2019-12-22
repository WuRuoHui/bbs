create table notification
(
	id bigint not null,
	notifier bigint null,
	receiver bigint null,
	outer_id bigint null,
	type int null,
	gmt_create bigint null,
	status int default 0 null,
	constraint notification_pk
		primary key (id)
);