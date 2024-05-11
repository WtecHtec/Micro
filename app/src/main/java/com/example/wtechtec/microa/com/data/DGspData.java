package com.example.wtechtec.microa.com.data;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by WtecHtec on 2018/1/6.
 */

public class DGspData {
    public DGspData(){


    }

    public  String result;



    public  void GetDelMmess(String id){
        String path= null;





        HttpURLConnection conn   = null;
        try {
            path = "http://microa.duapp.com/DGspDel.jsp?id="+ URLEncoder.encode(id,"utf-8");
            URL url=new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            result=getStreamAsString(conn.getInputStream(),"utf-8");
Log.i("DEl",result+"22");

        } catch (IOException e) {
            e.printStackTrace();
        }




    }


    public  void GetJDMmess(String id){
        String path= null;





        HttpURLConnection conn   = null;
        try {
            path = "http://microa.duapp.com/DGUpdate.jsp?id="+ URLEncoder.encode(id,"utf-8");
            URL url=new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            result=getStreamAsString(conn.getInputStream(),"utf-8");
            Log.i("DEl",result+"22");

        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    public  String GetDIdMmess(String userid){
        String path= null;





        HttpURLConnection conn   = null;
        try {
            path = "http://microa.duapp.com/DGspDid.jsp?d_id="+ URLEncoder.encode(userid,"utf-8");
            URL url=new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            result=getStreamAsString(conn.getInputStream(),"utf-8");


        } catch (IOException e) {
            e.printStackTrace();
        }

        return  result.substring(result.indexOf("{"),result.length()-16);


    }
    public  String GetIdMmess(String userid){
        String path= null;





        HttpURLConnection conn   = null;
        try {
            path = "http://microa.duapp.com/DGspId.jsp?userid="+ URLEncoder.encode(userid,"utf-8");
            URL url=new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            result=getStreamAsString(conn.getInputStream(),"utf-8");


        } catch (IOException e) {
            e.printStackTrace();
        }

        return  result.substring(result.indexOf("{"),result.length()-16);


    }


    public  String GetMmess(String xx){
        String path= null;





        HttpURLConnection conn   = null;
        try {
            path = "http://microa.duapp.com/DGspSee.jsp?xx="+URLEncoder.encode(xx,"utf-8")+"";
            URL url=new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            result=getStreamAsString(conn.getInputStream(),"utf-8");


        } catch (IOException e) {
            e.printStackTrace();
        }


        return  result.substring(result.indexOf("{"),result.length()-16);


    }



    public  void  AddData( String userid, String spname, String spnumber, String shaddress, String shphone,
                          String shsj, String bzly, String xf, String xdsj, String xx){
         String path= null;





         HttpURLConnection conn   = null;
         try {
         //    String  pt="{\"userid\":\"1020\",\"qhdz\":\"创业园112\",\"qhh\":\"2468\",\"shdz\":\"男生宿舍六栋\",\"shhm\":\"13456768779\",\"shsj\":\"2018.1.6\",\"bzly\":\"\",\"xf\":\"1\",\"xdsj\":\"2018-01-06 13:50\"}";
          //String paths = "{'userid':"+userid+",'qhdz':"+qhdz+",'qhh':"+qhh+",'shdz':"+shdz+",'shhm':"+shhm+",'shsj':"+shsj+",'bzly':"+bzly+",'xf':"+xf+",'xdsj':"+xdsj+"}";
           // path="http://microa.duapp.com/DLkdAdd.jsp?dlkdifo="+URLEncoder.encode(paths,"utf-8");
             path="http://microa.duapp.com/DGspAdd.jsp?dgspifo={%27userid%27:%27"+URLEncoder.encode(userid,"utf-8")+"%27,%27spname%27:%27"+URLEncoder.encode(spname,"utf-8")+"%27,%27spnumber%27:%27"+URLEncoder.encode(spnumber,"utf-8")+"%27,%27shaddress%27:%27"+URLEncoder.encode(shaddress,"utf-8")+"%27,%27shphone%27:%27"+URLEncoder.encode(shphone,"utf-8")+"%27,%27shsj%27:%27"+URLEncoder.encode(shsj,"utf-8")+"%27,%27bzly%27:%27"+URLEncoder.encode(bzly,"utf-8")+"%27,%27xf%27:%27"+URLEncoder.encode(xf,"utf-8")+"%27,%27xx%27:%27"+URLEncoder.encode(xx,"utf-8")+"%27,%27xdsj%27:%27"+URLEncoder.encode(xdsj,"utf-8")+"%27}";
             URL url=new URL(path);
             conn = (HttpURLConnection) url.openConnection();
             conn.setRequestMethod("POST");
             String l=getStreamAsString(conn.getInputStream(),"utf-8");



         } catch (IOException e) {
             e.printStackTrace();
         }

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
