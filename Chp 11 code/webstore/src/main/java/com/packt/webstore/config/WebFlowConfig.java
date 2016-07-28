package com.packt.webstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.webflow.config.AbstractFlowConfiguration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;

@Configuration
public class WebFlowConfig extends AbstractFlowConfiguration {

   @Bean
   public FlowDefinitionRegistry flowRegistry() {
       return getFlowDefinitionRegistryBuilder()
               .setBasePath("/WEB-INF/flows")
               .addFlowLocationPattern("/**/*-flow.xml")
               .build();
   
   }
   
   @Bean
   public FlowExecutor flowExecutor() {
       return getFlowExecutorBuilder(flowRegistry()).build();
   }
   
   
   @Bean
   public FlowHandlerMapping flowHandlerMapping() {
       FlowHandlerMapping handlerMapping = new FlowHandlerMapping();
       handlerMapping.setOrder(-1);
       handlerMapping.setFlowRegistry(flowRegistry());
       return handlerMapping;
   }

   @Bean
   public FlowHandlerAdapter flowHandlerAdapter() {
       FlowHandlerAdapter handlerAdapter = new FlowHandlerAdapter();
       handlerAdapter.setFlowExecutor(flowExecutor());
       handlerAdapter.setSaveOutputToFlashScopeOnRedirect(true);
       return handlerAdapter;
       }
}
