
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
    telefono VARCHAR(32) NOT NULL,
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
    interes DECIMAL(16, 2) unsigned AS ((capital * tasa_interes * (DATEDIFF(fecha_fin, fecha_inicio)))/ 36500) NOT NULL,
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
    nro_prestamo INT(8) unsigned NOT NULL ,
    nro_pago INT(2) unsigned NOT NULL ,
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





#############################################################################
# Vista trans_cajas_ahorro  que es utilizada por el usuario ATM
#############################################################################


CREATE VIEW trans_cajas_ahorro AS 
 
   SELECT t.fecha,t.hora,t.monto,TPCYDYC.*

  	    FROM 
  	        Transaccion as t ,
  	                    (#Realizo la union de los 4 tipos de transacciones para luego 
  	                     #hacer el producto cartesiano con la transaccion	

  		                      (SELECT DETYC.*, tpc.cod_caja

	  	                            FROM 
	                                    Transaccion_por_caja as tpc ,
	                                                               (#Realizo las uniones de las transacciones que son por caja para luego 
	                                                               	#hacer el producto cartesiono con "Transaccion_por_caja"
	    	                                                        
	    	                                                             (SELECT "--" as tipo_doc, "--" as nro_doc,
	    	                                                                     "--" as apellido, "--" as nombre ,
	                                                                             "Deposito" as Tipo ,"--" as nro_cliente,
	                                                                              depo.*,ca.saldo,"--" as destino
	                                                           
	                                                                          FROM Deposito as depo,
	                                                                          Caja_Ahorro as ca
	                                                                      
	                                                                       WHERE
	          	                                                           depo.nro_ca = ca.nro_ca 
	   	 	                                                             )

	   		                                                          UNION 

	   		                                                             (SELECT c.tipo_doc, c.nro_doc, c.apellido,
	   		                                                                     c.nombre, EYT.*

	   			                                                               FROM Cliente as c, 
	   			                                                                              ( #Uno las transacciones Extraccion y transferecia
	   			                                                                                #que necesitan conectarse con el cliente  
			   		                                                                             
			   		                                                                              (SELECT "Extraccion", Extra.nro_cliente,Extra.nro_trans,
			   		                                                                                      Extra.nro_ca,ca.saldo,"--" as destino 
			                                                                                          
			                                                                                          FROM Extraccion as Extra,
			          	                                                                                   Caja_Ahorro as ca
			                                                                                          
			                                                                                        WHERE
			          	                                                                            Extra.nro_ca = ca.nro_ca
			   	 	                                                                              )
			    	                                                                            
			    	                                                                            UNION

			    	                                                                              (SELECT "Transferencia",t.nro_cliente,t.nro_trans,
			    	                                                                              	       t.origen,ca.saldo,t.destino 
						                                                                           
						                                                                              FROM Transferencia as t,
						                                                                                   Caja_Ahorro as ca
						                                                                             
						                                                                             WHERE
							                                                                         t.origen = ca.nro_ca
			    	                                                                               )
		    	                                                                              
		    	                                                                              )EYT

		                                                                       WHERE
		    	                                                               c.nro_cliente = EYT.nro_cliente
		                                                                 )

	                                                                )DETYC
	    
	                              WHERE 
	    	                      tpc.nro_trans = DETYC.nro_trans
		                      
		                      ) 

                            UNION

    	                     (SELECT c.tipo_doc, c.nro_doc, c.apellido, c.nombre, D.*,"--"

	   	                          FROM Cliente as c, 
	     	                                      (SELECT "Debito",deb.nro_cliente,deb.nro_trans,deb.nro_ca,ca.saldo,"--"
	                                                 
	                                                   FROM Debito as deb,
	        	                                            Caja_Ahorro as ca
	                                                  WHERE
	        	                                      deb.nro_ca = ca.nro_ca
	        	                                  )D
	                             WHERE
	    	                     c.nro_cliente = D.nro_cliente
		                    )

                        )TPCYDYC
       WHERE 
  	   t.nro_trans = TPCYDYC.nro_trans; 

########################################################################################################

    


###########################################################################################
###########################Store procedures################################################
###########################################################################################

