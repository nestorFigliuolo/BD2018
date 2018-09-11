CREATE DATABASE banco;

USE banco;

CREATE TABLE Ciudad (
    cod_postal DECIMAL(4) unsigned NOT NULL,
    nombre VARCHAR(32) NOT NULL,

    CONSTRAINT pk_ciudad
    PRIMARY KEY (cod_postal)

) ENGINE = InnoDB;

CREATE TABLE Sucursal (
    nro_suc DECIMAL(3) unsigned NOT NULL,
    nombre VARCHAR(32) NOT NULL,
    direccion VARCHAR(32) NOT NULL,
    telefono VARCHAR(10) NOT NULL,
    horario VARCHAR(32) NOT NULL,
    cod_postal DECIMAL(4) NOT NULL,

    CONSTRAINT pk_sucursal
    PRIMARY KEY (nro_suc)
) ENGINE = InnoDB;

CREATE TABLE Empleado (
    legajo DECIMAL(4) unsigned NOT NULL,
    apellido VARCHAR(32) NOT NULL,
    nombre VARCHAR(32) NOT NULL,
    tipo_doc VARCHAR(32) NOT NULL,
    numero_doc DECIMAL(8) unsigned NOT NULL,
    direccion VARCHAR(32) NOT NULL,
    telefono VARCHAR(32) NOT NULL,
    cargo VARCHAR(32) NOT NULL,
    password VARCHAR(32) NOT NULL,
    nro_suc DECIMAL(3) unsigned NOT NULL,
    
    CONSTRAINT pk_empleado 
    PRIMARY KEY (legajo),

    CONSTRAINT FK_empleado_nro_suc
    FOREIGN KEY (nro_suc) REFERENCES Sucursal(nro_suc)

) ENGINE = InnoDB;

CREATE TABLE Cliente (
    nro_cliente DECIMAL(5) unsigned NOT NULL,
    apellido VARCHAR(32) NOT NULL,
    nombre VARCHAR(32) NOT NULL,
    tipo_doc VARCHAR(32) NOT NULL,
    numero_doc DECIMAL(8) unsigned NOT NULL,
    direccion VARCHAR(32) NOT NULL,
    telefono VARCHAR(32) NOT NULL,

    CONSTRAINT pk_cliente
    PRIMARY KEY (nro_cliente)
    
) ENGINE = InnoDB;


CREATE TABLE Plazo_Fijo (
    nro_plazo DECIMAL(8) unsigned NOT NULL,
    capital DECIMAL(65, 2) unsigned NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    tasa_interes DECIMAL(65, 2) unsigned NOT NULL,
    interes DECIMAL(65, 2) AS ((capital * tasa_interes * TO_DAYS(TIMEDIFF(fecha_fin, fecha_inicio))/ 36500)),
     nro_suc DECIMAL(3) unsigned NOT NULL,


    CONSTRAINT pk_plazo_fijo 
    PRIMARY KEY (nro_plazo),

    CONSTRAINT fk_plazo_fijo_nro_suc
    FOREIGN KEY (nro_suc) REFERENCES Sucursal(nro_suc)

) ENGINE = InnoDB;

CREATE TABLE Tasa_Plazo_Fijo( 
    periodo DECIMAL(3) unsigned NOT NULL,
    monto_inf DECIMAL(65, 2) unsigned NOT NULL,
    monto_sup DECIMAL(65, 2) unsigned NOT NULL,
    tasa DECIMAL(65, 2) unsigned NOT NULL,

    CONSTRAINT pk_tasa_plazo_fijo
    PRIMARY KEY (periodo,monto_inf,monto_sup)
   

) ENGINE = InnoDB;

CREATE TABLE Plazo_Cliente(
    nro_plazo DECIMAL(8) unsigned NOT NULL,
    nro_cliente DECIMAL(5) unsigned NOT NULL,
  
    CONSTRAINT pk_plazo_cliente
    PRIMARY KEY (nro_plazo,nro_cliente),
    

    CONSTRAINT fk_plazo_cliente_plazo
    FOREIGN KEY (nro_plazo) REFERENCES Plazo_Fijo(nro_plazo),

    CONSTRAINT fk_plazo_cliente_cliente
    FOREIGN KEY (nro_cliente) REFERENCES Cliente(nro_cliente)

)ENGINE = InnoDB;

