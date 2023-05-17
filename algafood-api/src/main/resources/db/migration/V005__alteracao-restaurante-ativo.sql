ALTER TABLE restaurante
  ADD ativo tinyint(1) DEFAULT 1 not null;

UPDATE restaurante
  SET ativo = true;
