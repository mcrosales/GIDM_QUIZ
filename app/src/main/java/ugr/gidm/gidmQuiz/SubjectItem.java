package ugr.gidm.gidmQuiz;

public class SubjectItem {

    private int mBgColor;
    private String mSubjectTitle;
    private String mSubjectID;

    SubjectItem(int imageId, String subjectTitle, String subjectID) {
        this.mBgColor = imageId;
        this.mSubjectTitle = subjectTitle;
        this.mSubjectID = subjectID;
    }

    public int getmBgColor() {
        return mBgColor;
    }

    public String getmSubjectTitle() {
        return mSubjectTitle;
    }

    public String getmSubjectID() {
        return mSubjectID;
    }
}
