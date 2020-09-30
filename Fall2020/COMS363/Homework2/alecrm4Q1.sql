-- Alec Meyer

-- part a.
SELECT 
COUNT(*) AS numOfIngredient
FROM ingredient;

-- part b.
SELECT f.fid, f.fname
FROM food f
INNER JOIN recipe r ON f.fid = r.fid
INNER JOIN ingredient i ON r.iid = i.iid
WHERE i.iname = 'Green Onion' AND f.fid IN
(SELECT f.fid
	FROM food 
    INNER JOIN recipe r ON f.fid = r.fid
    INNER JOIN ingredient i2 ON r.iid = i2.iid
    WHERE i2.iname = 'Beef');

-- part c.
SELECT i.iid, i.iname
FROM ingredient i
LEFT JOIN recipe r ON r.iid = i.iid
WHERE r.iid is NULL

-- part d. 
SELECT f.fid, f.fname,