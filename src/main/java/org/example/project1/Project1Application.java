//package org.example.project1;
//
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//
//@SpringBootApplication
//public class Project1Application  {
//
//
//
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder app) {
//        return app.sources(Project1Application.class);
//    }
//    public static void main(String[] args) {
//
//        SpringApplication.run(Project1Application.class, args);
//    }
//}


//package org.example.project1;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//
//@SpringBootApplication
//public class Project1Application extends SpringBootServletInitializer {
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder app) {
//        return app.sources(Project1Application.class);
//    }
//
//    public static void main(String[] args) {
//        SpringApplication.run(Project1Application.class, args);
//
//    }
//
//}

package org.example.project1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Project1Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder app) {
        return app.sources(Project1Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Project1Application.class, args);
    }

    //debug purpose to check whether controller works
    @Bean
    public CommandLineRunner checkBeans(ApplicationContext ctx) {
        return args -> {
            System.out.println("==== Registered beans ====");
            for (String name : ctx.getBeanDefinitionNames()) {
                System.out.println(name);
            }
        };
    }
}

