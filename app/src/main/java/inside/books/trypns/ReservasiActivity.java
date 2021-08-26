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
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import inside.books.trypns.adapter.JamAdapter;
import inside.books.trypns.adapter.TanggalAdapter;
import inside.books.trypns.data.DataHari;
import inside.books.trypns.helper.Session;
import inside.books.trypns.interfaces.SendDate;
import inside.books.trypns.interfaces.SendInteger;

public class ReservasiActivity extends AppCompatActivity {

    CardView cdKurang, cdTambah, cdLapangan1, cdLapangan2, cdLapangan3, cdReservasi;
    RecyclerView recTanggal, recJam;
    TextView textBulan, textLapangan1, textLapangan2, textLapangan3, textJumlahJam, textJumlahHarga;

    ArrayList<Date> listDates = new ArrayList<>();
    List<String> listJam = new ArrayList<>();
    SimpleDateFormat sdf;
    Calendar cal, currentCal;
    Context context;
    int indexDay, indexMonth;
    String lapanganku;

    TanggalAdapter tanggalAdapter;
    JamAdapter jamAdapter;
    Session session;

    int indexWaktu;

    ImageView imBack;

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservasi);

        context = ReservasiActivity.this;

        setWidget();
        setCalendar(indexDay);

        setWaktu();

        cdKurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal.add(Calendar.MONTH, -1);
                setCalendar(indexDay);
            }
        });

        cdTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal.add(Calendar.MONTH, 1);
                setCalendar(indexDay);
            }
        });

        cdLapangan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLapangan("1");
            }
        });

        cdLapangan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLapangan("2");
            }
        });

        cdLapangan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLapangan("3");
            }
        });

        cdReservasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(context, ReservasiSelesai.class);
                startActivity(i);
            }
        });

        imBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i = new Intent(context, MenuUtama.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
    }

    private void setWaktu(){

        indexWaktu = 0;

        listJam = Arrays.asList(getResources().getStringArray(R.array.list_jam));
        jamAdapter = new JamAdapter(listJam, context, new SendInteger() {
            @Override
            public void value(int value) {

                if(value==0){
                    indexWaktu = indexWaktu-1;
                }else{
                    indexWaktu = indexWaktu + 1;
                }

                int jumlahHarga = indexWaktu*150000;

                textJumlahHarga.setText(String.valueOf(jumlahHarga)+" IDR");
                textJumlahJam.setText(String.valueOf(indexWaktu)+" Jam");

            }
        });

        recJam.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recJam.setHasFixedSize(true);
        recJam.setAdapter(jamAdapter);


        jamAdapter.notifyDataSetChanged();
    }


    private void setCalendar(int date){

        listDates.clear();
        int parameter;
        int paramMonth = Integer.valueOf(new SimpleDateFormat("MM").format(cal.getTime()));

        if(indexMonth<=paramMonth){
            parameter = 1;
        }else{
            parameter = 0;
        }




        int maksHari = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        currentCal.set(Calendar.DAY_OF_MONTH, 1);

        while (listDates.size()<maksHari){
            listDates.add(currentCal.getTime());
            currentCal.add(Calendar.DAY_OF_MONTH,1);
        }

        textBulan.setText(sdf.format(cal.getTime()));

        tanggalAdapter = new TanggalAdapter(listDates, context, date, parameter, new SendDate() {
            @Override
            public void value(Date value) {
                setCalendar(value.getDate());
            }
        });

        recTanggal.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recTanggal.setHasFixedSize(true);
        recTanggal.setAdapter(tanggalAdapter);
        recTanggal.scrollToPosition(indexDay);


        tanggalAdapter.notifyDataSetChanged();

    }



    private void setWidget(){

        cdKurang = findViewById(R.id.reservasiBulanKurang);
        cdTambah = findViewById(R.id.reservasiBulanTambah);

        cdLapangan1 = findViewById(R.id.reservasiLap1);
        cdLapangan2 = findViewById(R.id.reservasiLap2);
        cdLapangan3 = findViewById(R.id.reservasiLap3);

        textLapangan1 = findViewById(R.id.reservasiLap1Text);
        textLapangan2 = findViewById(R.id.reservasiLap2Text);
        textLapangan3 = findViewById(R.id.reservasiLap3Text);

        textJumlahJam = findViewById(R.id.reservasiJam);
        textJumlahHarga = findViewById(R.id.reservasiHarga);

        recTanggal = findViewById(R.id.reservasiTanggal);
        recJam = findViewById(R.id.reservasiWaktu);
        textBulan = findViewById(R.id.reservasiBulan);

        cdReservasi = findViewById(R.id.reservasiButton);
        imBack = findViewById(R.id.reservasiBack);

        session = new Session(context);
        session.saveSPInt(Session.INDEXTANGGAL, 0);

        setLapangan("0");

        sdf = new SimpleDateFormat("MMMM yyyy");
        cal = Calendar.getInstance();
        currentCal = Calendar.getInstance();

        indexDay = Integer.valueOf(new SimpleDateFormat("dd").format(cal.getTime()));
        indexMonth = Integer.valueOf(new SimpleDateFormat("MM").format(cal.getTime()));

    }

    private void setLapangan(String tipe){

        lapanganku = tipe;

        switch (tipe){
            case "1" :{
                cdClicked(cdLapangan1, textLapangan1);
                cdUnclicked(cdLapangan2, textLapangan2);
                cdUnclicked(cdLapangan3, textLapangan3);
            }

            break;

            case "2" :{
                cdUnclicked(cdLapangan1, textLapangan1);
                cdClicked(cdLapangan2, textLapangan2);
                cdUnclicked(cdLapangan3, textLapangan3);
            }

            break;

            case "3" :{
                cdUnclicked(cdLapangan1, textLapangan1);
                cdUnclicked(cdLapangan2, textLapangan2);
                cdClicked(cdLapangan3, textLapangan3);
            }

            break;

           default:
               cdUnclicked(cdLapangan1, textLapangan1);
               cdUnclicked(cdLapangan2, textLapangan2);
               cdUnclicked(cdLapangan3, textLapangan3);
        }

    }

    private void cdClicked(CardView cardView, TextView textView){

        cardView.setCardBackgroundColor(Color.parseColor("#983732"));
        textView.setTextColor(Color.parseColor("#FFFFFF"));

    }

    private void cdUnclicked(CardView cardView, TextView textView){

        cardView.setCardBackgroundColor(Color.parseColor("#D1CCCC"));
        textView.setTextColor(Color.parseColor("#737373"));

    }

}
