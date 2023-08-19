package helper;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;

public class OlatApiCall {

    private final String body;
    private final Integer statusCode;
    private final Connection.Response response;

    public OlatApiCall(String pApiUrl, Connection.Method pMethod, String pOlatToken) throws IOException {
        this.response = Jsoup.connect(pApiUrl).method(pMethod).header("X-OLAT-TOKEN", pOlatToken).ignoreContentType(true).execute();
        this.body = this.response.body();
        this.statusCode = this.response.statusCode();
    }

    public String getResponseBody(){
        return this.body;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public Connection.Response getResponse() {
        return response;
    }
}
