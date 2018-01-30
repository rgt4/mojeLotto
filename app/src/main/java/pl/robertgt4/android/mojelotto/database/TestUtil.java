package pl.robertgt4.android.mojelotto.database;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pl.robertgt4.android.mojelotto.utils.DateUtils;

/**
 * Created by RRogowski on 2018-01-30.
 *
 * do wciśnięcia danych testowych do bazy danych
 */

public class TestUtil {

    public static void insertTestData(SQLiteDatabase db) {

        if (db==null) {
            return;
        }

        //lista losowań
        //daty w tym formacie lecą 2018-01-27 00:00:00
        List<ContentValues> losowania = new ArrayList<ContentValues>();
        List<ContentValues> liczby = new ArrayList<ContentValues>();

        ContentValues cv = new ContentValues();
        ContentValues cvLiczby;

        //lotto

        cv.put(LosowaniaContract.tableLosowania.COLUMN_GAME_NAME, "lotto");
        cv.put(LosowaniaContract.tableLosowania.COLUMN_NUMER_LOSOWANIA,"997");
        cv.put(LosowaniaContract.tableLosowania.COLUMN_GAME_DATE, DateUtils.dateToMillis("2018-01-01 00:00:00"));
        losowania.add(cv);

        for (int i=1; i<7 ; i++) {

            cvLiczby = new ContentValues();
            cvLiczby.put(LosowaniaContract.tableLosowanieLiczby.COLUMN_ID_LOSOWANIA, 997);
            cvLiczby.put(LosowaniaContract.tableLosowanieLiczby.COLUMN_LP, 1);
            cvLiczby.put(LosowaniaContract.tableLosowanieLiczby.COLUMN_LICZBA, new Random().nextInt(50));
            liczby.add(cvLiczby);

        }

        //plus - tak samo jak lotto, tylko nazwa gry inna

        cv = new ContentValues();
        cv.put(LosowaniaContract.tableLosowania.COLUMN_GAME_NAME, "plus");
        cv.put(LosowaniaContract.tableLosowania.COLUMN_NUMER_LOSOWANIA,"998");
        cv.put(LosowaniaContract.tableLosowania.COLUMN_GAME_DATE, DateUtils.dateToMillis("2018-02-01 00:00:00"));
        losowania.add(cv);

        for (int i=1; i<7 ; i++) {

            cvLiczby = new ContentValues();
            cvLiczby.put(LosowaniaContract.tableLosowanieLiczby.COLUMN_ID_LOSOWANIA, 998);
            cvLiczby.put(LosowaniaContract.tableLosowanieLiczby.COLUMN_LP, 1);
            cvLiczby.put(LosowaniaContract.tableLosowanieLiczby.COLUMN_LICZBA, new Random().nextInt(50));
            liczby.add(cvLiczby);

        }

        //superszansa ma 7 cyfr 0-9

        cv = new ContentValues();
        cv.put(LosowaniaContract.tableLosowania.COLUMN_GAME_NAME, "superszansa");
        cv.put(LosowaniaContract.tableLosowania.COLUMN_NUMER_LOSOWANIA,"999");
        cv.put(LosowaniaContract.tableLosowania.COLUMN_GAME_DATE, DateUtils.dateToMillis("2018-02-01 00:00:00"));
        losowania.add(cv);

        for (int i=1; i<8 ; i++) {

            cvLiczby = new ContentValues();
            cvLiczby.put(LosowaniaContract.tableLosowanieLiczby.COLUMN_ID_LOSOWANIA, 999);
            cvLiczby.put(LosowaniaContract.tableLosowanieLiczby.COLUMN_LP, 1);
            cvLiczby.put(LosowaniaContract.tableLosowanieLiczby.COLUMN_LICZBA, new Random().nextInt(10));
            liczby.add(cvLiczby);

        }

        try {
            db.beginTransaction();
            //kasujemy dane
            db.delete(LosowaniaContract.tableLosowania.TABLE_NAME,null,null);
            db.delete(LosowaniaContract.tableLosowanieLiczby.TABLE_NAME,null,null);

            for (ContentValues c:losowania) {
                db.insert(LosowaniaContract.tableLosowania.TABLE_NAME,null,c);
            }
            for (ContentValues c:liczby) {
                db.insert(LosowaniaContract.tableLosowanieLiczby.TABLE_NAME,null,c);
            }
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("insertTestData"," nie udało się danych wstawić :/");
        } finally {
            db.endTransaction();
        }


    }

}
