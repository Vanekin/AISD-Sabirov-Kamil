package publicc;

public class Main {
    public static void main(String[] args) throws Exception {
        JSONMapper mapper = new JSONMapper();

        Student student = new Student("Поттер", "Гарри", "Джеймсович",12, "11-гриффиндор");
        String JSONStr = mapper.toJson(student);
        System.out.println(JSONStr);
        Student studentFromJSON = (Student) mapper.parseJson(JSONStr, Student.class);
        String JSONStr2 = mapper.toJson(studentFromJSON);
        System.out.println(JSONStr2);

    }
}
