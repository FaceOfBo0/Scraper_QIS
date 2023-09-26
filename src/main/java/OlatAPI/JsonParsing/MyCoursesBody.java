package OlatAPI.JsonParsing;

public record MyCoursesBody(long key, String softkey, String resourcename,
                            String displayname, long resourceableId, String resourceableTypeName) {

    @Override
    public String toString() {
        return "OlatCourseInfo{" +
                "key=" + key +
                ", softKey='" + softkey + '\'' +
                ", resourcename='" + resourcename + '\'' +
                ", displayname='" + displayname + '\'' +
                ", resourceableId=" + resourceableId +
                ", resourceableTypeName='" + resourceableTypeName + '\'' +
                '}';
    }
}
