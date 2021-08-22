package inside.books.trypns;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedIstanceState){
        super.onCreate(savedIstanceState);
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
