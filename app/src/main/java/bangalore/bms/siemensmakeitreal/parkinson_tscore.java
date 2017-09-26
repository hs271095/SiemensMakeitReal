package bangalore.bms.siemensmakeitreal;

import java.util.ArrayList;

/**
 * Created by Harshit on 25-09-2017.
 */


    class parkinson_tscore
    {
        public int pd_score(ArrayList<Long>a)
        {

            int scale0=0;
            int x=0;
            long sum=0;
            double avg=0.0;

            int length= 10;
            for(int i=0;i<length-1;i++)
            {
                if(a.get(i)==a.get(i+1) || a.get(i)>a.get(i+1))
                    scale0+=1;

            }
            if(scale0>length-2)
                return 0;

            for(int k=0;k<length-1;k++)
            {
                sum=sum+Math.abs(a.get(k+1)-a.get(k));
            }
            avg=(float)sum/length-1;
            if(avg<30.0)
                x=1;
            else if(avg>=30.0 && avg<70.0)
                x=2;
            else if(avg>=70.0 && avg<150.0)
                x=3;
            else if(avg>150.0)
                x=4;
            return x;


        }
    }


