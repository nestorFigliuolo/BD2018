
CREATE DATABASE banco;

USE banco;

CREATE TABLE Ciudad (
    cod_postal INT(4) unsigned NOT NULL,
    nombre VARCHAR(32) NOT NULL,

    CONSTRAINT pk_ciudad
    PRIMARY KEY (cod_postal)

) ENGINE = InnoDB;

CREATE TABLE Sucursal (
    nro_suc INT(3) unsigned NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(32) NOT NULL,
    direccion VARCHAR(32) NOT NULL,
    telefono VARCHAR(10) NOT NULL,
    horario VARCHAR(32) NOT NULL,
    cod_postal INT(4) unsigned NOT NULL,

    CONSTRAINT pk_sucursal
    PRIMARY KEY (nro_suc),

    CONSTRAINT fk_sucursal_ciudad
    FOREIGN KEY (cod_postal) REFERENCES Ciudad(cod_postal)
) ENGINE = InnoDB;

CREATE TABLE Empleado (
    legajo INT(4) unsigned NOT NULL AUTO_INCREMENT,
    apellido VARCHAR(32) NOT NULL,
    nombre VARCHAR(32) NOT NULL,
    tipo_doc VARCHAR(20) NOT NULL,
    nro_doc INT(8) unsigned NOT NULL,
    direccion VARCHAR(32) NOT NULL,
    telefono VARCHAR(32) NOT NULL,
    cargo VARCHAR(32) NOT NULL,
    password VARCHAR(32) NOT NULL,
    nro_suc INT(3) unsigned NOT NULL,
    
    CONSTRAINT pk_empleado 
    PRIMARY KEY (legajo),

    CONSTRAINT FK_empleado_nro_suc
    FOREIGN KEY (nro_suc) REFERENCES Sucursal(nro_suc)

) ENGINE = InnoDB;

CREATE TABLE Cliente (
    nro_cliente INT(5) unsigned NOT NULL AUTO_INCREMENT,
    apellido VARCHAR(32) NOT NULL,
    nombre VARCHAR(32) NOT NULL,
    tipo_doc VARCHAR(20) NOT NULL,
    nro_doc INT(8) unsigned NOT NULL,
    direccion VARCHAR(32) NOT NULL,
    telefono VARCHAR(32) NOT NULL,
    fecha_nac DATE NOT NULL,

    CONSTRAINT pk_cliente
    PRIMARY KEY (nro_cliente)
    
) ENGINE = InnoDB;


CREATE TABLE Plazo_Fijo (
    nro_plazo INT(8) unsigned NOT NULL AUTO_INCREMENT,
    capital DECIMAL(16, 2) unsigned NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    tasa_interes DECIMAL(4, 2) unsigned NOT NULL,
    interes DECIMAL(16, 2) unsigned AS ((capital * tasa_interes * TO_DAYS(TIMEDIFF(fecha_fin, fecha_inicio))/ 36500)) NOT NULL,
    nro_suc INT(3) unsigned NOT NULL,


    CONSTRAINT pk_plazo_fijo 
    PRIMARY KEY (nro_plazo),

    CONSTRAINT fk_plazo_fijo_nro_suc
    FOREIGN KEY (nro_suc) REFERENCES Sucursal(nro_suc)

) ENGINE = InnoDB;

CREATE TABLE Tasa_Plazo_Fijo( 
    periodo INT(3) unsigned NOT NULL,
    monto_inf DECIMAL(16, 2) unsigned NOT NULL,
    monto_sup DECIMAL(16, 2) unsigned NOT NULL,
    tasa DECIMAL(4, 2) unsigned NOT NULL,

    CONSTRAINT pk_tasa_plazo_fijo
    PRIMARY KEY (periodo,monto_inf,monto_sup)
   

) ENGINE = InnoDB;

CREATE TABLE Plazo_Cliente(
    nro_plazo INT(8) unsigned NOT NULL,
    nro_cliente INT(5) unsigned NOT NULL,
  
    CONSTRAINT pk_plazo_cliente
    PRIMARY KEY (nro_plazo,nro_cliente),
    

    CONSTRAINT fk_plazo_cliente_plazo
    FOREIGN KEY (nro_plazo) REFERENCES Plazo_Fijo(nro_plazo),

    CONSTRAINT fk_plazo_cliente_cliente
    FOREIGN KEY (nro_cliente) REFERENCES Cliente(nro_cliente)

)ENGINE = InnoDB;

