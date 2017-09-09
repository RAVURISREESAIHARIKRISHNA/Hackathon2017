create table posts(
--    PostID
    pid number(5) primary key,
    description varchar2(100) not null,
    owner_ph number(10) not null,
    post_date date not null,
    upvotes number(38) not null,
    downvotes number(38) not null,
    reply varchar2(100) ,
    reply_date date ,
    status number(1) not null
    
);
