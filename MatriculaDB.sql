

host cls

-- CREATE TABLESPACE LAB_01M
-- DATAFILE 'C:\oraclexe\app\oracle\oradata\XE\LAB_01M.dbf'
-- SIZE 100M 
-- ONLINE;
-- CREATE TEMPORARY TABLESPACE LAB_01M_TEMP
-- TEMPFILE 'C:\oraclexe\app\oracle\oradata\XE\LAB_01M_TEMP.dbf'
-- SIZE 12M
-- AUTOEXTEND ON;
-- CREATE USER LAB_01M IDENTIFIED BY root123
  -- DEFAULT TABLESPACE LAB_01M
  -- TEMPORARY TABLESPACE LAB_01M_TEMP;
-- GRANT RESOURCE, CONNECT TO LAB_01M;
-- GRANT CREATE SESSION TO LAB_01M;
-- GRANT ALL PRIVILEGES TO LAB_01M;
 conn system/root

drop table Curso;
drop table Carrera;
drop table Usuario;

create table Carrera(
	codigo	varchar2(20) 	NOT NULL,
	nombre 	varchar2(50) 	NOT NULL,
	titulo 	varchar2(50) 	NOT NULL
);

create table Curso(
	codigo 			varchar2(20) 	NOT NULL,
	nombre 			varchar2(50) 	NOT NULL,
	creditos 		number(3) 		NOT NULL,
	horas_semanales number(2) 		NOT NULL,
	nivel 			varchar2(5) 	NOT NULL,
	ciclo			varchar2(5)		NOT NULL,
	codigo_carrera		varchar2(20) 	NOT NULL
);

Create Table Usuario(
cedula varchar2(10) NOT NULL, 
nombre varchar2(100) NOT NULL, 
telefono varchar2(10) NOT NULL, 
email varchar2(100) NOT NULL,
clave varchar2(50) NOT NULL
);

-- Llaves primarias
alter table Carrera add constraint Carrera_pk primary key(codigo);
alter table Curso add constraint Curso_pk primary key(codigo);
	

--Llaves foraneas (FK)
alter table Curso add constraint Curso_fk_cod_car foreign key (codigo_carrera) references Carrera ON DELETE SET NULL;

-- Usuarios login
insert into Usuario(cedula,nombre,telefono,email,clave) values('11111111', 'Christian Solis Quesada', '88888888','christiansq94@gmail.com','1234');
insert into Usuario(cedula,nombre,telefono,email,clave) values('22222222', 'Jose Quesada Valerio', '99999999','joseqv@gmail.com','12345');


commit;

-- Types para cursores
create or replace package TYPES
as
type ref_cursor is ref cursor;
end;
/
show error



-- Procedimientos almacenados

--Insertar Carreras
 
create or replace procedure insertar_carrera(Pcodigo in Carrera.codigo %TYPE,
Pnombre in Carrera.nombre %TYPE, 
Ptitulo in Carrera.titulo %TYPE) as
begin
	insert into Carrera(codigo,nombre,titulo) values (Pcodigo,Pnombre,Ptitulo);
    commit;	
end insertar_carrera;
/
show error

-- modificar las carreras
create or replace procedure modificar_carrera(
Pcodigo in Carrera.codigo %TYPE,
Pnombre in Carrera.nombre %TYPE, 
Ptitulo in Carrera.titulo %TYPE) as
begin
	update Carrera set nombre = Pnombre, titulo=Ptitulo where codigo=Pcodigo;
    commit;	
end modificar_carrera;
/
show error

--Eliminar carreras
create or replace procedure eliminar_carreras(Pcodigo in Carrera.codigo %TYPE) as
begin
	delete Carrera where codigo = Pcodigo;
    commit;	
end eliminar_carreras;
/
show error

--consultar carreras

create or replace function consultar_carrera(Pcodigo in Carrera.codigo %TYPE)
return TYPES.ref_cursor
as
carrera_cursor TYPES.ref_cursor;
begin
	open carrera_cursor for
    select codigo,nombre,titulo from Carrera where codigo = Pcodigo;		
	return carrera_cursor;
end consultar_carrera;
/
show error

--listar carreras
create or replace function listar_carrera
return TYPES.ref_cursor
as
carrera_cursor TYPES.ref_cursor;
begin
	open carrera_cursor for
	select codigo,nombre,titulo from Carrera;
	return carrera_cursor;
