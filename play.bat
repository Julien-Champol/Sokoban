@ECHO OFF
:: This script was made to launch fastly the sokoban game in the command prompt
:: used without any arguments
:: Script name : play.bat
:: Version : 1.0
:: Date : 08/16/2021 
:: Author : Julien CHAMPOL
ECHO Launching sokoban.
IF EXIST "Sokoban\dist\Sokoban.jar" (
    java -jar  Sokoban\dist\Sokoban.jar
) ELSE (
    ECHO Not able to launch.
)
PAUSE