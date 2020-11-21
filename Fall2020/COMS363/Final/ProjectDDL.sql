SET FOREIGN_KEY_CHECKS=0;
drop table if exists Users;
drop table if exists Tweets;
drop table if exists Hashtags;
drop table if exists Urls;
drop table if exists UserTweets;

Create Table Users(
screenName varchar(100) NOT NULL,
name varchar(100),
numFollowers int,
numFollowing int,
category varchar(40) CHECK (category IN( 'senate_group', 'presidential_candidate', 'reporter', 'Senator', 'General', null)),
subcategory varchar(40) CHECK(subcategory IN('GOP', 'Democrat', 'na', null)),
state varchar(40) default 'na',
Primary Key(screenName)
);


Create Table Tweets(
tid bigint NOT NULL,
text varchar(300) NOT NULL,
retweetCt int,
createdTime date NOT NULL,
userScreenName varchar(50) NOT NULL,
Primary key(tid),
Foreign Key(userScreenName) References Users(screenName) ON DELETE CASCADE
);

Create Table Hashtags(
name varchar(40) NOT NULL,
tid bigint NOT NULL,
PRIMARY KEY( name, tid ),
FOREIGN KEY(tid) references tweets(tid) ON DELETE CASCADE
);

Create Table Urls(
address varchar(600) NOT NULL,
tid bigint NOT NULL,
Primary Key(address),
Foreign Key(tid) References Tweets(tid)
);

Create Table UserTweets(
tid bigint NOT NULL,
screenName varchar(100) NOT NULL,
Primary Key(tid, screenName),
Foreign Key(tid) References Tweets(tid),
Foreign Key(screenName) References Users(screenName)
);




