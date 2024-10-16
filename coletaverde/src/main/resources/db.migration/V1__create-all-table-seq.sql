CREATE SEQUENCE SEQ_CAMINHAO
    INCREMENT BY 1
    NOMINVALUE
    NOMAXVALUE
    START WITH 1
    NOCACHE
    NOCYCLE;

CREATE SEQUENCE SEQ_COLETA
    INCREMENT BY 1
    NOMINVALUE
    NOMAXVALUE
    START WITH 1
    NOCACHE
    NOCYCLE;

CREATE SEQUENCE SEQ_ENDERECO
    INCREMENT BY 1
    NOMINVALUE
    NOMAXVALUE
    START WITH 1
    NOCACHE
    NOCYCLE;

CREATE SEQUENCE SEQ_MORADOR
    INCREMENT BY 1
    NOMINVALUE
    NOMAXVALUE
    START WITH 1
    NOCACHE
    NOCYCLE;

CREATE SEQUENCE SEQ_NOTIFICACAO
    INCREMENT BY 1
    NOMINVALUE
    NOMAXVALUE
    START WITH 1
    NOCACHE
    NOCYCLE;

CREATE SEQUENCE SEQ_ROTA
    INCREMENT BY 1
    NOMINVALUE
    NOMAXVALUE
    START WITH 1
    NOCACHE
    NOCYCLE;

CREATE SEQUENCE SEQ_TIPORESIDUO
    INCREMENT BY 1
    NOMINVALUE
    NOMAXVALUE
    START WITH 1
    NOCACHE
    NOCYCLE;


CREATE TABLE tb_caminhao (
    id           INTEGER DEFAULT SEQ_CAMINHAO.nextval NOT NULL,
    placa        VARCHAR2(10) NOT NULL,
    coordenada_x VARCHAR2(10),
    coordenada_y VARCHAR2(10),
    created_at   DATE,
    updated_at   DATE
);

ALTER TABLE tb_caminhao ADD CONSTRAINT tb_caminhao_pk PRIMARY KEY ( id );

CREATE TABLE tb_coleta (
    id               INTEGER DEFAULT SEQ_COLETA.nextval NOT NULL,
    rota_id          INTEGER NOT NULL,
    caminhao_id      INTEGER NOT NULL,
    endereco_id      INTEGER NOT NULL,
    previsao_horario DATE,
    created_at        DATE,
    updated_at       DATE
);

ALTER TABLE tb_coleta ADD CONSTRAINT tb_coleta_pk PRIMARY KEY ( id );

CREATE TABLE tb_coleta_tipo_residuo (
    coleta_id       INTEGER NOT NULL,
    tipo_residuo_id INTEGER
);

CREATE TABLE tb_endereco (
    id          INTEGER DEFAULT  SEQ_ENDERECO.nextval NOT NULL,
    cep         VARCHAR2(10) NOT NULL,
    rua         VARCHAR2(100) NOT NULL,
    bairro      VARCHAR2(50) NOT NULL,
    numero      INTEGER NOT NULL,
    complemento VARCHAR2(30),
    cidade      VARCHAR2(30) NOT NULL,
    estado      VARCHAR2(20) NOT NULL,
    created_at  DATE,
    updated_at  DATE
);

ALTER TABLE tb_endereco ADD CONSTRAINT tb_endereco_pk PRIMARY KEY ( id );

CREATE TABLE tb_morador (
    id          INTEGER DEFAULT SEQ_MORADOR.nextval NOT NULL,
    nome        VARCHAR2(50) NOT NULL,
    endereco_id INTEGER NOT NULL,
    senha       VARCHAR2(100) NOT NULL,
    created_at  DATE,
    updated_at  DATE
);

ALTER TABLE tb_morador ADD CONSTRAINT tb_morador_pk PRIMARY KEY ( id );

CREATE TABLE tb_rota (
    id         INTEGER DEFAULT SEQ_ROTA.nextval NOT NULL,
    nome       VARCHAR2(50),
    created_at DATE,
    updated_at DATE
);

ALTER TABLE tb_rota ADD CONSTRAINT tb_rota_pk PRIMARY KEY ( id );

CREATE TABLE tb_rota_endereco (
    endereco_id INTEGER NOT NULL,
    rota_id     INTEGER NOT NULL
);

CREATE TABLE tb_tipo_residuo (
    id         INTEGER DEFAULT SEQ_TIPORESIDUO.nextval NOT NULL,
    nome       VARCHAR2(30),
    descricao  VARCHAR2(50),
    created_at DATE,
    updated_at DATE
);


ALTER TABLE tb_tipo_residuo ADD CONSTRAINT tipo_residuo_pk PRIMARY KEY ( id );

CREATE TABLE tb_notificacoes (
    id INTEGER DEFAULT SEQ_NOTIFICACAO.nextval NOT NULL,
    destinatario VARCHAR2(100),
    assunto      VARCHAR2(100),
    mensagem     VARCHAR2(4000),
    data_envio   DATE
);

ALTER TABLE tb_notificacoes ADD CONSTRAINT tb_notificacoes_pk PRIMARY KEY ( id );

ALTER TABLE tb_coleta_tipo_residuo
    ADD CONSTRAINT fk_tb_cole_t_resi_cole FOREIGN KEY ( coleta_id )
        REFERENCES tb_coleta ( id );

ALTER TABLE tb_coleta_tipo_residuo
    ADD CONSTRAINT fk_tb_cole_t_resi_t_resi FOREIGN KEY ( tipo_residuo_id )
        REFERENCES tb_tipo_residuo ( id );

ALTER TABLE tb_coleta
    ADD CONSTRAINT fk_tb_coleta_caminhao FOREIGN KEY ( caminhao_id )
        REFERENCES tb_caminhao ( id );

ALTER TABLE tb_coleta
    ADD CONSTRAINT fk_tb_coleta_endereco FOREIGN KEY ( endereco_id )
        REFERENCES tb_endereco ( id );

ALTER TABLE tb_coleta
    ADD CONSTRAINT fk_tb_coleta_rota FOREIGN KEY ( rota_id )
        REFERENCES tb_rota ( id );

ALTER TABLE tb_morador
    ADD CONSTRAINT fk_tb_morador_endereco FOREIGN KEY ( endereco_id )
        REFERENCES tb_endereco ( id );

ALTER TABLE tb_rota_endereco
    ADD CONSTRAINT fk_tb_rota_ende_ende FOREIGN KEY ( endereco_id )
        REFERENCES tb_endereco ( id );

ALTER TABLE tb_rota_endereco
    ADD CONSTRAINT fk_tb_rota_ende_rota FOREIGN KEY ( rota_id )
        REFERENCES tb_rota ( id );


