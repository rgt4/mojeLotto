package pl.robertgt4.android.mojelotto;

import android.content.Context;

import java.util.List;

import pl.robertgt4.android.mojelotto.database.DBUtils;

/**
 * Created by RRogowski on 2018-02-02.
 */

public class LosowanieClass {

    private Long mDataLosowaniaMillis;
    private String mGameName;
    private Integer mNumerLosowania; //to jest numer nadany przez lotto
    private List<Integer> mLiczby;
    private boolean mTypy; //określa czy to są typy użyszkodnika, czy losowanie faktyczne

    private Context mContext;

    public LosowanieClass(Context context) {
        mContext=context;
    }

    public void fillTypy(String gameName) {

        mGameName=gameName;
        mTypy=true;
        mLiczby= DBUtils.getLosowanie(mGameName,0L,mContext);
        mDataLosowaniaMillis=0L;

    }

    public void fillLast(String gameName) {
        mTypy=true;
        mGameName=gameName;
        int id = DBUtils.getLastGameID(mGameName,null, mContext);
        mDataLosowaniaMillis = DBUtils.getGameDate(id, mContext);
        mNumerLosowania = DBUtils.getGameNumber(id, mContext);
        mLiczby=DBUtils.getOstatnieLosowanie(mGameName,mContext);

    }

    public Long getDataLosowaniaMillis() {
        return mDataLosowaniaMillis;
    }

    public String getGameName() {
        return mGameName;
    }

    public List<Integer> getLiczby() {
        return mLiczby;
    }

    public int getNumerLosowania() {
        return mNumerLosowania;
    }
}
