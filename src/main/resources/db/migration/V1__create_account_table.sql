CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table account
(
    id         bigint                                     NOT NULL,
    document   text check ( char_length(document) <= 11 ) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE                   NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE                   NOT NULL DEFAULT now(),
    deleted_at TIMESTAMP WITH TIME ZONE,
    constraint account_pkey primary key (id)
);

CREATE SEQUENCE account_id_seq increment 1 minvalue 1 maxvalue 9223372036854775807 start 1 cache 1;

ALTER TABLE account
    alter column id set default nextval('account_id_seq');
