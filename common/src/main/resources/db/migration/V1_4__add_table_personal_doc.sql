create table if not exists personal_doc
(
    id              bigserial
        primary key
        unique,
    personal_id     varchar(100) not null
        unique,
    document_type   varchar(20)  not null,
    doc_number      varchar(20)  not null
        unique,
    created         timestamp(6),
    changed         timestamp(6),
    expiration_date timestamp(6) not null,
    user_id         bigint       not null
        constraint personal_doc_users_id_fk
            references users
);

