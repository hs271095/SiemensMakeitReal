package bangalore.bms.siemensmakeitreal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class FingerTapper extends Activity {

    private int time = 11;
    private int taps = 0;
    private int tapsPerTwoSec = 0;
    private Handler handler;
    private Button bigButton;
    private TextView scoreText;
    private ArrayList<Float> tapFreq;
    Patient p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finger_tapper);
        Intent intent= getIntent();
        p=(Patient)intent.getSerializableExtra("patient");
        bigButton = (Button) findViewById(R.id.bigButton);
        scoreText = (TextView) findViewById(R.id.score);
        bigButton.setOnClickListener(bigListener);
        handler = new Handler();
        handler.post(runnable);
        tapFreq = new ArrayList<Float>();
    }

    private Runnable runnable = new Runnable() {

        @Override
        public void run()
        {
            if (time <= 0)
            {
                //finishGame();
                Toast.makeText(FingerTapper.this, "Finished the Test", Toast.LENGTH_SHORT).show();
                //Toast.makeText(FingerTapper.this, "" + tapFreq.get(0) + " " + tapFreq.get(1)+ " " + tapFreq.get(2)+ " " + tapFreq.get(3)+ " " + tapFreq.get(4), Toast.LENGTH_SHORT).show();
                //Toast.makeText(FingerTapper.this, "" + tapFreq.get(1), Toast.LENGTH_SHORT).show();
                parkinson_fscore FScorehelper = new parkinson_fscore();
                int sample = FScorehelper.pd_score(tapFreq);
                p.score_1=sample;
                p.insertfs(tapFreq);
                //Toast.makeText(FingerTapper.this, "" + sample, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FingerTapper.this, PdMainactivity.class);
                intent.putExtra("patient",p);
                startActivity(intent);
            }
            else
            {
                time--;
                bigButton.setText("" + time);
                if(time%2 == 0 && (time < 9)) {

                    tapFreq.add((float) tapsPerTwoSec / 2);
                    tapsPerTwoSec = 0;

                }
                handler.postDelayed(runnable,1000);
            }
        }
    };

    private View.OnClickListener bigListener = new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            taps += 1;
            tapsPerTwoSec += 1;
            scoreText.setText("Taps: " + taps);
        }

    };

}
