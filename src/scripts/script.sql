
create table tbimoveis(
idproprietario number not null,
nomeproprietario varchar2(50),
enderecoproprietario varchar(50),
constraint idproprietario_pk primary key (idproprietario)
);

create sequence tbimoveis_seq;
alter sequence tbimoveis_seq nocache;

insert into tbimoveis values (tbimoveis_seq.nextval, 'teste', 'rua a');