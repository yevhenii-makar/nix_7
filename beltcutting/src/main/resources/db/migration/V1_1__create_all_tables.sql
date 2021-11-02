create table users
(
    id serial primary key,
    name text,
    email text,
    password text
);

create table roles
(
    id serial primary key,
    role_name text
);

create table users_roles
(
    users_id integer references users (id) on update cascade on delete cascade,
    roles_id integer references roles (id) on update cascade on delete cascade
);
create table manufacturers
(
    id serial primary key ,
    name text
);

create table units
(
    id serial primary key,
    name text
);


create table cards
(
    id serial primary key,
    name text,
    count bigint,
    size integer,
    units_id integer references units (id) on update cascade on delete cascade,
    manufacturers_id integer references manufacturers (id) on update cascade on delete cascade,
    accessories_id integer references cards (id) on update cascade on delete cascade
);

create table  pieces
(
    id serial primary key,
    size integer,
    pieces_number integer,
    units_id integer references units (id) on update cascade on delete cascade,
    cards_id integer references cards (id) on update cascade on delete cascade

);
create table statuses
(
    id serial primary key,
    name text
);



create table  tasks
(
    id serial primary key,
    name text,
    message text,
    data_time timestamp,
    status_id integer references statuses (id) on update cascade on delete cascade,
    users_id integer references users (id) on update cascade on delete cascade,
    cards_id integer references cards (id) on update cascade on delete cascade


);

create table complectations
(
    id serial primary key,
    size integer,
    tasks_id integer references tasks (id) on update cascade on delete cascade,
    cards_id integer references cards (id) on update cascade on delete cascade,
    pieces_id integer references pieces (id) on update cascade on delete cascade

);
