/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lendle.courses.wp.midterm_wp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lendle
 */
public class FileDownloader {
    static void downloadFile(final URL url, final File destinationFile, final FileDownloaderCallback callback){
        Thread t=new Thread(){
            public void run(){
                try{
                    byte [] buffer=new byte[4096];
                    long bytesDownloaded=0;
                    HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
                    httpcon.addRequestProperty("User-Agent", "Mozilla/4.0");
                    try(InputStream input=httpcon.getInputStream(); OutputStream output=new FileOutputStream(destinationFile)){
                        int bytesRead=input.read(buffer);
                        while(bytesRead!=-1){
                            bytesDownloaded+=bytesRead;
                            output.write(buffer, 0, bytesRead);
                            callback.totalBytesDownloaded(bytesDownloaded, false, false);
                            System.out.println("read");
                            bytesRead=input.read(buffer);
                            System.out.println("after read");
                        }
                        callback.totalBytesDownloaded(bytesDownloaded, true, false);
                    }catch(Exception e){
                        e.printStackTrace();
                        callback.totalBytesDownloaded(bytesDownloaded, false, true);
                    }
                }catch(IOException ex){
                    Logger.getLogger(FileDownloader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        t.start();
    }
}
