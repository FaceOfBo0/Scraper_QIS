package OlatAPI.JsonParsing;

public record MyCoursesBody(int key, String softkey, String resourcename,
                            String displayname, int resourceableId, String resourceableTypeName) {

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
