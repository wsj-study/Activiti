package org.activiti.app.ui;

import org.activiti.app.conf.ApplicationConfiguration;
import org.activiti.app.servlet.ApiDispatcherServletConfiguration;
import org.activiti.app.servlet.AppDispatcherServletConfiguration;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.DispatcherType;
import java.util.EnumSet;


@Import(ApplicationConfiguration.class)
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
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
	/*配置 过滤器 open-in-view: true*/
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

}