end listar_carrera;
/
show error

-- Cursos

--insertar cursos
create or replace procedure insertar_cursos(
Pcodigo 	in Curso.codigo %TYPE,
Pnombre 	in Curso.nombre %TYPE, 
Pcreditos 	in Curso.creditos %TYPE,
Phoras_Sem 	in Curso.horas_semanales %TYPE,
Pnivel		in Curso.nivel %TYPE,
Pciclo 		in Curso.ciclo %TYPE,
Pcodigo_carrera 	in Curso.codigo_carrera %TYPE
) is
begin
	insert into Curso(codigo,nombre,creditos,horas_semanales,nivel,ciclo,codigo_carrera) 
	values (Pcodigo,Pnombre,Pcreditos,Phoras_Sem,Pnivel,Pciclo,Pcodigo_carrera);
    commit;	
end insertar_cursos;
/
show error

--modificar cursos
create or replace procedure modificar_cursos(
Pcodigo 	in Curso.codigo %TYPE,
Pnombre 	in Curso.nombre %TYPE, 
Pcreditos 	in Curso.creditos %TYPE,
Phoras_Sem 	in Curso.horas_semanales %TYPE,
Pnivel		in Curso.nivel %TYPE,
Pciclo 		in Curso.ciclo %TYPE,
Pcodigo_carrera 	in Curso.codigo_carrera %TYPE) is
begin
	update Curso set nombre = Pnombre, creditos=Pcreditos, horas_semanales=Phoras_Sem,
	nivel=Pnivel, ciclo=Pciclo, codigo_carrera=Pcodigo_carrera
	where codigo=Pcodigo;	
	commit;
end modificar_cursos;
/
show error

--eliminar cursos
create or replace procedure eliminar_curso(Pcodigo in Curso.codigo %TYPE) is
begin
	delete Curso where codigo = Pcodigo;
    commit;	
end eliminar_curso;
/
show error

--consultar curso
create or replace function consultar_cursos(Pcodigo in Curso.codigo %TYPE)

return TYPES.ref_cursor
as
curso_cursor TYPES.ref_cursor;
begin
	open curso_cursor for
	select codigo,nombre,creditos,horas_semanales,nivel,ciclo,codigo_carrera from Curso where codigo = Pcodigo;	
	return curso_cursor;
end consultar_cursos;
/
show error

--listar cursos
create or replace function listar_cursos
return TYPES.ref_cursor
as
curso_cursor TYPES.ref_cursor;
begin
	open curso_cursor for
	select codigo,nombre,creditos,horas_semanales,nivel,ciclo,codigo_carrera from Curso;
	return curso_cursor;
end listar_cursos;
/
show error

--Formatos tabla
COLUMN Codigo FORMAT A10
COLUMN Nombre FORMAT A10
COLUMN Creditos FORMAT 99
COLUMN Horas_semanales FORMAT 99
COLUMN Nivel FORMAT A10
COLUMN Ciclo FORMAT A10
COLUMN Codigo_carrera FORMAT A10

-- delete from carrera;

 execute insertar_carrera('EIF','Ing. Sist. Info.','Bachiller');
 execute insertar_carrera ('ETE', 'administracion', 'Bachiller');
 execute insertar_carrera ('LIX', 'Ingles', 'Bachiller');
 -- commit;
-- select * from Carrera;
PROMPT --------------- Devuelve carrera segun su codigo------------------------------------

 variable x refcursor
exec :x:= consultar_carrera('EIF');
print x;
PROMPT --------------- Devuelve todas las carreras ----------------------------------------

 variable x refcursor
exec :x:= listar_carrera;
print x;

PROMPT =================================================================

-- delete from curso;
execute insertar_cursos('EIF-201','Programacion 1',9,9,'II','I','EIF');
execute insertar_cursos('EIF-402','Bases de Datos',9,9,'III','I','EIF');

PROMPT --------------- Devuelve Curso segun su codigo------------------------------------

 variable x refcursor
exec :x:= consultar_cursos('EIF-201');
print x;
PROMPT --------------- Devuelve todos las cursos -----------------------------------------

 variable x refcursor
exec :x:= listar_cursos;
print x;

