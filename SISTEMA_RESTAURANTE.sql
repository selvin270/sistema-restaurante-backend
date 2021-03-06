CREATE TABLE TIENDA
(
	ID_TIENDA SERIAL NOT NULL,
	DIR_TIENDA VARCHAR(50) NOT NULL,
	TEL_TIENDA VARCHAR(50) NOT NULL,
	CONSTRAINT TIENDA_PK PRIMARY KEY (ID_TIENDA)
)
INSERT INTO TIENDA (DIR_TIENDA, TEL_TIENDA) VALUES ('Zona 5', '22229898');

CREATE TABLE PRODUCTO
(
	ID_PRODUCTO SERIAL NOT NULL,
	ID_TIENDA INT NOT NULL,
	DESCRIPCION VARCHAR(50) NOT NULL,
	FECHA_INGRESO DATE NOT NULL,
	CONSTRAINT PRODUCTO_PK PRIMARY KEY (ID_PRODUCTO),
	FOREIGN KEY (ID_TIENDA) REFERENCES TIENDA (ID_TIENDA)
)
INSERT INTO PRODUCTO (ID_TIENDA, DESCRIPCION) VALUES (1, 'Carne');

CREATE TABLE PERIODO_CONTABLE
(
	ANIO INT NOT NULL,
	MES INT NOT NULL,
	FECHA_INICIO DATE NOT NULL,
	FECHA_FIN DATE NOT NULL,
	CERRADO VARCHAR(20) NOT NULL,
	CONSTRAINT PERIODO_CONTABLE_PK PRIMARY KEY (ANIO, MES)
)

CREATE TABLE FACTURA
(
	ID_FACTURA SERIAL NOT NULL,
	ID_TIENDA INT NOT NULL,
	ANIO INT NOT NULL,
	MES INT NOT NULL,
	FECHA DATE NOT NULL,
	NOMBRE VARCHAR(50) NOT NULL,
	NIT VARCHAR(20) NOT NULL,
	DIRECCION VARCHAR(80) NOT NULL,
	TELEFONO VARCHAR(20) NOT NULL,
	CONSTRAINT FACTURA_PK PRIMARY KEY (ID_TIENDA, ID_FACTURA, ANIO, MES),
	UNIQUE(ID_FACTURA),
	FOREIGN KEY (ID_TIENDA) REFERENCES TIENDA (ID_TIENDA),
	FOREIGN KEY (ANIO, MES) REFERENCES PERIODO_CONTABLE (ANIO, MES)
)

CREATE TABLE EMPLEADO
(
	ID_EMPLEADO SERIAL NOT NULL,
	ID_TIENDA INT NOT NULL,
	P_NOMBRE VARCHAR(30) NOT NULL,
	S_NOMBRE VARCHAR(30),
	P_APELLIDO VARCHAR(30) NOT NULL,
	S_APELLIDO VARCHAR(30)
	CONSTRAINT EMPLEADO_PK PRIMARY KEY (ID_EMPLEADO),
	FOREIGN KEY (ID_TIENDA) REFERENCES TIENDA (ID_TIENDA),
)
INSERT INTO EMPLEADO (ID_TIENDA, P_NOMBRE, S_NOMBRE, P_APELLIDO, S_APELLIDO, FEC_ALTA) VALUES (1, 'Mario', 'Estuardo', 'Perez', 'Montenegro');
--ALTER TABLE EMPLEADO DROP COLUMN FEC_ALTA;
--TO_DATE('09/05/2019', 'DD/MM/YYYY')

CREATE TABLE INVENTARIO
(
	ID_INVENTARIO SERIAL NOT NULL,
	ID_TIENDA INT NOT NULL,
	ID_PRODUCTO INT NOT NULL,
	SALDO NUMERIC,
	CONSTRAINT INVENTARIO_PK PRIMARY KEY (ID_INVENTARIO),
	FOREIGN KEY (ID_TIENDA) REFERENCES TIENDA (ID_TIENDA),
	FOREIGN KEY (ID_PRODUCTO) REFERENCES PRODUCTO (ID_PRODUCTO)
)

