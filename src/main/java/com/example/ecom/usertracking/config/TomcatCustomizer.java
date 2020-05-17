package com.example.ecom.usertracking.config;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
public class TomcatCustomizer implements
        WebServerFactoryCustomizer<TomcatServletWebServerFactory> {
    private int maxUploadSizeInMb = 10 * 1024 * 1024; // 10 MB

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        factory.addConnectorCustomizers(this::customize);
    }

    private void customize(final Connector connector) {

        if (connector.getProtocolHandler() instanceof AbstractHttp11Protocol) {
            ((AbstractHttp11Protocol<?>) connector
                    .getProtocolHandler())
                    .setMaxSwallowSize(maxUploadSizeInMb);
        }
    }

    private Connector redirectConnector() {
        Connector connector = new Connector("org.Apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(8080);
        connector.setSecure(false);
        connector.setRedirectPort(8443);
        return connector;
    }
}