CREATE TABLE Prestamo(
    nro_prestamo DECIMAL(8) unsigned NOT NULL,
    fecha DATE NOT NULL,
    cant_meses DECIMAL(2) unsigned NOT NULL,
    monto DECIMAL(65,2) unsigned NOt NULL,
    tasa_interes DECIMAL(65,2) unsigned NOt NULL,
    interes DECIMAL(65,2) AS ((monto*tasa_interes*cant_meses)/1200),
    valor_cuota DECIMAL(65,2) AS ((monto+interes)/cant_meses),
    legajo DECIMAL(4) unsigned NOT NULL,
    nro_cliente DECIMAL(5) unsigned NOT NULL,

    CONSTRAINT pk_prestamo
    PRIMARY KEY (nro_prestamo),

    CONSTRAINT fk_prestamo_empleado
    FOREIGN KEY (legajo) REFERENCES Empleado(legajo),

    CONSTRAINT fk_prestamo_cliente
    FOREIGN KEY (nro_cliente) REFERENCES Cliente(nro_cliente)

)ENGINE = InnoDB;

CREATE TABLE Pago(
    nro_prestamo DECIMAL(8) unsigned NOT NULL,
    nro_pago DECIMAL(2) unsigned NOT NULL,
    fecha_venc DATE NOT NULL,
    fecha_pago DATE NOT NULL,

    CONSTRAINT pk_pago
    PRIMARY KEY (nro_prestamo,nro_pago),

    CONSTRAINT fk_pago_prestamo
    FOREIGN KEY (nro_prestamo) REFERENCES Prestamo(nro_prestamo)

)ENGINE = InnoDB;

CREATE TABLE Tasa_Prestamo(
	periodo DECIMAL(3) unsigned NOT NULL,
	monto_inf DECIMAL(65,2) unsigned NOT NULL,
	monto_sup DECIMAL(65,2) unsigned NOT NULL,
	tasa DECIMAL(65,2) unsigned NOT NULL,

	CONSTRAINT pk_tasa_prestamo
	PRIMARY KEY (periodo,monto_inf,monto_sup)

)ENGINE = InnoDB;

CREATE TABLE Caja_Ahorro(
    nro_ca DECIMAL(8) unsigned NOT NULL,
    CBU DECIMAL(18) unsigned  NOT NULL,
    saldo DECIMAL(65,2) unsigned NOT NULL,

    CONSTRAINT pk_caja_ahorro
    PRIMARY KEY (nro_ca)

)ENGINE = InnoDB;

CREATE TABLE Cliente_CA(
    nro_cliente DECIMAL(5) unsigned NOT NULL,
    nro_ca DECIMAL(8) unsigned NOT NULL,

    CONSTRAINT pk_cliente_ca
    PRIMARY KEY (nro_cliente,nro_ca),

    CONSTRAINT fk_cliente_ca_cliente
    FOREIGN KEY (nro_cliente) REFERENCES Cliente(nro_cliente),

    CONSTRAINT fk_cliente_ca_ca
    FOREIGN KEY (nro_ca) REFERENCES Caja_Ahorro(nro_ca)

)ENGINE = InnoDB;

CREATE TABLE Tarjeta(
    nro_tarjeta DECIMAL(16) unsigned NOT NULL,
    PIN VARCHAR(32) NOT NULL,
    CVT VARCHAR(32) NOT NULL,
    fecha_venc DATE NOT NULL,
    nro_cliente DECIMAL(5) unsigned NOT NULL,
    nro_ca DECIMAL(8) unsigned NOT NULL,

    CONSTRAINT pk_tarjeta
    PRIMARY KEY (nro_tarjeta),

    CONSTRAINT fk_tarjeta_cliente
    FOREIGN KEY (nro_cliente) REFERENCES Cliente(nro_cliente),

    CONSTRAINT fk_tarjeta_ca
    FOREIGN KEY (nro_ca) REFERENCES Caja_Ahorro(nro_ca)

)ENGINE = InnoDB;

