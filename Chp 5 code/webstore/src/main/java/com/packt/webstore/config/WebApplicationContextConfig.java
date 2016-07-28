package com.packt.webstore.config;

import java.util.ArrayList;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MarshallingView;
import org.springframework.web.util.UrlPathHelper;

import com.packt.webstore.domain.Product;

@Configuration
@EnableWebMvc
@ComponentScan("com.packt.webstore")
public class WebApplicationContextConfig extends WebMvcConfigurerAdapter {

     @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
 
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");

        return resolver;
    }
    
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
       UrlPathHelper urlPathHelper = new UrlPathHelper();
       urlPathHelper.setRemoveSemicolonContent(false);

       configurer.setUrlPathHelper(urlPathHelper);
    }
    
    @Bean
    public MessageSource messageSource() { 
       ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
       resource.setBasename("messages");
       return resource;    
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry.addResourceHandler("/img/**")
              .addResourceLocations("/resources/images/");
    }
    
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver=new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        return resolver;
    }
    
    @Bean
    public MappingJackson2JsonView jsonView() {
       MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
       jsonView.setPrettyPrint(true);
       
       return jsonView; 
    }
    
    @Bean
    public MarshallingView xmlView() {
       Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
       marshaller.setClassesToBeBound(Product.class);
       
       MarshallingView xmlView = new MarshallingView(marshaller);
       return xmlView;
    }
    
    @Bean
    public ViewResolver contentNegotiatingViewResolver(
             ContentNegotiationManager manager) {
       ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
       resolver.setContentNegotiationManager(manager);
          
       ArrayList<View>   views = new ArrayList<>();
       views.add(jsonView());
       views.add(xmlView());
          
       resolver.setDefaultViews(views);
             
       return resolver;
    }



}
