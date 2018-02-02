package pl.robertgt4.android.mojelotto;

// TODO : create sqlite database with test data


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import pl.robertgt4.android.mojelotto.database.DBUtils;
import pl.robertgt4.android.mojelotto.database.LosowaniaContract;
import pl.robertgt4.android.mojelotto.database.LosowanieDbHelper;
import pl.robertgt4.android.mojelotto.database.TestUtil;
import pl.robertgt4.android.mojelotto.utils.DateUtils;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pokaDane();
        //ożywiamy testowo jeden fragment z losowymi numerkami (na razie bez wczytywania z bazy)
        if (   savedInstanceState==null) {

            FragmentManager fm = getSupportFragmentManager();

            WynikFragment wf = new WynikFragment();

            wf.setmTypy(DBUtils.getLosowanie(getString(R.string.lotto),0L,this));
            //wf.setWynik(new ArrayList<Integer>(Arrays.asList(23,54,65,76,56,98)));

            LosowanieClass lcLotto = new LosowanieClass(this);
            lcLotto.fillLast(getString(R.string.lotto));
            wf.setWynik(lcLotto.getLiczby());
            wf.setNazwaGry(getString(R.string.lotto));
            wf.setSavDataLosowania(DateUtils.millisToDate(lcLotto.getDataLosowaniaMillis()));

            fm.beginTransaction()
                    .add(R.id.fl_lotto_wynik,wf)
                    .commit();

            wf = new WynikFragment();

            wf.setmTypy(DBUtils.getLosowanie(getString(R.string.plus),0L,this));
            //wf.setWynik(new ArrayList<Integer>(Arrays.asList(23,54,66,77,56,98)));
            LosowanieClass lcPlus = new LosowanieClass(this);
            lcPlus.fillLast(getString(R.string.plus));
            wf.setWynik(lcPlus.getLiczby());
            wf.setNazwaGry(getString(R.string.plus));
            wf.setSavDataLosowania(DateUtils.millisToDate(lcPlus.getDataLosowaniaMillis()));

            fm.beginTransaction()
                    .add(R.id.fl_plus_wynik,wf)
                    .commit();

        }



    }

    private void pokaDane() {

        SQLiteDatabase db;

        LosowanieDbHelper losowanieDbHelper = new LosowanieDbHelper(this);
        db = losowanieDbHelper.getWritableDatabase();
        TestUtil.insertTestData(db, this);
        Cursor losowania = db.query(LosowaniaContract.tableLosowania.TABLE_NAME,null,null,null,null,null,LosowaniaContract.tableLosowania.COLUMN_GAME_DATE);


        StringBuilder sb = new StringBuilder();

        if (losowania.moveToFirst()) {

            sb.append("ilość wierszy: " + losowania.getCount());
            sb.append(" > ");

            do {

                sb.append(losowania.getString(losowania.getColumnIndex(LosowaniaContract.tableLosowania.COLUMN_GAME_NAME)));
                sb.append(" - ");
                Long lData = losowania.getLong(losowania.getColumnIndex(LosowaniaContract.tableLosowania.COLUMN_GAME_DATE));
                if (lData==0) {
                    sb.append("**typ użyszkodnika**");
                    sb.append(losowania.getLong(losowania.getColumnIndex(LosowaniaContract.tableLosowania.COLUMN_GAME_DATE)));
                } else {
                    sb.append(DateUtils.millisToDate(lData));
                }
                sb.append(" - ");
                sb.append(losowania.getString(losowania.getColumnIndex(LosowaniaContract.tableLosowania.COLUMN_NUMER_LOSOWANIA)));
                sb.append(" - ");
                sb.append(losowania.getString(losowania.getColumnIndex(LosowaniaContract.tableLosowania._ID)));
                sb.append(" < ");

            } while (losowania.moveToNext()) ;

            ((TextView) findViewById(R.id.testTV)).setText(sb.toString());

        }

        losowania.close();
        db.close();

    }
}
