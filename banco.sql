CREATE DATABASE banco;

USE banco;

CREATE TABLE Ciudad (
    cod_postal DECIMAL(4) unsigned NOT NULL;
    nombre VARCHAR(32) NOT NULL;

    CONSTRAINT pk_ciudad
    PRIMARY KEY (cod_postal)
) ENGINE = InnoDB;

CREATE TABLE Sucursal (
    nro_sucursal DECIMAL(3) unsigned NOT NULL;
    nombre VARCHAR(32) NOT NULL;
    direccion VARCHAR(32) NOT NULL;
    telefono VARCHAR(10) NOT NULL;
    horario VARCHAR(32) NOT NULL;
    cod_postal DECIMAL(4) NOT NULL;

    CONSTRAINT pk_sucursal
    PRIMARY KEY (nro_sucursal)
) ENGINE = InnoDB;

CREATE TABLE Empleado (
    legajo DECIMAL(4) unsigned NOT NULL;
    apellido VARCHAR(32) NOT NULL;
    nombre VARCHAR(32) NOT NULL;
    tipo_doc VARCHAR(32) NOT NULL;
    numero_doc DECIMAL(8) unsigned NOT NULL;
    direccion VARCHAR(32) NOT NULL;
    telefono VARCHAR(32) NOT NULL;
    cargo VARCHAR(32) NOT NULL;
    password VARCHAR(32) NOT NULL;
    
    CONSTRAINT pk_empleado 
    PRIMARY KEY (legajo),

    CONSTRAINT FK_empleado_nro_suc
    FOREIGN KEY (nro_suc) REFERENCES sucursal(nro_suc)

) ENGINE = InnoDB;

CREATE TABLE Cliente (
    nro_cliente DECIMAL(5) unsigned NOT NULL;
    apellido VARCHAR(32) NOT NULL
    nombre VARCHAR(32) NOT NULL;
    tipo_doc VARCHAR(32) NOT NULL;
    numero_doc DECIMAL(8) unsigned NOT NULL;
    direccion VARCHAR(32) NOT NULL;
    telefono VARCHAR(32) NOT NULL;

    CONSTRAINT pk_cliente
    PRIMARY KEY (nro_cliente)
    
) ENGINE = InnoDB;

CREATE TABLE Plazo_Fijo (
    nro_plazo DECIMAL(8) unsigned NOT NULL;
    capital DECIMAL(65, 2) unsigned NOT NULL;
    fecha_inicio DATE NOT NULL;
    fecha_fin DATE NOT NULL;
    tasa_interes DECIMAL(65, 2) unsigned NOT NULL;
    interes DECIMAL(65, 2) AS ((capital * tasa_interes * TO_DAYS(TIMEDIFF(fecha_fin, fecha_inicio))/ 36500);

    CONSTRAINT pk_plazo_fijo 
    PRIMARY KEY (nro_plazo)

    CONSTRAINT fk_plazo_fijo_nro_suc
    FOREIGN KEY (nro_suc) REFERENCES sucursal(nro_cliente)

) ENGINE = InnoDB;

CREATE TABLE Tasa_Plazo_Fijo( 
    periodo DECIMAL(3) unsigned NOT NULL;
    monto_inf DECIMAL(65, 2) unsigned NOT NULL;
    monto_sup DECIMAL(65, 2) unsigned NOT NULL;
    tasa DECIMAL(65, 2) unsigned NOT NULL;

    CONSTRAINT pk_tasa_plazo_fijo
    PRIMARY KEY (periodo),
    PRIMARY KEY (monto_inf),
    PRIMARY KEY (monto_sup)

) ENGINE = InnoDB;

#Comentario para probar si anda el github y 
#que se modifico el archivo agregando esto.