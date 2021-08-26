package inside.books.trypns.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {

    public static final String TRYME = "session_cpns";

    public static final String NAMA = "nama";
    public static final String EMAIL = "email";
    public static final String TIPELOGIN = "tipelogin";

    public static final String INDEXTANGGAL = "indexTanggal";
    public static final String INDEXJAM = "indexJam";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public Session(Context context) {
        sp = context.getSharedPreferences(TRYME, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value) {
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value) {
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value) {
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getNama(){
        return sp.getString(NAMA, null);
    }

    public String getEmail(){
        return sp.getString(EMAIL, null);
    }

    public int getLogin(){
        return sp.getInt(TIPELOGIN, 0);
    }

    public int getIndexTanggal(){ return sp.getInt(INDEXTANGGAL, 0);}

    public int getIndexJam(){ return sp.getInt(INDEXJAM, 0);}


}
