# HTTP Test
The purpose of this project is to explore how to access REST APIs from a Java client.

Note that this program is specific to integration with QAD's Adaptive ERP and includes endpoints and header content (such as x-qad-workspace-id) which are specifid to calling those REST APIs.  It is also helpful in showing the differences between using oath/bearer authentication versus using basic (user/password) authentication.

This program has the following maven dependencies:

	  	<dependency>
			<groupId>org.apache.httpcomponents</groupId>
			<artifactId>httpcore</artifactId>
			<version>4.4.1</version>
		</dependency>
	  	<dependency>
			<groupId>org.apache.httpcomponents</groupId>
			<artifactId>httpclient</artifactId>
			<version>4.5.3</version>
		</dependency>
		<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-databind</artifactId>
			<version>2.9.8</version>
		</dependency>

Which results in the following jar files:

  commons-codec-1.9.jar
  commons-logging-1.2.jar
  httpclient-4.5.3.jar
  httpcore-4.4.1.jar
  jackson-annotatiions-2.9.0.jar
  jackson-core-2.9.8.jar
  jackson-databind-2.9.8.jar
