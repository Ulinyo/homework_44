package lesson44;

import com.sun.net.httpserver.HttpExchange;
import common.UrlEncodedUtils;
import server.ContentType;
import server.ResponseCodes;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Map;

public class Lesson45Server extends Lesson44Server{

    public Lesson45Server(String host, int port) throws IOException {
        super(host, port);
        registerGet("/login", this::loginGetHadler);
        registerPost("/login", this::loginPostHandler);
    }

    private void loginPostHandler(HttpExchange exchange) {
        String cType = getContentType(exchange);
        String raw = getBody(exchange);
        String fmt = "<p>raw data: <b>%s</b></p>" +
                "<p>Content-Type: <b>%s</b></p>" +
                "<p>after processed: <b>%s</b></p>";

        Map<String, String> parsed = UrlEncodedUtils.parseUrlEncoded(raw, "&");

        String data = String.format(fmt, raw, cType, parsed);

        try{
            sendByteData(exchange, ResponseCodes.OK, ContentType.TEXT_HTML, data.getBytes());
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void loginGetHadler(HttpExchange exchange) {
        Path path = makeFilePath("login.html");
        sendFile(exchange, path, ContentType.TEXT_HTML);
    }


}
