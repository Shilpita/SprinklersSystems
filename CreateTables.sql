
CREATE TABLE SPRINKLER_GROUP(
      sprinklerID VARCHAR2(5)
      ,location VARCHAR2(10)
      ,functionalStatus VARCHAR2(10)
      ,currentStatus VARCHAR2(10)
      ,waterConsumption VARCHAR2(10)
      ,PRIMARY KEY (SprinklerID)
);

CREATE TABLE SPRINKLER_SCHEDULE(
      scheduleID VARCHAR2(10)
      ,sprinklerID VARCHAR2(5)
      ,scheduleDate Date
      ,startTime TIMESTAMP 
      ,endTime TIMESTAMP
      ,totalHours NUMBER
      ,primary key (scheduleID, sprinklerID)
    ,foreign key (sprinklerID) references SPRINKLER_GROUP(sprinklerID)
    ON DELETE CASCADE
);

CREATE TABLE WATER_CONSUMPTION(

);

