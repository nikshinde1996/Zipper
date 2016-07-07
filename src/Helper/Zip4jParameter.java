package Helper;

/**
 * Created by nikhi on ०५-०७-२०१६.
 */

import java.util.*;
import net.lingala.zip4j.util.Zip4jConstants;
import java.lang.*;
import javax.swing.*;

public class Zip4jParameter{
    public static Map<String,Integer> parameters = new LinkedHashMap<>();

    public Zip4jParameter(){
        parameters.put("AES_STRENGTH_128 ",Zip4jConstants.AES_STRENGTH_128);
        parameters.put("AES_STRENGTH_192 ",Zip4jConstants.AES_STRENGTH_192);
        parameters.put("AES_STRENGTH_256 ",Zip4jConstants.AES_STRENGTH_256);
        parameters.put("COMP_AES_ENC ",Zip4jConstants.COMP_AES_ENC);
        parameters.put("COMP_DEFLATE ",Zip4jConstants.COMP_DEFLATE);
        parameters.put("DEFLATE_LEVEL_FAST ",Zip4jConstants.DEFLATE_LEVEL_FAST);
        parameters.put("DEFLATE_LEVEL_FASTEST ",Zip4jConstants.DEFLATE_LEVEL_FASTEST);
        parameters.put("DEFLATE_LEVEL_MAXIMUM ",Zip4jConstants.DEFLATE_LEVEL_MAXIMUM);
        parameters.put("DEFLATE_LEVEL_NORMAL ",Zip4jConstants.DEFLATE_LEVEL_NORMAL);
        parameters.put("DEFLATE_LEVEL_ULTRA ",Zip4jConstants.DEFLATE_LEVEL_ULTRA);
        parameters.put("ENC_METHOD_AES ",Zip4jConstants.ENC_METHOD_AES);
        parameters.put("ENC_METHOD_STANDARD ",Zip4jConstants.ENC_METHOD_STANDARD);
        parameters.put("ENC_NO_ENCRYPTION ",Zip4jConstants.ENC_NO_ENCRYPTION);
    }

    public static Map<String,Integer> getCompresssionLevels(){
        return parameters;
    }

    public static String[] getLevels(){
        String[] level = new String[parameters.size()];
        int i=0;
        for(Object o : parameters.keySet()){
            level[i++] = new String(o.toString());
        }
        return level;
    }

    public static String[] getCompLevels(){
        String x[] = {"AES_STRENGTH_128 ","AES_STRENGTH_192 ","AES_STRENGTH_256 ","COMP_AES_ENC ","COMP_DEFLATE ","DEFLATE_LEVEL_FAST ","DEFLATE_LEVEL_FASTEST ",
        "DEFLATE_LEVEL_MAXIMUM ","DEFLATE_LEVEL_NORMAL ","DEFLATE_LEVEL_ULTRA ", "ENC_METHOD_AES ", "ENC_METHOD_STANDARD ", "ENC_NO_ENCRYPTION "};

        return x;
    }
}
