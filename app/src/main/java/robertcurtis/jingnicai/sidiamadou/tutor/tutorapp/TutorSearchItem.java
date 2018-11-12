package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp;

public class TutorSearchItem {
    private String TutorName;
    private String MajorName;





    public TutorSearchItem(String NameText, String MajorText) {
        TutorName = NameText;
        MajorName = MajorText;

        if (MajorName == null) {
            MajorName = "none listed";
        }
    }

    public String getMajorName() {
        return MajorName;
    }
    public String getTutorName() {
        return TutorName;
    }
}
