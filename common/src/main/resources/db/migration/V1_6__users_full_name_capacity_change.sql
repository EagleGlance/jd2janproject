alter table users
    alter column full_name type varchar(300) using full_name::varchar(300);