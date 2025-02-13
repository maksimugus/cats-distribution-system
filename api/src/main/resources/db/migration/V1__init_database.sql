create table owners (
    date_of_birth date not null,
    id bigserial
        primary key,
    name varchar(255) not null
);

alter table owners
    owner to postgres;

create table cats (
    date_of_birth date not null,
    id bigserial
        primary key,
    owner_id bigint
        constraint fkc0phghv1jwbvelwan7pndwrei
            references owners,
    breed varchar(255) not null,
    color varchar(255) not null
        constraint cats_color_check
            check ((color)::text = ANY
                   ((ARRAY ['BLACK'::character varying, 'WHITE'::character varying, 'RED'::character varying, 'GREY'::character varying, 'BROWN'::character varying])::text[])),
    name varchar(255) not null
);

alter table cats
    owner to postgres;

create table cats_friends (
    cat_id bigint not null
        constraint fkj7kirp45prm5e9is6srsd9j8k
            references cats,
    friend_id bigint not null
        constraint fk1wen44y0bpnqmdpmkwswsh6hu
            references cats
);

alter table cats_friends
    owner to postgres;

create table users (
    id bigserial
        primary key,
    owner_id bigint
        unique
        constraint fk84vou4vrhr2a4l2c4e1i0vxo3
            references owners,
    login varchar(255) not null
        unique,
    password varchar(255) not null,
    role varchar(255) not null
        constraint users_role_check
            check ((role)::text = ANY
                   ((ARRAY ['ROLE_ADMIN'::character varying, 'ROLE_USER'::character varying])::text[]))
);

alter table users
    owner to postgres;
