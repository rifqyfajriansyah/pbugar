package inside.books.trypns;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import inside.books.trypns.data.DataUser;
import inside.books.trypns.databaseHelper.DbUser;

public class RegisterActivity extends AppCompatActivity {

    EditText edNama, edEmail, edPassword;
    String nama, email, password;
    DataUser dataUser;
    TextView txLogin;
    CardView cdDaftar;

    Context context;
    DbUser dbUser;

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        context = RegisterActivity.this;
        setWidget();

        cdDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setValue();
                if(!nama.equals("")&&!email.equals("")&&!password.equals("")){
                    if(dbUser.setInsert(dataUser)==1){
                        Toast.makeText(context, "Berhasil menambah akun", Toast.LENGTH_SHORT).show();
                        i = new Intent(context, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);;
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(context, "User telah terdaftar", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context, "Silahkan lengkapi form terlebih dahulu", Toast.LENGTH_SHORT).show();
                }

            }
        });

        txLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(context, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            }
        });

    }

    private void setWidget(){

        edNama = findViewById(R.id.edDaftarNama);
        edEmail = findViewById(R.id.edDaftarEmail);
        edPassword = findViewById(R.id.edDaftarPassword);

        txLogin = findViewById(R.id.txMasuk);
        cdDaftar = findViewById(R.id.btDaftar);

        dbUser = new DbUser(context);


    }

    private void setValue(){

        nama = edNama.getText().toString();
        email = edEmail.getText().toString();
        password = edPassword.getText().toString();

        dataUser = new DataUser(nama, email, password);

    }



}
