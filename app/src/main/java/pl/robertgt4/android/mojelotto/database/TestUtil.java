package pl.robertgt4.android.mojelotto.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pl.robertgt4.android.mojelotto.R;
import pl.robertgt4.android.mojelotto.utils.DateUtils;

/**
 * Created by RRogowski on 2018-01-30.
 *
 * do wciśnięcia danych testowych do bazy danych
 */

public class TestUtil {

    public static void insertTestData(SQLiteDatabase db, Context context) {

        Long newID = 0L;
        if (db==null) {
            return;
        }

        try {
            db.beginTransaction();
            //kasujemy dane
            db.delete(LosowaniaContract.tableLosowania.TABLE_NAME,null,null);
            db.delete(LosowaniaContract.tableLosowanieLiczby.TABLE_NAME,null,null);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("insertTestData"," nie udało się danych skasować :/");
        } finally {
            db.endTransaction();
        }



            //lista losowań
        //daty w tym formacie lecą 2018-01-27 00:00:00
        List<ContentValues> losowania = new ArrayList<ContentValues>();
        List<ContentValues> liczby = new ArrayList<ContentValues>();

        ContentValues cv;
        ContentValues cvLiczby;

        //lotto
        cv = new ContentValues();
        cv.put(LosowaniaContract.tableLosowania.COLUMN_GAME_NAME, context.getString(R.string.lotto));
        cv.put(LosowaniaContract.tableLosowania.COLUMN_NUMER_LOSOWANIA,997);
        cv.put(LosowaniaContract.tableLosowania.COLUMN_GAME_DATE, DateUtils.dateToMillis("2018-01-01 00:00:00"));
        losowania.add(cv);

        try {
            db.beginTransaction();
            newID = db.insert(LosowaniaContract.tableLosowania.TABLE_NAME,null,cv);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("insertTestData"," nie udało się danych wstawić :/");

        } finally {
            db.endTransaction();
        }
        liczby = new ArrayList<ContentValues>();
        for (int i=1; i<7 ; i++) {

            cvLiczby = new ContentValues();
            cvLiczby.put(LosowaniaContract.tableLosowanieLiczby.COLUMN_ID_LOSOWANIA, newID);
            cvLiczby.put(LosowaniaContract.tableLosowanieLiczby.COLUMN_LP, i);
            cvLiczby.put(LosowaniaContract.tableLosowanieLiczby.COLUMN_LICZBA, new Random().nextInt(50));
            liczby.add(cvLiczby);

        }

        try {
            db.beginTransaction();
            for (ContentValues c:liczby) {
                db.insert(LosowaniaContract.tableLosowanieLiczby.TABLE_NAME,null,c);

            }
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("insertTestData"," nie udało się danych wstawić :/");
        } finally {
            db.endTransaction();
        }

        //plus - tak samo jak lotto, tylko nazwa gry inna

        cv = new ContentValues();
        cv.put(LosowaniaContract.tableLosowania.COLUMN_GAME_NAME, context.getString(R.string.plus));
        cv.put(LosowaniaContract.tableLosowania.COLUMN_NUMER_LOSOWANIA,998);
        cv.put(LosowaniaContract.tableLosowania.COLUMN_GAME_DATE, DateUtils.dateToMillis("2017-02-25 00:00:00"));
        losowania.add(cv);

        try {
            db.beginTransaction();
            newID = db.insert(LosowaniaContract.tableLosowania.TABLE_NAME,null,cv);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("insertTestData"," nie udało się danych wstawić :/");

        } finally {
            db.endTransaction();
        }

        liczby = new ArrayList<ContentValues>();
        for (int i=1; i<7 ; i++) {

            cvLiczby = new ContentValues();
            cvLiczby.put(LosowaniaContract.tableLosowanieLiczby.COLUMN_ID_LOSOWANIA, newID);
            cvLiczby.put(LosowaniaContract.tableLosowanieLiczby.COLUMN_LP, i);
            cvLiczby.put(LosowaniaContract.tableLosowanieLiczby.COLUMN_LICZBA, new Random().nextInt(50));
            liczby.add(cvLiczby);

        }

        try {
            db.beginTransaction();
                for (ContentValues c:liczby) {
                    db.insert(LosowaniaContract.tableLosowanieLiczby.TABLE_NAME,null,c);

            }
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("insertTestData"," nie udało się danych wstawić :/");
        } finally {
            db.endTransaction();
        }

        //superszansa ma 7 cyfr 0-9

        cv = new ContentValues();
        cv.put(LosowaniaContract.tableLosowania.COLUMN_GAME_NAME, context.getString(R.string.superszansa));
        cv.put(LosowaniaContract.tableLosowania.COLUMN_NUMER_LOSOWANIA,999);
        cv.put(LosowaniaContract.tableLosowania.COLUMN_GAME_DATE, DateUtils.dateToMillis("2018-02-01 00:00:00"));
        losowania.add(cv);

        try {
            db.beginTransaction();
            newID = db.insert(LosowaniaContract.tableLosowania.TABLE_NAME,null,cv);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("insertTestData"," nie udało się danych wstawić :/");

        } finally {
            db.endTransaction();
        }

        liczby = new ArrayList<ContentValues>();
        for (int i=1; i<8 ; i++) {

            cvLiczby = new ContentValues();
            cvLiczby.put(LosowaniaContract.tableLosowanieLiczby.COLUMN_ID_LOSOWANIA, newID);
            cvLiczby.put(LosowaniaContract.tableLosowanieLiczby.COLUMN_LP, i);
            cvLiczby.put(LosowaniaContract.tableLosowanieLiczby.COLUMN_LICZBA, new Random().nextInt(10));
            liczby.add(cvLiczby);

        }
        try {
            db.beginTransaction();
            for (ContentValues c:liczby) {
                db.insert(LosowaniaContract.tableLosowanieLiczby.TABLE_NAME,null,c);
            }
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("insertTestData"," nie udało się danych wstawić :/");
        } finally {
            db.endTransaction();
        }
// teraz moje typy - czyli brak daty
        //lotto
        cv = new ContentValues();
        cv.put(LosowaniaContract.tableLosowania.COLUMN_GAME_NAME, context.getString(R.string.lotto));
        cv.put(LosowaniaContract.tableLosowania.COLUMN_NUMER_LOSOWANIA,897);
        cv.put(LosowaniaContract.tableLosowania.COLUMN_GAME_DATE, 0);
        losowania.add(cv);

        try {
            db.beginTransaction();
            newID = db.insert(LosowaniaContract.tableLosowania.TABLE_NAME,null,cv);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("insertTestData"," nie udało się danych wstawić :/");

        } finally {
            db.endTransaction();
        }

        liczby = new ArrayList<ContentValues>();

        for (int i=1; i<7 ; i++) {

            cvLiczby = new ContentValues();
            cvLiczby.put(LosowaniaContract.tableLosowanieLiczby.COLUMN_ID_LOSOWANIA, newID);
            cvLiczby.put(LosowaniaContract.tableLosowanieLiczby.COLUMN_LP, i);
            cvLiczby.put(LosowaniaContract.tableLosowanieLiczby.COLUMN_LICZBA, new Random().nextInt(50));
            liczby.add(cvLiczby);

        }
        try {
            db.beginTransaction();
            for (ContentValues c:liczby) {
                db.insert(LosowaniaContract.tableLosowanieLiczby.TABLE_NAME,null,c);
            }
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("insertTestData"," nie udało się danych wstawić :/");
        } finally {
            db.endTransaction();
        }
        //plus - tak samo jak lotto, tylko nazwa gry inna

        cv = new ContentValues();
        cv.put(LosowaniaContract.tableLosowania.COLUMN_GAME_NAME, context.getString(R.string.plus));
        cv.put(LosowaniaContract.tableLosowania.COLUMN_NUMER_LOSOWANIA,898);
        cv.put(LosowaniaContract.tableLosowania.COLUMN_GAME_DATE, 0);
        losowania.add(cv);
        try {
            db.beginTransaction();
            newID = db.insert(LosowaniaContract.tableLosowania.TABLE_NAME,null,cv);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("insertTestData"," nie udało się danych wstawić :/");

        } finally {
            db.endTransaction();
        }

        liczby = new ArrayList<ContentValues>();
        for (int i=1; i<7 ; i++) {

            cvLiczby = new ContentValues();
            cvLiczby.put(LosowaniaContract.tableLosowanieLiczby.COLUMN_ID_LOSOWANIA, newID);
            cvLiczby.put(LosowaniaContract.tableLosowanieLiczby.COLUMN_LP, i);
            cvLiczby.put(LosowaniaContract.tableLosowanieLiczby.COLUMN_LICZBA, new Random().nextInt(50));
            liczby.add(cvLiczby);

        }
        try {
            db.beginTransaction();
            for (ContentValues c:liczby) {
                db.insert(LosowaniaContract.tableLosowanieLiczby.TABLE_NAME,null,c);
            }
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("insertTestData"," nie udało się danych wstawić :/");
        } finally {
            db.endTransaction();
        }
        //superszansa ma 7 cyfr 0-9

        cv = new ContentValues();
        cv.put(LosowaniaContract.tableLosowania.COLUMN_GAME_NAME, context.getString(R.string.superszansa));
        cv.put(LosowaniaContract.tableLosowania.COLUMN_NUMER_LOSOWANIA,899);
        cv.put(LosowaniaContract.tableLosowania.COLUMN_GAME_DATE, 0);
        losowania.add(cv);
        try {
            db.beginTransaction();
            newID = db.insert(LosowaniaContract.tableLosowania.TABLE_NAME,null,cv);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("insertTestData"," nie udało się danych wstawić :/");

        } finally {
            db.endTransaction();
        }

        liczby = new ArrayList<ContentValues>();
        for (int i=1; i<8 ; i++) {

            cvLiczby = new ContentValues();
            cvLiczby.put(LosowaniaContract.tableLosowanieLiczby.COLUMN_ID_LOSOWANIA, newID);
            cvLiczby.put(LosowaniaContract.tableLosowanieLiczby.COLUMN_LP, i);
            cvLiczby.put(LosowaniaContract.tableLosowanieLiczby.COLUMN_LICZBA, new Random().nextInt(10));
            liczby.add(cvLiczby);

        }
        try {
            db.beginTransaction();
            for (ContentValues c:liczby) {
                db.insert(LosowaniaContract.tableLosowanieLiczby.TABLE_NAME,null,c);
            }
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("insertTestData"," nie udało się danych wstawić :/");
        } finally {
            db.endTransaction();
        }
//        try {
//            db.beginTransaction();
//            //kasujemy dane
//            db.delete(LosowaniaContract.tableLosowania.TABLE_NAME,null,null);
//            db.delete(LosowaniaContract.tableLosowanieLiczby.TABLE_NAME,null,null);
//
//
//            for (ContentValues c:losowania) {
//                db.insert(LosowaniaContract.tableLosowania.TABLE_NAME,null,c);
//
//            }
//            for (ContentValues c:liczby) {
//                db.insert(LosowaniaContract.tableLosowanieLiczby.TABLE_NAME,null,c);
//            }
//            db.setTransactionSuccessful();
//        } catch (SQLException e) {
//            Log.e("insertTestData"," nie udało się danych wstawić :/");
//        } finally {
//            db.endTransaction();
//        }

            //db.close();

    }

}
