package OlatAPI.JsonParsing;

import com.google.gson.*;

import java.lang.reflect.Type;

public class MyCoursesBodyDeserializer implements JsonDeserializer<MyCoursesBody> {
    @Override
    public MyCoursesBody deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jObject = jsonElement.getAsJsonObject();
        long keyValue = jObject.get("key").getAsLong();
        long idValue = jObject.get("resourceableId").getAsLong();
        return new MyCoursesBody(keyValue, jObject.get("softkey").getAsString(), jObject.get("resourcename").getAsString(),
                jObject.get("displayname").getAsString(), idValue, jObject.get("resourceableTypeName").getAsString());
    }
}
