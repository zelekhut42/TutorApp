package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp;

public class MyTutorsItem {

    private String TutorName;

    public String getMajorName() {
        return MajorName;
    }

    private String MajorName;

    public MyTutorsItem(String NameText, String MajorText) {
        TutorName = NameText;
        MajorName = MajorText;
    }

    public String getTutorName() {
        return TutorName;
    }
}
