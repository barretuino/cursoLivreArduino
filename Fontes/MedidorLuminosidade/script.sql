-- Cria��o da Database (Base de Dados)
create database pjGerencia;

-- Definindo como database em uso
use pjGerencia;

-- Cria��o da Tabela
CREATE TABLE `pjgerencia`.`medidas` (
  `data` DATE NOT NULL,
  `hora` TIME NOT NULL,
  `valor` FLOAT NOT NULL
);

-- Selecionando os dados
SELECT * FROM pjgerencia.medidas;