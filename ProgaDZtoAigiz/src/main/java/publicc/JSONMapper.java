package publicc;

import java.lang.reflect.Field;

public class JSONMapper {

    public String toJson(Object object) throws IllegalAccessException {
        Field[] fields = object.getClass().getFields();
        StringBuilder sb = new StringBuilder("{");
        for (Field f : fields) {
            //"fieldName":
            sb.append("\"").append(f.getName()).append("\":");
            String className = f.getClass().getSimpleName();
            //значение
            if (f.getType().equals(String.class)) {
                sb.append("\"").append(f.get(object)).append("\"");
            } else if (f.getType().getSuperclass().equals(Number.class)) {
                sb.append(f.get(object).toString()).append("\"");
            }
            sb.append(",");
        }
        sb.replace(sb.length() - 1, sb.length(), "}");
        return sb.toString();
    }
    //{"lastName":"...", "firstName":"...","middleName":"...", "group":"...","age":21}
    public Object parseJson(String json, Class clazz) throws Exception {
        Object object = clazz.getConstructor().newInstance();
        json = json.replaceAll("[{}\"]", "").trim();
        String[] fieldValuePair = json.split(",");
        for (String pair : fieldValuePair) {
            String[] keyValue = pair.split(":", 2);
            if (keyValue.length != 2) continue;
            String fieldName = keyValue[0].trim();
            String fieldValue = keyValue[1].trim();
            Field field = clazz.getField(fieldName);
            if (field.getType() == String.class) {
                field.set(object, fieldValue);
            } else if (field.getType() == Integer.class) {
                field.set(object, Integer.parseInt(fieldValue));
            }
        }
        return object;
    }
}
