package OlatAPI;

import org.jsoup.Connection;

public interface OlatAPICall {
    public String getResponseBody();

    public int getStatusCode();

    public Connection.Response getResponse();
}
