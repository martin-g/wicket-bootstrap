package de.agilecoders.wicket;

import org.apache.wicket.util.time.Duration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.bio.SocketConnector;
import org.eclipse.jetty.webapp.WebAppContext;

public class Start {
    public static void main(final String[] args) throws Exception {
        final int timeout = (int) Duration.ONE_HOUR.getMilliseconds();
        
        final Server server = new Server();
        
        final SocketConnector connector = new SocketConnector();
        connector.setMaxIdleTime(timeout);
        connector.setSoLingerTime(-1);
        connector.setPort(8080);
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
