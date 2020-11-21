	-- Q9 Index
CREATE INDEX subCategoryInd
ON users (subcategory);

ALTER TABLE users
DROP INDEX subCategoryInd;

-- Q7 Index
CREATE INDEX stateInd
ON users (state);

ALTER TABLE users
DROP INDEX stateInd;

-- Q18 Index
CREATE INDEX screenNameInd
ON users (screenName);

ALTER TABLE users
DROP INDEX screenNameInd;

-- Q16 Index
CREATE INDEX createdTimeInd
ON tweets (createdTime);

ALTER TABLE tweets
DROP INDEX createdTimeInd;

-- Q3 and Q23 Buffer pool change
SHOW VARIABLES LIKE 'innodb_buffer_pool_size';
SET GLOBAL innodb_buffer_pool_size = 1073741824;

SET GLOBAL innodb_buffer_pool_size = 8388608;
