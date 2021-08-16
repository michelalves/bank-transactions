create table transaction
(
    id             bigint                   NOT NULL,
    account_id     bigint                   NOT NULL
        constraint account_fkey references account,
    operation_type int                      NOT NULL,
    amount         numeric(19, 2)           NOT NULL,
    created_at     TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at     TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    deleted_at     TIMESTAMP WITH TIME ZONE,
    constraint transaction_pkey primary key (id)
);

CREATE SEQUENCE transaction_id_seq increment 1 minvalue 1 maxvalue 9223372036854775807 start 1 cache 1;

ALTER TABLE transaction
    alter column id set default nextval('transaction_id_seq');
