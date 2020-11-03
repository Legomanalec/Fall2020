SELECT * from actor where month(last_update)=2;

SET SQL_SAFE_UPDATES = 0;

ALTER TABLE actor
DROP COLUMN last_update_new;
ALTER TABLE actor
ADD last_update_new date;

UPDATE actor 
SET last_update_new = last_update;
 create index last_updateidx on actor (last_update_new);