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

public class JZgcData {
    public JZgcData(){


    }

    public  String result;



    public  void GetDelMmess(String id){
        String path= null;





        HttpURLConnection conn   = null;
        try {
            path = "http://microa.duapp.com/DNbsDel.jsp?id="+ URLEncoder.encode(id,"utf-8");
            URL url=new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            result=getStreamAsString(conn.getInputStream(),"utf-8");


        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    public  String GetIdMmess(){
        String path= null;





        HttpURLConnection conn   = null;
        try {
            path = "http://microa.duapp.com/JZgcSee.jsp";
            URL url=new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            result=getStreamAsString(conn.getInputStream(),"utf-8");


        } catch (IOException e) {
            e.printStackTrace();
        }

        return  result.substring(result.indexOf("{"),result.length()-16);


    }


    public  String GetMmessAll(){
        String path= null;





        HttpURLConnection conn   = null;
        try {
            path = "http://microa.duapp.com/JZgcSeeAll.jsp";
            URL url=new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            result=getStreamAsString(conn.getInputStream(),"utf-8");


        } catch (IOException e) {
            e.printStackTrace();
        }


        return  result.substring(result.indexOf("{"),result.length()-16);


    }

    public  void GetJDMmess(String id){
        String path= null;





        HttpURLConnection conn   = null;
        try {
            path = "http://microa.duapp.com/DNUpdate.jsp?id="+ URLEncoder.encode(id,"utf-8");
            URL url=new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            result=getStreamAsString(conn.getInputStream(),"utf-8");
            Log.i("DEl",result+"22");

        } catch (IOException e) {
            e.printStackTrace();
        }




    }


    public  void  AddData(String userid, String dnsw, String bsaddress, String bsphon, String bssj, String bsly,
                          String xf, String zt,String xdsj, String xx){
         String path= null;





         HttpURLConnection conn   = null;
         try {
         //    String  pt="{\"userid\":\"1020\",\"qhdz\":\"创业园112\",\"qhh\":\"2468\",\"shdz\":\"男生宿舍六栋\",\"shhm\":\"13456768779\",\"shsj\":\"2018.1.6\",\"bzly\":\"\",\"xf\":\"1\",\"xdsj\":\"2018-01-06 13:50\"}";
          //String paths = "{'userid':"+userid+",'qhdz':"+qhdz+",'qhh':"+qhh+",'shdz':"+shdz+",'shhm':"+shhm+",'shsj':"+shsj+",'bzly':"+bzly+",'xf':"+xf+",'xdsj':"+xdsj+"}";
           // path="http://microa.duapp.com/DLkdAdd.jsp?dlkdifo="+URLEncoder.encode(paths,"utf-8");
             path="http://microa.duapp.com/DNbsAdd.jsp?dnbsifo={%27userid%27:%27"+URLEncoder.encode(userid,"utf-8")+"%27,%27dnsw%27:%27"+URLEncoder.encode(dnsw,"utf-8")+"%27,%27bsaddress%27:%27"+URLEncoder.encode(bsaddress,"utf-8")+"%27,%27bsphone%27:%27"+URLEncoder.encode(bsphon,"utf-8")+"%27,%27bssj%27:%27"+URLEncoder.encode(bssj,"utf-8")+"%27,%27bzly%27:%27"+URLEncoder.encode(bsly,"utf-8")+"%27,%27xf%27:%27"+URLEncoder.encode(xf,"utf-8")+"%27,%27zt%27:%27"+URLEncoder.encode(zt,"utf-8")+"%27,%27xdsj%27:%27"+URLEncoder.encode(xdsj,"utf-8")+"%27,%27xx%27:%27"+URLEncoder.encode(xx,"utf-8")+"%27}";
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