delimiter !
  
   create procedure transferencia(in monto DECIMAL(16,2),in nro_tarjeta BIGINT(16),in destino INT(8),in cod_caja INT(5)) 
   	  begin
	     
          #Declaro variables que utilizo 
            declare saldo_caja_origen DECIMAL(16,2);
	        declare cliente INT(5);
	        declare cajaOrigen INT(8);
	       
	        declare exit handler for sqlexception
		         BEGIN # Si se produce una SQLEXCEPTION,  se retrocede la transacción con ROLLBACK 
			         SELECT "SQLEXCEPTION!,  transacción abortada" AS resultado; 
			         ROLLBACK; 
		         END;

         start transaction;#Inicio la transaccion de manera atomica 
              

                IF EXISTS (SELECT * from caja_ahorro where nro_ca = destino) THEN

                         #Obtengo el numero de caja origen y cliente asociada a la tarjeta y los bloqueo
                         #Se asume que el numero de tarjeta ingresado por parametro existe y es valido
                            SELECT nro_cliente,nro_ca INTO cliente,cajaOrigen 
                               FROM Tarjeta where Tarjeta.nro_tarjeta = nro_tarjeta for update;
		                
		                 #Obtengo el salgo actual de la caja origen y lo bloqueo
		                   SELECT saldo INTO saldo_caja_origen from caja_ahorro where nro_ca = cajaOrigen for update;       

                                IF saldo_caja_origen>=monto THEN

									     #Creo una nueva transaccion para la caja origen
									       INSERT INTO Transaccion (fecha,hora,monto) VALUES (curdate(),curtime(),monto);

									     #Creo una nueva transaccion por caja para la caja origen 
									       INSERT INTO Transaccion_por_caja VALUES (last_insert_id(),cod_caja);

									     #Creo la nueva transferencia entre las cajas origen y destino
								           INSERT INTO Transferencia VALUES (last_insert_id(),cliente,cajaOrigen,destino);

								         #Creo una nueva transaccion para la caja destino
									       INSERT INTO Transaccion (fecha,hora,monto) VALUES (curdate(),curtime(),monto);

									     #Creo una nueva transaccion por caja para la caja origen 
									       INSERT INTO Transaccion_por_caja VALUES (last_insert_id(),cod_caja);  
								         
								         #Cro el nuevo deposito para la caja destino
								           INSERT INTO deposito VALUES (last_insert_id(),destino);
								           
								         #Actualizo la caja origen decrementando el saldo 
									       update Caja_Ahorro set saldo=saldo-monto 
									           where Caja_Ahorro.nro_ca = cajaOrigen;

										 #Actualizo la caja destino aumentando el saldo
										    update Caja_Ahorro set saldo=saldo+monto 
									           where Caja_Ahorro.nro_ca = destino;

									      SELECT 'La transferencia se realizo con exito' AS resultado; 
								ELSE
							       SELECT 'Saldo insuficiente para realizar la transferencia' AS resultado;
								END IF; 	           

			    ELSE 
			         SELECT "Error: cuenta inexistente " AS resultado;
                
                END IF; 
	     
	     commit;#cometo la transaccion para que los datos se guarden en la bd.     
     end; !

delimiter ;


########################################################################################################3

delimiter !

create procedure extraccion(in monto DECIMAL(16,2),in nro_tarjeta BIGINT(16),in cod_caja INT(5))
	BEGIN
          #Declaro variables que utilizo 
            declare saldo_caja_origen DECIMAL(16,2);
	        declare cliente INT(5);
	        declare cajaOrigen INT(8);
	       
	        declare exit handler for sqlexception
		         BEGIN # Si se produce una SQLEXCEPTION,  se retrocede la transacción con ROLLBACK 
			         SELECT "SQLEXCEPTION!,  transacción abortada" AS resultado; 
			         ROLLBACK; 
		         END;

            start transaction;#Inicio la transaccion de manera atomica 

				     #Obtengo el numero de caja origen y cliente asociada a la tarjeta y los bloqueo
		             #Se asume que el numero de tarjeta ingresado por parametro existe y es valido
		                  SELECT nro_cliente,nro_ca INTO cliente,cajaOrigen 
		                        FROM Tarjeta where Tarjeta.nro_tarjeta = nro_tarjeta for update;  

                     #Obtengo el salgo actual de la caja origen y lo bloqueo
		                   SELECT saldo INTO saldo_caja_origen from caja_ahorro where nro_ca = cajaOrigen for update;
      
                        IF saldo_caja_origen>=monto THEN
                            
                                  #Creo una nueva transaccion 
							         INSERT INTO Transaccion (fecha,hora,monto) VALUES (curdate(),curtime(),monto);

						          #Creo una nueva transaccion por caja  
									 INSERT INTO Transaccion_por_caja VALUES (last_insert_id(),cod_caja);

								  #Creo nueva extraccion para la caja 
								     INSERT INTO Extraccion VALUES (last_insert_id(),cliente,cajaOrigen);

                                  #Actualizo la caja decrementando el saldo 
								     update Caja_Ahorro set saldo=saldo-monto 
							           where Caja_Ahorro.nro_ca = cajaOrigen;
 

                                     SELECT 'La transferencia se realizo con exito' AS resultado; 
						ELSE
						
				    	       SELECT 'Saldo insuficiente para realizar la transferencia' AS resultado;
			
						END IF; 	 




             commit;#cometo la transaccion para que los datos se guarden en la bd. 
	END; !


