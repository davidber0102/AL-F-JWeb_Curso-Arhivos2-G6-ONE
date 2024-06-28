CREATE TABLE pacientes(
    id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre varchar(100) not null,
    email varchar(100) not null unique,
    documento_identidad varchar(14) not null unique,
    telefono varchar(20) not null,
    calle varchar(100) not null,
    distrito varchar(100) not null,
    ciudad varchar(100) not null,
    numero varchar(20),
    complemento varchar(100)
    );