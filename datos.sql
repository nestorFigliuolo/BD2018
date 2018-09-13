
#--------------------------Entidades----------------------------------------------------#

INSERT INTO Ciudad VALUES (8000,"Bahia Blanca");
INSERT INTO Ciudad VALUES (7530,"Coronel Pringles");
INSERT INTO Ciudad VALUES (7540,"Coronel Suarez");
INSERT INTO Ciudad VALUES (7600,"Mar del plata");
INSERT INTO Ciudad VALUES (8153,"Monte Hermoso");
INSERT INTO Ciudad VALUES (7630,"Necochea");
INSERT INTO Ciudad VALUES (8168,"Sierra de la ventana");
INSERT INTO Ciudad VALUES (7000,"Tandil");
INSERT INTO Ciudad VALUES (8150,"Coronel Dorrego");

INSERT INTO Sucursal VALUES (1, "Centro", "Colon 20", "2911234567", "10 a 15", "8000");
INSERT INTO Sucursal VALUES (2, "Alem", "Alem 1000", "2911234568", "10 a 15", "8000");
INSERT INTO Sucursal VALUES (3, "Avenida", "Sarmiento 30", "2911234457", "10 a 20", "7540");
INSERT INTO Sucursal VALUES (4, "Boulebar10", "Saavedra 1000", "2911274568", "10 a 22", "7000");

INSERT INTO Cliente VALUES (1,"Perez", "Pepito", "DNI", 12345678, "Chiclana 10", "2919922334","1990/04/17");
INSERT INTO Cliente VALUES (2,"Gomez", "Juan", "DNI", 12345378, "Alem 10", "2919912334","1990/04/17");
INSERT INTO Cliente VALUES (3,"Messi", "Jose", "DNI", 12350078, "Laprida 10", "291932334","1990/04/17");
INSERT INTO Cliente VALUES (4,"Ramirez", "Pedro", "DNI", 99345678, "Cerrito 10", "291333334","1990/04/17");

INSERT INTO Empleado VALUES (1234,"Tata", "Robert", "DNI", 55345678, "Cerrito 10", "121333334","Asistente",md5("tatarobert"),1);
INSERT INTO Empleado VALUES (1235,"Gomez", "Pepe", "DNI", 55345678, "Cerrito 10", "121333334","Asistente",md5("gomezpepe"),2);
INSERT INTO Empleado VALUES (1236,"Iglesias", "Barbara", "DNI", 55345678, "Cerrito 10", "121333334","Asistente",md5("iglesiasbarbara"),3);
INSERT INTO Empleado VALUES (1237,"Estebanez", "Jorge", "DNI", 55345678, "Cerrito 10", "121333334","Asistente",md5("estebanezjorge"),4);

INSERT INTO Caja_Ahorro VALUES (3, 555001000000000001, 0.00);
INSERT INTO Caja_Ahorro VALUES (4, 555001000044440002, 1000.00);
INSERT INTO Caja_Ahorro VALUES (5, 555001012345000001, 7343.00);
INSERT INTO Caja_Ahorro VALUES (6, 555001000022220002, 1112.00);

INSERT INTO Caja VALUES(100);
INSERT INTO Caja VALUES(200);
INSERT INTO Caja VALUES(300);
INSERT INTO Caja VALUES(400);
INSERT INTO Caja VALUES(500);
INSERT INTO Caja VALUES(600);

INSERT INTO Ventanilla VALUES (100,1);
INSERT INTO Ventanilla VALUES (200,2);
INSERT INTO Ventanilla VALUES (300,3);

INSERT INTO ATM VALUES (400,8000,"14 de Julio 4000");
INSERT INTO ATM VALUES (500,7600,"Estomba 500");
INSERT INTO ATM VALUES (600,7000,"San Martin 80");

#----------Tasa Plazo Fijo -------------------#

INSERT INTO Tasa_Plazo_Fijo VALUES (30, 0, 60000, 5.50);
INSERT INTO Tasa_Plazo_Fijo VALUES (60, 0, 60000, 6.25);
INSERT INTO Tasa_Plazo_Fijo VALUES (90, 0, 60000, 6.50);
INSERT INTO Tasa_Plazo_Fijo VALUES (120, 0, 60000, 6.75);
INSERT INTO Tasa_Plazo_Fijo VALUES (180, 0, 60000, 7.00);
INSERT INTO Tasa_Plazo_Fijo VALUES (360, 0, 60000, 7.50);

