create table user_statuses
(
    id          int  not null primary key,
    description text not null
);

insert into user_statuses (id, description)
values (0, 'ACTIVE');
insert into user_statuses (id, description)
values (1, 'SUSPENDED');

create table users
(
    id bigserial primary key,
    name text,
    email text,
    password text,
    status     int         not null references user_statuses (id),
    created_at timestamptz not null default now()
);

create unique index users_email_uindex on users (email);

create table authorities
(
    id    int primary key,
    value text not null
);

insert into authorities (id, value)
values (0, 'ROLE_MANAGER');
insert into authorities (id, value)
values (1, 'ROLE_TECHNICAL_SPECIALIST');
insert into authorities (id, value)
values (2, 'ROLE_MACHINE_OPERATOR');
insert into authorities (id, value)
values (3, 'ROLE_ADMIN');


create unique index authorities_value_uindex on authorities (value);

create table user_authorities
(
    user_id      bigint not null,
    authority_id int    not null,
    primary key (user_id, authority_id),
    constraint user_authorities_users_fk foreign key (user_id)
        references users (id) on delete cascade,
    constraint user_authorities_authorities_fk foreign key (authority_id)
        references authorities (id) on delete cascade
);


create table manufacturers
(
    id bigserial primary key ,
    name text
);

create table units
(
    id bigserial primary key,
    name text unique
);

insert into units (id, name) values (1, 'ribbed');
insert into units (id, name) values (2, 'mm');
insert into units (id, name) values (3, 'inch/100');
insert into units (id, name) values (4, 'pc');


create table cards
(
    id bigserial primary key,
    name text,
    count int,
    price bigint,
    size integer,
    unit_id bigint references units (id) ,
    manufacturer_id bigint references manufacturers (id) ,
    accessory_id bigint references cards (id)
);

create table  pieces
(
    id bigserial primary key,
    size integer,
    pieces_number integer,
    unit_id bigint references units (id) ,
    card_id bigint references cards (id)

);

create table task_statuses
(
    id bigserial primary key,
    value text
);
insert into task_statuses (id, value)
values (0, 'CREATED');
insert into task_statuses (id, value)
values (1, 'TECHNICAL_REVIEW');
insert into task_statuses (id, value)
values (2, 'PRODUCTION_REVIEW');
insert into task_statuses (id, value)
values (3, 'READY');
insert into task_statuses (id, value)
values (4, 'END');
insert into task_statuses (id, value)
values (5, 'CLARIFICATION_REQUEST');
insert into task_statuses (id, value)
values (6, 'CANCELED');



create table  tasks
(
    id bigserial primary key,
    name text,
    message text,
    count integer,
    status_id bigint references task_statuses (id) ,
    user_id bigint references users (id) ,
    card_id bigint references cards (id),
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now()


);

create table equipments
(
    id bigserial primary key,
    size bigint,
    task_id bigint references tasks (id) ,
    card_id bigint references cards (id) ,
    piece_id bigint references pieces (id)

);

create table refresh_tokens
(
    value     uuid        not null primary key,
    user_id   bigint      not null,
    issued_at timestamptz not null,
    expire_at timestamptz not null,
    next      uuid,
    constraint refresh_tokens_user_fk foreign key (user_id)
        references users (id) on delete cascade,
    constraint refresh_tokens_next_fk foreign key (next)
        references refresh_tokens (value) on delete cascade
);

