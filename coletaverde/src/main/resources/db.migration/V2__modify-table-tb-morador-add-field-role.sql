ALTER TABLE TB_MORADOR
    ADD ROLE VARCHAR2(50) DEFAULT 'USER'
    ADD CONSTRAINT email_unico UNIQUE (email);