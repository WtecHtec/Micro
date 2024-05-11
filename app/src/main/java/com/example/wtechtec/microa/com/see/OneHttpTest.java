package com.example.wtechtec.microa.com.see;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by xuetao on 2017/9/11.
 */

public class OneHttpTest {

    private  String urlpath="http://v3.wufazhuce.com:8000/api/onelist/idlist/?channel=update&version=4.0.2&uuid=ffffffff-a90e-706a-63f7-ccf973aae5ee&platform=android";

    public  String  DoLists(){
        String result="";
        ByteArrayOutputStream baos;
        try {

            URL url=new URL(urlpath);

            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setConnectTimeout(1000);
            if (httpURLConnection.getResponseCode()==200){

                InputStream inputStream=httpURLConnection.getInputStream();
                 byte[] bytes=new byte[1024];
                int i=-1;
                baos=new ByteArrayOutputStream();
                while ((i=inputStream.read(bytes))!=-1){
                    baos.write(bytes,0,i);

                }
                result=new String(baos.toByteArray());



            }




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return  result;
    }


    public  String DoSeeOne( String itemid){
        String result="";


        ByteArrayOutputStream baos;
        try {
            String  path="   http://v3.wufazhuce.com:8000/api/essay/ "+ URLEncoder.encode(itemid,"UTF-8")+" ?channel=wdj&source=channel_reading&source_id=9264&version=4.0.2&uuid=ffffffff-a90e-706a-63f7-ccf973aae5ee&platform=android";

            URL url=new URL(path);

            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(1000);
            if (httpURLConnection.getResponseCode()==200){

                InputStream inputStream=httpURLConnection.getInputStream();
                byte[] bytes=new byte[1024];
                int i=-1;
                baos=new ByteArrayOutputStream();
                while ((i=inputStream.read(bytes))!=-1){
                    baos.write(bytes,0,i);

                }
                result=new String(baos.toByteArray());



            }




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return   result;
    }

    public  String DoOneList( String data){
         String result="";


        ByteArrayOutputStream baos;
        try {

            String  path="http://v3.wufazhuce.com:8000/api/onelist/"+ URLEncoder.encode(data,"UTF-8")+"/0?channel=wdj&version=4.0.2&uuid=ffffffff-a90e-706a-63f7-ccf973aae5ee&platform=android";
            URL url=new URL(path);

            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(1000);
            if (httpURLConnection.getResponseCode()==200){

                InputStream inputStream=httpURLConnection.getInputStream();
                byte[] bytes=new byte[1024];
                int i=-1;
                baos=new ByteArrayOutputStream();
                while ((i=inputStream.read(bytes))!=-1){
                    baos.write(bytes,0,i);

                }
                result=new String(baos.toByteArray());



            }




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return   result;
    }

    public  InputStream DoImageView(String  imageurl){

        InputStream inputStream=null;

        try {
            URL url=new URL(imageurl);
        HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(1000);

        inputStream=httpURLConnection.getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return  inputStream;

    }

}
