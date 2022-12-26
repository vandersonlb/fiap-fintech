-- Gerado por Oracle SQL Developer Data Modeler 21.4.1.349.1605
--   em:        2022-11-11 20:18:43 BRT
--   site:      Oracle Database 11g
--   tipo:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE t_ft_categoria (
    cd_categoria NUMBER(2) NOT NULL,
    cd_grupo     NUMBER(2) NOT NULL,
    nm_categoria VARCHAR2(30 CHAR)
);

ALTER TABLE t_ft_categoria ADD CONSTRAINT pk_ft_categoria PRIMARY KEY ( cd_categoria );

ALTER TABLE t_ft_categoria ADD CONSTRAINT un_ft_nm_categoria UNIQUE ( nm_categoria );

CREATE TABLE t_ft_conta (
    nr_conta NUMBER(6) NOT NULL,
    nr_cpf   NUMBER(11) NOT NULL,
    nm_conta VARCHAR2(20 CHAR) NOT NULL,
    vl_saldo NUMBER(10, 2) DEFAULT 0 NOT NULL
);

ALTER TABLE t_ft_conta ADD CONSTRAINT pk_ft_conta PRIMARY KEY ( nr_conta );

CREATE TABLE t_ft_grupo_categoria (
    cd_grupo NUMBER(2) NOT NULL,
    nm_grupo VARCHAR2(30 CHAR) NOT NULL
);

ALTER TABLE t_ft_grupo_categoria ADD CONSTRAINT pk_ft_grupo_categoria PRIMARY KEY ( cd_grupo );

ALTER TABLE t_ft_grupo_categoria ADD CONSTRAINT un_ft_nm_grupo UNIQUE ( nm_grupo );

CREATE TABLE t_ft_investimento (
    nr_conta        NUMBER(6) NOT NULL,
    cd_investimento NUMBER(2) NOT NULL,
    nm_investimento VARCHAR2(20 CHAR) NOT NULL,
    vl_saldo        NUMBER(10, 2) DEFAULT 0 NOT NULL,
    vl_meta         NUMBER(10, 2) DEFAULT 0 NOT NULL
);

ALTER TABLE t_ft_investimento ADD CONSTRAINT pk_ft_investimento PRIMARY KEY ( cd_investimento );

CREATE TABLE t_ft_tipo (
    cd_tipo NUMBER(1) NOT NULL,
    nm_tipo VARCHAR2(15 CHAR) NOT NULL
);

ALTER TABLE t_ft_tipo ADD CONSTRAINT pk_ft_tipo PRIMARY KEY ( cd_tipo );

CREATE TABLE t_ft_transacao (
    nr_conta        NUMBER(6) NOT NULL,
    sq_transacao    NUMBER(5) NOT NULL,
    nm_transacao    VARCHAR2(20 CHAR) NOT NULL,
    cd_investimento NUMBER(2),
    cd_tipo         NUMBER(1) NOT NULL,
    vl_transacao    NUMBER(10, 2) NOT NULL,
    dt_transacao    DATE NOT NULL,
    cd_categoria    NUMBER(2) NOT NULL,
    ob_transacao    VARCHAR2(60 CHAR)
);

ALTER TABLE t_ft_transacao ADD CONSTRAINT ck_ft_vl_transacao CHECK ( vl_transacao > 0 );

ALTER TABLE t_ft_transacao ADD CONSTRAINT pk_ft_transacao PRIMARY KEY ( nr_conta,
                                                                        sq_transacao );

CREATE TABLE t_ft_usuario (
    nr_cpf        NUMBER(11) NOT NULL,
    nm_completo   VARCHAR2(30 CHAR) NOT NULL,
    dt_nascimento DATE NOT NULL,
    ds_email      VARCHAR2(30 CHAR) NOT NULL,
    nr_celular    VARCHAR2(11 CHAR) NOT NULL,
    ds_senha      VARCHAR2(50 CHAR) NOT NULL
);


ALTER TABLE t_ft_usuario ADD CONSTRAINT pk_ft_usuario PRIMARY KEY ( nr_cpf );

ALTER TABLE t_ft_usuario ADD CONSTRAINT un_ft_email UNIQUE ( ds_email );

ALTER TABLE t_ft_usuario ADD CONSTRAINT un_ft_celular UNIQUE ( nr_celular );

ALTER TABLE t_ft_categoria
    ADD CONSTRAINT fk_ft_categoria_grupo FOREIGN KEY ( cd_grupo )
        REFERENCES t_ft_grupo_categoria ( cd_grupo );

