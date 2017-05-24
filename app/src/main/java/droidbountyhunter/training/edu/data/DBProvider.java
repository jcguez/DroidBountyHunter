package droidbountyhunter.training.edu.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author Giovani González
 * Created by darkgeat on 5/24/17.
 */

public class DBProvider {

    private DBHelper oDB;
    private SQLiteDatabase db;

    public DBProvider(Context context) {
        oDB = new DBHelper(context);
    }

    public void CloseDB(){
        if (db.isOpen()){
            db.close();
        }
    }

    public boolean isOpenDB(){
        return db.isOpen();
    }

    public long executeSQL(String sql, Object[] bindArgs){
        long iRet = 0;
        db = oDB.getWritableDatabase();
        db.execSQL(sql, bindArgs);
        CloseDB();
        return iRet;
    }

    public Cursor querySQL(String sql, String[] selectionArgs){
        Cursor oRet = null;
        db = oDB.getReadableDatabase();
        oRet = db.rawQuery(sql,selectionArgs);
        return oRet;
    }

    public void DeleteFugitivo(String pID){
        // Se elimina fugitivo de la base de datos...
        Object[] oData = {pID};
        executeSQL("DELETE FROM " + DBHelper.TABLE_NAME + " WHERE " + DBHelper._ID + " = ?", oData);
    }

    public void UpdateFugitivo(String pStatus, String pID){
        // Se actauliza fugitivo de la base de datos...
        Object[] oData = {pStatus, pID};
        executeSQL("UPDATE " + DBHelper.TABLE_NAME + " SET " + DBHelper.COLUMN_NAME_STATUS+ " = ?"
                + " WHERE " + DBHelper._ID + " = ?", oData);
    }

    public void InsertFugitivo(String pNombre){
        // Se elimina fugitivo de la base de datos...
        Object[] oData = {pNombre, "0"};
        executeSQL("INSERT INTO " + DBHelper.TABLE_NAME + "(" + DBHelper.COLUMN_NAME_NAME + "," + DBHelper.COLUMN_NAME_STATUS
                + ") VALUES(?,?)", oData);
    }


    public String[][] ObtenerFugitivos(boolean pCapturado){
        int iCnt = 0;
        String[][] aData = null;
        String[] aFils = {(pCapturado ? "1" : "0")};
        Cursor aRS = querySQL("SELECT * FROM " + DBHelper.TABLE_NAME + " WHERE " + DBHelper.COLUMN_NAME_STATUS + " = ?"
                + " ORDER BY " + DBHelper.COLUMN_NAME_NAME, aFils);
        if(aRS.getCount() > 0){
            aData = new String[aRS.getCount()][3];
            while (aRS.moveToNext()){
                aData[iCnt][0] = aRS.getString(aRS.getColumnIndex(DBHelper._ID));
                aData[iCnt][1] = aRS.getString(aRS.getColumnIndex(DBHelper.COLUMN_NAME_NAME));
                aData[iCnt][2] = aRS.getString(aRS.getColumnIndex(DBHelper.COLUMN_NAME_STATUS));
                iCnt++;
            }
        }else {
            aData = new String[0][];
        }
        aRS.close();
        CloseDB();
        return aData;
    }

    public int ContarFugitivos(){
        int iCnt = 0;
        String[] aFils = {""};
        Cursor aRS = querySQL("SELECT " + DBHelper._ID + " FROM " + DBHelper.TABLE_NAME + " WHERE id <> ?", aFils);
        iCnt = aRS.getCount();
        aRS.close();
        CloseDB();
        return iCnt;
    }

    private static class DBHelper extends SQLiteOpenHelper{

        private static final String TAG = "DBManager";
        private static final String DATABASE_NAME = "droidBH.db";
        private static final int DATABASE_VERSION = 1;
        public static final String TABLE_NAME = "fugitivos";
        public static final String _ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_STATUS = "status";

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.w("[CHECK]","DBHelper.onCreate...");
            db.execSQL("CREATE TABLE " + DBHelper.TABLE_NAME + " ("
                + DBHelper._ID + " INTEGER PRIMARY KEY,"
                + DBHelper.COLUMN_NAME_NAME + " TEXT,"
                + DBHelper.COLUMN_NAME_STATUS + " INTEGER"
                + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Actualización de la BDD de la versión " + oldVersion + " a la "
                + newVersion + ", de la que se destruira la información anterior");

            db.execSQL("DROP TABLE IF EXISTS " + DBHelper.TABLE_NAME);
            onCreate(db);
        }
    }
}
