package helper;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OlatApiConnector {

    private final String username;
    private final String password;
    private final String apiAuthUrl;
    private Integer statusCode;
    private String responseBody;
    private Map<String, String> cookies = new HashMap<>(0);
    private Connection.Response response;

    public OlatApiConnector(String restApiPath, String username, String password){
        this.username = username;
        this.apiAuthUrl = restApiPath + "/auth/" + username;
        this.password = password;
    }

    public void connect() throws IOException {
        this.response = Jsoup.connect(this.apiAuthUrl)
                .data("password", this.password)
                .method(Connection.Method.GET)
                .ignoreContentType(true)
                .execute();

        this.statusCode = this.response.statusCode();
        this.responseBody = this.response.body();

        this.cookies = this.response.cookies();
    }

    public String getResponseBody() {
        return this.responseBody;
    }

    public String getIdentityKey() throws IOException {
        return this.response.parse().getElementsByTag("hello").attr("identityKey");
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public Connection.Response getResponse() {
        return this.response;
    }

    public Integer getStatusCode() {
        return this.statusCode;
    }
}
