Activiti
========

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.activiti/activiti-engine/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.activiti/activiti-engine)

Homepage: http://activiti.org/


Activiti is a light-weight workflow and Business Process Management (BPM) Platform targeted at business people, developers and system admins. Its core is a super-fast and rock-solid BPMN 2 process engine for Java. It's open-source and distributed under the Apache license. Activiti runs in any Java application, on a server, on a cluster or in the cloud. It integrates perfectly with Spring, it is extremely lightweight and based on simple concepts. 

Activiti JIRA: https://activiti.atlassian.net

Activiti QA: http://build.activiti.org:8080


一、下载代码
	
	//下载git 地址代码
	1.git clone git@github.com:wsj-study/Activiti.git
	//进入代码目录
	2.cd Activiti
	//基于分支activiti6.0.0 创建自己的分支 study6
	3.git checkout -b study6 activiti-6.0.0
	//编译
	4.mvn clean test-compile
	5.使用idea import project
	
二、  发现不升级用6.0.0也能达到一样的效果(？？？)
	
	1.activiti-6 源码 modules activiti-spring-boot pom 
	<spring.boot.version>1.2.6.RELEASE</spring.boot.version> -><spring.boot.version>2.0.0.RELEASE</spring.boot.version>
	rebuild reimport
	
	2.activiti-spring-boot-starter-basic 
	根据编译报错逐一进行修改 编译 修改  直至compile 通过       mvn clean test-compile
	3.activiti-spring-boot-starter-basic pom.xml 指定自定义版本  <version>6.0.0-boot2.0</version> 
	修改 acitivit-root 的pox.xml ${project.version} -> 6.0.0 指定其版本 从而避免引入6.0.0-boot2.0
	install mvn clean install -DskipTest (保证依赖的jar是6.0.0)
	
	4.activiti-spring-boot-starter-basic 脱离 整个项目时候 依赖的版本还是指定<version>6.0.0-boot2.0</version> 所以指定acitivit 依赖的<version>6.0.0</version>
	