package inside.books.trypns;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import inside.books.trypns.adapter.ViewPagerAdapter;

public class DetailLapangan extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    Bundle bundle;
    String completeData;
    Context context;

    Integer[] images  = new Integer[3];
    String[] data;

    LinearLayout lJam;
    ImageView imBack, imLokasi;
    TextView txNama, txBintang, txPenjelasan, txCjam, txClokasi;
    CardView cdReservasi, cdCjam, cdClokasi;

    Intent i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lapangan);


        context = DetailLapangan.this;
        initWidget();
        getDataku();

        setOperasi("Operasional");


        cdClokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperasi("Lokasi");
            }
        });

        cdCjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperasi("Operasional");
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        cdReservasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(context, ReservasiActivity.class);
                startActivity(i);
            }
        });

    }

    private void getDataku() {

        bundle = getIntent().getExtras();
        completeData = bundle.getString("lapangan");

        data = completeData.split("-");
        images[0] = getImage(data[6]);
        images[1] = getImage(data[7]);
        images[2] = getImage(data[8]);


        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(context, images);

        viewPager.setAdapter(viewPagerAdapter);

        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for(int i = 0; i < dotscount; i++){

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        txNama.setText(data[1]);
        txBintang.setText(data[2]);
        txPenjelasan.setText(data[10]);

        Glide.with(context).load(getImage(data[9])).into(imLokasi);

    }

    private void initWidget(){

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);

        imBack = findViewById(R.id.lapanganBack);
        imLokasi = findViewById(R.id.lapanganMap);

        lJam = findViewById(R.id.lapanganJam);

        txNama = findViewById(R.id.lapanganNama);
        txBintang = findViewById(R.id.lapanganBintang);
        txPenjelasan = findViewById(R.id.lapanganDetail);
        txCjam = findViewById(R.id.lapanganTextOperasional);
        txClokasi = findViewById(R.id.lapanganTextLokasi);

        cdCjam = findViewById(R.id.lapanganOperasional);
        cdClokasi = findViewById(R.id.lapanganLokasi);

        cdReservasi = findViewById(R.id.lapanganReservasi);



    }

    private int getImage(String imageName) {

        Integer drawableResourceId = getResources().getIdentifier(imageName, "drawable", getPackageName());

        return drawableResourceId;
    }


    private void setOperasi(String text){

        if(text.equals("Operasional")){

            lJam.setVisibility(View.VISIBLE);
            imLokasi.setVisibility(View.GONE);

            cdCjam.setCardBackgroundColor(Color.parseColor("#983732"));
            txCjam.setTextColor(Color.parseColor("#FFFFFF"));

            cdClokasi.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            txClokasi.setTextColor(Color.parseColor("#000000"));


        }else{

            lJam.setVisibility(View.GONE);
            imLokasi.setVisibility(View.VISIBLE);

            cdClokasi.setCardBackgroundColor(Color.parseColor("#983732"));
            txClokasi.setTextColor(Color.parseColor("#FFFFFF"));

            cdCjam.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            txCjam.setTextColor(Color.parseColor("#000000"));

        }
    }






}
