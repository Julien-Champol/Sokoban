@ECHO OFF
:: This script was made to launch fastly the sokoban game in the command prompt
:: used without any arguments
:: Script name : play.bat
:: Version : 1.0
:: Date : 08/16/2021 
:: Author : Julien CHAMPOL
ECHO Launching sokoban.
cd Sokoban\dist\
java -jar Sokoban.jar
PAUSE