// Based on https://stackoverflow.com/a/3732328

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.InetAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class HelloServer {

    public static void main(String[] args) throws Exception {
        String addr = "0.0.0.0";
	Integer port = 8000;
        HttpServer server = HttpServer.create(new InetSocketAddress(InetAddress.getByName(addr), port), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null); // creates a default executor
	System.out.printf("Listening on: %s:%d\n", addr, port);
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            System.out.printf("Received request: %s\n", t.getRequestHeaders().values());
            String response = "Hello World\n";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

}
