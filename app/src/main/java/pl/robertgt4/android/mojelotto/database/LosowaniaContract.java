package pl.robertgt4.android.mojelotto.database;

import android.provider.BaseColumns;

/**
 * Created by RRogowski on 2018-01-30.
 */

public class LosowaniaContract {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "losowania.db";

    public static final class tableLosowania implements BaseColumns {

        public final static String TABLE_NAME = "losowania";
        public final static String COLUMN_GAME_NAME = "gameName";
        public final static String COLUMN_NUMER_LOSOWANIA = "nrLosowania";

        //jeśli jest null, to dane dotyczące tego losowania to są numery wytypowane przez użyszkodnika

        public final static String COLUMN_GAME_DATE = "gameDate";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NUMER_LOSOWANIA + " INTEGER NOT NULL, " +
                COLUMN_GAME_DATE + " INTEGER , " +
                COLUMN_GAME_NAME + " TEXT NOT NULL" +
                ");";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    }

    public static final class tableLosowanieLiczby implements BaseColumns {
        public final static String TABLE_NAME = "liczby";
        public final static String COLUMN_ID_LOSOWANIA = "idLosowania";
        public final static String COLUMN_LP = "lp";
        public final static String COLUMN_LICZBA = "liczba";

        public static final String CREATE_TABLE= "CREATE TABLE " +
                TABLE_NAME + " (" +
                COLUMN_ID_LOSOWANIA + " INTEGER NOT NULL, " +
                COLUMN_LP + " INTEGER NOT NULL, " +
                COLUMN_LICZBA + " INTEGER NOT NULL " +
                ");";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    }

}
