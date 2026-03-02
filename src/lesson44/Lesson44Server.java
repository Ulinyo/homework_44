package lesson44;

import com.sun.net.httpserver.HttpExchange;
import common.UrlEncodedUtils;
import dataModel.BooksDataModel;
import dataModel.ProfileDataModel;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import models.Book;
import models.Employee;
import repository.BookRepository;
import repository.EmployeeRepository;
import server.BasicServer;
import server.ContentType;
import server.Cookie;
import server.ResponseCodes;
import dataModel.BookDataModel;
import dataModel.EmployeeDataModel;

import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Lesson44Server extends BasicServer {
    private final static Configuration freemarker = initFreeMarker();
    private EmployeeRepository employeeRepository = new EmployeeRepository();
    private BookRepository bookRepository = new BookRepository();

    private final Map<String, Employee> sessions = new HashMap<>();

    public Lesson44Server(String host, int port) throws IOException {
        super(host, port);
        registerGet("/books", this::booksHandler);
        registerGet("/book", this::bookHandler);
        registerGet("/employee", this::employeeHandler);

        registerGet("/register", this::registerGetHandler);
        registerPost("/register", this::registerPostHandler);
        registerGet("/login", this::loginGetHadler);
        registerPost("/login", this::loginPostHandler);
        registerGet("/profile", this::profileHandler);

        registerGet("/take-book", this::takeBookHandler);
        registerGet("/return-book", this::returnBookHandler);

        registerGet("/logout", this::logoutHandler);
    }

    private void logoutHandler(HttpExchange exchange) {

        String cookieRaw = getCookies(exchange);
        if (cookieRaw != null) {
            Map<String, String> cookies = Cookie.parse(cookieRaw);
            String sessionId = cookies.get("SessionId");
            if (sessionId != null) {
                sessions.remove(sessionId);
            }
        }
        Cookie deleteCookie = Cookie.make("SessionId", "");
        deleteCookie.setMaxAge(0);
        setCookie(exchange, deleteCookie);

        redirect303(exchange, "/login");
    }

    private void returnBookHandler(HttpExchange exchange) {
        Employee employee = getAuthorizedEmployee(exchange);

        if(employee == null) {
            redirect303(exchange, "/login");
            return;
        }
        Map<String, String> map = getQueryParams(exchange);
        if (!map.containsKey("id")) {
            redirect303(exchange, "/books");
            return;
        }
        int id = Integer.parseInt(map.get("id"));

        Book book = bookRepository.findById(id);

        if(book != null && !book.isAvailable()) {
            if(employee.getEmail().equals(book.getEmployee())) {
                book.returnBook(employee);
                bookRepository.saveToFile();
                employeeRepository.saveToFile();
            }
        }
        redirect303(exchange, "/books");
    }

    private void takeBookHandler(HttpExchange exchange) {
        Employee employee = getAuthorizedEmployee(exchange);

        if(employee == null) {
            redirect303(exchange, "/login");
            return;
        }

        Map<String, String> map = getQueryParams(exchange);
        String idStr = map.get("id");
        if(idStr == null) {
            redirect303(exchange, "/books");
            return;
        }

        int id = Integer.parseInt(idStr);
        Book book = bookRepository.findById(id);

        if(book == null) {
            redirect303(exchange, "/books");
            return;
        }

        if(book.isAvailable() && employee.limitPresentBooks()) {
            book.takeBook(employee);
            bookRepository.saveToFile();
            employeeRepository.saveToFile();
        }
        redirect303(exchange, "/books");
    }

    private Map<String, String> getQueryParams(HttpExchange exchange) {
        String query = exchange.getRequestURI().getQuery();
        if (query == null || query.isBlank()) return Map.of();

        return UrlEncodedUtils.parseUrlEncoded(query, "&");
    }

    private void profileHandler(HttpExchange exchange) {
        Employee employee = getAuthorizedEmployee(exchange);
        if(employee == null) {
            redirect303(exchange, "/login");
            return;
        }
        ProfileDataModel pdm = new ProfileDataModel(employee, bookRepository);
        renderTemplate(exchange, "profile.html", pdm);
    }

    private Employee getAuthorizedEmployee(HttpExchange exchange) {

        String cookieRaw = getCookies(exchange);
        if (cookieRaw == null) return null;
        Map<String, String> cookies = Cookie.parse(cookieRaw);
        String sessionId = cookies.get("SessionId");

        if (sessionId == null) return null;
        Employee sessionEmployee = sessions.get(sessionId);
        if(sessionEmployee == null) return null;

        return employeeRepository.getEmployers().get(sessionEmployee.getEmail());
    }

    private void registerPostHandler(HttpExchange exchange) {
        String raw = getBody(exchange);
        Map<String, String> parsed = UrlEncodedUtils.parseUrlEncoded(raw, "&");
        String email = parsed.get("email");
        String name = parsed.get("user-name");
        String password = parsed.get("user-password");

        boolean isHave = employeeRepository.signUp(password, email, name);
        String message = (isHave) ? "<h2>Вы успешно зарегестрировались </h2>"
                : "<h2>Такой пользователь уже есть </h2><a href='/register'>Попробовать еще раз</a>";
        try{
            sendByteData(exchange, ResponseCodes.OK, ContentType.TEXT_HTML, message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loginPostHandler(HttpExchange exchange) {
        String raw = getBody(exchange);
        Map<String, String> parsed = UrlEncodedUtils.parseUrlEncoded(raw, "&");

        String email = parsed.get("email");
        String password = parsed.get("user-password");

        Employee employee = employeeRepository.findByEmailAndPassword(email, password);

        if (employee != null) {
            String sessionId = UUID.randomUUID().toString();
            sessions.put(sessionId, employee);

            Cookie sessionCookie = Cookie.make("SessionId", sessionId);
            sessionCookie.setHttpOnly(true);
            sessionCookie.setMaxAge(600);
            setCookie(exchange, sessionCookie);

            redirect303(exchange, "/profile");
        } else {
            String message = "<h2>Неверный email или пароль</h2>" +
                    "<a href='/login'>Попробовать еще раз</a>";
            try {
                sendByteData(exchange, ResponseCodes.OK,
                        ContentType.TEXT_HTML,
                        message.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void loginGetHadler(HttpExchange exchange) {
        Path path = makeFilePath("login.html");
        sendFile(exchange, path, ContentType.TEXT_HTML);
    }

    private void registerGetHandler(HttpExchange exchange) {
        Path path = makeFilePath("register.html");
        sendFile(exchange, path, ContentType.TEXT_HTML);
    }

    private static Configuration initFreeMarker() {
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
            // путь к каталогу в котором у нас хранятся шаблоны
            // это может быть совершенно другой путь, чем тот, откуда сервер берёт файлы
            // которые отправляет пользователю
            cfg.setDirectoryForTemplateLoading(new File("data"));

            // прочие стандартные настройки о них читать тут
            // https://freemarker.apache.org/docs/pgui_quickstart_createconfiguration.html
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setWrapUncheckedExceptions(true);
            cfg.setFallbackOnNullLoopVariable(false);
            return cfg;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void employeeHandler(HttpExchange exchange) {
        renderTemplate(exchange, "employee.html", new EmployeeDataModel());
    }

    private void booksHandler(HttpExchange exchange) {
        Employee employee = getAuthorizedEmployee(exchange);
        renderTemplate(exchange, "books.html", new BooksDataModel(bookRepository.getBooks(), employee));
    }

    private void bookHandler(HttpExchange exchange) {
        renderTemplate(exchange, "book.html", new BookDataModel());
    }


    protected void renderTemplate(HttpExchange exchange, String templateFile, Object dataModel) {
        try {
            // Загружаем шаблон из файла по имени.
            // Шаблон должен находится по пути, указанном в конфигурации
            Template temp = freemarker.getTemplate(templateFile);

            // freemarker записывает преобразованный шаблон в объект класса writer
            // а наш сервер отправляет клиенту массивы байт
            // по этому нам надо сделать "мост" между этими двумя системами

            // создаём поток, который сохраняет всё, что в него будет записано в байтовый массив
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            // создаём объект, который умеет писать в поток и который подходит для freemarker
            try (OutputStreamWriter writer = new OutputStreamWriter(stream)) {

                // обрабатываем шаблон заполняя его данными из модели
                // и записываем результат в объект "записи"
                temp.process(dataModel, writer);
                writer.flush();

                // получаем байтовый поток
                var data = stream.toByteArray();

                // отправляем результат клиенту
                sendByteData(exchange, ResponseCodes.OK, ContentType.TEXT_HTML, data);
            }
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    private static String getKeyMap(Map<String, String> map) {
        String key = "";
        for(Map.Entry<String, String> m : map.entrySet()) {
            key = m.getKey();
        }
        return key;
    }
}
