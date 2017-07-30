CLS
ECHO OFF
ECHO ####################################
ECHO Please run with Administrator rights
ECHO ####################################
ECHO .
set CP=optimizing-java-1.0.0-SNAPSHOT.jar
rem set GC_LOG_OPTS="-Xloggc:gc-alloc-`date +%s`.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+PrintTenuringDistribution"
set GC_LOG_OPTS=-Xloggc:gc-alloc-%todaysdate%.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+PrintTenuringDistribution
set MEM_OPTS=-Xmx1G

REM DATE
FOR /F "TOKENS=1 eol=/ DELIMS=/ " %%A IN ('DATE/T') DO SET dd=%%A
FOR /F "TOKENS=1,2 eol=/ DELIMS=/ " %%A IN ('DATE/T') DO SET mm=%%B
FOR /F "TOKENS=1,2,3 eol=/ DELIMS=/ " %%A IN ('DATE/T') DO SET yyyy=%%C
SET todaysdate=%yyyy%_%mm%_%dd%
REM EPOCH TIME
REM Would be nice... 
REM Maybe?..  http://gnuwin32.sourceforge.net/packages/coreutils.htm

if exist "%JAVA_HOM%" goto opt1 ELSE goto opt2

:opt1
set JAVA_CMD=%JAVA_HOME%\bin\
goto exec

:opt2
if exist "%JAVA_BIN%" goto opt3 ELSE goto end
goto exec

:opt3
set JAVA_CMD=JAVA_BIN
goto exec

:end
echo "For this command to run, either $JAVA_HOME must be set, or java must be in the path."
exit 

:exec
ECHO "%JAVA_CMD%"
cd "%JAVA_CMD%"
java -cp %CP% %GC_LOG_OPTS% %MEM_OPTS% optjava.ModelAllocator"


