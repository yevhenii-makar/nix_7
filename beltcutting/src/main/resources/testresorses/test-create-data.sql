create table if not exists user_statuses
(
    id          int  not null primary key,
    description text not null
);

insert into user_statuses (id, description)
values (0, 'ACTIVE'),
       (1, 'SUSPENDED')
on duplicate key update description = description;

insert into users(id, name, email, password, status, created_at)
values (2, 'manager1', 'manager1@mail.com', '$2a$10$Kd0Uzb3jzHdoFVRCiAYHyumW.wOiqMwP4qD6vdhDQAX/RBUnBv3Vi', 0, CURRENT_TIMESTAMP()),
       (3, 'manager2', 'manager2@mail.com', '$2a$10$Kd0Uzb3jzHdoFVRCiAYHyumW.wOiqMwP4qD6vdhDQAX/RBUnBv3Vi', 0, CURRENT_TIMESTAMP()),
       (4, 'tech', 'tech@mail.com', '$2a$10$Kd0Uzb3jzHdoFVRCiAYHyumW.wOiqMwP4qD6vdhDQAX/RBUnBv3Vi', 0, CURRENT_TIMESTAMP()),
       (5, 'operator', 'operator@mail.com', '$2a$10$Kd0Uzb3jzHdoFVRCiAYHyumW.wOiqMwP4qD6vdhDQAX/RBUnBv3Vi', 0, CURRENT_TIMESTAMP())
on duplicate key update name=name,
                        email=email,
                        password= password,
                        status = status,
                        created_at = CURRENT_TIMESTAMP();

-- alter table authorities add value text;
-- insert into authorities (id, value)
-- values (0, 'ROLE_MANAGER'),
--        (1, 'ROLE_TECHNICAL_SPECIALIST'),
--        (2, 'ROLE_MACHINE_OPERATOR'),
--        (3, 'ROLE_ADMIN');
insert into authorities (id)
values (0),
       (1),
       (2),
       (3)
on duplicate key update id = id;

insert into user_authorities (user_id, authority_id)
values (2, 0),
       (3, 0),
       (3, 1),
       (4, 1),
       (5, 2)
on duplicate key update user_id= user_id,
                        authority_id=authority_id;

insert into manufacturers (id, name)
values (0, 'contitech'),
       (1, 'gates')
on duplicate key update name= name;

insert into units(id, name)
values (0, 'pc'),
       (1, 'ribbed'),
       (2, 'mm'),
       (3, 'inch/100')
on duplicate key update name= name;

insert into cards(id, name, count, size, unit_id, manufacturer_id, accessory_id)
values (0, 'pj 701', 150, null, 1, 0, null),
       (1, 'T10 450', 200, null, 2, 0, null),
       (2, '300 xl', 1500, null, 3, 0, null),
       (3, '5 pj 701', 5, 5, 0, 0, 0),
       (4, '25 T10 450', 1, 25, 0, 0, 1),
       (5, '300 xl 039', 3, 39, 0, 0, 2)
on duplicate key update name=name,
                        count=count,
                        size=size,
                        unit_id=unit_id,
                        manufacturer_id=manufacturer_id,
                        accessory_id=accessory_id;


insert into pieces(id, size, pieces_number, unit_id, card_id)
values (0, 150, 1, 1, 0),
       (1, 150, 1, 2, 1),
       (2, 50, 2, 2, 1),
       (3, 750, 1, 3, 2),
       (4, 250, 2, 3, 2),
       (5, 500, 3, 3, 2)
on duplicate key update id=id,
                        size=size,
                        pieces_number=pieces_number,
                        unit_id=unit_id,
                        card_id=card_id;

create table if not exists task_statuses
(
    id    bigint auto_increment primary key,
    value text
);

-- insert into task_statuses (id, value)
-- values (0, 'CREATED'),
--        (1, 'TECHNICAL_REVIEW'),
--        (2, 'PRODUCTION_REVIEW'),
--        (3, 'READY'),
--        (4, 'END'),
--        (5, 'CLARIFICATION_REQUEST'),
--        (6, 'CANCELED')
-- on duplicate key update value=value ;

insert into task_statuses (id)
values (0),
       (1),
       (2),
       (3),
       (4),
       (5),
       (6)
on duplicate key update id=id;

insert into tasks (id, name, message, count, status, user_id, card_id, created_at, updated_at)
values (0, 'taskCreated', 'create task', '5', 0, 2, 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (1, 'taskTECHNICAL_REVIEW', 'TECHNICAL_REVIEW task', 2, 1, 2, 4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (2, 'taskPRODUCTION_REVIEW', 'PRODUCTION_REVIEW task', 3, 2, 4, 4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP())
on duplicate key update name   = name,
                        message=message,
                        count=count,
                        status=status,
                        user_id=user_id,
                        card_id=card_id;


insert into equipments (id, size, task_id, card_id, piece_id)
values (1, 50, 1, 1, 1),
       (2, 50, 1, 1, 2),
       (3, 50, 2, 1, 1),
       (4, 25, 2, 1, 2);

