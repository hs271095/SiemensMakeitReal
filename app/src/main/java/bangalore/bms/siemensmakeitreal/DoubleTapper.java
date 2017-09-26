package bangalore.bms.siemensmakeitreal;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class DoubleTapper extends Activity {

    private int time = 10;
    private int taps = 0;
    private Handler handler;
    private Button leftButton, rightButton;
    private TextView scoreText;
    private ArrayList<Long> tapTimeIntervals;
    long firstTap;
    long secondTap;
    int selected;
    Patient p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.double_tapper);
        leftButton = (Button) findViewById(R.id.leftButton);
        rightButton = (Button)findViewById(R.id.rightButton);
        scoreText = (TextView) findViewById(R.id.scoreDouble);
        Intent intent= getIntent();
        p=(Patient)intent.getSerializableExtra("patient");
        firstTap = secondTap = -1;
        selected = -1;
        tapTimeIntervals = new ArrayList<Long>();
        leftButton.setBackgroundColor(Color.GREEN);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selected == 1)
                    Toast.makeText(DoubleTapper.this, "Please tap other button", Toast.LENGTH_SHORT).show();
                else{
                    leftButton.setBackgroundColor(Color.LTGRAY);
                    taps += 1;
                    scoreText.setText("Taps: " + taps);
                    selected = 1;
                    if(selected == -1)
                        firstTap = System.currentTimeMillis();
                    else{
                        secondTap = System.currentTimeMillis();
                        tapTimeIntervals.add(secondTap - firstTap);
                        firstTap = secondTap;
                    }
                    selected = 1;
                    rightButton.setBackgroundColor(Color.GREEN);
                }
            }
        });
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selected == 2)
                    Toast.makeText(DoubleTapper.this, "Please tap other button", Toast.LENGTH_SHORT).show();
                else {
                    rightButton.setBackgroundColor(Color.LTGRAY);
                    taps += 1;
                    scoreText.setText("Taps: " + taps);
                    secondTap = System.currentTimeMillis();
                    tapTimeIntervals.add(secondTap - firstTap);
                    firstTap = secondTap;
                    selected = 2;
                    leftButton.setBackgroundColor(Color.GREEN);
                }
            }
        });
        handler = new Handler();
        handler.post(runnable);
    }

    private Runnable runnable = new Runnable() {

        @Override
        public void run()
        {
            if (time <= 0)
            {
                //finishGame();
                Toast.makeText(DoubleTapper.this, "Finished the Test", Toast.LENGTH_SHORT).show();
                parkinson_tscore scoreHelper= new parkinson_tscore();
                int x = scoreHelper.pd_score(tapTimeIntervals);
                Intent intent = new Intent(DoubleTapper.this, PdMainactivity.class);
                p.score_2=x;
                p.inserttd(tapTimeIntervals);
                intent.putExtra("patient",p);
                startActivity(intent);
            }
            else
            {
                time--;
                leftButton.setText("" + time);
                rightButton.setText("" + time);
                handler.postDelayed(runnable,1000);
            }
        }
    };
}
