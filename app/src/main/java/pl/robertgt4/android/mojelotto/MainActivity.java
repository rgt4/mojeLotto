package pl.robertgt4.android.mojelotto;

// TODO : create sqlite database with test data


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import pl.robertgt4.android.mojelotto.database.LosowaniaContract;
import pl.robertgt4.android.mojelotto.database.LosowanieDbHelper;
import pl.robertgt4.android.mojelotto.database.TestUtil;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pokaDane();

    }

    private void pokaDane() {

        SQLiteDatabase db;

        LosowanieDbHelper losowanieDbHelper = new LosowanieDbHelper(this);
        db = losowanieDbHelper.getWritableDatabase();
        TestUtil.insertTestData(db);
        Cursor losowania = db.query(LosowaniaContract.tableLosowania.TABLE_NAME,null,null,null,null,null,LosowaniaContract.tableLosowania.COLUMN_GAME_DATE);

        StringBuilder sb = new StringBuilder();

        if (losowania.moveToFirst()) {

            sb.append("ilość wierszy: " + losowania.getCount());
            sb.append(" > ");

            do {

                sb.append(losowania.getString(losowania.getColumnIndex(LosowaniaContract.tableLosowania.COLUMN_GAME_NAME)));
                sb.append(" - ");
                sb.append(losowania.getString(losowania.getColumnIndex(LosowaniaContract.tableLosowania.COLUMN_GAME_DATE)));
                sb.append(" - ");
                sb.append(losowania.getString(losowania.getColumnIndex(LosowaniaContract.tableLosowania.COLUMN_NUMER_LOSOWANIA)));
                sb.append(" - ");
                sb.append(losowania.getString(losowania.getColumnIndex(LosowaniaContract.tableLosowania._ID)));
                sb.append(" < ");

            } while (losowania.moveToNext()) ;

            ((TextView) findViewById(R.id.testTV)).setText(sb.toString());

        }

    }
}
