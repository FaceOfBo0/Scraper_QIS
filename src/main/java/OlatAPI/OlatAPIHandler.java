package OlatAPI;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OlatAPIHandler {

    private final String username;
    private final String password;
    private final String apiAuthUrl;
    private Integer statusCode;
    private String responseBody;
    private Map<String, String> cookies = new HashMap<>(0);
    private Connection.Response response;
    private static String olatToken;
    private static String apiBaseUrl;

    public OlatAPIHandler(String username, String password) throws IOException {
        apiBaseUrl = "https://olat-ce.server.uni-frankfurt.de/olat/restapi";
        this.username = username;
        this.apiAuthUrl = apiBaseUrl + "/auth/" + username;
        this.password = password;
    }

    public void connect() throws IOException {
        this.response = Jsoup.connect(this.apiAuthUrl)
                .data("password", this.password)
                .method(Connection.Method.GET)
                .ignoreContentType(true)
                .execute();

        this.statusCode = response.statusCode();
        this.responseBody = response.body();
        olatToken = response.headers().get("X-OLAT-TOKEN");
        this.cookies = response.cookies();
    }

    public String getResponseBody() {
        return this.responseBody;
    }

    public static String getOlatToken() {
        return olatToken;
    }

    public static String getApiBaseUrl(){ return apiBaseUrl;}

    public String getIdentityKey() throws IOException {
        return response.parse().getElementsByTag("hello").attr("identityKey");
    }

    public String getUsername() {
        return this.username;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public Connection.Response getResponse() {
        return response;
    }

    public Integer getStatusCode() {
        return this.statusCode;
    }
}
