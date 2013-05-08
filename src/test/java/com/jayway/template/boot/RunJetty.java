package com.jayway.template.boot;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;

public class RunJetty {
    public static void main(String[] args) throws Exception {
        Server server = new Server();
        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(8080);

        server.addConnector(connector);

        WebAppContext context = new WebAppContext();
        context.setResourceBase("src/main/webapp");
        context.setContextPath("/");
        context.setParentLoaderPriority(true);

        server.setHandler(context);
        server.start();
    }
}