delimiter ;



#################################################################################################################
####################################Triger#####################################################

delimiter !
create Trigger insert_pagos
	
	AFTER INSERT ON Prestamo 

	for each row
      BEGIN
    

       declare i INT default 1;
       

    

	       while i<=NEW.cant_meses do
		   
		         INSERT INTO Pago(nro_prestamo,nro_pago,fecha_venc) VALUES(NEW.nro_prestamo,i,date_add(NEW.fecha,interval i month));
	             set i = i+1;

          end while;

      END ; ! 
delimiter ;


########################################################################################
########################################################################################
#-----------------------------------Usuarios-------------------------------------------#
########################################################################################
########################################################################################

#Creo el usuario admin que tiene acceso a todas las tablas de la base de datos
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';

#Privilegios
GRANT ALL PRIVILEGES ON banco.* TO 'admin'@'localhost' WITH GRANT OPTION;

#############################################################################

CREATE USER 'empleado'@'%'  IDENTIFIED BY 'empleado';

#solo realizar consultas sobre empleado ,sucursal ,tasa_plazo_fijo y tasa_prestamo
GRANT SELECT ON banco.Empleado TO 'empleado'@'%';

GRANT SELECT ON banco.Sucursal TO 'empleado'@'%';

GRANT SELECT ON banco.Tasa_Plazo_Fijo TO 'empleado'@'%';

GRANT SELECT ON banco.Tasa_Prestamo TO 'empleado'@'%';
#######################################################

GRANT SELECT ON banco.Prestamo TO 'empleado'@'%';
GRANT INSERT ON banco.Prestamo TO 'empleado'@'%';

GRANT SELECT ON banco.Plazo_Fijo TO 'empleado'@'%';
GRANT INSERT ON banco.Plazo_Fijo TO 'empleado'@'%';

GRANT SELECT ON banco.Plazo_Cliente TO 'empleado'@'%';
GRANT INSERT ON banco.Plazo_Cliente TO 'empleado'@'%';

GRANT SELECT ON banco.Caja_Ahorro TO 'empleado'@'%';
GRANT INSERT ON banco.Caja_Ahorro TO 'empleado'@'%';

GRANT SELECT ON banco.Tarjeta TO 'empleado'@'%';
GRANT INSERT ON banco.Tarjeta TO 'empleado'@'%';

#######################################################

GRANT SELECT ON banco.Cliente_CA TO 'empleado'@'%';
GRANT INSERT ON banco.Cliente_CA TO 'empleado'@'%';
GRANT UPDATE ON banco.Cliente_CA TO 'empleado'@'%';

GRANT SELECT ON banco.Cliente TO 'empleado'@'%';
GRANT INSERT ON banco.Cliente TO 'empleado'@'%';
GRANT UPDATE ON banco.Cliente TO 'empleado'@'%';

GRANT SELECT ON banco.Pago TO 'empleado'@'%';
GRANT INSERT ON banco.Pago TO 'empleado'@'%';
GRANT UPDATE ON banco.Pago TO 'empleado'@'%';




drop user atm;

CREATE USER 'atm'@'%' IDENTIFIED BY 'atm';     

GRANT SELECT ON trans_cajas_ahorro TO 'atm'@'%';

GRANT SELECT ON banco.Tarjeta TO 'atm'@'%';
GRANT UPDATE ON banco.Tarjeta TO 'atm'@'%';

GRANT EXECUTE ON procedure transferencia to 'atm'@'%';
GRANT EXECUTE ON procedure extraccion to 'atm
