package inside.books.trypns;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class JadwalActivity extends AppCompatActivity {

    ImageView imback;
    Context context;
    CardView cardView;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);

        context = JadwalActivity.this;

        imback = findViewById(R.id.jadwalBack);
        cardView = findViewById(R.id.jadwalButton);

        imback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(context, MenuUtama.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(context, KonfirmasiActivity.class);
                startActivity(i);
            }
        });

    }
}
