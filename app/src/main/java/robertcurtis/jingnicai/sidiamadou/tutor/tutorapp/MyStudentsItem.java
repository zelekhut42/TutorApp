package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp;

public class MyStudentsItem {

    private String StudentName;
    private String MajorName;
    private String StudentID;

    public MyStudentsItem(String NameText, String MajorText, String ID) {
        StudentName = NameText;
        MajorName = MajorText;
        StudentID = ID;
    }

    public String getStudentName() {
        return StudentName;
    }
    public String getMajorName() {
        return MajorName;
    }

    public String getStudentID() {
        return StudentID;
    }
}
