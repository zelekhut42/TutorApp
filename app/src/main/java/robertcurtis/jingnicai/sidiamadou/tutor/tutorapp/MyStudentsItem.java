package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp;

public class MyStudentsItem {

    private String TutorName;

    public String getMajorName() {
        return MajorName;
    }

    private String MajorName;

    public MyStudentsItem(String NameText, String MajorText) {
        TutorName = NameText;
        MajorName = MajorText;
    }

    public String getStudentName() {
        return TutorName;
    }
}
