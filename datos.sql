
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

INSERT INTO Cliente VALUES (1,"Perez", "Pepito", "DNI", 12345678, "Chiclana 10", "2919922334","1990/04/17");

INSERT INTO Caja_Ahorro VALUES (3, 555001000000000001, 0.00);

##############################################################
INSERT INTO Caja VALUES();
INSERT INTO Caja VALUES();
INSERT INTO Caja VALUES();
INSERT INTO Caja VALUES();
INSERT INTO Caja VALUES();
INSERT INTO Caja VALUES();
INSERT INTO Caja VALUES();
INSERT INTO Caja VALUES();
INSERT INTO Caja VALUES();
INSERT INTO Caja VALUES();
INSERT INTO Caja VALUES();
INSERT INTO Caja VALUES();
##############################################################
INSERT INTO Cliente_CA VALUES (1, 3);


#######################################################3
INSERT INTO Transaccion VALUES (1235432311, "2018-08-10", "13:37", 1200.10);

INSERT INTO Transaccion_por_caja VALUES(1235432311,2);

INSERT INTO Debito VALUES (1235432311, "Debito Compra",1, 3);

