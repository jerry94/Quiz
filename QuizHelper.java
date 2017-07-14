package com.jerry94.w3villaquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GARVIT on 13-07-2017.
 */
public class QuizHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "quizdb";
    private static final String TABLE_QUEST = "quest";
    private static final String KEY_ID = "qid";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer";
    private static final String KEY_OPTA = "opta";
    private static final String KEY_OPTB = "optb";
    private static final String KEY_OPTC = "optc";
    private SQLiteDatabase dbase;

    public QuizHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase = db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "
                + KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT)";
        db.execSQL(sql);
        addQuestion();
    }

    private void addQuestion() {
        Question q1 = new Question("5 + 2 = ?", "7", "8", "6", "7");
        this.addQuestion(q1);
        Question q2 = new Question("12 + 18 = ?", "20", "28", "30", "30");
        this.addQuestion(q2);
        Question q3 = new Question("19 - 7 = ?", "12", "13", "26", "12");
        this.addQuestion(q3);
        Question q4 = new Question("5 * 7 = ?", "12", "35", "2", "35");
        this.addQuestion(q4);
        Question q5 = new Question("3 * 1 = ?", "-3", "1", "3", "3");
        this.addQuestion(q5);
        Question q6 = new Question("0 / 1 = ?", "1", "0", "10", "0");
        this.addQuestion(q6);
        Question q7 = new Question("13 * 4 = ?", "52", "46", "69", "52");
        this.addQuestion(q7);
        Question q8 = new Question("9 * 7 = ?", "16", "63", "36", "63");
        this.addQuestion(q8);
        Question q9 = new Question("276 / 3 = ?", "81", "91", "92", "92");
        this.addQuestion(q9);
        Question q10 = new Question("33 * 3 = ?", "101", "33", "99", "99");
        this.addQuestion(q10);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        onCreate(db);
    }

    public void addQuestion(Question quest) {
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER,quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());
        dbase.insert(TABLE_QUEST, null, values);
    }

    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                int hello=cursor.getInt(0);
                String data=cursor.getString(1)+" "+cursor.getString(2)+cursor.getString(3);
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                Log.e("Check @getAllQuestion",String.valueOf(cursor.getString(2)));
                Log.e("Check @cursorValue",String.valueOf(cursor.toString()));

                System.out.print("###################################"+cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        return quesList;
    }
}