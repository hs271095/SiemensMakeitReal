package bangalore.bms.siemensmakeitreal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harshit on 26-09-2017.
 */

public class Pdscoredisplay extends AppCompatActivity {
    Patient p;
    TextView tv1;
    TextView tv2;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdscore);
        Intent intent=getIntent();
        p=(Patient)intent.getSerializableExtra("patient");
        tv1=(TextView)findViewById(R.id.score);
        tv2=(TextView)findViewById(R.id.score2);
        tv1.setText(""+p.score_1);
        tv2.setText(""+p.score_2);
        try {
            machineLearning();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void machineLearning() throws IOException {

        String path =  getApplicationContext().getFilesDir() + "/Data.csv";
        //Toast.makeText(this, path, Toast.LENGTH_SHORT).show();
        //File file = new File(path);
        File file = new File(getCacheDir(), "MyCache");
        file.createNewFile();
        FileWriter mFileWriter = null;
        try {
            mFileWriter = new FileWriter(file);
            //Toast.makeText(this, "Made", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            //Toast.makeText(this, "Not made", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        CSVWriter writer = new CSVWriter(mFileWriter);


        List<String[]> records = new ArrayList<String[]>();
        // adding header record
        records.add(new String[]{"age", "gender", "freq1", "freq2", "freq3", "freq4", "freq5", "diff1",  "diff2", "diff3", "diff4", "diff5", "diff6", "diff7", "diff8", "diff9", "diff10","theClass"});
        records.add(new String[]{"21", "male", "3",  "2.5", "3", "3.5", "3.5", "334", "354", "376", "354", "334", "312", "299", "323", "382", "350","negative"});
        records.add(new String[]{"22", "male", "2.5",  "3.0", "2.5", "3.5", "2.5", "364", "324", "260", "290", "334", "362", "379", "313", "332", "312","negative"});
        records.add(new String[]{"23", "male", "3.5",  "2.5", "2", "1", "0", "334", "200", "390", "150", "400", "500", "208", "390", "500", "350","positive"});
        records.add(new String[]{"25", "male", "3.5",  "1.5", "2.5", "1", "3.5", "329", "450", "432", "465", "490", "500", "390", "450", "500", "563","positive"});
        records.add(new String[]{"27", "male", "3",  "1", "2", "0.5", "3", "329", "450", "432", "465", "490", "500", "390", "450", "500", "563","positive"});
        records.add(new String[]{"31", "male", "3.5",  "1.5", "2.5", "1", "3.5", "309", "490", "412", "495", "400", "590", "390", "510", "580", "563","positive"});
        records.add(new String[]{"30", "male", "3",  "2.5", "3", "3.5", "3.5", "344", "350", "366", "334", "294", "302", "289", "333", "372", "340","negative"});
        records.add(new String[]{"23", "male", "2",  "2.5", "2", "3", "2.5", "364", "324", "260", "290", "334", "362", "379", "313", "332", "312","negative"});
        records.add(new String[]{"22", "male", "3",  "3.5", "4", "3.5", "4", "319", "305", "311", "303", "342", "291", "312", "331", "227", "340","negative"});
        records.add(new String[]{"26", "male", "2.5",  "3", "3", "3", "2.5", "490", "450", "424", "347", "373", "346", "357", "363", "360", "361","negative"});
        records.add(new String[]{"27", "male", "2.5",  "2", "2", "1", "1", "1222", "1146", "591", "1074", "521", "1246", "1171", "453", "1151", "900","positive"});
        records.add(new String[]{"31", "male", "1.5",  "0.5", "2.5", "2", "0.5", "886", "840", "323", "197", "1821", "1638", "1101", "591", "1264", "563","positive"});
        records.add(new String[]{"23", "male", "2",  "2.5", "2", "2.5", "2", "319", "305", "311", "303", "342", "291", "312", "331", "227", "340","negative"});
        records.add(new String[]{"21", "male", "1.5",  "2", "2.5", "3", "3.5", "490", "450", "424", "347", "373", "346", "357", "363", "360", "361","negative"});


        records.add(new String[]{"21", "female", "3",  "2.5", "3", "3.5", "3.5", "334", "354", "376", "354", "334", "312", "299", "323", "382", "350","negative"});
        records.add(new String[]{"22", "female", "2.5",  "3.0", "2.5", "3.5", "2.5", "364", "324", "260", "290", "334", "362", "379", "313", "332", "312","negative"});
        records.add(new String[]{"23", "female", "3.5",  "2.5", "2", "1", "0", "334", "200", "390", "150", "400", "500", "208", "390", "500", "350","positive"});
        records.add(new String[]{"25", "female", "3.5",  "1.5", "2.5", "1", "3.5", "329", "450", "432", "465", "490", "500", "390", "450", "500", "563","positive"});
        records.add(new String[]{"27", "female", "3",  "1", "2", "0.5", "3", "329", "450", "432", "465", "490", "500", "390", "450", "500", "563","positive"});
        records.add(new String[]{"31", "female", "3.5",  "1.5", "2.5", "1", "3.5", "309", "490", "412", "495", "400", "590", "390", "510", "580", "563","positive"});
        records.add(new String[]{"30", "female", "3",  "2.5", "3", "3.5", "3.5", "344", "350", "366", "334", "294", "302", "289", "333", "372", "340","negative"});
        records.add(new String[]{"23", "female", "2",  "2.5", "2", "3", "2.5", "364", "324", "260", "290", "334", "362", "379", "313", "332", "312","negative"});
        records.add(new String[]{"22", "female", "3",  "3.5", "4", "3.5", "4", "319", "305", "311", "303", "342", "291", "312", "331", "227", "340","negative"});
        records.add(new String[]{"26", "female", "2.5",  "3", "3", "3", "2.5", "490", "450", "424", "347", "373", "346", "357", "363", "360", "361","negative"});
        records.add(new String[]{"27", "female", "2.5",  "2", "2", "1", "1", "1222", "1146", "591", "1074", "521", "1246", "1171", "453", "1151", "900","positive"});
        records.add(new String[]{"31", "female", "1.5",  "0.5", "2.5", "2", "0.5", "886", "840", "323", "197", "1821", "1638", "1101", "591", "1264", "563","positive"});
        records.add(new String[]{"23", "female", "2",  "2.5", "2", "2.5", "2", "319", "305", "311", "303", "342", "291", "312", "331", "227", "340","negative"});
        records.add(new String[]{"21", "female", "1.5",  "2", "2.5", "3", "3.5", "490", "450", "424", "347", "373", "346", "357", "363", "360", "361","negative"});


        writer.writeAll(records);
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileReader mFileReader = null;

        try {
            mFileReader = new FileReader(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        CSVReader reader = new CSVReader(mFileReader);
        String[] myList = null;
        try {
            myList = reader.readNext();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final Attribute AttributeAge = new Attribute(myList[0]);
        FastVector fvNominalVal = new FastVector(2);
        fvNominalVal.addElement("male");
        fvNominalVal.addElement("female");
        final Attribute AttributeGender = new Attribute(myList[1], fvNominalVal);

        final Attribute AttributeFreq[] = new Attribute[5];
        AttributeFreq[0] = new Attribute(myList[2]);
        AttributeFreq[1] = new Attribute(myList[3]);
        AttributeFreq[2] = new Attribute(myList[4]);
        AttributeFreq[3] = new Attribute(myList[5]);
        AttributeFreq[4] = new Attribute(myList[6]);

        final Attribute AttributeDiff[] = new Attribute[10];
        AttributeDiff[0] = new Attribute(myList[7]);
        AttributeDiff[1] = new Attribute(myList[8]);
        AttributeDiff[2] = new Attribute(myList[9]);
        AttributeDiff[3] = new Attribute(myList[10]);
        AttributeDiff[4] = new Attribute(myList[11]);
        AttributeDiff[5] = new Attribute(myList[12]);
        AttributeDiff[6] = new Attribute(myList[13]);
        AttributeDiff[7] = new Attribute(myList[14]);
        AttributeDiff[8] = new Attribute(myList[15]);
        AttributeDiff[9] = new Attribute(myList[16]);


        // Declare a nominal attribute along with its values

        // Declare the class attribute along with its values
        FastVector fvClassVal = new FastVector(2);
        fvClassVal.addElement("positive");
        fvClassVal.addElement("negative");
        final Attribute ClassAttribute = new Attribute(myList[17], fvClassVal);

        FastVector fvWekaAttributes = new FastVector(5);

        fvWekaAttributes.addElement(AttributeAge);
        fvWekaAttributes.addElement(AttributeGender);
        for(int i=0;i<5;i++)
            fvWekaAttributes.addElement(AttributeFreq[i]);
        for(int i=0;i<10;i++)
            fvWekaAttributes.addElement(AttributeDiff[i]);
        fvWekaAttributes.addElement(ClassAttribute);

        // Create an empty training set
        Instances isTrainingSet = new Instances("Rel", fvWekaAttributes, 10);
        isTrainingSet.setClassIndex(17);

        int cnt = 0;
        Instance iExample = new Instance(18);
        try {
            myList = reader.readNext();
            while(myList != null)
            {
                cnt++;
                //Toast.makeText(this, "" + cnt, Toast.LENGTH_SHORT).show();

                iExample.setValue((Attribute)fvWekaAttributes.elementAt(0), Integer.parseInt(myList[0]));
                iExample.setValue((Attribute)fvWekaAttributes.elementAt(1), myList[1]);
                for(int i=0;i<5;i++)
                    iExample.setValue((Attribute)fvWekaAttributes.elementAt(i+2), Float.parseFloat(myList[i+2]));
                for(int i=0;i<10;i++)
                    iExample.setValue((Attribute)fvWekaAttributes.elementAt(i+7), Long.parseLong(myList[i+7]));
                iExample.setValue((Attribute)fvWekaAttributes.elementAt(17), myList[17]);

                // add the instance
                isTrainingSet.add(iExample);
                myList = reader.readNext();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Instances isEvalSet = new Instances("Rel", fvWekaAttributes, 10);

        // Set class index
        isEvalSet.setClassIndex(17);

        // Create the instance
        Instance eval = new Instance(18);
        eval.setValue((Attribute)fvWekaAttributes.elementAt(0), 25);
        eval.setValue((Attribute)fvWekaAttributes.elementAt(1), "male");
        for(int i=0;i<5;i++)
            eval.setValue((Attribute)fvWekaAttributes.elementAt(2+i), i + 1);
        for(int i=0;i<10;i++)
            eval.setValue((Attribute)fvWekaAttributes.elementAt(2+i), 350 + (-1)*i*25);
        eval.setValue((Attribute)fvWekaAttributes.elementAt(17), "positive");

        // add the instance
        isEvalSet.add(eval);

        eval = new Instance(18);
        eval.setValue((Attribute)fvWekaAttributes.elementAt(0), 26);
        eval.setValue((Attribute)fvWekaAttributes.elementAt(1), "male");
        for(int i=0;i<5;i++)
            eval.setValue((Attribute)fvWekaAttributes.elementAt(2+i), 3 + 1 * i%2);
        for(int i=0;i<10;i++)
            eval.setValue((Attribute)fvWekaAttributes.elementAt(2+i), 350 + 72 * i%2);
        eval.setValue((Attribute)fvWekaAttributes.elementAt(17), "negative");

        // add the instance
        isEvalSet.add(eval);

        eval = new Instance(18);
        eval.setValue((Attribute)fvWekaAttributes.elementAt(0), 26);
        eval.setValue((Attribute)fvWekaAttributes.elementAt(1), "male");
        for(int i=0;i<5;i++)
            eval.setValue((Attribute)fvWekaAttributes.elementAt(2+i), 3 + -2*i%2);
        for(int i=0;i<10;i++)
            eval.setValue((Attribute)fvWekaAttributes.elementAt(2+i), 350 + (-1)*i*50);
        eval.setValue((Attribute)fvWekaAttributes.elementAt(17), "positive");

        // add the instance
        isEvalSet.add(eval);

        Classifier cModel = (Classifier)new NaiveBayes();
        try {
            cModel.buildClassifier(isTrainingSet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Test the model
        Evaluation eTest = null;
        try {
            eTest = new Evaluation(isTrainingSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            eTest.evaluateModel(cModel, isEvalSet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Print the result à la Weka explorer:
        String strSummary = eTest.toSummaryString();
        Toast.makeText(this, "" + strSummary, Toast.LENGTH_SHORT).show();

        Instances dataUnpredicted = new Instances("TestInstances", fvWekaAttributes, 1);
        // last feature is target variable
        dataUnpredicted.setClassIndex(dataUnpredicted.numAttributes() - 1);

        Instance newInstance = new Instance(dataUnpredicted.numAttributes()) {
            {
                int age=Integer.parseInt(p.age);
                String gen=p.gender;
                setValue(AttributeAge, age);
                if(gen.equals("male"))
                    setValue(AttributeGender, "male");
                else
                    setValue(AttributeGender, "female");
                for(int i=0;i<5;i++)
                    setValue(AttributeFreq[i],p.getfroma(i));
                for(int i=0;i<10;i++)
                    setValue(AttributeDiff[i],p.getfromb(i));
            }
        };
        // reference to dataset
        newInstance.setDataset(dataUnpredicted);

        double result = 123;
        try {
            result = cModel.classifyInstance(newInstance);
            if(result == 0)
                Toast.makeText(this, "Tested Positive for PD", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Tested Negative for PD", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "An error occured", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
