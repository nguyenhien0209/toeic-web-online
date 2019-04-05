use toeiconline;

alter table comment add constraint fk_comment_listenguideline foreign key (listenguidelineid) references listenguideline(listenguidelineid);