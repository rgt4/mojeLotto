package pl.robertgt4.android.mojelotto.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by RRogowski on 2018-01-30.
 */

public class LosowanieDbHelper extends SQLiteOpenHelper {

    public LosowanieDbHelper(Context context) {
        super(context, LosowaniaContract.DB_NAME,null,LosowaniaContract.DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(LosowaniaContract.tableLosowania.CREATE_TABLE);
        sqLiteDatabase.execSQL(LosowaniaContract.tableLosowanieLiczby.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(LosowaniaContract.tableLosowania.DELETE_TABLE);
        sqLiteDatabase.execSQL(LosowaniaContract.tableLosowanieLiczby.DELETE_TABLE);
        onCreate(sqLiteDatabase);

    }
}
