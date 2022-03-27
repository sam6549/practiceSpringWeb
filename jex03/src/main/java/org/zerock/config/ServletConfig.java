package org.zerock.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@ComponentScan(basePackages = {"org.zerock.controller"})
public class ServletConfig implements WebMvcConfigurer{
//	@Override
//	public void configureViewResolvers(ViewResolverRegistry registry) {
//		InternalResourceViewResolver bean = new InternalResourceViewResolver();
//		bean.setViewClass(JstlView.class);
//		bean.setPrefix("/WEB-INF/views/");
//		bean.setSuffix(".jsp");
//		registry.viewResolver(bean);
//	}
//	
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
//	}
	
//	@Bean(name= "multipartResolver")
//	public CommonsMultipartResolver getResolver() throws IOException{
//		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//		
//		//10mb
//		resolver.setMaxUploadSize(1024 * 1024 * 10);
//		
//		//2mb
//		resolver.setMaxUploadSizePerFile(1024 * 1024 * 2);
//		
//		//1mb
//		resolver.setMaxInMemorySize(1024 * 1024);
//		
//		//temp upload
//		resolver.setUploadTempDir(new FileSystemResource("c:\\upload\\tmp"));
//		
//		resolver.setDefaultEncoding("UTF-8");
//		
//		return resolver;
//	}
}
