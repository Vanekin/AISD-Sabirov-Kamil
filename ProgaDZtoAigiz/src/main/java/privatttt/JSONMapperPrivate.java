package privatttt;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class JSONMapperPrivate {
    public String toJson(Object obj) throws Exception {
        Field[] fields = obj.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder("{");
        for (Field f : fields) {
            sb.append("\"").append(f.getName()).append("\":");
            //f.setAccessible(true);
            Method getter = obj.getClass().getMethod("get" +
                    f.getName().toUpperCase().charAt(0) +
                    f.getName().substring(1));
            if (f.getType().equals(String.class)) {
                sb.append("\"").append(getter.invoke(obj)).append("\"");
            } else if (f.getType().getSuperclass().equals(Number.class)) {
                sb.append(getter.invoke(obj).toString());
            } else {
                sb.append("\"").append(getter.invoke(obj).toString()).append("\"");
            }
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("}");

        return sb.toString();
    }

    public Object parseJson(String json, Class clazz) throws Exception {
        Object object = clazz.getConstructor().newInstance();
        json = json.replaceAll("[{}\"]", "").trim();
        String[] fieldValuePair = json.split(",");
        for (String pair : fieldValuePair) {
            String[] keyValue = pair.split(":", 2);
            if (keyValue.length != 2) continue;
            String fieldName = keyValue[0].trim();
            String fieldValue = keyValue[1].trim();
            Field field = clazz.getDeclaredField(fieldName);
            fieldName = fieldName.toUpperCase().charAt(0) + fieldName.substring(1);
            Method setter = object.getClass().getMethod("set" + fieldName, field.getType());
            if (field.getType().equals(String.class)) {
                setter.invoke(object, fieldValue);
            } else if (field.getType().equals(Integer.class)) {
                setter.invoke(object, Integer.parseInt(fieldValue));
            }
        }
        return object;
    }
}