CREATE TABLE Prestamo(
    nro_prestamo INT(8) unsigned NOT NULL AUTO_INCREMENT,
    fecha DATE NOT NULL,
    cant_meses INT(2) unsigned NOT NULL,
    monto DECIMAL(10,2) unsigned NOT NULL,
    tasa_interes DECIMAL(4,2) unsigned NOT NULL,
    interes DECIMAL(9,2) unsigned AS ((monto*tasa_interes*cant_meses)/1200) NOT NULL,
    valor_cuota DECIMAL(9,2) unsigned AS ((monto+interes)/cant_meses) NOT NULL,
    legajo INT(4) unsigned NOT NULL,
    nro_cliente INT(5) unsigned NOT NULL,

    CONSTRAINT pk_prestamo
    PRIMARY KEY (nro_prestamo),

    CONSTRAINT fk_prestamo_empleado
    FOREIGN KEY (legajo) REFERENCES Empleado(legajo),

    CONSTRAINT fk_prestamo_cliente
    FOREIGN KEY (nro_cliente) REFERENCES Cliente(nro_cliente)

)ENGINE = InnoDB;

CREATE TABLE Pago(
    nro_prestamo INT(8) unsigned NOT NULL,
    nro_pago INT(2) unsigned NOT NULL,
    fecha_venc DATE NOT NULL,
    fecha_pago DATE ,

    CONSTRAINT pk_pago
    PRIMARY KEY (nro_prestamo,nro_pago),

    CONSTRAINT fk_pago_prestamo
    FOREIGN KEY (nro_prestamo) REFERENCES Prestamo(nro_prestamo)

)ENGINE = InnoDB;

CREATE TABLE Tasa_Prestamo(
	periodo INT(3) unsigned NOT NULL,
	monto_inf DECIMAL(10,2) unsigned NOT NULL,
	monto_sup DECIMAL(10,2) unsigned NOT NULL,
	tasa DECIMAL(4,2) unsigned NOT NULL,

	CONSTRAINT pk_tasa_prestamo
	PRIMARY KEY (periodo,monto_inf,monto_sup)

)ENGINE = InnoDB;

CREATE TABLE Caja_Ahorro(
    nro_ca INT(8) unsigned NOT NULL AUTO_INCREMENT,
    CBU BIGINT(18) unsigned  NOT NULL,
    saldo DECIMAL(16,2) unsigned NOT NULL,

    CONSTRAINT pk_caja_ahorro
    PRIMARY KEY (nro_ca)

)ENGINE = InnoDB;

CREATE TABLE Cliente_CA(
    nro_cliente INT(5) unsigned NOT NULL,
    nro_ca INT(8) unsigned NOT NULL,

    CONSTRAINT pk_cliente_ca
    PRIMARY KEY (nro_cliente,nro_ca),

    CONSTRAINT fk_cliente_ca_cliente
    FOREIGN KEY (nro_cliente) REFERENCES Cliente(nro_cliente),

    CONSTRAINT fk_cliente_ca_ca
    FOREIGN KEY (nro_ca) REFERENCES Caja_Ahorro(nro_ca)

)ENGINE = InnoDB;

CREATE TABLE Tarjeta(
    nro_tarjeta BIGINT(16) unsigned NOT NULL AUTO_INCREMENT,
    PIN VARCHAR(32) NOT NULL,
    CVT VARCHAR(32) NOT NULL,
    fecha_venc DATE NOT NULL,
    nro_cliente INT(5) unsigned NOT NULL,
    nro_ca INT(8) unsigned NOT NULL,

    CONSTRAINT pk_tarjeta
    PRIMARY KEY (nro_tarjeta),

    CONSTRAINT fk_tarjeta_cliente_ca
    FOREIGN KEY (nro_cliente,nro_ca) REFERENCES Cliente_CA(nro_cliente,nro_ca)

)ENGINE = InnoDB;

CREATE TABLE Caja(
    cod_caja INT(5) unsigned NOT NULL AUTO_INCREMENT,

    CONSTRAINT pk_caja
    PRIMARY KEY (cod_caja) 

)ENGINE = InnoDB;


CREATE TABLE Ventanilla(
    cod_caja INT(5) unsigned NOT NULL,
    nro_suc INT(3) unsigned NOT NULL,

    CONSTRAINT pk_ventanilla
    PRIMARY KEY (cod_caja),

    CONSTRAINT fk_ventanilla_caja
    FOREIGN KEY (cod_caja) REFERENCES Caja(cod_caja),

    CONSTRAINT fk_ventanilla_sucursal
    FOREIGN KEY (nro_suc) REFERENCES Sucursal(nro_suc)

)ENGINE = InnoDB;

CREATE TABLE ATM(
	cod_caja INT(5) unsigned NOT NULL,
	cod_postal INT(4) unsigned NOT NULL,
    direccion VARCHAR(60) NOT NULL,

    CONSTRAINT pk_ATM
    PRIMARY KEY (cod_caja),

    CONSTRAINT fk_ATM_caja
    FOREIGN KEY (cod_caja) REFERENCES Caja(cod_caja),

    CONSTRAINT fk_ATM_ciudad
    FOREIGN KEY (cod_postal) REFERENCES Ciudad(cod_postal)


)ENGINE = InnoDB;

CREATE TABLE Transaccion(
	nro_trans INT(10) unsigned NOT NULL AUTO_INCREMENT,
	fecha DATE NOT NULL,
	hora TIME NOT NULL,
	monto DECIMAL(16,2) unsigned NOT NULL,

	CONSTRAINT pk_transaccion
	PRIMARY KEY (nro_trans)

)ENGINE = InnoDB;

