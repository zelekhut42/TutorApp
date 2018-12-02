package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp;

public class MyTutorsItem {

    private String TutorName;
    private String MajorName;
    private String TutorID;

    public MyTutorsItem(String NameText, String MajorText, String ID) {
        TutorName = NameText;
        MajorName = MajorText;
        TutorID = ID;
    }

    public String getTutorName() {
        return TutorName;
    }
    public String getMajorName() {
        return MajorName;
    }

    public String getTutorID() {
        return TutorID;
    }
}
