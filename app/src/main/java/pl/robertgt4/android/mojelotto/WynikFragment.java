package pl.robertgt4.android.mojelotto;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RRogowski on 2018-02-01.
 */

public class WynikFragment extends Fragment {

    //stałe do zapisana w stanie
    private static final String SAV_LICZBY_WYLOSOWANE = "wylosowane";
    private static final String SAV_LICZBY_ZAKRESLONE = "typy";
    private static final String SAV_NAZWA_GRY = "nazwagry";
    private static final String SAV_DATA_LOSOWANIA = "datalosowania";

    private List<Integer> mLiczbyWylosowane; //liczby do wyświetlenia
    private List<Integer> mTypy; //liczby zakreślone przez uższkodnika
    private String nazwaGry;
    private String dataLosowania;

    public WynikFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //odtwarzamy stan
        if(savedInstanceState!=null) {
            mLiczbyWylosowane = savedInstanceState.getIntegerArrayList(SAV_LICZBY_WYLOSOWANE);
            mTypy=savedInstanceState.getIntegerArrayList(SAV_LICZBY_ZAKRESLONE);
            nazwaGry=savedInstanceState.getString(SAV_NAZWA_GRY);
            dataLosowania=savedInstanceState.getString(SAV_DATA_LOSOWANIA);

        }
        //to jest view z piłeczkami
        View rootView = inflater.inflate(R.layout.wynik,container,false);

        ((TextView) rootView.findViewById(R.id.tv_nazwa_gry)).setText(nazwaGry);
        ((TextView) rootView.findViewById(R.id.tv_game_date)).setText(dataLosowania);

        //sprawdzam czy jest lista i czy ma 6 typów, jak nie ma to spadamy
        if (mLiczbyWylosowane.size()==6) {
            if ((nazwaGry.equals(getString(R.string.lotto))) || (nazwaGry.equals(getString(R.string.plus)))) {

                int[] tvWyniki = new int[] {R.id.wynik_1,
                        R.id.wynik_2,
                        R.id.wynik_3,
                        R.id.wynik_4,
                        R.id.wynik_5,
                        R.id.wynik_6,
                        R.id.wynik_7 };

                //zakładam, że liczby są posortowane od najmniejszej (odppowiednie zapytanie do bazy)
                for (int i=1;i<7;i++) {

                    int liczba = mLiczbyWylosowane.get(i-1);
                    if (mTypy.contains(liczba)) {
                        //trafiona
                        ((TextView) rootView.findViewById(tvWyniki[i-1])).setText(Integer.toString(liczba));
                    } else {
                        //nie trafiona - zmieniamy kolor na blado czewrony
                        //do tego przyda się wyciągnąć TextView do zmiennej
                        TextView tv = (TextView) rootView.findViewById(tvWyniki[i-1]);
                        tv.setText(Integer.toString(liczba));
                        tv.setBackgroundResource(R.drawable.pileczka_czerwona_blada);
                    }

                    ((TextView) rootView.findViewById(tvWyniki[6])).setVisibility(View.INVISIBLE);


                }

            }
        }

        return rootView;
    }

    public void  setWynik(List<Integer> wynik) {
        mLiczbyWylosowane = wynik;
    }

    public void setmTypy(List<Integer> typy) {
        mTypy = typy;
    }

    public void setNazwaGry(String nazwa) {
        nazwaGry=nazwa;
    }

    public void setSavDataLosowania(String data) {
        dataLosowania=data;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putString(SAV_NAZWA_GRY,nazwaGry);
        outState.putString(SAV_DATA_LOSOWANIA,dataLosowania);
        outState.putIntegerArrayList(SAV_LICZBY_WYLOSOWANE,(ArrayList<Integer>) mLiczbyWylosowane);
        outState.putIntegerArrayList(SAV_LICZBY_ZAKRESLONE,(ArrayList<Integer>) mTypy);
    }
}
