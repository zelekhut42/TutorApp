package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp;

public class MyStudentsItem {

    private String StudentName;
    private String MajorName;
    private String StudentID;
    private String SkillID;
    private String SkillName;

    public MyStudentsItem(String StdNameText, String StdMajorText, String StdID, String SklID, String SklName) {
        StudentName = StdNameText;
        MajorName = StdMajorText;
        StudentID = StdID;
        SkillID = SklID;
        SkillName = SklName;
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

    public String getSkillID() {
        return SkillID;
    }

    public String getSkillName() {
        return SkillName;
    }
}
