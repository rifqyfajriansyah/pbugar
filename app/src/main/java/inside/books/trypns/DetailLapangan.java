package inside.books.trypns;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

    ImageView imBack, imLokasi;
    TextView txNama, txBintang, txPenjelasan, txCjam, txClokasi, txJam;
    CardView cdJam, cdLokasi, cdReservasi, cdCjam, cdClokasi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lapangan);


        context = DetailLapangan.this;
        initWidget();
        getDataku();




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

    }

    private void getDataku() {

        bundle = getIntent().getExtras();
        completeData = bundle.getString("lapangan");

        data = completeData.split("-");
        images[0] = getImage(data[6]);
        images[1] = getImage(data[6]);
        images[2] = getImage(data[6]);


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

    }

    private void initWidget(){

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);



    }

    private int getImage(String imageName) {

        Integer drawableResourceId = getResources().getIdentifier(imageName, "drawable", getPackageName());

        return drawableResourceId;
    }




}
