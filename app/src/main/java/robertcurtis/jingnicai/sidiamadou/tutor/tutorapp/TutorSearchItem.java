package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp;

public class TutorSearchItem {
    private String TutorName;

    public String getMajorName() {
        return MajorName;
    }

    private String MajorName;

    public TutorSearchItem(String NameText, String MajorText) {
        TutorName = NameText;
        MajorName = MajorText;
    }

    public String getTutorName() {
        return TutorName;
    }
}
