create database if not exists estoque;

use estoque;

drop table if exists categoria;

create table categoria (
	id int not null auto_increment primary key,
    tamanho varchar(255),
    embalagem varchar(255)
);

drop table if exists produto;

create table produto (
	id int not null auto_increment primary key,
    nome varchar(255),
    preco_unitario varchar(255),
    unidade varchar(255),
    quantidade_em_estoque varchar(255),
    quantidade_minima_em_estoque varchar(255),
    quantidade_maxima_em_estoque varchar(255),
    id_categoria int not null,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id)
);

show tables;