use toeiconline;

alter table comment add constraint fk_comment_user foreign key (userid) references user(userid);