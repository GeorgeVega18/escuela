
insert into t_alumnos values (default,'Sebastian','Vega','Nova',1);

select * from t_alumnos ta;

create table t_profesor (
	id_t_profesor SERIAL not null,
	nombre varchar (80),
	ap_paterno varchar(80),
	ap_materno varchar(80),
	activo int,
	primary key (id_t_profesor)
    foreign key (id_t_materias) references t_materias (id_t_materias) );

