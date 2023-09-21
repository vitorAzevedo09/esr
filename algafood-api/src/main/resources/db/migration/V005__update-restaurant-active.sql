ALTER TABLE restaurant
  ADD active tinyint(1) DEFAULT 1 NOT NULL;

UPDATE restaurant
  SET active = true;

