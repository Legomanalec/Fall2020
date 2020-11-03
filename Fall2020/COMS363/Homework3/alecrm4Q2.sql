
drop procedure if exists total_sales;
delimiter //
create procedure total_sales(IN query_month VARCHAR(100))
begin 
SELECT
CONCAT(c.city, _utf8',', cy.country) AS store, SUM(p.amount) AS total_sales
FROM payment AS p
INNER JOIN rental AS r ON p.rental_id = r.rental_id
INNER JOIN inventory AS i ON r.inventory_id = i.inventory_id
INNER JOIN store AS s ON i.store_id = s.store_id
INNER JOIN address AS a ON s.address_id = a.address_id
INNER JOIN city AS c ON a.city_id = c.city_id
INNER JOIN country AS cy ON c.country_id = cy.country_id
INNER JOIN staff AS m ON s.manager_staff_id = m.staff_id
WHERE month(r.rental_date)= query_month
GROUP BY s.store_id;
end;
//delimiter ;
