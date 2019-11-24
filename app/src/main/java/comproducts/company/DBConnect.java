package comproducts.company;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import comproducts.company.Modul.Service;

public class DBConnect extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static String DB_NAME = "db_services.db";
    private static String DB_PATH = "";
    private final Context mContext;
    private SQLiteDatabase mDataBase;
    private boolean mNeedUpdate = false;

    public DBConnect(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        if (android.os.Build.VERSION.SDK_INT >= 17)
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        else
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.mContext = context;

        copyDataBase();

        this.getReadableDatabase();
    }

    public void updateDataBase() {
        if (mNeedUpdate) {
            File dbFile = new File(DB_PATH + DB_NAME);
            if (dbFile.exists())
                dbFile.delete();

            copyDataBase();

            mNeedUpdate = false;
        }
    }

    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    private void copyDataBase() {
        if (!checkDataBase()) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDBFile();
            } catch (IOException e) {
            //    Toast.makeText(mContext, "CheckDataBase" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void copyDBFile() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        //InputStream mInput = mContext.getResources().openRawResource(R.raw.info);
        OutputStream mOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0)
            mOutput.write(mBuffer, 0, mLength);
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public boolean openDataBase() {
        mDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDataBase != null;
    }

    @Override
    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            mNeedUpdate = true;
    }

    public Service getServiceById(int IdService) {
        SQLiteDatabase db = this.getWritableDatabase();
        Service service = null;
        Cursor c;
        try {
            c = db.rawQuery("select * from services where IdService =" + IdService, null);
            if (c == null) return null;
            c.moveToFirst();
            service = new Service(c.getInt(c.getColumnIndex("IdService")),
                    c.getString(c.getColumnIndex("image")),
                    c.getString(c.getColumnIndex("titleFr")),
                    c.getString(c.getColumnIndex("DescriptionFr")));
            c.close();
        } catch (Exception e) {
            //Toast.makeText(mContext, "err" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        db.close();
        return service;
    }





    public ArrayList<Service> getAllServices() {
        ArrayList<Service> temp = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;
        try {
            c = db.rawQuery("select * from services", null);
            if (c == null) return null;
            c.moveToFirst();
            do {
                Service d = new Service(c.getInt(c.getColumnIndex("IdService")),
                        c.getString(c.getColumnIndex("image")),
                        c.getString(c.getColumnIndex("titleFr")),
                        c.getString(c.getColumnIndex("DescriptionFr")));
                temp.add(d);
            } while (c.moveToNext());
            c.close();
        } catch (Exception e) {
            Toast.makeText(mContext, "err" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        db.close();
        return temp;
    }


}

