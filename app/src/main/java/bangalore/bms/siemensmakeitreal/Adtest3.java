package bangalore.bms.siemensmakeitreal;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.GONE;

/**
 * Created by Harshit on 25-09-2017.
 */

public class Adtest3 extends AppCompatActivity {
    EditText ans;
    TextView textCounter;
    ImageView iv;
    Button btn;
    int score=2;
    int score2;
    String a;
    String data[]=new String[4];
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorytest3);
        iv=(ImageView)findViewById(R.id.iv3);
        iv.setVisibility(GONE);
        Intent intent=getIntent();
        score2=intent.getIntExtra("score2",0);
        btn=(Button)findViewById(R.id.btnnext3);
        textCounter=(TextView)findViewById(R.id.tv_timer3);
        ans=(EditText)findViewById(R.id.et_ans3);

        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                textCounter.setText("seconds remaining: " + millisUntilFinished / 1000);
                if(millisUntilFinished/1000==15){
                    iv.setVisibility(View.VISIBLE);
                    score-=1;
                }
            }

            public void onFinish() {
                textCounter.setText("done!");

            }
        }.start();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go =new Intent(Adtest3.this,Adtest4.class);
                a=ans.getText().toString();
                if(a.matches("") || !(a.matches("Sauce Pan")||a.matches("sauce pan"))){
                    score=0;
                    Toast.makeText(Adtest3.this, "You  did not  give the expected Answer", Toast.LENGTH_SHORT).show();
                    Toast.makeText(Adtest3.this, ""+score, Toast.LENGTH_SHORT).show();
                    Intent intent=getIntent();
                    data= intent.getStringArrayExtra("patientdata");
                    go.putExtra("patientdata",data);
                    go.putExtra("score3", score2 + score);
                    startActivity(go);
                }
                if(a.matches("Sauce Pan")||a.matches("sauce pan")){

                    Toast.makeText(Adtest3.this, "" + score, Toast.LENGTH_SHORT).show();
                    Intent intent=getIntent();
                    data= intent.getStringArrayExtra("patientdata");
                    go.putExtra("patientdata",data);
                    go.putExtra("score3", score2 + score);
                    startActivity(go);

                }

            }
        });
    }

}
