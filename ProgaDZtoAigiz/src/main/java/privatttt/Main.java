package privatttt;

public class Main {
    public static void main(String[] args) throws Exception {
        JSONMapperPrivate mapper = new JSONMapperPrivate();

        StudentPrivate student = new StudentPrivate("Поттер", "Гарри", "Джеймсович",12, "11-гриффиндор");
        String JSONStrPrivate = mapper.toJson(student);
        System.out.println(JSONStrPrivate);
        StudentPrivate studentPrivateFromJSON = (StudentPrivate) mapper.parseJson(JSONStrPrivate, StudentPrivate.class);
        String JSONStrPrivate2 = mapper.toJson(studentPrivateFromJSON);
        System.out.println(JSONStrPrivate2);
    }
}
