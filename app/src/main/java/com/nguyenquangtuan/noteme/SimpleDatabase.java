package com.nguyenquangtuan.noteme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.LoginFilter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SimpleDatabase extends SQLiteOpenHelper {
    // declare require values
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "SimpleDB";
    private static final String TABLE_NAME = "SimpleTable";

    public SimpleDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // declare table column names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_CREATEDDATE = "createddate";
    private static final String KEY_CREATEDTIME = "createdtime";
    private static final String KEY_MODIFIEDDATE = "modifieddate";
    private static final String KEY_MODIFIEDTIME = "modifiedtime";
    private static final String KEY_SUBJECT = "subject";


    // creating tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDb = "CREATE TABLE " + TABLE_NAME + " (" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_TITLE + " TEXT," +
                KEY_CONTENT + " TEXT," +
                KEY_CREATEDDATE + " TEXT," +
                KEY_CREATEDTIME + " TEXT," +
                KEY_MODIFIEDDATE + " TEXT," +
                KEY_MODIFIEDTIME + " TEXT,"+
                KEY_SUBJECT + " TEXT"
                + " )";
        db.execSQL(createDb);
    }

    // upgrade db if older version exists
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion >= newVersion)
            return;

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(KEY_TITLE, note.getTitle());
        v.put(KEY_CONTENT, note.getContent());
        v.put(KEY_CREATEDDATE, note.getCreatedDate());
        v.put(KEY_CREATEDTIME, note.getCreatedTime());
        note.setModifiedDate(" ");
        note.setModifiedTime(" ");
        v.put(KEY_MODIFIEDDATE,note.getModifiedDate());
        v.put(KEY_MODIFIEDTIME,note.getModifiedTime());
        v.put(KEY_SUBJECT,note.getSubject());


        // inserting data into db
        long ID = db.insert(TABLE_NAME, null, v);
        return ID;
    }

    public Note getNote(long id) {
        String modifiedDate,modifiedTime,subject;
        SQLiteDatabase db = this.getWritableDatabase();
        Log.i("MYMESSAGE","id =  "+id);
        String[] query = new String[]{KEY_ID, KEY_TITLE, KEY_CONTENT,KEY_CREATEDDATE,KEY_CREATEDTIME,KEY_MODIFIEDDATE,KEY_MODIFIEDTIME,KEY_SUBJECT};
        Cursor cursor = db.query(TABLE_NAME, query, KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
//            if(cursor.getString(5) == null){
//                modifiedDate = "";
//            }else{
//                modifiedDate = cursor.getString(5);
//            }
//            if(cursor.getString(6) == null){
//                modifiedTime = "";
//            }else{
//                modifiedTime = cursor.getString(6);
//            }
//            if(cursor.getString(7) == null){
//                subject = "";
//            }else{
//                subject = cursor.getString(7);
//            }

            return new Note(
                    Long.parseLong(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7));
        }
        return null;


    }

    public List<Note> getAllNotes() {
        List<Note> allNotes = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + KEY_ID + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(Long.parseLong(cursor.getString(0)));
                note.setTitle(cursor.getString(1));
                note.setContent(cursor.getString(2));
                note.setCreatedDate(cursor.getString(3));
                note.setCreatedTime(cursor.getString(4));
                note.setModifiedDate(cursor.getString(5));
                note.setModifiedTime(cursor.getString(6));
                note.setSubject(cursor.getString(7));
//                if(note.getModifiedDate() == null){
//                   note.setModifiedDate("");
//                }else{
//                    note.setModifiedDate(cursor.getString(5));
//                }
//                if(note.getModifiedTime() == null){
//                    note.setModifiedTime("");
//                }else{
//                    note.setModifiedTime(cursor.getString(6));
//                }
//                if(note.getSubject() == null){
//                    note.setSubject("");
//                }else {
//                    note.setSubject(cursor.getString(7));
//                }
                allNotes.add(note);
            } while (cursor.moveToNext());
        }

        return allNotes;

    }

    public int editNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        Log.d("Edited", "Edited Title: -> " + note.getTitle() + "\n ID -> " + note.getId());
        c.put(KEY_TITLE, note.getTitle());
        c.put(KEY_CONTENT, note.getContent());
        c.put(KEY_CREATEDDATE, note.getCreatedDate());
        c.put(KEY_CREATEDTIME, note.getCreatedTime());
        c.put(KEY_MODIFIEDDATE, note.getModifiedDate());
        c.put(KEY_MODIFIEDTIME, note.getModifiedTime());
        c.put(KEY_SUBJECT, note.getSubject());
        return db.update(TABLE_NAME, c, KEY_ID + "=?", new String[]{String.valueOf(note.getId())});
    }
    public List<Note> searchNote(String s){
        SQLiteDatabase db = this.getWritableDatabase();
        List<Note> allNotes = new ArrayList<>();
        Cursor cursor = db.query(true, TABLE_NAME, new String[] { KEY_ID,
                        KEY_TITLE,KEY_CONTENT,KEY_CREATEDDATE,KEY_CREATEDTIME,KEY_MODIFIEDDATE,KEY_MODIFIEDTIME,KEY_SUBJECT},
                KEY_CONTENT + " like '%" + s + "%' OR " + KEY_TITLE + " like '%" + s + "%' OR "
                        + KEY_SUBJECT + " like '%" + s + "%'",null, null, null, null,
                null);
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(Long.parseLong(cursor.getString(0)));
                note.setTitle(cursor.getString(1));
                note.setContent(cursor.getString(2));
                note.setCreatedDate(cursor.getString(3));
                note.setCreatedTime(cursor.getString(4));
                note.setModifiedDate(cursor.getString(5));
                note.setModifiedTime(cursor.getString(6));
                note.setSubject(cursor.getString(7));
                allNotes.add(note);
            } while (cursor.moveToNext());
        }

        return allNotes;

    }

    void deleteNote(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }


}
