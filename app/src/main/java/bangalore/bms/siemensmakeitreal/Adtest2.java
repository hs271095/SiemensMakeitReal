package bangalore.bms.siemensmakeitreal;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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

public class Adtest2 extends AppCompatActivity {
    EditText ans;
    TextView textCounter;
    ImageView iv;
    Button btn;
    int score=2;
    String a;
    int score1;
    String data[]=new String[4];
    Boolean flag=false;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorytest2);
        Intent intent= getIntent();
        score1= intent.getIntExtra("score1",0);
        iv=(ImageView)findViewById(R.id.iv2);
        iv.setVisibility(GONE);
        btn=(Button)findViewById(R.id.btnnext2);
        textCounter=(TextView)findViewById(R.id.tv_timer2);
        ans=(EditText)findViewById(R.id.et_ans2);

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
                flag=true;
            }
        }.start();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go =new Intent(Adtest2.this,Adtest3.class);
                a=ans.getText().toString();
                    if(a=="" || !(a.matches("Red Cross")||a.matches("red cross"))){
                        score=0;
                        Toast.makeText(Adtest2.this, "You did not give the expected Answer", Toast.LENGTH_SHORT).show();
                        Toast.makeText(Adtest2.this, ""+score, Toast.LENGTH_SHORT).show();
                        Intent intent=getIntent();
                        data= intent.getStringArrayExtra("patientdata");
                        go.putExtra("patientdata",data);
                        go.putExtra("score2",score1+score);
                        startActivity(go);
                    }

                    Toast.makeText(Adtest2.this, ""+score, Toast.LENGTH_SHORT).show();

                Intent intent=getIntent();
                data= intent.getStringArrayExtra("patientdata");
                go.putExtra("patientdata",data);
                go.putExtra("score2",score1+score);
                    startActivity(go);

            }
        });
    }
}
