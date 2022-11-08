public class Student {
    //属性：id、姓名、年龄、家庭住址
    private String id;
    private String name;
    private int age ;
    private String address;
    //构造
    public Student() {
    }
    public Student(String id, String name, int age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }
    //javabean
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0 && age <= 60) {
            this.age = age;
        }else {
            System.out.println("年龄输入有误");
        }

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
