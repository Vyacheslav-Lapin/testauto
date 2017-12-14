package com.epam.trainings.testauto.io.server;

import com.epam.trainings.testauto.io.ser.Point;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

// https://habrahabr.ru/post/69136/
@Log4j2
public class HttpServer {

    public static final int PORT = 8080;

    @SneakyThrows
    public static void main(String[] args) {
        try (ServerSocket ss = new ServerSocket(PORT)) {
            while (true) {
                System.out.println("Server is running on port " + PORT);
                System.out.println("Follow the white rabbit: http://localhost:" + PORT + "/");
                Socket s = ss.accept();
                log.info("Client accepted");
                new Thread(new SocketProcessor(s)).start();
            }
        }
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class SocketProcessor implements Runnable {

        private static final ObjectWriter OBJECT_WRITER = new ObjectMapper()
                .writer()
                .withDefaultPrettyPrinter();

        private Socket s;
        private InputStream is;
        private OutputStream os;

//        @SneakyThrows
        private SocketProcessor(Socket s) throws IOException {
            this(s, s.getInputStream(), s.getOutputStream());
        }

        @Override
        @SneakyThrows
        public void run() {
            try {
                readInputHeaders();

                //language=HTML
//                writeResponse("<html><body><h1>Hello from Habrahabr</h1></body></html>");

                String s = OBJECT_WRITER.writeValueAsString(new Point(876, 2));
                writeResponse(s);

            } finally {
                try {
                    s.close();
                } catch (Exception t) { /*do nothing*/ }
            }
            System.err.println("Client processing finished");
        }

        @SneakyThrows
        private void writeResponse(String s) {

//            OBJECT_WRITER.writeValueAsString(
//                    new Food()
//            );

            String response = "HTTP/1.1 200 OK\r\n" +
                    "Server: YarServer/2009-09-09\r\n" +
                    "Content-Type: application/json\r\n" +
                    "Content-Length: " + s.length() + "\r\n" +
                    "Connection: close\r\n\r\n";

            String result = response + s;
            os.write(result.getBytes());
            os.flush();
        }

        @SneakyThrows
        private void readInputHeaders() {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while (true) {
                String s = br.readLine();
                if (s == null || s.trim().length() == 0) {
                    break;
                }
            }
        }
    }
}
