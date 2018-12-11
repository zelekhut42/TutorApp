package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp;

public class MySkillsItem {
    private String SkillName;
    private String SkillID;
    private String HaveButtonType;

    public MySkillsItem(String SkillText, String ID, String buttonType) {
        SkillName = SkillText;
        SkillID = ID;
        HaveButtonType = buttonType;
    }

    public String getSkillName() {
        return SkillName;
    }
    public String getSkillID() {
        return SkillID;
    }

    public String getHaveButtonType() {
        return HaveButtonType;
    }

    public void setHaveButtonType(String haveButtonType) {
        HaveButtonType = haveButtonType;
    }
}
