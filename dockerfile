FROM java:8
EXPOSE 8080
ADD /CDB/target/com.excilys.computerdb-0.0.4.jar computerdb.jar
ENTRYPOINT ["java","-jar","demo.jar"]
