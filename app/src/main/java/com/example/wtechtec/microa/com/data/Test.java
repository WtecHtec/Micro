package com.example.wtechtec.microa.com.data;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by WtecHtec on 2018/1/6.
 */

public class Test {
    private  String path="http://microa.duapp.com/DLkdId.jsp?userid=1020";
    public  String result;
    public Test(){

    }
    public  String GetMmess(){
        Log.i("Test",result+"12");


             HttpURLConnection conn   = null;
             try {
                 URL url=new URL(path);
                 conn = (HttpURLConnection) url.openConnection();
                 conn.setRequestMethod("POST");
                 result=getStreamAsString(conn.getInputStream(),"utf-8");


             } catch (IOException e) {
                 e.printStackTrace();
             }

      return  result.substring(result.indexOf("{"),result.length()-16);


    }
    private static String getStreamAsString(InputStream stream, String charset) throws IOException {
        try {
            Reader reader = new InputStreamReader(stream, charset);
            StringBuilder response = new StringBuilder();

            final char[] buff = new char[1024];
            int read = 0;
            while ((read = reader.read(buff)) > 0) {
                response.append(buff, 0, read);
            }

            return response.toString();
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }
}
