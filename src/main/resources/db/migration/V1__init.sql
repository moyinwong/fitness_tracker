create table exercise (id integer not null auto_increment, workout_id integer not null, name varchar(255) not null, primary key (id)) engine=InnoDB;
create table exercise_set (exercise_id integer not null, id integer not null auto_increment, reps integer not null, sets integer not null, weight float(23) not null, primary key (id)) engine=InnoDB;
create table users (id integer not null auto_increment, first_name varchar(255), last_name varchar(255), password varchar(255), username varchar(255), primary key (id)) engine=InnoDB;
create table workout (duration float(23) not null, id integer not null auto_increment, user_id integer not null, created_at datetime(6), updated_at datetime(6), name varchar(255) not null, primary key (id)) engine=InnoDB;
alter table exercise add constraint FKim5ih59gfsc718iyew89ki5xu foreign key (workout_id) references workout (id);
alter table exercise_set add constraint FK2l9crqua2gdu9b38sfd4954s7 foreign key (exercise_id) references exercise (id);
alter table workout add constraint FKsgixgqgg68ue1xhk2vcfgb5wr foreign key (user_id) references users (id);
