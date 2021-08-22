package inside.books.trypns;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import inside.books.trypns.data.DataUser;
import inside.books.trypns.databaseHelper.DbUser;
import inside.books.trypns.helper.Session;

public class MainActivity extends AppCompatActivity {

    Context context;
    EditText edEmail, edPassword;
    CardView cdMasuk;
    CheckBox cbIngat;
    TextView txDaftar, txLupa;

    String email, password;
    int ingat;
    Intent i;

    Session session;
    DbUser dbUser;
    DataUser dataUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;

        setWidget();
        setFunc();

        txDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(context, RegisterActivity.class);
                startActivity(i);
            }
        });

        cdMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setValue();
                if(!email.equals("")&&!password.equals("")){

                    dataUser = dbUser.getUser(email, password);
                    if(dataUser!=null){

                        session.saveSPString(Session.NAMA, dataUser.getNama());
                        session.saveSPString(Session.EMAIL, dataUser.getEmail());
                        session.saveSPInt(Session.TIPELOGIN, ingat);

                        i = new Intent(context, MenuUtama.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        finish();

                    }else{
                        Toast.makeText(context, "User dan password tidak sesuai", Toast.LENGTH_SHORT).show();
                    }


                }else{
                    Toast.makeText(context, "Silahkan lengkapi form terlebih dahulu", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    private void setWidget(){

        session  = new Session(context);

        txDaftar = findViewById(R.id.txDaftar);
        txLupa = findViewById(R.id.txLupa);
        edEmail = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);

        cdMasuk = findViewById(R.id.btMasuk);
        cbIngat = findViewById(R.id.rdIngat);

        dbUser = new DbUser(context);

    }

    private void setValue(){

        email = edEmail.getText().toString();
        password = edPassword.getText().toString();
        ingat = getCheck();

    }

    private void setFunc(){

        if(session.getLogin()==1){

            i = new Intent(context, MenuUtama.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();

        }else{
            session.saveSPString(Session.NAMA, null);
            session.saveSPString(Session.EMAIL, null);
        }
    }

    private int getCheck(){
        if(cbIngat.isChecked()){
            return 1;
        }else{
            return 0;
        }
    }



}
