package de.agilecoders.wicket;

import org.apache.wicket.util.time.Duration;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;

public class Start {
    public static void main(final String[] args) {
        final int timeout = (int) Duration.ONE_HOUR.getMilliseconds();

        final Server server = new Server();

        final HttpConfiguration http_config = new HttpConfiguration();

        final ServerConnector connector = new ServerConnector(server, new HttpConnectionFactory(http_config));
        connector.setPort(8080);
        connector.setIdleTimeout(timeout);

        server.addConnector(connector);

        final WebAppContext bb = new WebAppContext();
        bb.setServer(server);
        bb.setContextPath("/");
        bb.setWar("src/main/webapp");
        server.setHandler(bb);

        try {
            System.out.println(">>> STARTING EMBEDDED JETTY SERVER, PRESS ANY KEY TO STOP");
            server.start();
            System.in.read();
            System.out.println(">>> STOPPING EMBEDDED JETTY SERVER");
            server.stop();
            server.join();
        } catch (final Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