CREATE TABLE Caja(
    cod_caja DECIMAL(5) unsigned NOT NULL,

    CONSTRAINT pk_caja
    PRIMARY KEY (cod_caja) 

)ENGINE = InnoDB;


CREATE TABLE Ventanilla(
    cod_caja DECIMAL(5) unsigned NOT NULL,
    nro_suc DECIMAL(3) unsigned NOT NULL,

    CONSTRAINT pk_ventanilla
    PRIMARY KEY (cod_caja),

    CONSTRAINT fk_ventanilla_caja
    FOREIGN KEY (cod_caja) REFERENCES Caja(cod_caja),

    CONSTRAINT fk_ventanilla_sucursal
    FOREIGN KEY (nro_suc) REFERENCES Sucursal(nro_suc)

)ENGINE = InnoDB;

CREATE TABLE ATM(
	cod_caja DECIMAL(5) unsigned NOT NULL,
	cod_postal DECIMAL(4) unsigned NOT NULL,
    direccion VARCHAR(60) NOT NULL,

    CONSTRAINT pk_ATM
    PRIMARY KEY (cod_caja),

    CONSTRAINT fk_ATM_caja
    FOREIGN KEY (cod_caja) REFERENCES Caja(cod_caja),

    CONSTRAINT fk_ATM_ciudad
    FOREIGN KEY (cod_postal) REFERENCES Ciudad(cod_postal)


)ENGINE = InnoDB;

CREATE TABLE Transaccion(
	nro_trans DECIMAL(10) unsigned NOT NULL,
	fecha DATE NOT NULL,
	hora VARCHAR(32) NOT NULL,
	monto DECIMAL(65,2) unsigned NOT NULL,

	CONSTRAINT pk_transaccion
	PRIMARY KEY (nro_trans)

)ENGINE = InnoDB;

CREATE TABLE Debito(
    nro_trans DECIMAL(10) unsigned NOT NULL,
    descripcion VARCHAR(100) NOT NULL,
    nro_cliente DECIMAL(5) unsigned NOT NULL,
    nro_ca DECIMAL(8) unsigned NOT NULL,

    CONSTRAINT pk_debito
    PRIMARY KEY (nro_trans),

    CONSTRAINT fk_debito_transaccion
    FOREIGN KEY (nro_trans) REFERENCES Transaccion(nro_trans),

    CONSTRAINT fk_debito_cliente
    FOREIGN KEY (nro_cliente) REFERENCES Cliente(nro_cliente),

    CONSTRAINT fk_debito_ca
    FOREIGN KEY (nro_ca) REFERENCES Caja_Ahorro(nro_ca)




)ENGINE = InnoDB;



CREATE TABLE Transaccion_por_caja(
	 nro_trans DECIMAL(10) unsigned NOT NULL,
	 cod_caja DECIMAL(5) unsigned NOT NULL,

	 CONSTRAINT pk_transaccion_por_caja
	 PRIMARY KEY (nro_trans),

	 CONSTRAINT fk_transaccion_por_caja_transaccion
	 FOREIGN KEY (nro_trans) REFERENCES Transaccion(nro_trans),

	 CONSTRAINT fk_transaccion_por_caja_caja
	 FOREIGN KEY (cod_caja) REFERENCES Caja(cod_caja)

)ENGINE = InnoDB;

CREATE TABLE Deposito(
	nro_trans DECIMAL(10) unsigned NOT NULL,
	nro_ca DECIMAL(8) unsigned NOT NULL,

    CONSTRAINT pk_deposito
    PRIMARY KEY (nro_trans),

    CONSTRAINT fk_deposito_transaccion
    FOREIGN KEY (nro_trans) REFERENCES Transaccion(nro_trans),

    CONSTRAINT fk_deposito_caja_ahorro
    FOREIGN KEY (nro_ca) REFERENCES Caja_Ahorro(nro_ca)


)ENGINE = InnoDB;