CREATE TABLE Debito(
    nro_trans INT(10) unsigned NOT NULL,
    descripcion TEXT(100) ,
    nro_cliente INT(5) unsigned NOT NULL,
    nro_ca INT(8) unsigned NOT NULL,

    CONSTRAINT pk_debito
    PRIMARY KEY (nro_trans),

    CONSTRAINT fk_debito_transaccion
    FOREIGN KEY (nro_trans) REFERENCES Transaccion(nro_trans),

    CONSTRAINT fk_debito_cliente_ca
    FOREIGN KEY (nro_cliente,nro_ca) REFERENCES Cliente_CA(nro_cliente,nro_ca)

)ENGINE = InnoDB;



CREATE TABLE Transaccion_por_caja(
	 nro_trans INT(10) unsigned NOT NULL,
	 cod_caja INT(5) unsigned NOT NULL,

	 CONSTRAINT pk_transaccion_por_caja
	 PRIMARY KEY (nro_trans),

	 CONSTRAINT fk_transaccion_por_caja_transaccion
	 FOREIGN KEY (nro_trans) REFERENCES Transaccion(nro_trans),

	 CONSTRAINT fk_transaccion_por_caja_caja
	 FOREIGN KEY (cod_caja) REFERENCES Caja(cod_caja)

)ENGINE = InnoDB;

CREATE TABLE Deposito(
	nro_trans INT(10) unsigned NOT NULL,
	nro_ca INT(8) unsigned NOT NULL,

    CONSTRAINT pk_deposito
    PRIMARY KEY (nro_trans),

    CONSTRAINT fk_deposito_transaccion
    FOREIGN KEY (nro_trans) REFERENCES Transaccion_por_caja(nro_trans),

    CONSTRAINT fk_deposito_caja_ahorro
    FOREIGN KEY (nro_ca) REFERENCES Caja_Ahorro(nro_ca)


)ENGINE = InnoDB;

CREATE TABLE Extraccion(
	nro_trans INT(10) unsigned NOT NULL,
	nro_cliente INT(5) unsigned NOT NULL,
    nro_ca INT(8) unsigned NOT NULL,

    CONSTRAINT pk_extraccion
    PRIMARY KEY (nro_trans),
    
    CONSTRAINT fk_extraccion_transaccion
    FOREIGN KEY (nro_trans) REFERENCES Transaccion_por_caja(nro_trans),

    CONSTRAINT fk_extraccion_cliente_ca
    FOREIGN KEY (nro_cliente,nro_ca) REFERENCES Cliente_CA(nro_cliente,nro_ca)

)ENGINE = InnoDB;

CREATE TABLE Transferencia(
	nro_trans INT(10) unsigned NOT NULL,
	nro_cliente INT(5) unsigned NOT NULL,
	origen INT(8) unsigned NOT NULL,
	destino INT(8) unsigned NOT NULL,

    CONSTRAINT pk_transferencia
	PRIMARY KEY (nro_trans),

    CONSTRAINT fk_transferencia_transaccion
    FOREIGN KEY (nro_trans) REFERENCES Transaccion_por_caja(nro_trans),

    CONSTRAINT fk_transferencia_caja_origen
    FOREIGN KEY (nro_cliente,origen) REFERENCES Cliente_CA(nro_cliente,nro_ca),

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


/*CREATE VIEW trans_cajas_ahorro AS 
    SELECT
        ca.nro_ca, ca.saldo,
        t.nro_trans, t.fecha ,t.hora,t.monto,caja.cod_caja,t.destino,
        c.nro_cliente, c.tipo_doc, c.nro_doc, c.nombre, c.apellido
    FROM 
        (Caja_Ahorro as ca JOIN 
        Transaccion as t JOIN
        Cliente as c);
*/

CREATE VIEW trans_cajas_ahorro AS 
 
 (SELECT
 	    ca.nro_ca, ca.saldo,
        t.nro_trans, t.fecha ,t.hora,"Deposito" as Tipo,t.monto,tpc.cod_caja,
        c.nro_cliente, c.tipo_doc, c.nro_doc, c.nombre, c.apellido
    FROM 
        (Caja_Ahorro as ca JOIN 
        Transaccion as t JOIN 
        Transaccion_por_caja as tpc JOIN
        Cliente as c JOIN
        Debito  
        ))

      UNION

 (SELECT
 	    ca.nro_ca, ca.saldo,
        t.nro_trans, t.fecha ,t.hora,"Transferencia",t.monto,tpc.cod_caja,
        c.nro_cliente, c.tipo_doc, c.nro_doc, c.nombre, c.apellido
    FROM 
        (Caja_Ahorro as ca JOIN 
        Transaccion as t JOIN 
        Transaccion_por_caja as tpc JOIN
        Cliente as c JOIN
        Debito  
        ))

 ;


     

GRANT SELECT ON trans_cajas_ahorro TO 'atm'@'%';





