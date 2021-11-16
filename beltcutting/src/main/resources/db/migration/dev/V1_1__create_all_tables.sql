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
    created_at timestamp with time zone not null default now()
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
    id serial primary key ,
    name text
);

create table units
(
    id serial primary key,
    name text unique
);


create table cards
(
    id serial primary key,
    name text,
    count int,
    price bigint,
    size integer,
    unit_id integer references units (id) ,
    manufacturer_id integer references manufacturers (id) ,
    accessorY_id integer references cards (id) 
);

create table  pieces
(
    id serial primary key,
    size integer,
    pieces_number integer,
    unit_id integer references units (id) ,
    card_id integer references cards (id) 

);

create table task_statuses
(
    id serial primary key,
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
    id serial primary key,
    name text,
    message text,
    count integer,
    status integer references task_statuses (id) ,
    user_id integer references users (id) ,
    card_id integer references cards (id),
    created_at timestamp with time zone not null default now(),
    updated_at timestamp with time zone not null default now()


);

create table complectations
(
    id serial primary key,
    size integer,
    task_id integer references tasks (id) ,
    card_id integer references cards (id) ,
    piece_id integer references pieces (id)

);

create table refresh_tokens
(
    value     uuid        not null primary key,
    user_id   bigint      not null,
    issued_at timestamp with time zone not null,
    expire_at timestamp with time zone not null,
    next      uuid,
    constraint refresh_tokens_user_fk foreign key (user_id)
        references users (id) on delete cascade,
    constraint refresh_tokens_next_fk foreign key (next)
        references refresh_tokens (value) on delete cascade
);

