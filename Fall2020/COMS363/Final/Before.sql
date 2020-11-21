

-- Q3
SELECT COUNT(DISTINCT users.state) AS statenum, group_concat(DISTINCT users.state) as states, hashtags.name
FROM hashtags, tweets, users
WHERE users.state != "" AND users.state != "na" AND YEAR(tweets.createdTime) = '2016' AND tweets.tid = hashtags.tid
AND users.screenName = tweets.userScreenName 
GROUP BY hashtags.name
ORDER BY statenum DESC
LIMIT 5;

-- Q7
SELECT count(tweets.tid) AS tweet_count, users.screenName AS screen_name, users.category
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

SELECT users.screenName AS screen_name, users.subcategory, users.numFollowers
FROM users
WHERE users.subcategory = 'GOP'
ORDER BY users.numFollowers DESC
LIMIT 5;

SELECT users.screenName, users.subcategory, users.numFollowers
FROM users
WHERE users.subcategory = 'Democrat'
ORDER BY users.numFollowers DESC
LIMIT 5;


-- Q16
SELECT users.screenName AS user_name, users.category, tweets.text AS texts, tweets.retweetCt, urls.address
From users
JOIN tweets ON tweets.userScreenName = users.screenName
JOIN urls ON urls.tid = tweets.tid
WHERE MONTH(tweets.createdTime) = 2
AND YEAR(tweets.createdTime) = 2016
ORDER BY tweets.retweetCt DESC
LIMIT 5;

-- Q18
SELECT users.screenName AS mentionedUser, users.state AS mentionedUserState, GROUP_CONCAT(DISTINCT posters.screenName) as postingUsers
FROM users 
JOIN usertweets as U1 ON U1.screenName = users.screenName
JOIN tweets ON tweets.tid = U1.tid AND MONTH(tweets.createdTime) = 1 AND YEAR(tweets.createdTime) = 2016
JOIN users as posters ON posters.screenName = tweets.userScreenName
WHERE posters.subcategory IN('GOP')  
GROUP BY users.screenName
ORDER BY COUNT(U1.tid) DESC
LIMIT 5;

-- Q23
SELECT hashtags.name, COUNT(tweets.tid) as num_uses
FROM hashtags
JOIN tweets ON tweets.tid = hashtags.tid
JOIN users ON users.screenName = tweets.userScreenName 
WHERE users.subcategory IN('GOP') AND (MONTH(tweets.createdTime) = 1 OR MONTH(tweets.createdTime) = 2 OR MONTH(tweets.createdTime) = 3) AND YEAR(tweets.createdTime) = 2016
GROUP BY hashtags.name
ORDER BY num_uses
DESC LIMIT 5;













