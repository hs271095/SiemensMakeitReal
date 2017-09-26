package bangalore.bms.siemensmakeitreal;

import java.util.ArrayList;

class parkinson_fscore
{

    public int pd_score(ArrayList<Float> a)
    {
        int x;
        int y=0;
        int z=0;
        int j=0;
        int k=0;
        int scale0=0;
        int final_score;
        double sum=0.0;
        double avg=0.0;
        double score=0.0;
        for(int i=0;i<4;i++)
        {
            if(a.get(i)==a.get(i+1) || a.get(i)<a.get(i+1))
                scale0+=1;
            sum+=a.get(i);
        }
        if(scale0>3)
            return 0;

        if((a.get(0)-a.get(4))>=2.5)
            x=4;
        else if((a.get(0)-a.get(4))>=2.0 && (a.get(0)-a.get(4))<2.5)
            x=3;
        else if((a.get(0)-a.get(4))>=1.5 && (a.get(0)-a.get(4))<2.0)
            x=2;
        else if((a.get(0)-a.get(4))>=1.0 && (a.get(0)-a.get(4))<1.5)
            x=1;
        else
            x=0;

        while(k!=-1 && j<4)
        {
            if((a.get(j)-a.get(j+1))>=2.5)
            {
                y=4;
                k=-1;
            }

            else if((a.get(j)-a.get(j+1))>=2.0 && (a.get(j)-a.get(j+1))<2.5)
            {
                y=3;
                k=-1;
            }
            else if((a.get(j)-a.get(j+1))>=1.5 && (a.get(j)-a.get(j+1))<2.0)
            {
                y=2;
                k=-1;
            }
            else if((a.get(j)-a.get(j+1))>=1.0 && (a.get(j)-a.get(j+1))<1.5)
            {
                y=1;
                k=-1;
            }
            j+=1;
        }



        avg=sum/5;
        if(avg>2.0 && avg<=2.5)
            z=1;
        else if(avg>1.5 && avg<=2.0)
            z=2;
        else if(avg>1.0 && avg<=1.5)
            z=3;
        else if(avg<=1.0)
            z=4;
        score=(x+y+z)/3.0;
        final_score=(int)Math.round(score);
        return final_score;
    }
}
