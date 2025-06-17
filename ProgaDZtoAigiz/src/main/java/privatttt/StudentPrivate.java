package privatttt;

public class StudentPrivate {
    private String name;
    private String middleName;
    private String lastName;
    private Integer age;
    private String group;


    public StudentPrivate() {}

    public StudentPrivate(String name, String middleName, String lastName, Integer age, String group) {
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.age = age;
        this.group = group;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public String getGroup() {
        return group;
    }
}
