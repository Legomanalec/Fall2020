use sakila_mod;

drop trigger if exists delete_actor;

delimiter //
CREATE TRIGGER delete_actor AFTER DELETE ON sakila_mod.actor
FOR EACH ROW 
BEGIN
    
    
    DELETE FROM film_actor actor WHERE film_actor.actor_id = OLD.actor.actor_id;

END;//
delimiter ;

