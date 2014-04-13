@echo Shutting Down Pet Store sample Database
@echo off

java -cp "..\h2\h2-1.3.175.jar" org.h2.tools.Server -tcpShutdown tcp://localhost:19092 -tcpPassword "p3tZsh0p"