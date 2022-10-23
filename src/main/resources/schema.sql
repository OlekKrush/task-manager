-- USER
-- ROLE
-- ROLE_ADMIN (global CREATE, UPDATE, DELETE)
-- ROLE_CREATOR (own CREATE(project))
-- ROLE_USER (READ)

-- ROLE_LEAD (own CREATE(tasks), UPDATE(projects, tasks), DELETE(projects, tasks))
-- ROLE_COLLABORATOR (
--    invit(
--      CREATE(tasks),
--      own (
--          UPDATE(tasks),
--          DELETE(tasks)
--      )))
-- ROLE_VISITOR (invite READ)

-- USER_ROLE (service roles)
-- PROJECT
-- MEMBER
-- TASK

-- CREATE EXTENSION if not exists pgcrypto; -- for gen_random_uuid

create table if not exists user_details
(
    sub      varchar(255) unique not null,
    email    varchar unique      not null,
    password varchar             not null,

    constraint user_details_pkey primary key (sub)
);

create table if not exists user_profile
(
    sub         varchar(255) unique not null,
    enabled     boolean             not null,
    family_name varchar             not null,
    given_name  varchar             not null,
    picture     text                not null,

    constraint user_profile_pkey primary key (sub),

    constraint fk_user_profile_sub
        foreign key (sub)
            references user_details (sub) on delete cascade
);


create table if not exists role
(
    role_id int unique     not null primary key,
    kind    varchar unique not null
);

create table if not exists user_role
(
    sub     varchar(255) not null,
    role_id int          not null,

    constraint fk_user_role_role_id
        foreign key (role_id)
            references role (role_id),

    constraint fk_user_sub_user_role_sub
        foreign key (sub)
            references user_details (sub) on delete cascade
);

create table if not exists project
(
    project_id varchar(50) unique not null primary key,
    name       varchar            not null,
    key        varchar(5)         not null,
    lead       varchar            not null,
    is_private boolean            not null default false,
    img        text               not null default '../../../assets/images/icon-business-pack/svg/101-laptop.svg',

    constraint fk_user_sub_project_lead
        foreign key (lead)
            references user_details (sub) on delete cascade
);

create table if not exists member
(
    project_id varchar(50)  not null,
    role_id    int          not null,
    member_id  varchar(255) not null,

    constraint member_pkey primary key (project_id, member_id),

    constraint fk_member_project_id
        foreign key (project_id)
            references project (project_id),

    constraint fk_member_role_id
        foreign key (role_id)
            references role (role_id),

    constraint fk_user_sub_member_member_id
        foreign key (member_id)
            references user_details (sub) on delete cascade
);


create table if not exists task
(
    task_id      bigserial unique not null,
    summary      varchar          not null,
    project_id   varchar(50)      not null,
    key          int              not null,
    assign_to_id varchar,
    creator_id   varchar          not null,
    checker_id   varchar,
    priority     varchar          not null default 'Medium',
    description  text,
    labels       varchar[]        not null default array []::varchar[],
    date_time_s  timestamp        not null default current_timestamp, /* start date */
    date_time_e  timestamp, /* end date */

    constraint UC_task_projectAndKey unique (project_id, key),

    constraint task_pkey primary key (task_id),

    constraint fk_user_sub_task_creator_id
        foreign key (creator_id)
            references user_details (sub),

    constraint fk_user_sub_task_assign_to_id
        foreign key (assign_to_id)
            references user_details (sub),

    constraint fk_user_sub_task_checker_id
        foreign key (checker_id)
            references user_details (sub),

    constraint fk_project_project_id_task_project_id
        foreign key (project_id)
            references project (project_id)
);
