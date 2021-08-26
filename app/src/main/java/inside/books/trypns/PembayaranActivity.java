package inside.books.trypns;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import inside.books.trypns.helper.Session;

public class PembayaranActivity extends AppCompatActivity {

    Session session;
    CheckBox cb;
    EditText edNama;
    ImageView imback;
    CardView cardView;

    Context context;

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);

        context = PembayaranActivity.this;
        session = new Session(context);

        imback = findViewById(R.id.bayarBack);
        cardView = findViewById(R.id.bayarButton);

        edNama = findViewById(R.id.bayarNama);
        cb = findViewById(R.id.bayarCb);

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    edNama.setText(session.getNama());
                }else{
                    edNama.setText("");
                }
            }
        });


        imback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(context, ReservasiActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
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
