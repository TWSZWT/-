public class User {
    // 属性：用户名、密码、身份证号码、手机号码
    private String name;
    private String password;
    private String id;
    private String phone;

    //构造

    public User() {
    }

    public User(String name, String password, String id, String phone) {
        this.name = name;
        this.password = password;
        this.id = id;
        this.phone = phone;
    }

    //javabean

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
