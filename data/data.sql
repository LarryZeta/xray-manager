create table t_order_record
(
    id           int auto_increment
        primary key,
    user_id      int                                     not null,
    order_id     varchar(32) default ''                  not null,
    order_price  varchar(8)                              not null,
    order_status varchar(16) default 'INIT'              not null,
    active_time  timestamp                               null,
    create_time  timestamp   default current_timestamp() not null,
    update_time  timestamp   default current_timestamp() not null on update current_timestamp(),
    remark       varchar(64) default ''                  not null,
    constraint t_order_record_uk_order_id
        unique (order_id)
);

create table t_user_base_info
(
    id          int auto_increment
        primary key,
    user_name   varchar(32) default ''                  not null,
    phone       varchar(32) default ''                  not null,
    email       varchar(32) default ''                  not null,
    passwd      varchar(64) default ''                  null,
    user_status varchar(16) default 'INIT'              not null,
    create_time timestamp   default current_timestamp() not null,
    update_time timestamp   default current_timestamp() not null on update current_timestamp(),
    remark      varchar(64) default ''                  not null,
    constraint t_user_base_info_pk2
        unique (email)
);

create table t_user_role_info
(
    id          int auto_increment
        primary key,
    user_id     int                                     not null,
    role_code   varchar(16) default ''                  not null,
    role_name   varchar(32) default ''                  not null,
    role_status varchar(16) default 'INIT'              not null,
    create_time timestamp                               not null,
    update_time timestamp   default current_timestamp() not null on update current_timestamp(),
    remark      varchar(64) default ''                  not null
);

create table t_xray_account_info
(
    id             int auto_increment
        primary key,
    user_id        int                                     not null,
    uuid           varchar(32)                             not null,
    account_status varchar(8)  default 'INIT'              not null,
    effective_time timestamp                               not null,
    expire_time    timestamp                               not null,
    create_time    timestamp   default current_timestamp() not null,
    update_time    timestamp   default current_timestamp() not null on update current_timestamp(),
    remark         varchar(64) default ''                  not null
);

create table t_xray_server_info
(
    id          int auto_increment
        primary key,
    address     varchar(64) default '' not null,
    server_name varchar(32) default '' not null,
    token       varchar(64) default '' not null,
    tag         varchar(32) default '' not null,
    remark      varchar(64) default '' not null
);
