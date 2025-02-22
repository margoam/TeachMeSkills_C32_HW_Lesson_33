CREATE TABLE IF NOT EXISTS public.transactions
(
    transaction_id bigint NOT NULL DEFAULT nextval('transactions_transaction_id_seq'::regclass),
    from_account character varying COLLATE pg_catalog."default" NOT NULL,
    to_account character varying COLLATE pg_catalog."default" NOT NULL,
    amount numeric NOT NULL DEFAULT 0.0,
    "timestamp" date NOT NULL,
    username character varying(30) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT transactions_pkey PRIMARY KEY (transaction_id),
    CONSTRAINT account_number FOREIGN KEY (from_account)
    REFERENCES public.accounts (account_number) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT username FOREIGN KEY (from_account)
    REFERENCES public.accounts (account_number) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.transactions
    OWNER to user1;