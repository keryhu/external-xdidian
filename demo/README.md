this is my custom jar file

function:  two class  
1: stringUtil  验证传入的参数是何种格式，支持email，phone，uuid，公司名字，人名的验证
2： securityUtils，验证当前用户的登录名，权限，登录用户的IP地址，是否已经登陆。
3:  增加全局的model，user
4:  增加全局的Role
5: 增加了java8 time json 序列号和反序列化
6: 增加了GrantedAuthority 的反序列化
7  取出了UserDetails的 implements

为了让这个application中加入的spring 依赖，不影响到加入到的其它项目，所有这个jar中的依赖scope都设置为

	<scope>provided</scope>


此application的 pom


	<groupId>com.xdidian</groupId>
	<artifactId>xdidian-share</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	
如果其他maven需求加入此jar:

第一步在此jar的pom.xml中增加：

	<distributionManagement>
	    <repository>
	        <id>internal.repo</id>
	        <name>Temporary Staging Repository</name>
	        <url>file://${project.build.directory}/mvn-repo</url>
	    </repository>
	</distributionManagement>
	
	<plugins>
	    <plugin>
	        <artifactId>maven-deploy-plugin</artifactId>
	        <version>2.8.2</version>
	        <configuration>
	            <altDeploymentRepository>internal.repo::default::file://${project.build.directory}/mvn-repo</altDeploymentRepository>
	        </configuration>
	    </plugin>
	</plugins>


第二步： 设置brew 安装的 maven 的setting..xml文件，github帐号，密码

文件地址是： /usr/local/Cellar/maven/3.3.9/libexec/conf/settings.xml

	<settings>
	  <servers>
	    <server>
	      <id>github</id>
	      <username>YOUR-USERNAME</username>
	      <password>YOUR-PASSWORD</password>
	    </server>
	  </servers>
	</settings>

此pom.xml 中增加：

	<properties>
	  <github.global.server>github</github.global.server>
	</properties>

再增加：

	<build>
	    <plugins>
	        <plugin>
	            <groupId>com.github.github</groupId>
	            <artifactId>site-maven-plugin</artifactId>
	            <version>0.12</version>
	            <configuration>
	                <message>Maven artifacts for ${project.version}</message>  <!-- git commit message -->
	                <noJekyll>true</noJekyll>                                  <!-- disable webpage processing -->
	                <outputDirectory>${project.build.directory}/mvn-repo</outputDirectory> <!-- matches distribution management repository url above -->
	                <branch>refs/heads/mvn-repo</branch>                       <!-- remote branch name -->
	                <includes><include>**/*</include></includes>
	                <repositoryName>YOUR-REPOSITORY-NAME</repositoryName>      <!-- github repo name -->
	                <repositoryOwner>YOUR-GITHUB-USERNAME</repositoryOwner>    <!-- github username  -->
	            </configuration>
	            <executions>
	              <!-- run site-maven-plugin's 'site' target as part of the build's normal 'deploy' phase -->
	              <execution>
	                <goals>
	                  <goal>site</goal>
	                </goals>
	                <phase>deploy</phase>
	              </execution>
	            </executions>
	        </plugin>
	    </plugins>
	</build>

运行语句

> mvn clean deploy 将上传文件到github



最好在需要加载此依赖的jar的pom.xml文件中加上－－本jar 的

	<groupId>com.xdidian</groupId>
		<artifactId>xdidian-share</artifactId>
		<version>0.0.1-SNAPSHOT</version>
	
和：

	<repository>
				<id>external-xdidian</id>
				<url>https://raw.github.com/keryhu/external-xdidian/mvn-repo/</url>
				<snapshots>
					<enabled>true</enabled>
					<updatePolicy>always</updatePolicy>
				</snapshots>
			</repository>


