

-- Question 3 when k = 5, year = 2016
SELECT COUNT(DISTINCT users.state) AS numStates, group_concat(DISTINCT users.state) as States, hashtags.name
FROM hashtags, tweets, users
WHERE users.state != "" and users.state != "na" and year(tweets.createdTime) = '2016' AND tweets.tid = hashtags.tid
AND users.screenName = tweets.userScreenName 
GROUP BY hashtags.name
ORDER BY numStates DESC
LIMIT 5;

-- Question 7 when k =5, hashtag = "GOPDebate", state = "NC", nonth = 2, year = 2016
SELECT count(tweets.tid), users.screenName, users.category
FROM users
join tweets on users.screenName = tweets.userScreenName
join hashtags on tweets.tid = hashtags.tid
where hashtags.name = "GOPDebate"
and users.state = "NC"
and month(tweets.createdTime) = 2
and year(tweets.createdTime) = 2016
group by users.screenName
order by count(tweets.tid) DESC
limit 5;

-- Question 9 Democrat

SELECT users.screenName, users.subCategory, users.numFollowers
FROM users
WHERE users.subcategory = 'Democrat'
ORDER BY users.numFollowers DESC
LIMIT 5;


-- Question 16 when k = 5, month = 2, year = 2016
SELECT users.screenName, users.category, tweets.text, tweets.retweetCt, urls.address
From users
Join tweets on tweets.userScreenName = users.screenName
Join urls on urls.tid = tweets.tid
Where month(tweets.createdTime) = 2
And year(tweets.createdTime) = 2016
Order by tweets.retweetCt desc
limit 5;

-- Q18
SELECT COUNT(U1.tid), mentioned.screenName AS user_name, mentioned.state AS mentionedUserState, GROUP_CONCAT(DISTINCT posters.screenName) as postingUsers
FROM users as mentioned
JOIN usertweets as U1 on U1.screenName = mentioned.screenName
JOIN tweets ON tweets.tid = U1.tid AND month(tweets.createdTime) = 1 AND year(tweets.createdTime) = 2016
JOIN users as posters on posters.screenName = tweets.userScreenName
WHERE posters.subcategory in('GOP')  
GROUP BY mentioned.screenName
ORDER BY COUNT(U1.tid) DESC
LIMIT 5;

-- Q23
SELECT hashtags.name, COUNT(tweets.tid) as num_uses
FROM hashtags
JOIN tweets ON tweets.tid = hashtags.tid
JOIN users ON users.screenName = tweets.userScreenName 
where users.subCategory in('GOP') AND (month(tweets.createdTime) = 1 OR month(tweets.createdTime) = 2 OR month(tweets.createdTime) = 3) AND year(tweets.createdTime) = 2016
group by hashtags.name
order by num_uses
desc LIMIT 5;













