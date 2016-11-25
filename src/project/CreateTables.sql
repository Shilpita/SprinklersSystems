
CREATE TABLE SPRINKLER_GROUP(
      sprinklerID VARCHAR2(5)
      ,location VARCHAR2(10)
      ,functionalStatus VARCHAR2(10)
      ,currentStatus VARCHAR2(10)
      ,waterConsumption VARCHAR2(10)
      ,PRIMARY KEY (SprinklerID)
);

CREATE TABLE SPRINKLER_SCHEDULE(
       scheduleName VARCHAR2(10)
      ,sprinklerID VARCHAR2(5)
      ,LOCATION VARCHAR2(10)
      ,scheduleStartDate Date
      ,scheduleEndDate Date
      ,SCHEDULEMONTH VARCHAR2(10)
      ,startTime VARCHAR2(20)
      ,endTime VARCHAR2(20)
      ,totalHours NUMBER
      ,totalDays NUMBER
      ,waterFlow VARCHAR2(10)
      ,TOTALWATERCONSUMPTION NUMBER
 --     ,primary key (scheduleID, sprinklerID)
      ,foreign key (sprinklerID) references SPRINKLER_GROUP(sprinklerID)
      ON DELETE CASCADE
);

--DROP TABLE SPRINKLER_SCHEDULE;

--CREATE TABLE WATER_CONSUMPTION(
--
--);

