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
WHERE r.iid is NULL;

-- part d. 
SELECT f.fid, 
f.fname, 
COUNT(ingredient.iid) AS 'Num of Ingredient', 
SUM(recipe.amount) AS 'Amount of Grams'
FROM food f
INNER JOIN recipe ON f.fid = recipe.fid
INNER JOIN ingredient ON recipe.iid = ingredient.iid
GROUP BY f.fid, fname
ORDER BY COUNT(ingredient.iid) desc;

-- part e.
SELECT f.fid, f.fname, r.iid, i.iname,
cast(r.amount * i.caloriepergram as decimal(10,2)) AS calories
FROM food f
LEFT JOIN recipe AS r ON f.fid = r.fid
LEFT JOIN ingredient AS i ON r.iid = i.iid
WHERE f.fid


-- part f.
-- SELECT f.fname, f.fid
-- FROM food f
-- LEFT JOIN recipe r ON f.fid = r.fid
-- LEFT JOIN ingredient i ON i.iid = r.iid
-- WHERE i.category = 'Vegetable'
-- AND i.caloriepergram * r.amount > 5
-- ORDER BY f.fid , f.fname DESC;