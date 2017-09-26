package bangalore.bms.siemensmakeitreal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Harshit on 25-09-2017.
 */

public class Scoredisplay extends AppCompatActivity {
    Button btn;
    TextView tv;
    String data[]= new String[4];
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoresdisplay);
        tv=(TextView)findViewById(R.id.viewscore);
        Intent intent= getIntent();
        data= intent.getStringArrayExtra("patientdata");
        int score=intent.getIntExtra("score4",-1);
        btn=(Button)findViewById(R.id.ret_btn);
        tv.setText(""+score);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go =new Intent(Scoredisplay.this,MainActivity.class);
                go.putExtra("patientdata",data);
                startActivity(go);

            }
        });

    }
}
