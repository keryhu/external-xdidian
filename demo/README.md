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
	<artifactId>xdidian</artifactId>
	<version>0.0.1</version>
	
如果其他maven需求加入此jar:

先终端 cd 到此项目根目录，执行

> mvn clean install

接着在新建 groupId/artifactId/version/的目录，将
artifactId-version.jar和artifactId-version-source.jar放入此目录下。

第三步： 在github上新建一个地址 external-jar,然后将上面的存放jar的目录上传到此目录，（就可以访问[https://github.com/keryhu/external-jar/tree/master/]()查看到被传入的jar

再：需要被加入的maven项目的pom中加入两端代码。（注意与上面jar的设置一致）

	<!-- 此处增加的是自定义的jar Begin！！ -->
		<dependency>
			<groupId>com.xdidian</groupId>
	        <artifactId>xdidian</artifactId>
			<version>0.0.1</version>
		</dependency>
		<!-- 此处增加的是自定义的jar End！！ -->

			
			
和：（注意url地址）

    <!-- 此处增加的是自定义的jar Begin！！ -->
		<repository>
			<id>project-common</id>
			<name>Project Common</name>
			<url>https://github.com/keryhu/external-jar/tree/master/</url>
		</repository>
		<!-- 此处增加的是自定义的jar End！！ -->


