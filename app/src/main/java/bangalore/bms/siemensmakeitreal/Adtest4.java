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

public class Adtest4 extends AppCompatActivity {
    EditText ans;
    TextView textCounter;
    ImageView iv;
    Button btn;
    int score=2;
    int score3;
    String a;
    String data[]=new String[4];
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorytest4);
        iv=(ImageView)findViewById(R.id.iv4);
        iv.setVisibility(GONE);
        Intent intent=getIntent();
        score3=intent.getIntExtra("score3",0);
        btn=(Button)findViewById(R.id.btnnext4);
        textCounter=(TextView)findViewById(R.id.tv_timer4);
        ans=(EditText)findViewById(R.id.et_ans4);

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
                Intent go =new Intent(Adtest4.this,Scoredisplay.class);
                a=ans.getText().toString();
                if(a.matches("")|| !(a.matches("Telegram")||a.matches("telegram"))){
                    score=0;
                    Toast.makeText(Adtest4.this, "You did not Answer the Question", Toast.LENGTH_SHORT).show();
                    Toast.makeText(Adtest4.this, ""+score, Toast.LENGTH_SHORT).show();
                    Intent intent=getIntent();
                    data= intent.getStringArrayExtra("patientdata");
                    go.putExtra("patientdata",data);
                    go.putExtra("score4",score3+score);
                    startActivity(go);
                }
                if(a.matches("Telegram")||a.matches("telegram")) {

                    Toast.makeText(Adtest4.this, "" + score, Toast.LENGTH_SHORT).show();
                    Intent intent=getIntent();
                    data= intent.getStringArrayExtra("patientdata");
                    go.putExtra("patientdata",data);
                    go.putExtra("score4", score3 + score);
                    startActivity(go);
                }

            }
        });
    }
}

