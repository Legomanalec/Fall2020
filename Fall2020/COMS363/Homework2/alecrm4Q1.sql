-- Alec Meyer

-- part a.
SELECT 
COUNT(*) AS numOfIngredient
FROM ingredient;

-- part b.
SELECT food.fid, ingredient.iid
FROM food, ingredient
LEFT JOIN recipe ON food.fid = recipe.fid and ingredient.iid = recipe.iid
WHERE ingredient.iname = "Green Onion" and ingredient.iname = "Beef"

-- part c.