create table users (
    id bigserial not null,
    email varchar(255) not null CONSTRAINT user_email_unique UNIQUE,
    password varchar(255) not null,
    name varchar(255) not null,
    created_at timestamp,
    updated_at timestamp,
    primary key (id)
);

create table roles (
    id bigserial not null,
    name varchar(255) not null CONSTRAINT role_name_unique UNIQUE,
    created_at timestamp,
    updated_at timestamp,
    primary key (id)
);

create table user_role (
    user_id bigint REFERENCES users(id),
    role_id bigint REFERENCES roles(id)
);

create table tags (
    id bigserial not null,
    label varchar(150) not null CONSTRAINT tag_label_unique UNIQUE,
    primary key (id)
);

create table posts (
    id bigserial not null,
    title varchar(150) not null,
    url varchar(255),
    short_desc varchar(500),
    content text not null,
    view_count bigint default 0,
    created_by bigint not null REFERENCES users(id),
    created_on timestamp,
    updated_on timestamp,
    primary key (id)
);

create table post_tag (
    post_id bigint not null REFERENCES posts(id),
    tag_id bigint not null REFERENCES tags(id),
    primary key (post_id, tag_id)
);

create table comments (
    id bigserial not null,
    post_id bigint not null REFERENCES posts(id),
    email varchar(150) not null,
    name varchar(150) not null,
    content text not null,
    created_on timestamp,
    updated_on timestamp,
    primary key (id)
);

create table pageviews (
    id bigserial not null,
    referrer varchar(255),
    url varchar(255),
    visit_time timestamp,
    post_id bigint REFERENCES posts(id),
    primary key (id)
);

ALTER SEQUENCE roles_id_seq RESTART WITH 101;
ALTER SEQUENCE users_id_seq RESTART WITH 101;
ALTER SEQUENCE tags_id_seq RESTART WITH 101;
ALTER SEQUENCE posts_id_seq RESTART WITH 101;
ALTER SEQUENCE comments_id_seq RESTART WITH 101;
ALTER SEQUENCE pageviews_id_seq RESTART WITH 101;
