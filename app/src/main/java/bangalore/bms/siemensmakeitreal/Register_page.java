package bangalore.bms.siemensmakeitreal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by Harshit on 24-09-2017.
 */

public class Register_page extends AppCompatActivity{
    Button register;
    EditText et_name;
    EditText age;
    RadioGroup gendermf;
    RadioGroup type_pdad;
    String gender;
    String type;
    String name;
    String age_;
    Context context=this;
    Patient p=new Patient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
        register=(Button)findViewById(R.id.register_but);
        et_name=(EditText)findViewById(R.id.et_com_name);
        age=(EditText)findViewById(R.id.et_age);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Register_page.this, "Patient Data inserted Successfully!", Toast.LENGTH_SHORT).show();
                gendermf=(RadioGroup)findViewById(R.id.gender_mf);
                type_pdad=(RadioGroup)findViewById(R.id.type);
                gender=((RadioButton)findViewById(gendermf.getCheckedRadioButtonId())).getText().toString();
                type=((RadioButton)findViewById(type_pdad.getCheckedRadioButtonId())).getText().toString();
                name=et_name.getText().toString();
                age_ =age.getText().toString();
                String data[]=new String[4];
                data[0]=name;
                data[1]=age_;
                data[2]=gender;
                data[3]=type;
                p.insertdata(name, age_,gender);
                DataHelper dataHelper=new DataHelper(context);
                dataHelper.insertData(name, age_,gender,type);
                Intent go= new Intent(Register_page.this,HomePage.class);
                go.putExtra("patientdata",data);
                go.putExtra("patient",p);
                startActivity(go);

            }
        });




    }
}
