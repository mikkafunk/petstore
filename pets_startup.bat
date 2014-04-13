@echo Starting the Pet Store sample Database
@echo off

@rem set up H2  
set CP="..\h2\h2-1.3.175.jar"

start javaw -cp %CP% org.h2.tools.Server -tcp -tcpAllowOthers -tcpPort 19092 ^
-tcpPassword "p3tZsh0p" -baseDir "..\h2\pets"

