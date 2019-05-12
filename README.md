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
	
	4.activiti-spring-boot-starter-basic 脱离 整个项目时候 依赖的版本还是指定<version>6.0.0-boot2.0</version> 所以指定acitivit 其他 依赖的<version>6.0.0</version>
	
	
	总结git
		git add . 不加参数默认为将修改操作的文件和未跟踪新添加的文件添加到git系统的暂存区，注意不包括删除
		git add * 当前目录及其子目录下
		git add -u .  -u 表示将已跟踪文件中的修改和删除的文件添加到暂存区，不包括新增加的文件，注意这些被删除的文件被加入到暂存区再被提交并推送到服务器的版本库之后这个文件就会从git系统中消失了。
		git add -A . -A 表示将所有的已跟踪的文件的修改与删除和新增的未跟踪的文件都添加到暂存区。
		
		git commit - m "mesage"
			-a参数可以将所有已跟踪文件中的执行修改或删除操作的文件都提交到本地仓库，即使它们没有经过git add添加到暂存区， 
			注意: 新加的文件（即没有被git系统管理的文件）是不能被提交到本地仓库的。
			
		git push -u origin study6(本地分支名称) 推送到远程仓库
	

	
	git add * 添加当前目录机器子目录下的update 文件
	
三、工作流平台的搭建
	
	1、基于Activiti-ui 工程升级搭建平台
		1.1升级acitivit-spring-boot模块依赖版本
		1.2改造activit-ui为Srping boot 工程省级搭建平台
		1.3基于改造后的acitivit-ui创建workflow工程
	2、开发步骤
		2.1.基于源码Activiti6.0.0版本checkout出新的base分支
		2.2定义acitivit-spring-boot、activiti-ui及子模块版本6.0.0-boot2.0
		2.3基于activiti-ui依赖版本6.0.0-boot2运行activiti-app模块
		
		2.4改造acitiviti-app为spring boot 工程
		2.5升级acitiviti-ui使用acitivit-spring-boot-starter*6.0.0-boot2.0
		2.6启动运行基于spring boot 工程activiti-app
		
		2.7升级activiti-spring-boot依赖boot2.0版本:编译、排错、安装
		2.8重新安装acitivit-ui:编译、排错、安装
		2.9启动运行基于spring boot2工程activiti-app
		
	3.搭建我们自己的工作流平台
		3.1创建新的独立工程workflow
		3.2添加依赖(6.0.0)及配置文件
		3.3集成web相关资源文件
