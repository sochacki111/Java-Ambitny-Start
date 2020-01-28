create table todos (
    id int unsigned primary key auto_increment,
    text varchar(255) not null,
    done boolean default false
);
