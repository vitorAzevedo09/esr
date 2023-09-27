ALTER TABLE restaurants
  ADD active tinyint(1) DEFAULT 1 NOT NULL;

UPDATE restaurants
  SET active = true;

