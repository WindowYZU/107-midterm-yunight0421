/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lendle.courses.wp.midterm_wp;

/**
 *
 * @author lendle
 */
public interface FileDownloaderCallback {
    public void totalBytesDownloaded(long bytes, boolean finished, boolean failed);
}
