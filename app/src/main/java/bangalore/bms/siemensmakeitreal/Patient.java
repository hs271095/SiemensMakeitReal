package bangalore.bms.siemensmakeitreal;

import java.io.Serializable;
import java.io.SerializablePermission;
import java.util.ArrayList;

/**
 * Created by Harshit on 26-09-2017.
 */

public class Patient implements Serializable{
    public String name;
    public String age;
    public String gender;
    public ArrayList<Float> a;
    public ArrayList<Long>b;
    public int score_1;
    public int score_2;
    Patient(){

    }
    public void insertdata(String n, String a,String g){
        name=n;
        age=a;
        gender=g;
    }
    public void insertfs(ArrayList<Float> f){
        a = new ArrayList<Float>();
        for(int i=0;i<f.size();i++)
            a.add(f.get(i));

    }
    public void inserttd(ArrayList<Long> l ){
        b = new ArrayList<Long>();
        for(int i=0;i<l.size();i++)
            b.add(l.get(i));

    }
    public float getfroma(int i){
        return a.get(i);
    }
    public long getfromb(int i){
        return b.get(i);
    }
    public void insert_score1(int s1){
        score_1=s1;
    }
    public void insert_score2(int s2){
        score_2=s2;
    }
}
