package com.easy.arch.config;

//import com.easy.arch.config.filter.XSSFilter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
//                DispatcherServlet.class,
                ServletConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
//    @Override
//    protected Filter[] getServletFilters() {
//        return new Filter[]{
//                new CharacterEncodingFilter("UTF-8", true, true),
//                           new XSSFilter("/;/scripts/*;/styles/*;/images/*")
//
//        };
//    }
//
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
//        context.register(ServletConfig.class);
//        ServletRegistration.Dynamic dispathcer=servletContext.addServlet("dispatcher",new DispatcherServlet());
//        dispathcer.addMapping("/");
//        dispathcer.setLoadOnStartup(1);
//    }
}