四、操作
	
	4.1 基于源码acitivit-6.0.0 创建两个分支
		git checkout -b v6.0 acitivit-6.0.0
		
		git checkout -b boot acitivit-6.0.0
		
		
	4.2 boot 分支下 activiti-spring-boot 模块下设置版本

		mvn versions:set -DnewVersion=6.0.0-boot2.0(该模块没有设置自己版本号依赖的父版本 手动设置版本号(6.0.0) 重新操作 他的子模块也是依赖的父版本即自己版本)
		
	4.3 安装版本 activiti-spring-boot
		
		mvn clean install source:jar -Dmaven.test.skip=true 
		(安装失败 版本号现在是6.0.0-boot2.0 but activiti依赖的版本还是6.0.0 不是6.0.0-boot2.0 需要手动指定版本6.0.0
		在acitivit-spring-boot directory 下 全局替换${project.version}==> 6.0.0再次执行
		mvn clean install source:jar -Dmaven.test.skip=true 
		安装失败 activiti-spring-boot-starter-basic jar  activit 相关6.0.0-boot2.0找不到 (但是在activiti-spring-boot-starter-basic能向上链接到activiti-root版本 已经指定了对应版本)
		
			在activit-root 新加 <activiti-version>6.0.0</activiti-version> 把 activiti-root.pom 下的 project.version 修改为 activiti-version
			
		mvn clean install source:jar -Dmaven.test.skip=true  安装成功	
		)
	4.4 安装版本 acitivit-ui
	
		1.指定acvitivi-ui 版本 6.0.0
		2.mvn versions:set -DnewVersion=6.0.0-boot2.0
		3.mvn clean install source:jar -Dmaven.test.skip=true
		4.cd acitivit-app       mvn clean tomcat7:run 启动看是否有问题
		
		4.升级改造为springboot工程
			1.activiti-app pom下添加依赖
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-web</artifactId>
				<version>1.2.6.RELEASE</version>
			</dependency>
			 <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>1.2.6.RELEASE</version>
            </plugin>
			2.acitivit-app src 创建java 目录 创建package org.activiti.app.ui(org.activiti.app为原包结构保持一致)
				创建springboot 启动类
					
					@SpringBootApplication
					public class ActivitiUIApplication extends SpringBootServletInitializer {

						public static void main(String[] args) {
							SpringApplication.run(ActivitiUIApplication.class, args);
						}

						@Override
						protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
							return builder.sources(ActivitiUIApplication.class);
						}

						@Bean
						public ServletRegistrationBean apiDispatcher() {
							DispatcherServlet api = new DispatcherServlet();
							//设置 启动用的spring容器 用的是哪个实现类
							api.setContextClass(AnnotationConfigWebApplicationContext.class);
							//对应java配置类是哪个
							api.setContextConfigLocation(ApiDispatcherServletConfiguration.class.getName());
							ServletRegistrationBean registrationBean = new ServletRegistrationBean();
							registrationBean.setServlet(api);
							registrationBean.addUrlMappings("/api/*");
							registrationBean.setLoadOnStartup(1);
							registrationBean.setAsyncSupported(true);
							registrationBean.setName("api");
							return registrationBean;
						}

						@Bean
						public ServletRegistrationBean appDispatcher() {
							DispatcherServlet app = new DispatcherServlet();
							//设置 启动用的spring容器 用的是哪个实现类
							app.setContextClass(AnnotationConfigWebApplicationContext.class);
							//对应java配置类是哪个
							app.setContextConfigLocation(AppDispatcherServletConfiguration.class.getName());
							ServletRegistrationBean registrationBean = new ServletRegistrationBean();
							registrationBean.setServlet(app);
							registrationBean.addUrlMappings("/app/*");
							registrationBean.setLoadOnStartup(1);
							registrationBean.setAsyncSupported(true);
							registrationBean.setName("app");
							return registrationBean;
						}
				
					}



			3.mvn clean spring-boot:run 排错
				1.exclude slf4j
				2.  报错Caused by: java.lang.IllegalStateException: Cannot find changelog location: class path resource [db/changelog/db.changelog-master.yaml] (please add changelog or check your Liquibase configuration)
					解决 禁用 application.yml
						liquibase:
							enabled: false
				3.	报错Caused by: java.lang.IllegalArgumentException: Cannot find template location(s): [classpath:/templates/] (please add some templates, check your FreeMarker configuration, or set spring.freemarker.checkTemplateLocation=false)
					解决 禁用 application.yml
						spring:
						  freemarker:
							check-template-location: false
				4   各种bean 无法装载
					org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'abstractModelHistoryResource': Injection of autowired dependencies failed; nested exception is org.springframework.beans.factory.BeanCreationException: Could not autowire field: protected org.activiti.app.service.api.ModelService org.activiti.app.rest.editor.AbstractModelHistoryResource.modelService; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type [org.activiti.app.service.api.ModelService] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
					Caused by: org.springframework.beans.factory.BeanCreationException: Could not autowire field: protected org.activiti.app.service.api.ModelService org.activiti.app.rest.editor.AbstractModelHistoryResource.modelService; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type [org.activiti.app.service.api.ModelService] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
					Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type [org.activiti.app.service.api.ModelService] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
					Caused by: org.springframework.beans.factory.BeanCreationException: Could not autowire field: protected org.activiti.app.service.api.ModelService org.activiti.app.rest.editor.AbstractModelHistoryResource.modelService; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type [org.activiti.app.service.api.ModelService] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
					Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type [org.activiti.rest.service.api.RestResponseFactory] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
					启动类添加注解
						@ComponentScan(basePackages = {
							"org.activiti.app.conf",
							"org.activiti.app.repository",
							"org.activiti.app.service",
							"org.activiti.app.security",
							"org.activiti.app.model.component"}) 或者 @Import(ApplicationConfiguration.class)
				
				5.activiti-app.properties 中 security.rememberme.key=testkey 属性 和 spring [org.springframework.boot.autoconfigure.security.SecurityProperties]: Bean property 'rememberme[key] 冲突
					
					security.rememberme.key=testkey 修改为 appconf.security.rememberme.key=testkey  全局查找替换 修改其他模块的参数 activiti-app-conf 需要重新安装保证一直
					
					activiti-app 目录下 mvn clean install source:jar -Dmaven.test.skip=true  安装成功	
				
				6.Caused by: java.lang.IllegalArgumentException: Not an managed type: class org.activiti.app.domain.idm.PersistentToken at org.hibernate.ejb.metamodel.MetamodelImpl.managedType(MetamodelImpl.java:200)
					
					添加注解 @EntityScan(basePackages={"org.activiti.app.domain"})
					
				7.
					Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'entityManagerFactory' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaAutoConfiguration.class]: Invocation of init method failed; nested exception is javax.persistence.PersistenceException: [PersistenceUnit: default] Unable to build EntityManagerFactory

					Caused by: javax.persistence.PersistenceException: [PersistenceUnit: default] Unable to build EntityManagerFactory
							at org.hibernate.ejb.Ejb3Configuration.buildEntityManagerFactory(Ejb3Configuration.java:925)
							at org.hibernate.ejb.Ejb3Configuration.buildEntityManagerFactory(Ejb3Configuration.java:900)
						...好多
					Caused by: org.hibernate.cache.NoCacheRegionFactoryAvailableException: Second-level cache is used in the application, but property hibernate.cache.region.factory_class is not given, please either disable second level cache or set correct region factory class name to property hibernate.cache.region.factory_class (and make sure the second level cache provider, hibernate-infinispan, for example, is available in the classpath).
						at org.hibernate.cache.internal.NoCachingRegionFactory.buildEntityRegion(NoCachingRegionFactory.java:69)
						at org.hibernate.internal.SessionFactoryImpl.<init>(SessionFactoryImpl.java:352)
						at org.hibernate.cfg.Configuration.buildSessionFactory(Configuration.java:1799)
						at org.hibernate.ejb.EntityManagerFactoryImpl.<init>(EntityManagerFactoryImpl.java:96)
						at org.hibernate.ejb.Ejb3Configuration.buildEntityManagerFactory(Ejb3Configuration.java:915)
	
				解决  修改 org.activiti.app.conf.DatabaseConfiguration  返回值 LocalContainerEntityManagerFactoryBean 而不是接口  （没明白 不是一样的吗 别的地方也有这个类 内容）
				
					@Bean(name = "entityManagerFactory")
					  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
						log.info("Configuring EntityManager");
						LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
						lcemfb.setPersistenceProvider(new HibernatePersistence());
						lcemfb.setPersistenceUnitName("persistenceUnit");
						lcemfb.setDataSource(dataSource());
						lcemfb.setJpaDialect(new HibernateJpaDialect());
						lcemfb.setJpaVendorAdapter(jpaVendorAdapter());

						Properties jpaProperties = new Properties();
						jpaProperties.put("hibernate.cache.use_second_level_cache", false);
						jpaProperties.put("hibernate.generate_statistics", env.getProperty("hibernate.generate_statistics", Boolean.class, false));
						lcemfb.setJpaProperties(jpaProperties);

						lcemfb.setPackagesToScan("org.activiti.app.domain");
						lcemfb.afterPropertiesSet();
						return lcemfb;
					  }

					 @Bean(name = "transactionManager")
					  public PlatformTransactionManager annotationDrivenTransactionManager() {
						JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
						jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
						return jpaTransactionManager;
					  }

					修改了子模块代码 重新安装 mvn clean install source:jar -Dmaven.test.skip=true
					
					启动成功

				8.添加依赖
					<dependency>
						<groupId>org.springframework.boot</groupId>
						<artifactId>spring-boot-starter-actuator</artifactId>
						<version>1.2.6.RELEASE</version>
					</dependency>

					<dependency>
						<groupId>org.springframework.boot</groupId>
						<artifactId>spring-boot-starter-hateoas</artifactId>
						<version>1.2.6.RELEASE</version>
					</dependency>
					重新启动 报错
					Caused by: org.springframework.beans.factory.BeanCreationException: Could not autowire field: private com.fasterxml.jackson.databind.ObjectMapper org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration$HypermediaConfiguration$HalObjectMapperConfiguration.primaryObjectMapper; nested exception is org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type [com.fasterxml.jackson.databind.ObjectMapper] is defined: expected single matching bean but found 2: objectMapper,_halObjectMapper
						
						有两个 objectMapper  去掉一个 org.activiti.app.conf.JacksonConfiguration 注释掉这个类
						重新安装  mvn clean install source:jar -Dmaven.test.skip=true 继续启动




				9====================升级acitiviti-ui使用acitivit-spring-boot-starter*6.0.0-boot2.0
				
					1.acitivit-app 添加依赖
						<dependency>
							<groupId>org.activiti</groupId>
							<artifactId>activiti-spring-boot-starter-basic</artifactId>
							<version>6.0.0</version>
						</dependency>
					
					
					Caused by: org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.activiti.spring.SpringProcessEngineConfiguration]: Factory method 'springProcessEngineConfiguration' threw exception; nested exception is java.io.FileNotFoundException: class path resource [processes/] cannot be resolved to URL because it does not exist
						at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:189)
						at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:588)
						... 139 more
					Caused by: java.io.FileNotFoundException: class path resource [processes/] cannot be resolved to URL because it does not exist
						
						解决1、resource 下 创建 drectory processes 放一个流程定义文件
							2、application.yml 禁用检查
								activiti:
									check-process-definitions: false

					2.运行
						
						Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'rememberMeServices': Injection of autowired dependencies failed; nested exception is org.springframework.beans.factory.BeanCreationException: Could not autowire field: private org.activiti.app.security.CustomUserDetailService org.activiti.app.security.CustomPersistentRememberMeServices.customUserDetailService; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type [org.activiti.app.security.CustomUserDetailService] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
					
						Caused by: org.springframework.beans.factory.BeanCreationException: Could not autowire field: private org.activiti.app.security.CustomUserDetailService org.activiti.app.security.CustomPersistentRememberMeServices.customUserDetailService; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type [org.activiti.app.security.CustomUserDetailService] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}

						Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type [org.activiti.app.security.CustomUserDetailService] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}

						解决排除 activiti 的 SecurityAutoConfiguration @SpringBootApplication(exclude = {org.activiti.spring.boot.SecurityAutoConfiguration.SecurityAutoConfiguration.class})
					3.运行
						
						java.lang.IllegalArgumentException: Unsupported scheduler type: class org.springframework.core.task.SimpleAsyncTaskExecutor

						
						Caused by: java.lang.IllegalArgumentException: Unsupported scheduler type: class org.springframework.core.task.SimpleAsyncTaskExecutor
							at org.springframework.scheduling.config.ScheduledTaskRegistrar.setScheduler(ScheduledTaskRegistrar.java:93)
							
							at org.activiti.app.conf.SchedulingConfiguration.configureTasks(SchedulingConfiguration.java:35)  ====》 SchedulingConfiguration
							
						
						解决 定时任务冲突 org.activiti.app.conf.SchedulingConfiguration 注释掉这个类
							重新安装
							mvn clean install source:jar -Dmaven.test.skip=true

	
				
	
	
	
	
	
	
	
	
	
	
	
		