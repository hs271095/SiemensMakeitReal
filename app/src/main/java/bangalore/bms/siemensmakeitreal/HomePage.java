package bangalore.bms.siemensmakeitreal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Harshit on 24-09-2017.
 */

public class HomePage extends AppCompatActivity {
    String data[]=new String[4];
    TextView tv;
    Button btnStartPD;
    Button btnStartAD;
    Patient P;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        tv=(TextView)findViewById(R.id.tv2);
        Intent intent1=getIntent();
        data= intent1.getStringArrayExtra("patientdata");
        P=(Patient)intent1.getSerializableExtra("patient");
        btnStartAD=(Button)findViewById(R.id.btnAD);
        btnStartPD=(Button)findViewById(R.id.BTNpd);
        btnStartPD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(HomePage.this,PdMainactivity.class);
                intent.putExtra("patientdata",data);
                intent.putExtra("patient",P);
                startActivity(intent);
            }
        });
        btnStartAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(HomePage.this,Memorytest.class);
                intent.putExtra("patientdata",data);
                startActivity(intent);

            }
        });

         if (data==null) {
             alert();
        }
        else{
             tv.setText(data[0]);
         }



    }
    public void alert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.MyDialogTheme);
        builder.setMessage("Register the Patient First ");
        builder.setTitle("Patient Not Registered");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent go = new Intent(HomePage.this,Register_page.class);
                startActivity(go);
            }
        });
        builder.show();
    }
}