CREATE TABLE TRANSACCION_INVENTARIO
(
	ID_TRANSA SERIAL NOT NULL,
	ID_TIPO INT NOT NULL,
	ID_TIENDA INT NOT NULL,
	ANIO INT NOT NULL,
	MES INT NOT NULL,
	FEC_TRANSA DATE NOT NULL,
	CONSTRAINT TRANSACCION_INVENTARIO_PK PRIMARY KEY (ID_TRANSA, ID_TIPO, ID_TIENDA, ANIO, MES),
	UNIQUE(ID_TRANSA, ID_TIPO),
	FOREIGN KEY (ID_TIENDA) REFERENCES TIENDA (ID_TIENDA),
	FOREIGN KEY (ANIO, MES) REFERENCES PERIODO_CONTABLE (ANIO, MES)
)

CREATE TABLE TRANSACCION_INVENTARIO_DET
(
	ID_TIENDA INT NOT NULL,
	ID_TRANSA INT NOT NULL,
	ID_TIPO INT NOT NULL,
	ANIO INT NOT NULL,
	MES INT NOT NULL,
	ID_PRODUCTO INT NOT NULL,
	UNIDADES NUMERIC NOT NULL,
	COSTO_U NUMERIC NOT NULL,
	COSTO_TOTAL NUMERIC NOT NULL,
	CONSTRAINT TRANSACCION_INVENTARIO_DET_PK PRIMARY KEY (ID_TIENDA, ID_TRANSA, ID_TIPO, ANIO, MES, ID_PRODUCTO),
	FOREIGN KEY (ID_TIENDA) REFERENCES TIENDA (ID_TIENDA),
	FOREIGN KEY (ID_TRANSA, ID_TIPO) REFERENCES TRANSACCION_INVENTARIO (ID_TRANSA, ID_TIPO),
	FOREIGN KEY (ANIO, MES) REFERENCES PERIODO_CONTABLE (ANIO, MES),
	FOREIGN KEY (ID_PRODUCTO) REFERENCES PRODUCTO (ID_PRODUCTO)
)

CREATE TABLE FACTURA_DETALLE
(
	ID_TIENDA INT NOT NULL,
	ID_FACTURA INT NOT NULL,
	ANIO INT NOT NULL,
	MES INT NOT NULL,
	ID_PRODUCTO INT NOT NULL,
	UNIDADES NUMERIC NOT NULL,
	PRECIO_UNIT NUMERIC NOT NULL,
	PRECIO_TOTAL NUMERIC NOT NULL,
	IMPORTE NUMERIC NOT NULL,
	IMPUESTO NUMERIC NOT NULL,
	CONSTRAINT FACTURA_DETALLE_PK PRIMARY KEY (ID_TIENDA, ID_FACTURA, ANIO, MES, ID_PRODUCTO),
	FOREIGN KEY (ID_TIENDA) REFERENCES TIENDA (ID_TIENDA),
	FOREIGN KEY (ID_FACTURA) REFERENCES FACTURA (ID_FACTURA),
	FOREIGN KEY (ANIO, MES) REFERENCES PERIODO_CONTABLE (ANIO, MES),
	FOREIGN KEY (ID_PRODUCTO) REFERENCES PRODUCTO (ID_PRODUCTO)
)




create trigger beforer_ins_prod before
insert
    on
    public.producto for each row execute function before_ins_fecha_producto();
    
create trigger after_ins_prod after
insert 
	on public.producto  for each row execute function crea_inventario_tienda();
	




CREATE OR REPLACE FUNCTION public.before_ins_fecha_producto()
 RETURNS trigger
 LANGUAGE plpgsql
AS $function$
	BEGIN
		new.fecha_ingreso := current_date;
	return new;
	END;
$function$
;



CREATE OR REPLACE FUNCTION public.crea_inventario_tienda()
 RETURNS trigger
 LANGUAGE plpgsql
AS $function$
/*	declare 
	tiendas cursor for 
			select id_tienda from tienda;
	tienda record;
	--v_date date := current_date;
*/
	BEGIN
	-- Open tienda
--	open tiendas;
--	
--	loop
--		-- Fetch row into the record
--		fetch tiendas into tienda;
--		-- Exit when no more recors
--		exit when not found;
		-- Create new record
		insert into inventario (id_tienda,id_producto,saldo) values (new.id_tienda,new.id_producto,0);
--	end loop;
	-- Close cursor
--	close tiendas;
	-- Update fec_ingreso on Producto
	new.fecha_ingreso := CURRENT_DATE;
	return new;
	end;
$function$
;

