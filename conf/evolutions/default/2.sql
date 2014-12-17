# Tasks schema

# --- !Ups

ALTER TABLE task ADD category varchar(255);

# --- !Downs

ALTER TABLE task DROP category;
