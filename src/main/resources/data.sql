insert into usuario (id,email,password,rol)
values (1,'iamfer19','$2a$10$TdVzFsT6DaTRHCsodbxaku/IMKzMaCnXQZCpwun.UJSXBxH5yECcO','ADMIN');

insert into sec_persona (id,identificacion, nombre,tipoid,ciudad,direccion,telefono)
values('1','4234233','Richard','PASAPORTE','Medellín','CRA 22-2323','32231233');

insert into sec_paciente (id,nombre,especie,raza,nacimiento,idper,fecharegistro)
values('1','Juan','León','tiznao','2022/12/22','4234233','2022/12/22');