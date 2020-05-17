package com.example.ecom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class UserTrackingApplication {

    private int maxUploadSizeInMb = 10 * 1024 * 1024; // 10 MB


    public static void main(String[] args) {
        SpringApplication.run(UserTrackingApplication.class, args);
    }

    @GetMapping("/")
    public String testEndpoint() {
        return "Hello World";
    }

    /* @Bean
     public TomcatEmbeddedServletContainerFactory tomcatEmbedded() {

         TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();

         tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {

             // connector other settings...

             // configure maxSwallowSize
             if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)) {
                 // -1 means unlimited, accept bytes
                 ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
             }

         });

         return tomcat;

     }*/
//    @Bean
//    public ServletWebServerFactory servletContainer() {
//        TomcatServletWebServerFactory Tomcat = new TomcatServletWebServerFactory() {
//            @Override
//            protected void postProcessContext(Context context) {
//                SecurityConstraint securityConstraint = new SecurityConstraint();
//                securityConstraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection collection = new SecurityCollection();
//                collection.addPattern("/*");
//                securityConstraint.addCollection(collection);
//                context.addConstraint(securityConstraint);
//            }
//        };
//        Tomcat.addAdditionalTomcatConnectors(redirectConnector());
//        return Tomcat;
//    }
//
//    private Connector redirectConnector() {
//        Connector connector = new Connector("org.Apache.coyote.http11.Http11NioProtocol");
//        connector.setScheme("http");
//        connector.setPort(8080);
//        connector.setSecure(false);
//        connector.setRedirectPort(8443);
//        return connector;
//    }
}