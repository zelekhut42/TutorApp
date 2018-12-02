package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp;

public class AllSkillsItem {
    private String SkillName;
    private String SkillID;
    private String WantButonType;
    private String HaveButtonType;

    public AllSkillsItem(String SkillText, String ID, String wantButonType, String haveButtonType) {
        SkillName = SkillText;
        SkillID = ID;
        WantButonType = wantButonType;
        HaveButtonType = haveButtonType;
    }

    public String getSkillName() {
        return SkillName;
    }
    public String getSkillID() {
        return SkillID;
    }

    public String getWantButonType() {
        return WantButonType;
    }

    public String getHaveButtonType() {
        return HaveButtonType;
    }

    public void setWantButonType(String wantButonType) {
        WantButonType = wantButonType;
    }

    public void setHaveButtonType(String haveButtonType) {
        HaveButtonType = haveButtonType;
    }
}
