set MyProject=C:\RM\project\rolemanagement-cucumber-serenity
cd %MyProject% 
call mvn -Dit.test=CucumberTestSuite clean verify
cd C:\Program Files (x86)\Java\jdk1.8.0_121\bin\
call java -jar C:\RM\project\rolemanagement-cucumber-serenity\emailservice.jar
