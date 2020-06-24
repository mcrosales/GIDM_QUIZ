package ugr.gidm.gidmQuiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    private GridView mGridView;
    private SubjectAdapter mSubjectAdapter;
    private ArrayList<SubjectItem> mSubjectItems;
    private int[] mColors;
    private String[] mSubjectTitles;
    private String[] mSubjectID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        setUpSubjectItems();

        mGridView = findViewById(R.id.categories_list);
        mSubjectAdapter = new SubjectAdapter(this, R.layout.grid_view_item, mSubjectItems);
        mGridView.setAdapter(mSubjectAdapter);

    }

    private void setUpSubjectItems() {
        mSubjectItems = new ArrayList<>();
        mSubjectTitles = getResources().getStringArray(R.array.subject_title);
        mSubjectID = getResources().getStringArray(R.array.subject_ID);

        mColors = getResources().getIntArray(R.array.colors);

        for (int i = 0; i < mSubjectTitles.length; i++) {
            int colorValue = 0;
            if (i % 2 == 0) {
                colorValue = mColors[1];
            } else {
                colorValue = mColors[0];
            }
            mSubjectItems.add(new SubjectItem(colorValue, mSubjectTitles[i], mSubjectID[i]));
            Log.d("TAG", "Title\t" + mSubjectTitles[i] + "\tID\t" + mSubjectID[i]);
        }
    }
}
