package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp;

public class MyTutorsItem {

    private String TutorName;
    private String MajorName;
    private String TutorID;
    private String SkillID;
    private String SkillName;

    public MyTutorsItem(String NameText, String MajorText, String ID, String SklID, String SklName) {
        TutorName = NameText;
        MajorName = MajorText;
        TutorID = ID;
        SkillID = SklID;
        SkillName = SklName;
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
    public String getSkillID() {
        return SkillID;
    }

    public String getSkillName() {
        return SkillName;
    }
}
