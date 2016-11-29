Select * from SPRINKLER_GROUP;

--DROP TABLE SPRINKLER_GROUP;

--Query 1 to get functional status =true and group given


SELECT * FROM WATER_CONSUMPTION;


SELECT * FROM SPRINKLER_SCHEDULE;

SELECT SPRINKLERID,LOCATION,STARTTIME,ENDTIME  FROM SPRINKLER_SCHEDULE 
WHERE LOCATION = 'North'
AND SCHEDULESTARTDATE <= '24-NOV-16' AND SCHEDULEENDDATE >= '24-NOV-16';

SELECT SCHEDULEMONTH ,LOCATION,SUM(TOTALWATERCONSUMPTION)
FROM SPRINKLER_SCHEDULE
GROUP BY SCHEDULEMONTH,LOCATION
order by location;

select * from SPRINKLER_SCHEDULE ;

SELECT  Distinct LOCATION , STARTTIME, ENDTIME  FROM SPRINKLER_SCHEDULE
    												WHERE  
    												SCHEDULESTARTDATE <= '27-NOV-2016'
    												AND SCHEDULEENDDATE >= '27-NOV-2016';
                
                            
                            
-- drop table    SPRINKLER_SCHEDULE                        
                            

                            
                            








