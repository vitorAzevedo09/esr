ALTER TABLE restaurants
  ADD open tinyint(1) DEFAULT 1 NOT NULL;

UPDATE restaurants
  SET open = false;

