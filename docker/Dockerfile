FROM tomcat:8.5-jdk8-adoptopenjdk-hotspot
MAINTAINER yungyu

WORKDIR /usr/local/tomcat/webapps

RUN rm -rf ./*

COPY ROOT.war .
