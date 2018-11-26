package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp;

public class AllSkillsItem {
    private String SkillName;
    private String SkillID;

    public AllSkillsItem(String SkillText, String ID) {
        SkillName = SkillText;
        SkillID = ID;
    }

    public String getSkillName() {
        return SkillName;
    }
    public String getSkillID() {
        return SkillID;
    }
}