ALTER TABLE t_ft_conta
    ADD CONSTRAINT fk_ft_conta_usuario FOREIGN KEY ( nr_cpf )
        REFERENCES t_ft_usuario ( nr_cpf );

ALTER TABLE t_ft_investimento
    ADD CONSTRAINT fk_ft_investimento_conta FOREIGN KEY ( nr_conta )
        REFERENCES t_ft_conta ( nr_conta );

ALTER TABLE t_ft_transacao
    ADD CONSTRAINT fk_ft_transacao_categoria FOREIGN KEY ( cd_categoria )
        REFERENCES t_ft_categoria ( cd_categoria );

ALTER TABLE t_ft_transacao
    ADD CONSTRAINT fk_ft_transacao_conta FOREIGN KEY ( nr_conta )
        REFERENCES t_ft_conta ( nr_conta );

ALTER TABLE t_ft_transacao
    ADD CONSTRAINT fk_ft_transacao_investimento FOREIGN KEY ( cd_investimento )
        REFERENCES t_ft_investimento ( cd_investimento );

ALTER TABLE t_ft_transacao
    ADD CONSTRAINT fk_ft_transacao_tipo FOREIGN KEY ( cd_tipo )
        REFERENCES t_ft_tipo ( cd_tipo );

CREATE SEQUENCE SQ_FT_CD_CATEGORIA 
START WITH 1 
    MAXVALUE 99 
    NOCACHE 
    ORDER ;

CREATE OR REPLACE TRIGGER T_FT_CATEGORIA_cd_categoria 
BEFORE INSERT ON T_FT_CATEGORIA 
FOR EACH ROW 
WHEN (NEW.cd_categoria IS NULL) 
BEGIN
:new.cd_categoria := sq_ft_cd_categoria.nextval;

end;
/

CREATE SEQUENCE SQ_FT_NR_CONTA 
START WITH 123456 
    MINVALUE 123456 
    MAXVALUE 999999 
    NOCACHE 
    ORDER ;

CREATE OR REPLACE TRIGGER T_FT_CONTA_nr_conta_TRG 
BEFORE INSERT ON T_FT_CONTA 
FOR EACH ROW 
WHEN (NEW.nr_conta IS NULL) 
BEGIN
:new.nr_conta := sq_ft_nr_conta.nextval;

end;
/

CREATE SEQUENCE SQ_FT_CD_GRUPO 
START WITH 1 
    MAXVALUE 99 
    NOCACHE 
    ORDER ;

CREATE OR REPLACE TRIGGER T_FT_GRUPO_CATEGORIA_cd_grupo 
BEFORE INSERT ON T_FT_GRUPO_CATEGORIA 
FOR EACH ROW 
WHEN (NEW.cd_grupo IS NULL) 
BEGIN
:new.cd_grupo := sq_ft_cd_grupo.nextval;

end;
/

CREATE SEQUENCE SQ_FT_CD_INVESTIMENTO 
START WITH 10 
    MINVALUE 10 
    MAXVALUE 99 
    NOCACHE 
    ORDER ;

CREATE OR REPLACE TRIGGER T_FT_INVESTIMENTO_cd_investime 
BEFORE INSERT ON T_FT_INVESTIMENTO 
FOR EACH ROW 
WHEN (NEW.cd_investimento IS NULL) 
BEGIN
:new.cd_investimento := sq_ft_cd_investimento.nextval;

end;
/

CREATE SEQUENCE SQ_FT_CD_TIPO 
START WITH 1 
    MINVALUE 1 
    MAXVALUE 9 
    NOCACHE 
    ORDER ;

CREATE OR REPLACE TRIGGER T_FT_TIPO_cd_tipo_TRG 
BEFORE INSERT ON T_FT_TIPO 
FOR EACH ROW 
WHEN (NEW.cd_tipo IS NULL) 
BEGIN
:new.cd_tipo := sq_ft_cd_tipo.nextval;

end;
/

CREATE SEQUENCE SQ_FT_TRANSACAO 
START WITH 1 
    MINVALUE 1 
    MAXVALUE 99999 
    NOCACHE 
    ORDER ;

CREATE OR REPLACE TRIGGER T_FT_TRANSACAO_sq_transacao 
BEFORE INSERT ON T_FT_TRANSACAO 
FOR EACH ROW 
WHEN (NEW.sq_transacao IS NULL) 
BEGIN
:new.sq_transacao := sq_ft_transacao.nextval;

end;
/



-- Relatório do Resumo do Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                             7
-- CREATE INDEX                             0
-- ALTER TABLE                             20
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           6
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          6
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
