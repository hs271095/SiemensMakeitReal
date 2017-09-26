package bangalore.bms.siemensmakeitreal;

/**
 * Created by Harshit on 25-09-2017.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PdMainactivity extends AppCompatActivity {

    private static boolean fingerTapTest = false, doubleTapTest = false;
    Patient p;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpd);
        final Intent intent =getIntent();
        p=(Patient)intent.getSerializableExtra("patient");
        Button fingerTap = (Button)findViewById(R.id.fingertapButton);
        Button doubleTap = (Button)findViewById(R.id.doubletapButton);
        Button testme=(Button)findViewById(R.id.PDsubmitButton);
        fingerTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fingerTapTest == true)
                    Toast.makeText(PdMainactivity.this, "You already completed this test", Toast.LENGTH_SHORT).show();
                else {
                    fingerTapTest = true;
                    Intent intent = new Intent(PdMainactivity.this, FingerTapper.class);
                    intent.putExtra("patient",p);
                    startActivity(intent);
                }
            }
        });

        doubleTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(doubleTapTest == true)
                    Toast.makeText(PdMainactivity.this, "You already completed this test", Toast.LENGTH_SHORT).show();
                else {
                    doubleTapTest = true;
                    Intent intent = new Intent(PdMainactivity.this, DoubleTapper.class);
                    intent.putExtra("patient",p);
                    startActivity(intent);
                }
            }
        });
        testme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(PdMainactivity.this,Pdscoredisplay.class);
                intent.putExtra("patient",p);
                startActivity(intent);
            }
        });
    }
}
