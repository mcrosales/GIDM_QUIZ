package ugr.gidm.gidmQuiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ugr.gidm.gidmQuiz.QuizContract.QuestionsTable;

public class QuizDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "quizzes.db";
    private static final int DB_VERSION = 8;

    public static final String SUBJECT_ACTIVITIES = "activities";
    public static final String SUBJECT_INTENTS = "intents";
    public static final String SUBJECT_PERMISSIONS = "permissions";
    public static final String SUBJECT_USER_INTERFACE = "user_interface";
    public static final String SUBJECT_FRAGMENTS = "fragments";
    public static final String SUBJECT_NOTIFICATIONS = "notifications";

    private final String CREATE_TABLE_QUERY = "CREATE TABLE " + QuestionsTable.TABLE_NAME +
            "(" +
            QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            QuestionsTable.COLUMN_QUESTION + " TEXT, " +
            QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
            QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
            QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
            QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
            QuestionsTable.COLUMN_ANSWER + " TEXT, " +
            QuestionsTable.COLUMN_SUBJECT + " TEXT" +
            ")";

    private final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME;

    private SQLiteDatabase db;
    private List<Question> mQuestionList;

    private Bundle subjectIntentBundle;

    public QuizDBHelper(Context context, Bundle bundle) {
        super(context, DB_NAME, null, DB_VERSION);
        this.subjectIntentBundle = bundle;
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

        //questions for subject user interface
        mQuestionList.add(new Question("Las Views son bloques constructivos básicos de componentes de UIs. ¿Cual de los siguientes no es un View predefinido?", "Button", "ToggleButton", "ModalButton", "CheckBox", "ModalButton", SUBJECT_USER_INTERFACE));
        mQuestionList.add(new Question("¿Cuál de estas opciones no es un listener definido por la clase View?", "onClickListener", "onLongClickListener ", "onKeyListener", "onFocusUpdatedListener", "onFocusUpdatedListener", SUBJECT_USER_INTERFACE));
        mQuestionList.add(new Question("¿Cuál de estas opciones no es un tipo de ViewGroup?", "RadioGroup", "TimePicker ", "DatePicker", "ImageGroup", "ImageGroup", SUBJECT_USER_INTERFACE));

        //questions for subject permissions
        mQuestionList.add(new Question("Los permisos se definen", "en el archivo strings.xml", "en el archivo build.gradle", "en el archivo AndroidManifest.xml", "en una clase", "en el archivo AndroidManifest.xml", SUBJECT_PERMISSIONS));
        mQuestionList.add(new Question("Las aplicaciones especifican los permisos que usan mediante una etiqueta", "<uses-permission>", "<has-permission>", "<permission>", "<granted-permission>", "<uses-permission>", SUBJECT_PERMISSIONS));
        mQuestionList.add(new Question("Los permisos se representan como", "String", "char[]", "int", "Integer", "String", SUBJECT_PERMISSIONS));

        //questions for subject intents
        mQuestionList.add(new Question("Un intent es una estructura de datos que representa", "una operación o un evento", "una operación", "un evento", "ninguna de las anteriores", "una operación o un evento", SUBJECT_INTENTS));
        mQuestionList.add(new Question("El intent lo construye", "otro intent", "un servicio", "una actividad", "una tarea", "una actividad", SUBJECT_INTENTS));
        mQuestionList.add(new Question("¿Cuál campo de los siguientes no pertence a un intent?", "Action", "Data", "Template", "Category", "Template", SUBJECT_INTENTS));

        //questions for subject notifications
        mQuestionList.add(new Question("Un Toast es", "un botón", "menú desplegable", "mensaje temporal", "menú fijo", "mensaje temporal", SUBJECT_NOTIFICATIONS));
        mQuestionList.add(new Question("Para mostrar un Toast se utiliza la instrucción", "Toast.make()", "Toast.show()", "Toast.appear()", "Toast.bring()", "Toast.show()", SUBJECT_NOTIFICATIONS));
        mQuestionList.add(new Question("Con el siguiente código: - Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS); - se accede a", "pantalla de ajustes", "notificaciones disponibles", "canales disponibles", "nada", "pantalla de ajustes", SUBJECT_NOTIFICATIONS));

        //questions for subject fragments
        mQuestionList.add(new Question("Un Fragmento representa", "una llamada a un servicio", "un clase especial de servicio", "una sección de una vista", "una parte de una FragmentActivity", "una parte de una FragmentActivity", SUBJECT_FRAGMENTS));
        mQuestionList.add(new Question("¿Cuál de estos métodos no pertence al ciclo de vida de un fragmento?", "onAttached()", "onCreateView()", "onActivityCreated()", "onStart()", "onAttached()", SUBJECT_FRAGMENTS));
        mQuestionList.add(new Question("¿Cuál de las siguientes no es una subclase de Fragment?", "DialogFragment", "ListFragment", "PreferenceFragmentCompat", "ModalFragment", "ModalFragment", SUBJECT_FRAGMENTS));

        //questions for subject activities
        mQuestionList.add(new Question("Una actividad, para la interacción del usuario, proporciona:", "una interfaz visual", "una API REST", "un servicio SOAP", "un método interaction()", "una interfaz visual", SUBJECT_ACTIVITIES));
        mQuestionList.add(new Question("¿Cuántas tareas de un usuario debe soportar una actividad?", "Máximo 2", "Una", "0", "Múltiples", "Una", SUBJECT_ACTIVITIES));
        mQuestionList.add(new Question("¿Cuál de estos métodos no pertence al ciclo de vida de las actividades?", "onStart()", "onResume()", "onRestart()", "onUpdate()", "onUpdate()", SUBJECT_ACTIVITIES));

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
            contentValues.put(QuestionsTable.COLUMN_SUBJECT, q.getmSubject());
            db.insert(QuestionsTable.TABLE_NAME, null, contentValues);
        }
    }

    public ArrayList<Question> getAllQuestions(String subjectID) {
        Log.d("TAG", "Getting all questions for : " + subjectID);
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        String SELECT_TABLE_QUERY = "SELECT * FROM " + QuestionsTable.TABLE_NAME + " WHERE " + QuestionsTable.COLUMN_SUBJECT + " = \"" + subjectID + "\"";
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
