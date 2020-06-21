package example.com.quizdroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import example.com.quizdroid.QuizContract.QuestionsTable;

public class QuizDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "quizzes.db";
    private static final int DB_VERSION = 8;

    public static final String CATEGORY_ACTIVITIES = "activities";
    public static final String CATEGORY_INTENTS = "intents";
    public static final String CATEGORY_PERMISSIONS = "permissions";
    public static final String CATEGORY_INDIA = "india";
    public static final String CATEGORY_FRAGMENTS = "fragments";
    public static final String CATEGORY_SCIENCE = "science";

    private final String CREATE_TABLE_QUERY = "CREATE TABLE " + QuestionsTable.TABLE_NAME +
            "(" +
            QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            QuestionsTable.COLUMN_QUESTION + " TEXT, " +
            QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
            QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
            QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
            QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
            QuestionsTable.COLUMN_ANSWER + " TEXT, " +
            QuestionsTable.COLUMN_CATEGORY + " TEXT" +
            ")";

    private final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME;

    private SQLiteDatabase db;
    private List<Question> mQuestionList;

    private Bundle categoryIntentBundle;

    public QuizDBHelper(Context context, Bundle bundle) {
        super(context, DB_NAME, null, DB_VERSION);
        this.categoryIntentBundle = bundle;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL(CREATE_TABLE_QUERY);

        setUpQuestions();
        insertQuestions();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_QUERY);
        onCreate(db);
    }

    private void setUpQuestions() {
        mQuestionList = new ArrayList<>();

        //questions for category india
        mQuestionList.add(new Question("What Indian city is the capital of two states?", "Mumbai", "Kolkatta", "Chennai", "Chandigarh", "Chandigarh", CATEGORY_INDIA));
        mQuestionList.add(new Question("Which is the smallest (in area) of the following Union Territories?", "Lakshadweep", "Chandigardh", "Daman & Diu", "Delhi", "Lakshadweep", CATEGORY_INDIA));
        mQuestionList.add(new Question("On which river has the Hirakud dam been built?", "Godavari", "Mahanadi", "Cauvery", "Periyar", "Mahanadi", CATEGORY_INDIA));
        mQuestionList.add(new Question("Which state is irrigated by the Ganga canal?", "Uttar Pradesh", "Bihar", "West Bengal", "Rajasthan", "Rajasthan", CATEGORY_INDIA));
        mQuestionList.add(new Question("The famous Gir forests are located in", "Mysore", "Kashmir", "Gujarat", "Kerala", "Gujarat", CATEGORY_INDIA));

        //questions for category permissions
        mQuestionList.add(new Question("Los permisos se definen", "en el archivo strings.xml", "en el archivo build.gradle", "en el archivo AndroidManifest.xml", "en una clase", "en el archivo AndroidManifest.xml", CATEGORY_PERMISSIONS));
        mQuestionList.add(new Question("Las aplicaciones especifican los permisos que usan mediante una etiqueta", "<uses-permission>", "<has-permission>", "<permission>", "<granted-permission>", "<uses-permission>", CATEGORY_PERMISSIONS));
        mQuestionList.add(new Question("Los permisos se representan como", "String", "char[]", "int", "Integer", "String", CATEGORY_PERMISSIONS));

        //questions for category intents
        mQuestionList.add(new Question("Un intent es una estructura de datos que representa", "una operación o un evento", "una operación", "un evento", "ninguna de las anteriores", "una operación o un evento", CATEGORY_INTENTS));
        mQuestionList.add(new Question("El intent lo construye", "otro intent", "un servicio", "una actividad", "una tarea", "una actividad", CATEGORY_INTENTS));
        mQuestionList.add(new Question("¿Cuál campo de los siguientes no pertence a un intent?", "Action", "Data", "Template", "Category", "Template", CATEGORY_INTENTS));

        //questions for category science
        mQuestionList.add(new Question("What is the first element on the periodic table?", "Uranium", "Helium", "Hydrogen", "Carbon", "Hydrogen", CATEGORY_SCIENCE));
        mQuestionList.add(new Question("What constitutes the biggest part of the human brain?", "Cerebrum", "Cerebellum", "Thalamus", "Medula", "Cerebrum", CATEGORY_SCIENCE));
        mQuestionList.add(new Question("Electric current is measured using what device?", "Anemometer", "Hygrometer", "Spectrometer", "Ammeter", "Ammeter", CATEGORY_SCIENCE));
        mQuestionList.add(new Question("What planet is closest in size to Earth?", "Mercury", "Mars", "Venus", "Jupiter", "Venus", CATEGORY_SCIENCE));
        mQuestionList.add(new Question("Who introduced the idea of natural selection?", "Herbert Spencer", "Charles Darwin", "Charles Dicken", "Karl Marx", "Charles Darwin", CATEGORY_SCIENCE));

        //questions for category fragments
        mQuestionList.add(new Question("Un Fragmento representa", "una llamada a un servicio", "un clase especial de servicio", "una sección de una vista", "una parte de una FragmentActivity", "una parte de una FragmentActivity", CATEGORY_FRAGMENTS));
        mQuestionList.add(new Question("¿Cuál de estos métodos no pertence al ciclo de vida de un fragmento?", "onAttached()", "onCreateView()", "onActivityCreated()", "onStart()", "onAttached()", CATEGORY_FRAGMENTS));
        mQuestionList.add(new Question("Which of these words means \"substitute\"?", "prediction", "period", "proof", "proxy", "proxy", CATEGORY_FRAGMENTS));

        //questions for category activities
        mQuestionList.add(new Question("Una actividad, para la interacción del usuario, proporciona:", "una interfaz visual", "una API REST", "un servicio SOAP", "un método interaction()", "una interfaz visual", CATEGORY_ACTIVITIES));
        mQuestionList.add(new Question("¿Cuántas tareas de un usuario debe soportar una actividad?", "Máximo 2", "Una", "0", "Múltiples", "Una", CATEGORY_ACTIVITIES));
        mQuestionList.add(new Question("¿Cuál de estos métodos no pertence al ciclo de vida de las actividades?", "onStart()", "onResume()", "onRestart()", "onUpdate()", "onUpdate()", CATEGORY_ACTIVITIES));

    }

    private void insertQuestions() {
        for(Question q : mQuestionList) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(QuestionsTable.COLUMN_QUESTION, q.getmQuestion());
            contentValues.put(QuestionsTable.COLUMN_OPTION1, q.getmOption1());
            contentValues.put(QuestionsTable.COLUMN_OPTION2, q.getmOption2());
            contentValues.put(QuestionsTable.COLUMN_OPTION3, q.getmOption3());
            contentValues.put(QuestionsTable.COLUMN_OPTION4, q.getmOption4());
            contentValues.put(QuestionsTable.COLUMN_ANSWER, q.getmAnswer());
            contentValues.put(QuestionsTable.COLUMN_CATEGORY, q.getmCategory());
            db.insert(QuestionsTable.TABLE_NAME, null, contentValues);
        }
    }

    public ArrayList<Question> getAllQuestions(String categoryID) {
        Log.d("TAG", "Getting all questions for : " + categoryID);
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        String SELECT_TABLE_QUERY = "SELECT * FROM " + QuestionsTable.TABLE_NAME + " WHERE " + QuestionsTable.COLUMN_CATEGORY + " = \"" + categoryID + "\"";
        Cursor cursor = db.rawQuery(SELECT_TABLE_QUERY, null);
        if(cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setmQuestion(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setmOption1(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setmOption2(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setmOption3(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setmOption4(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setmAnswer(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_ANSWER)));
                questionList.add(question);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return questionList;
    }
}