INSERT INTO Tasa_Plazo_Fijo VALUES (30, 60001, 150000, 5.55);
INSERT INTO Tasa_Plazo_Fijo VALUES (60, 60001, 150000, 6.30);
INSERT INTO Tasa_Plazo_Fijo VALUES (90, 60001, 150000, 6.55);
INSERT INTO Tasa_Plazo_Fijo VALUES (120, 60001, 150000, 6.80);
INSERT INTO Tasa_Plazo_Fijo VALUES (180, 60001, 150000, 7.05);
INSERT INTO Tasa_Plazo_Fijo VALUES (360, 60001, 150000, 7.55);

INSERT INTO Tasa_Plazo_Fijo VALUES (30, 150001, 99999999999999, 5.64);
INSERT INTO Tasa_Plazo_Fijo VALUES (60, 150001, 99999999999999, 6.39);
INSERT INTO Tasa_Plazo_Fijo VALUES (90, 150001, 99999999999999, 6.64);
INSERT INTO Tasa_Plazo_Fijo VALUES (120, 150001, 99999999999999, 6.89);
INSERT INTO Tasa_Plazo_Fijo VALUES (180, 150001, 99999999999999, 7.14);
INSERT INTO Tasa_Plazo_Fijo VALUES (360, 150001, 99999999999999, 7.64);

#----------Tasa Plazo Fijo -------------------#

#----------Tasa Prestamo -------------------#

INSERT INTO Tasa_Prestamo VALUES (6, 0, 3000, 17);
INSERT INTO Tasa_Prestamo VALUES (12, 0, 3000, 18.50);
INSERT INTO Tasa_Prestamo VALUES (24, 0, 3000, 20);
INSERT INTO Tasa_Prestamo VALUES (60, 0, 3000, 25);
INSERT INTO Tasa_Prestamo VALUES (120, 0, 3000, 30);

INSERT INTO Tasa_Prestamo VALUES (6, 3001, 10000, 20);
INSERT INTO Tasa_Prestamo VALUES (12, 3001, 10000, 21.50);
INSERT INTO Tasa_Prestamo VALUES (24, 3001, 10000, 23);
INSERT INTO Tasa_Prestamo VALUES (60, 3001, 10000, 28);
INSERT INTO Tasa_Prestamo VALUES (120, 3001, 10000, 33);

INSERT INTO Tasa_Prestamo VALUES (6, 10001, 99999999, 24);
INSERT INTO Tasa_Prestamo VALUES (12, 10001, 99999999, 25.50);
INSERT INTO Tasa_Prestamo VALUES (24, 10001, 99999999, 27);
INSERT INTO Tasa_Prestamo VALUES (60, 10001, 99999999, 32);
INSERT INTO Tasa_Prestamo VALUES (120, 10001, 99999999, 37);


#----------Tasa Prestamo -------------------#


#---------------------------------------------------------------------------------------#




INSERT INTO Cliente_CA VALUES (1, 3);
INSERT INTO Cliente_CA VALUES (2, 4);
INSERT INTO Cliente_CA VALUES (3, 4);
INSERT INTO Cliente_CA VALUES (4, 5);
INSERT INTO Cliente_CA VALUES (1, 6);

INSERT INTO Transaccion VALUES (1235432311, "2018-08-10", "13:37", 1200.10);
INSERT INTO Transaccion VALUES (1235432312, "2018-08-10", "13:37", 100.10);
INSERT INTO Transaccion VALUES (123, "2017-02-10", "05:37", 12.10);
INSERT INTO Transaccion VALUES (1233, "2017-03-10", "03:37", 12000.10);
INSERT INTO Transaccion VALUES (1234, "2017-01-11", "02:37", 1500.10);
INSERT INTO Transaccion VALUES (12355, "2016-01-13", "16:37", 1600.10);
INSERT INTO Transaccion VALUES (123112, "2010-01-14", "14:37", 1100.10);


INSERT INTO Transaccion_por_caja VALUES(1235432311,100);
INSERT INTO Transaccion_por_caja VALUES(1235432312,200);
INSERT INTO Transaccion_por_caja VALUES(123,100);
INSERT INTO Transaccion_por_caja VALUES(1233,200);
INSERT INTO Transaccion_por_caja VALUES(1234,100);
INSERT INTO Transaccion_por_caja VALUES(12355,600);



INSERT INTO Debito VALUES (1235432311, "Debito Compra",1, 3);
INSERT INTO Debito VALUES (123,"Debito mercado",2,4);


INSERT INTO Deposito VALUES (1235432311,3);   
INSERT INTO Deposito VALUES (1233,3);

INSERT INTO Extraccion VALUES(1235432312,1,3);
INSERT INTO Extraccion VALUES(12355,4,5);

INSERT INTO Transferencia VALUES (1235432311,1,3,5);
INSERT INTO Transferencia VALUES (1234,4,5,5);

