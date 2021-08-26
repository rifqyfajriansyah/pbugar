package inside.books.trypns;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import inside.books.trypns.adapter.MenuAdapter;
import inside.books.trypns.helper.Session;

public class MenuUtama extends AppCompatActivity {

    CardView cdSemua, cdFutsal, cdBasket, cdBadminton;
    TextView txNama, txSemua, txFutsal, txBasket, txBadminton;
    Session session;
    Context context;
    List<String> listLapangan, listRecycle = new ArrayList<>();
    RecyclerView recLapangan;
    MenuAdapter menuAdapter;
    String tipeku;

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);

        context = MenuUtama.this;

        setWidget();
        setTipe("Semua");
        setValue();

        txNama.setText(session.getNama());

        cdSemua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTipe("Semua");
            }
        });

        cdFutsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTipe("Futsal");
            }
        });

        cdBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTipe("Basket");
            }
        });

        cdBadminton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTipe("Badminton");
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, JadwalActivity.class);
                startActivity(i);
            }
        });
    }

    private void setWidget(){

        cdSemua = findViewById(R.id.cdSemua);
        cdFutsal = findViewById(R.id.cdFutsal);
        cdBasket = findViewById(R.id.cdBasket);
        cdBadminton = findViewById(R.id.cdBadminton);

        txSemua = findViewById(R.id.txSemua);
        txBadminton = findViewById(R.id.txBadminton);
        txBasket = findViewById(R.id.txBasket);
        txFutsal = findViewById(R.id.txFutsal);
        txNama = findViewById(R.id.menuNama);

        img = findViewById(R.id.menuUtamaJadwalku);

        recLapangan = findViewById(R.id.menuRecycle);


        session = new Session(context);



    }

    private void setValue(){

        //listRecycle.clear();
        listRecycle = setList();

        menuAdapter = new MenuAdapter(listRecycle, context);

        recLapangan.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recLapangan.setHasFixedSize(true);
        recLapangan.setAdapter(menuAdapter);


        menuAdapter.notifyDataSetChanged();
    }

    private List<String> setList(){
        List<String> listFunc = new ArrayList<>();
        listLapangan = Arrays.asList(getResources().getStringArray(R.array.list_lapangan));

        if(tipeku.equals("Semua")){
            listFunc = listLapangan;
        }else{
            for(int i=0; i<listLapangan.size(); i++){
                String[] arrayLapangan = listLapangan.get(i).split("-");
                if(arrayLapangan[1].equals(tipeku)){
                    listFunc.add(listLapangan.get(i));
                }
            }
        }

        return listFunc;

    }


    private void cdClicked(CardView cardView, TextView textView){

        cardView.setCardBackgroundColor(Color.parseColor("#983732"));
        textView.setTextColor(Color.parseColor("#FFFFFF"));

    }

    private void cdUnclicked(CardView cardView, TextView textView){

        cardView.setCardBackgroundColor(Color.parseColor("#D1CCCC"));
        textView.setTextColor(Color.parseColor("#737373"));

    }

    private void setTipe(String tipe){

        tipeku = tipe;
        setValue();

        switch (tipe){
            case "Semua" :{
                cdClicked(cdSemua, txSemua);
                cdUnclicked(cdFutsal, txFutsal);
                cdUnclicked(cdBadminton, txBadminton);
                cdUnclicked(cdBasket, txBasket);
            }

            break;

            case "Futsal" :{
                cdUnclicked(cdSemua, txSemua);
                cdClicked(cdFutsal, txFutsal);
                cdUnclicked(cdBadminton, txBadminton);
                cdUnclicked(cdBasket, txBasket);
            }

            break;

            case "Basket" :{
                cdUnclicked(cdSemua, txSemua);
                cdUnclicked(cdFutsal, txFutsal);
                cdClicked(cdBasket, txBasket);
                cdUnclicked(cdBadminton, txBadminton);
            }

            break;

            case "Badminton" :{
                cdUnclicked(cdSemua, txSemua);
                cdUnclicked(cdFutsal, txFutsal);
                cdUnclicked(cdBasket, txBasket);
                cdClicked(cdBadminton, txBadminton);
            }

            break;
        }

    }

}
