package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp;

public class WantedSkillsItem {
    private String SkillName;
    private String SkillID;

    public WantedSkillsItem(String SkillText, String ID) {
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
