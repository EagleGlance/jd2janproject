create table public.users
(
    id            bigserial
        primary key,
    name          varchar(100)                                          not null,
    surname       varchar(200)                                          not null,
    birth_date    timestamp(6)                                          not null,
    full_name     varchar(100),
    weight        real        default 80,
    email         varchar(100)
        unique,
    user_password varchar(200),
    gender        varchar(20) default 'NOT_SELECTED'::character varying not null
);

alter table public.users
    owner to dev;

create index users_birth_date_index
    on public.users (birth_date desc);

create index users_name_index
    on public.users (name);

create index users_name_surname_index
    on public.users (name, surname);

create table public.cars
(
    id         bigserial
        primary key,
    name       varchar(50)           not null,
    brand      varchar(50)           not null,
    price      real,
    user_id    bigint
        constraint cars_users_id_fk
            references public.users,
    created    timestamp(6)          not null,
    changed    timestamp(6)          not null,
    is_deleted boolean default false not null
);

alter table public.cars
    owner to dev;

create index cars_brand_index
    on public.cars (brand);

create index cars_created_index
    on public.cars (created desc);

create index cars_price_index
    on public.cars (price);

create unique index cars_user_id_uindex
    on public.cars (user_id);

create table public.locations
(
    id      bigserial
        primary key,
    country varchar(50)          not null,
    city    varchar(80)          not null,
    created timestamp(6)         not null,
    changed timestamp(6)         not null,
    visible boolean default true not null
);

alter table public.locations
    owner to dev;

create index locations_city_country_index
    on public.locations (city, country);

create index locations_city_index
    on public.locations (city);

create index locations_country_index
    on public.locations (country);

create table public.l_users_locations
(
    id          bigserial
        primary key,
    user_id     bigint       not null
        constraint l_users_locations_users_id_fk
            references public.users
            on update cascade on delete cascade,
    location_id bigint       not null
        constraint l_users_locations_locations_id_fk
            references public.locations
            on update cascade on delete cascade,
    created     timestamp(6) not null,
    changed     timestamp(6) not null
);

alter table public.l_users_locations
    owner to dev;

create index l_users_locations_user_id_index
    on public.l_users_locations (user_id);

create index l_users_locations_user_id_location_id_index
    on public.l_users_locations (user_id, location_id);

create table public.roles
(
    id        bigserial
        primary key,
    role_name varchar(100) not null,
    user_id   bigint       not null
        constraint roles_users_id_fk
            references public.users
            on update cascade on delete cascade,
    created   timestamp(6) not null,
    changed   timestamp(6) not null
);

alter table public.roles
    owner to dev;

create unique index roles_role_name_user_id_uindex
    on public.roles (role_name, user_id);

