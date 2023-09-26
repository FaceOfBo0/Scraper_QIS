package OlatAPI.JsonParsing;

public record MyEntriesBody(long key, String softkey, String resourcename,
                            String displayname, long resourceableId, String resourceableTypeName) {

    @Override
    public String toString() {
        return "MyEntriesBody {\n" +
                "key = " + key + '\n' +
                "softKey = '" + softkey + "'\n" +
                "resourcename = '" + resourcename + "'\n" +
                "displayname = '" + displayname + "'\n" +
                "resourceableId = " + resourceableId + '\n' +
                "resourceableTypeName = '" + resourceableTypeName + "'}";
    }
}
