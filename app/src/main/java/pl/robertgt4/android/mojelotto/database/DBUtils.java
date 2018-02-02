package pl.robertgt4.android.mojelotto.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RRogowski on 2018-02-01.
 */

public final class DBUtils {

    public static List<Integer> getOstatnieLosowanie(String gameName, Context context) {

        return getLosowanie(gameName, -1L, context);

    }

    private static SQLiteDatabase getDB(Context context) {
        LosowanieDbHelper dbh = new LosowanieDbHelper(context);
         return (dbh.getReadableDatabase());
    }

    public static List<Integer> getLosowanie(String gameName, Long dateMillis, Context context) {

        List<Integer> retVal = new ArrayList<Integer>();

        //LosowanieDbHelper dbh = new LosowanieDbHelper(context);
        SQLiteDatabase db=getDB(context);
        int idLosowania;
        if (dateMillis==0) {
            idLosowania = getTypyId(gameName, db);
        } else if (dateMillis==-1) {
            idLosowania = getLastGameID(gameName,db,null);
        } else {
            idLosowania = getIDForDate(gameName,dateMillis, db, null);
        }

        Cursor c = db.query(LosowaniaContract.tableLosowanieLiczby.TABLE_NAME,null,LosowaniaContract.tableLosowanieLiczby.COLUMN_ID_LOSOWANIA + "=?", new String[] {Integer.toString(idLosowania)},null,null,LosowaniaContract.tableLosowanieLiczby.COLUMN_LICZBA);

        if (c.moveToFirst()) {

            do {
                int liczba;
                liczba=c.getInt(c.getColumnIndex(LosowaniaContract.tableLosowanieLiczby.COLUMN_LICZBA));
                retVal.add(liczba);
            } while (c.moveToNext());

        }

        c.close();
        db.close();
        return retVal;

    }
    public static int getIDForDate(String gameName, Long gameDate, SQLiteDatabase db, Context context) {

        //zwracamy _id z pierwszego wiersza tabeli dla konkretnej daty

        if (db==null) {
            db=getDB(context);
        }

        Cursor c = db.query(LosowaniaContract.tableLosowania.TABLE_NAME,new String[] {LosowaniaContract.tableLosowania._ID},
                LosowaniaContract.tableLosowania.COLUMN_GAME_NAME + " = ? AND " + LosowaniaContract.tableLosowania.COLUMN_GAME_DATE + "= ? ",
                new String[] {gameName, String.valueOf(gameDate)},
                null,
                null,
                null ,
                null);

        int retVal = 0;

        if (c.moveToFirst()) {
            retVal = (c.getInt(0));
        } else {
            retVal = 0;
        }

        c.close();
        return retVal;
    }

    public static int getLastGameID(String gameName, SQLiteDatabase db, Context context) {

        //zwracamy _id z pierwszego wiersza tabeli z losowaniami posortowanego wg daty losowania DESC

        if (db==null) {
            db=getDB(context);
        }

        Cursor c = db.query(LosowaniaContract.tableLosowania.TABLE_NAME,new String[] {LosowaniaContract.tableLosowania._ID},
                LosowaniaContract.tableLosowania.COLUMN_GAME_NAME + " = ? ",new String[] {gameName},null,null,
                LosowaniaContract.tableLosowania.COLUMN_GAME_DATE + " DESC" , "1");

        int retVal = 0;

        if (c.moveToFirst()) {
            retVal = (c.getInt(0));
        } else {
            retVal = 0;
        }

        c.close();
        return retVal;
    }

    public static Long getGameDate(int losowanieId, Context context) {
        //zwracamy datę losowania w millis dla danego _ID

        Cursor c = getDB(context).query(LosowaniaContract.tableLosowania.TABLE_NAME,
                new String[] {LosowaniaContract.tableLosowania.COLUMN_GAME_DATE},
                LosowaniaContract.tableLosowania._ID + " = ? ",new String[] {String.valueOf(losowanieId)},
                null,
                null,
                null,
                "1");

        Long retVal = 0L;
        if (c.moveToFirst()) {
            retVal = c.getLong(0);
        }


        c.close();
        return retVal;

    }

    public static int getGameNumber(int losowanieId, Context context) {
        //zwracamy numer losowania (ustalany przez lotto) dla danego _ID

        Cursor c = getDB(context).query(LosowaniaContract.tableLosowania.TABLE_NAME,
                new String[] {LosowaniaContract.tableLosowania.COLUMN_NUMER_LOSOWANIA},
                LosowaniaContract.tableLosowania._ID + " = ? ",
                new String[] {String.valueOf(losowanieId)},
                null,
                null,
                null ,
                "1");

        int retVal = 0;
        if (c.moveToFirst()) {
            retVal = c.getInt(0);
        }

        c.close();
        return retVal;

    }

    //zwraca _id typów użyszkodnika
    //czyli wiersza z datą równą 0
    private static int getTypyId(String gameName, SQLiteDatabase db) {
        int retVal = 0;

        //LosowanieDbHelper dbh = new LosowanieDbHelper(context);
        //SQLiteDatabase db = dbh.getWritableDatabase();

        Cursor c = db.query(LosowaniaContract.tableLosowania.TABLE_NAME,
                new String[] {LosowaniaContract.tableLosowania._ID},
                LosowaniaContract.tableLosowania.COLUMN_GAME_NAME + "= ?" + " AND " + LosowaniaContract.tableLosowania.COLUMN_GAME_DATE + " = ? ",
                new String[] {gameName,String.valueOf(0)},
                null,null,
                null, Integer.toString(1));

        if (c.moveToFirst()) {
            retVal = c.getInt(c.getColumnIndex(LosowaniaContract.tableLosowania._ID));
        }

        c.close();
        return retVal;

    }
}
