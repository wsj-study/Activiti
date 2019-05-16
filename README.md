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
	
    
                    10、
    					2.7升级activiti-spring-boot依赖boot2.0版本:编译、排错、安装
    					2.8重新安装acitivit-ui:编译、排错、安装
    					2.9启动运行基于spring boot2工程activiti-app
    	
    					
    					1.基于boot分支 创建分支 boot2
    						
    						1.1git checkout -b boot2
    						
    						1.2cd  activiti-spring-boot 目录下 mvn versions:set -DnewVersion=6.0.0-boot2.0 修改版本成功
    						
    						1.3修改activiti-spring-boot pom <spring.boot.version>1.2.6.RELEASE</spring.boot.version> =》<spring.boot.version>2.0.0.RELEASE</spring.boot.version>
    						
    							add <spring.framework.version>5.0.4.RELEASE</spring.framework.version>
    								<spring.security.version>5.0.3.RELEASE</spring.security.version>
    						1.4 mvn clean test-compile
    							
    							activiti-spring-boot 右键 git 跟 study6 比较 修改代码
    							
    							mvn clean test-compile 
    							
    							删除包结构重新引入(不断)
    						1.5 mvn clean install source:jar  -Dmaven.test.skip=true	(安装)
    						
    				11.重新安装acitivit-ui:编译、排错、安装 升级为boot2.0
    					
    					1.cd activiti-ui 下
    						修改版本 mvn versions:set -DnewVersion=6.0.0-boot2.0
    						
    						activiti-ui-root.pom 修改
    						
    						<activiti.version>6.0.0</activiti.version>
    
    						<spring.boot.version>2.0.0.RELEASE </spring.boot.version>
    						<spring.version>5.0.4.RELEASE </spring.version>
    						<spring.security.version>5.0.3.RELEASE</spring.security.version>
    						<hibernate.version>5.2.14.Final</hibernate.version>
    						<jackson.version>2.9.4</jackson.version>
    						<codahale.metrics.version>3.0.2</codahale.metrics.version>
    						<hazelcastcache.version>3.1.5</hazelcastcache.version>
    						<batik.version>1.7</batik.version>
    						<jetty.version>9.1.3.v20140225</jetty.version>
    						
    						<dependency>
    							<groupId>org.liquibase</groupId>
    							<artifactId>liquibase-core</artifactId>
    							<version>3.6.1</version>
    						</dependency>
    						
    						<dependency>
    							<groupId>javax.validation</groupId>
    							<artifactId>validation-api</artifactId>
    							<version>2.0.1.Final</version>
    						</dependency>
    						
    						<dependency>
    							<groupId>org.jadira.usertype</groupId>
    							<artifactId>usertype.core</artifactId>
    							<version>6.0.1.GA</version>
    						</dependency>
    						
    						<dependency>
    							<groupId>org.springframework.data</groupId>
    							<artifactId>spring-data-jpa</artifactId>
    							<version>2.0.5.RELEASE</version>
    						</dependency>
    						
    						 <dependency>
    							<groupId>org.freemarker</groupId>
    							<artifactId>freemarker</artifactId>
    							<version>2.3.28</version>
    						</dependency>
    					2.mvn clean test-compile
    						activiti-app-logic 下 全文替换 findOne ==> getOne (改变了)
    						
    						构造 适合的参数
    						Model model = new Model();
    						model.setId(appModelDefinition.getId());
    						Example<Model> example = Example.of(model);
    	
    						modelRelationRepository.delete(persistedModelRelations);  ==》 modelRelationRepository.deleteAll(persistedModelRelations);
    						
    							构造适合的参数  捕获异常
    																		 try {
    							d = ISO8601Utils.parse(date);   =====》			d = ISO8601Utils.parse(date,new ParsePosition(0));
    																		} catch (ParseException e) {
    																		  e.printStackTrace();
    																		}
    						org.activiti.app.conf.DatabaseConfiguration 注解  移动到org.activiti.app.conf.ApplicationConfiguration 类上   再添加 @EntityScan(basePackages={"org.activiti.app.domain"}) (导入spring-boot-autoconfigure) jar
    																		配置文件关闭 2级缓存 然后注释掉这个类
    																		
    																		@EnableJpaRepositories({ "org.activiti.app.repository" })
    																		@EnableTransactionManagement
    	
    						
    						org.activiti.app.conf.ActivitiEngineConfiguration 下拷贝  到    org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration  springRejectedJobsHandler() 方法尾部 导入对用jar
    						
    														FormEngineConfiguration formEngineConfiguration = new FormEngineConfiguration();
    									formEngineConfiguration.setDataSource(dataSource);
    									
    									FormEngineConfigurator formEngineConfigurator = new FormEngineConfigurator();
    									formEngineConfigurator.setFormEngineConfiguration(formEngineConfiguration);
    									processEngineConfiguration.addConfigurator(formEngineConfigurator);
    									
    									DmnEngineConfiguration dmnEngineConfiguration = new DmnEngineConfiguration();
    									dmnEngineConfiguration.setDataSource(dataSource);
    								  
    								  DmnEngineConfigurator dmnEngineConfigurator = new DmnEngineConfigurator();
    								  dmnEngineConfigurator.setDmnEngineConfiguration(dmnEngineConfiguration);
    								  processEngineConfiguration.addConfigurator(dmnEngineConfigurator);
    	 
    						复制3个方法 改造
    						
    						@Bean	
    						public FormRepositoryService formEngineRepositoryService() {
    						  return processEngine().getFormEngineRepositoryService();
    						}
    						
    						@Bean
    						public org.activiti.form.api.FormService formEngineFormService() {
    						  return processEngine().getFormEngineFormService();
    						}
    						
    						Bean(name="clock")
    						@DependsOn("processEngine")
    						public Clock getClock() {
    							return processEngineConfiguration().getClock();
    						}
    						
    						||
    						||
    						
    						  @Bean
    						  @ConditionalOnMissingBean
    						  public FormRepositoryService formEngineRepositoryService(ProcessEngine processEngine) {
    							return processEngine.getFormEngineRepositoryService();
    						  }
    
    						  @Bean
    						  @ConditionalOnMissingBean
    						  public org.activiti.form.api.FormService formEngineFormService(ProcessEngine processEngine) {
    							return processEngine.getFormEngineFormService();
    						  }
    
    						  @Bean
    						  @ConditionalOnMissingBean
    						  public Clock clock(SpringProcessEngineConfiguration configuration) {
    							return configuration.getClock();
    						  }
    						
    						注释掉 ActivitiEngineConfiguration 类
    					3.切换到activiti-spring-boot(修改代码了) 安装 mvn clean install source:jar -Dmaven.test.skip=true
    						切换到activiti-ui  编译 mvn clean test-compile
    						
    						mvn clean install source:jar -Dmaven.test.skip=true
    						mvn clean test-compile
    						
    						
    						启动acitivit-app 报错版本冲突
    							activiti-app.pom 1.2.6修改为2.0.0 
    							activiti-spring-boot-starter-basic 版本 6.0.0修改为6.0.0-boot2.0
    							在修改入口类  ===》包结构变化
    						运行activiti-app
    						
    							日志jar 冲突 排除 activiti-app.pox 引入的6.0.0-boot2.0 的 slf4j-log4j12
    							再次启动  成功
    							
    							登录网页报错
    							http://localhost:9999/activiti-app/
    							
    							解决
    							后台空指针异常 进入 发现打印日志报的错(spring 1.2.6 参数为 false 2.0.0 true log 为 final类 使用cglib 子类不能复写final class 的)
    								修改配置文件添加
    									aop:
    										proxy-target-class: false
    							在此启动 acitivit-app
    							http://localhost:9999/activiti-app/报错  no Session
    							
    							org.hibernate.LazyInitializationException: could not initialize proxy - no Session
    							at org.hibernate.proxy.AbstractLazyInitializer.initialize(AbstractLazyInitializer.java:155)
    							at org.hibernate.proxy.AbstractLazyInitializer.getImplementation(AbstractLazyInitializer.java:268)
    							at org.hibernate.proxy.pojo.javassist.JavassistLazyInitializer.invoke(JavassistLazyInitializer.java:73)
    							
    							解决 读取懒加载 session 可能关闭  打开open session view 过滤器
    							(默认是true 手动设置) 并且配置对用的bean 启动类里面配置
    
    							open-in-view: true
    							
    							@Bean
    							public FilterRegistrationBean openEntityManagerInViewFilter(){
    								FilterRegistrationBean bean = new FilterRegistrationBean(new OpenEntityManagerInViewFilter());
    								bean.addUrlPatterns("/*");
    								bean.setName("openEntityManagerInViewFilter");
    								bean.setOrder(-200);
    								bean.setDispatcherTypes(EnumSet.of(DispatcherType.REQUEST,
    										DispatcherType.FORWARD,
    										DispatcherType.ASYNC));
    								return bean;
    							}
    													(自动 生成sql语句 程序启动的时候创建) 
    							generate-ddl: true
    							
    							
    							org.activiti.app.security.CustomPersistentRememberMeServices 修改 
    							
    							if (token == null) {
    							  // No series match, so we can't authenticate using this cookie
    							  throw new RememberMeAuthenticationException("No persistent token found for series id: " + presentedSeries);
    							}
    							修改为
    							try {
    							  if (token == null || token.getTokenValue() ==null) {
    								// No series match, so we can't authenticate using this cookie
    								throw new RememberMeAuthenticationException("No persistent token found for series id: " + presentedSeries);
    							  }
    							}catch (Exception e){
    							  
    							  throw new RememberMeAuthenticationException("No persistent token found for series id: " + presentedSeries);
    
    							}
    							
    							重新安装 acitivit-ui mvn clean install source:jar -Dmaven.test.skip=true
    							
    							再次启动
    							
    					4.配置activiti-ui 页面 在不使用tomcat 容器访问路径问题		
    							1、scripts/common/services/runtime-app-definition-service.js 文件下
    								
    								var urls = {
    									editor: baseUrl + '/editor/',
    									identity: baseUrl + '/idm/',
    									workflow: baseUrl + '/workflow/',
    									admin: 'http://localhost:8080/activiti-admin',
    									analytics: baseUrl + '/analytics/'
    								};
    								修改为
    								
    								var urls = {
    									editor: baseUrl + '/editor/index.html',
    									identity: baseUrl + '/idm/index.html',
    									workflow: baseUrl + '/workflow/index.html',
    									admin: 'http://localhost:8080/activiti-admin',
    									analytics: baseUrl + '/analytics/'
    								};
    							
    							
    							
	
				
	
	
	
	
	
	
	
	
	
	
	
		