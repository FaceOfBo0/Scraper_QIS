package OlatAPI;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

public class UserIDByParamCall implements OlatAPICall {

    private final String body;
    private final Integer statusCode;
    private final Connection.Response response;

    public UserIDByParamCall(String pParameter, String pValue) throws IOException {
        String userKeyHandle = "/users?"+pParameter+"=" + pValue;
        this.response = Jsoup.connect(OlatAPIHandler.getApiBaseUrl() + userKeyHandle)
                .method(Connection.Method.GET)
                .header("X-OLAT-TOKEN", OlatAPIHandler.getOlatToken())
                .ignoreContentType(true)
                .execute();
        this.body = this.response.body();
        this.statusCode = this.response.statusCode();
    }

    @Override
    public String getResponseBody() {
        return this.body;
    }

    @Override
    public int getStatusCode() {
        return this.statusCode;
    }

    @Override
    public Connection.Response getResponse() {
        return this.response;
    }
}
