package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp;

public class TutorSearchItem {
    private String TutorName;
    private String MajorName;
    private String TutorID;





    public TutorSearchItem(String NameText, String MajorText, String ID) {
        TutorName = NameText;
        MajorName = MajorText;
        TutorID = ID;

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

    public String getTutorID() {
        return TutorID;
    }
}
