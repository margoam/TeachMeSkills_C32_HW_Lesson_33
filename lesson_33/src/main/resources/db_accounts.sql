CREATE TABLE IF NOT EXISTS public.accounts
(
    id bigint NOT NULL DEFAULT nextval('accounts_id_seq'::regclass),
    account_number character varying(30) COLLATE pg_catalog."default" NOT NULL,
    balance numeric DEFAULT 0.0,
    username character varying(30) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT accounts_pkey PRIMARY KEY (id),
    CONSTRAINT account_number UNIQUE (account_number),
    CONSTRAINT username UNIQUE (username)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.accounts
    OWNER to user1;