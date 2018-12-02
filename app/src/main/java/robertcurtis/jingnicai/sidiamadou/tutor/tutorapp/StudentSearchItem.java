package robertcurtis.jingnicai.sidiamadou.tutor.tutorapp;

public class StudentSearchItem {

    private String StudentName;
    private String MajorName;
    private String StudentID;
    private String ButtonType;





    public StudentSearchItem(String NameText, String MajorText, String ID, String type) {
        StudentName = NameText;
        MajorName = MajorText;
        StudentID = ID;
        ButtonType = type;

        if (MajorName == null) {
            MajorName = "none listed";
        }
    }

    public String getMajorName() {
        return MajorName;
    }
    public String getStudentName() {
        return StudentName;
    }

    public String getButtonType() {
        return ButtonType;
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setButtonType(String buttonType) {
        ButtonType = buttonType;
    }
}
