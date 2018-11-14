package rych.gbook2;

import android.util.JsonReader;

import java.io.IOException;

public class BLreader {
    public static  boolean findName(JsonReader reader, String name){
        String mName;
        try {
            if(reader.hasNext()) mName=reader.nextName();
            else return false;

            while (!mName.equals(name)){
                reader.skipValue();
                if(reader.hasNext())
                    mName=reader.nextName();
                else return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
