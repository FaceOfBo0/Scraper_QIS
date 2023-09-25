package OlatAPI;

import OlatAPI.JsonParsing.MyCoursesBody;
import OlatAPI.JsonParsing.MyCoursesBodyDeserializer;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class MyCoursesCall implements OlatAPICall {

    private final String body;
    private final Integer statusCode;
    private final Connection.Response response;

    public MyCoursesCall() throws IOException {
        String myCoursesHandle = "/repo/entries/search?myentries=true";
        this.response = Jsoup.connect(OlatAPIHandler.getApiBaseUrl() + myCoursesHandle)
                .method(Connection.Method.GET)
                .header("X-OLAT-TOKEN", OlatAPIHandler.getOlatToken())
                .ignoreContentType(true)
                .execute();
        this.body = this.response.body();
        this.statusCode = this.response.statusCode();
    }

    public String getResponseBody(){
        return this.body;
    }

    public List<MyCoursesBody> getParsedResponseBody() {
        Type listCoursesType = new TypeToken<List<MyCoursesBody>>() {}.getType();
        GsonBuilder gs = new GsonBuilder();
        gs.registerTypeAdapter(MyCoursesBody.class, new MyCoursesBodyDeserializer());
        return gs.create().fromJson(this.body, listCoursesType);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Connection.Response getResponse() {
        return response;
    }
}