CREATE TABLE Extraccion(
	nro_trans DECIMAL(10) unsigned NOT NULL,
	nro_cliente DECIMAL(5) unsigned NOT NULL,
    nro_ca DECIMAL(8) unsigned NOT NULL,

    CONSTRAINT pk_extraccion
    PRIMARY KEY (nro_trans),
    
    CONSTRAINT fk_extraccion_transaccion
    FOREIGN KEY (nro_trans) REFERENCES Transaccion(nro_trans),

    CONSTRAINT fk_extraccion_cliente
    FOREIGN KEY (nro_cliente) REFERENCES Cliente(nro_cliente),

    CONSTRAINT fk_extraccion_caja_ahorro
    FOREIGN KEY (nro_ca) REFERENCES Caja_Ahorro(nro_ca)


)ENGINE = InnoDB;

CREATE TABLE Transferencia(
	nro_trans DECIMAL(10) unsigned NOT NULL,
	nro_cliente DECIMAL(5) unsigned NOT NULL,
	origen DECIMAL(8) unsigned NOT NULL,
	destino DECIMAL(8) unsigned NOT NULL,

    CONSTRAINT pk_transferencia
	PRIMARY KEY (nro_trans),

    CONSTRAINT fk_transferencia_transaccion
    FOREIGN KEY (nro_trans) REFERENCES Transaccion(nro_trans),
    
    CONSTRAINT fk_transferencia_cliente
    FOREIGN KEY (nro_cliente) REFERENCES Cliente(nro_cliente),

    CONSTRAINT fk_transferencia_caja_origen
    FOREIGN KEY (origen) REFERENCES Caja_Ahorro(nro_ca),

    CONSTRAINT fk_transferencia_caja_destino
    FOREIGN KEY (destino) REFERENCES Caja_Ahorro(nro_ca)

)ENGINE = InnoDB;

########################################################################################
########################################################################################
#-----------------------------------Usuarios-------------------------------------------#
########################################################################################
########################################################################################

#Creo el usuario admin que tiene acceso a todas las tablas de la base de datos
CREATE USER 'admin_banco'@'localhost' IDENTIFIED BY 'admin';

#Privilegios
GRANT ALL PRIVILEGES ON banco.* TO 'admin_banco'@'localhost' WITH GRANT OPTION;

#############################################################################

CREATE USER 'empleado'@'%'  IDENTIFIED BY 'empleado';

GRANT SELECT ON banco.Empleado TO 'empleado'@'%';

GRANT SELECT ON banco.Sucursal TO 'empleado'@'%';

GRANT SELECT ON banco.Tasa_Plazo_Fijo TO 'empleado'@'%';

GRANT SELECT ON banco.Tasa_Prestamo TO 'empleado'@'%';

GRANT SELECT ON banco.Prestamo TO 'empleado'@'%';
GRANT INSERT ON banco.Prestamo TO 'empleado'@'%';

GRANT SELECT ON banco.Plazo_Fijo TO 'empleado'@'%';
GRANT INSERT ON banco.Plazo_Fijo TO 'empleado'@'%';

GRANT SELECT ON banco.Plazo_Cliente TO 'empleado'@'%';
GRANT INSERT ON banco.Plazo_Cliente TO 'empleado'@'%';

GRANT SELECT ON banco.Caja_Ahorro TO 'empleado'@'%';
GRANT INSERT ON banco.Caja_Ahorro TO 'empleado'@'%';
GRANT UPDATE ON banco.Caja_Ahorro TO 'empleado'@'%';

GRANT SELECT ON banco.Tarjeta TO 'empleado'@'%';
GRANT INSERT ON banco.Tarjeta TO 'empleado'@'%';

GRANT SELECT ON banco.Cliente_CA TO 'empleado'@'%';
GRANT INSERT ON banco.Cliente_CA TO 'empleado'@'%';
GRANT UPDATE ON banco.Cliente_CA TO 'empleado'@'%';

#############################################################################

CREATE USER 'atm'@'%' IDENTIFIED BY 'atm';

CREATE VIEW banco.trans_cajas_ahorro AS 
    SELECT
        ca.nro_ca, ca.saldo,
        t.nro_trans, t.fecha hora,
        c.nro_cliente, c.tipo_doc, c.numero_doc, c.nombre, c.apellido
    FROM 
        Caja_Ahorro as ca JOIN 
        Transaccion as t JOIN
        Cliente as c;

GRANT SELECT ON banco.trans_cajas_ahorro TO 'atm'@'%';





