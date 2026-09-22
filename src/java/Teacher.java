import java.io.Serializable;

public class Teacher implements Serializable {
    private String name;
    private String teacherId;
    private String email;
    private String phone;
    private String avatar;
    private String password;

    public Teacher() {}

    public Teacher(String name, String teacherId, String email, String phone, String avatar) {
        this.name = name;
        this.teacherId = teacherId;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
    }

    public String getName() { return name != null ? name : "Teacher"; }
    public void setName(String name) { this.name = name; }

    public String getTeacherId() { return teacherId != null ? teacherId : "T-101"; }
    public void setTeacherId(String teacherId) { this.teacherId = teacherId; }

    public String getEmail() { return email != null ? email : ""; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone != null ? phone : ""; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}