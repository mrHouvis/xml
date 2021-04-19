import java.util.List;

public class Student {

    private String firstname;
    private String lastname;
    private String groupnumber;
    private List<Subject> subject;
    private double average;

    public Student() { }

    public Student(String firstname, String lastname, String groupnumber, List<Subject> subject, double average) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.groupnumber = groupnumber;
        this.subject = subject;
        this.average = average;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGroupnumber() {
        return groupnumber;
    }

    public void setGroupnumber(String groupnumber) {
        this.groupnumber = groupnumber;
    }

    public List<Subject> getSubject() {
        return subject;
    }

    public void setSubject(List<Subject> subject) {
        this.subject = subject;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", groupnumber='" + groupnumber + '\'' +
                ", subject=" + subject +
                ", average=" + average +
                '}';
    }
}
