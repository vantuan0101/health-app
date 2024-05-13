# health-app
https://www.geeksforgeeks.org/java-spring-boot-microservices-integration-of-eureka-and-spring-cloud-gateway/


- Public Access Repository:
  ```xml
  <repository>
		<id>framework</id>
		<url>https://raw.github.com/vantuan0101/repo/main/</url>
	</repository>
  ```

- Private Access Repository with Github Secret:
  ```xml
		<repository>
			<id>github</id>
			<url>https://maven.pkg.github.com/vantuan0101/repo</url>
		</repository>
  ```

  Config `settings.xml`
    ```xml
      <settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                            http://maven.apache.org/xsd/settings-1.0.0.xsd">

        <activeProfiles>
          <activeProfile>github</activeProfile>
        </activeProfiles>

        <profiles>
          <profile>
            <id>github</id>
            <repositories>
              <repository>
                <id>github</id>
                <url>https://maven.pkg.github.com/vantuan0101/repo</url>
                <snapshots>
                  <enabled>true</enabled>
                </snapshots>
              </repository>
            </repositories>
          </profile>
        </profiles>

        <servers>
          <server>
            <id>github</id>
            <username>USER_NAME</username>
            <password>TOKEN</password>
          </server>
        </servers>
      </settings>
  ```