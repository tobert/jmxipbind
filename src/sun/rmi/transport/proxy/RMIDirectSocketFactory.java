package sun.rmi.transport.proxy;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMISocketFactory;

public class RMIDirectSocketFactory extends RMISocketFactory {
     public Socket createSocket(String host, int port) throws IOException {
          return new Socket(host, port);
     }

     public ServerSocket createServerSocket(int port) throws IOException {
         String jmx_host = System.getProperty("com.sun.management.jmxremote.host");
         String jmx_port = System.getProperty("com.sun.management.jmxremote.port");

        // Allow JMX to bind to specific address
        if (jmx_host != null && jmx_port != null && port != 0 && Integer.toString(port).equals(jmx_port)) {
            InetAddress[] inetAddresses = InetAddress.getAllByName(jmx_host);
            if (inetAddresses.length > 0) {
                return new ServerSocket(port, 50, inetAddresses[0]);
            }
        }

        return new ServerSocket(port);
    }
}
