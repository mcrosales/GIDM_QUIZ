package ugr.gidm.gidmQuiz;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.constraint.ConstraintLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

import static ugr.gidm.gidmQuiz.SubjectAdapter.SUBJECT_COLOR;
import static ugr.gidm.gidmQuiz.SubjectAdapter.SUBJECT_ID;

public class QuestionActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private QuestionAdapter mAdapter;
    private ArrayList<Question> mQuestionList;
    private QuizDBHelper mDbHelper;

    private ConstraintLayout mParentLayout;
    private TextView mScoreTextView;
    private TextView mRemaningQuestionsTextView;
    private int mTotalQuestions;
    private int mScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle subjectBundle = null;
        if (getIntent() != null) {
            subjectBundle = getIntent().getExtras();
        }

        mParentLayout = findViewById(R.id.question_layout);
        if (subjectBundle != null) {
            String hexColor = String.format("#%06X", (0xFFFFFF & subjectBundle.getInt(SUBJECT_COLOR)));
            hexColor = "#44"+hexColor.substring(1);
            mParentLayout.setBackgroundColor(Color.parseColor(hexColor));
        }

        mScoreTextView = findViewById(R.id.score);
        mRemaningQuestionsTextView = findViewById(R.id.remaining_questions);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);

        mDbHelper = new QuizDBHelper(this, subjectBundle);
        if (subjectBundle != null) {
            mQuestionList = mDbHelper.getAllQuestions(subjectBundle.getString(SUBJECT_ID));
            mTotalQuestions = mQuestionList.size();
            mScore = 0;
            displayScore();
        }
        mAdapter = new QuestionAdapter(this, mQuestionList, subjectBundle.getString(SUBJECT_ID));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    public void displayScore() {
        String scoreString = "Puntuación " + mScore + "/" + mTotalQuestions;
        mScoreTextView.setText(scoreString);
        mRemaningQuestionsTextView.setText("Restantes: " + mTotalQuestions--);
    }

    public void updateScore() {
        mScore++;
    }
}
