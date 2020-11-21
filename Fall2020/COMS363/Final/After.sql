

-- Q3 
SELECT COUNT(DISTINCT users.state) AS numStates, group_concat(DISTINCT users.state) AS States, hashtags.name
FROM hashtags, tweets, users
WHERE users.state != "" AND users.state != "na" AND YEAR(tweets.createdTime) = '2016' AND tweets.tid = hashtags.tid
AND users.screenName = tweets.userScreenName 
GROUP BY hashtags.name
ORDER BY numStates DESC
LIMIT 5;


-- Q7 
SELECT count(tweets.tid), users.screenName, users.category
FROM users
JOIN tweets ON users.screenName = tweets.userScreenName
JOIN hashtags ON tweets.tid = hashtags.tid
WHERE hashtags.name = "GOPDebate"
AND users.state = "NC"
AND MONTH(tweets.createdTime) = 2
AND YEAR(tweets.createdTime) = 2016
GROUP BY users.screenName
ORDER BY count(tweets.tid) DESC
LIMIT 5;

-- Q9 
SELECT users.screenName, users.subCategory, users.numFollowers
FROM users 
WHERE users.subcategory = 'Democrat'
ORDER BY users.numFollowers DESC
LIMIT 5;


-- Q16
SELECT users.screenName, users.category, tweets.text, tweets.retweetCt, urls.address
FROM users
JOIN tweets ON tweets.userScreenName = users.screenName
JOIN urls ON urls.tid = tweets.tid
Where month(tweets.createdTime) = 2 AND YEAR(tweets.createdTime) = 2016
ORDER BY tweets.retweetCt DESC
LIMIT 5;

-- Q18
SELECT COUNT(U1.tid), mentioned.screenName AS user_name, mentioned.state AS mentionedUserState, GROUP_CONCAT(DISTINCT posters.screenName) as postingUsers
FROM users AS mentioned
JOIN usertweets AS U1 ON U1.screenName = mentioned.screenName
JOIN tweets ON tweets.tid = U1.tid AND MONTH(tweets.createdTime) = 1 AND YEAR(tweets.createdTime) = 2016
JOIN users AS posters ON posters.screenName = tweets.userScreenName
WHERE posters.subcategory IN('GOP')  
GROUP BY mentioned.screenName
ORDER BY COUNT(U1.tid) DESC
LIMIT 5;

-- Q23
SELECT hashtags.name, COUNT(tweets.tid) AS num_uses
FROM hashtags 
JOIN tweets ON tweets.tid = hashtags.tid
JOIN users ON users.screenName = tweets.userScreenName 
WHERE users.subCategory IN('GOP') AND (MONTH(tweets.createdTime) = 1 OR MONTH(tweets.createdTime) = 2 OR MONTH(tweets.createdTime) = 3) AND YEAR(tweets.createdTime) = 2016
GROUP BY hashtags.name
ORDER BY num_uses
DESC LIMIT 5;






INSERT INTO users (screenName,name,numFollowers,numFollowing,category,subCategory,state) values('TEST', 'TEST',234, 234, 'reporter', 'GOP', 'NC');
