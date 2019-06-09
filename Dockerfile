FROM javergarav/tomee

COPY env/tomee.xml conf/tomee.xml
COPY env/server.xml conf/server.xml

COPY target/students-ms.war webapps/students-ms.war

EXPOSE 3001
