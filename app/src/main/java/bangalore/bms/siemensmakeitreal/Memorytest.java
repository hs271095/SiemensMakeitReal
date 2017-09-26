package bangalore.bms.siemensmakeitreal;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Harshit on 25-09-2017.
 */

public class Memorytest extends AppCompatActivity {
    String data[]=new String[4];
    TextView timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorytest);

        timer=(TextView)findViewById(R.id.timer);
        new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timer.setText("done!");
                Intent go = new Intent(Memorytest.this,Adtest.class);
                Intent intent=getIntent();
                data= intent.getStringArrayExtra("patientdata");
                go.putExtra("patientdata",data);
                startActivity(go);

            }
        }.start();
    }
}
