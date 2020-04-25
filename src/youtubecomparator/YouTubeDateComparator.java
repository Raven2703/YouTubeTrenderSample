/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package youtubecomparator;

import youtubeparser.YouTubeDataParserException;
import youtubeparser.YouTubeVideo;
import youtubeparser.YouTubeDataParser;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 41626
 */
public class YouTubeDateComparator implements Comparator {
    
    public static void test1(){
        System.out.println("Performing YouTubeDateComparator Test 1");
        String filename = "data/youtubedata_15_50.json";
        System.out.println("Testing the file: " + filename);
        System.out.println("Excepting Result:");
        System.out.println("--------------------");      
        System.out.println("sort by date id UC6eCfDyUc4TwvwxwTDxMfMg\n" +
                            "sort by date channel luckyhoof\n" +
                            "sort by date title Reunited after a long holiday\n" +
                            "sort by date date 2008-02-11T22:59:52.000Z\n" +
                            "sort by date views 1885637");
        System.out.println("--------------------");
        try{
            //Construct a YouTubeVideo list
            YouTubeDataParser dataparser = new YouTubeDataParser();
            List<YouTubeVideo> result = dataparser.parse(filename);
            YouTubeDateComparator cmp = new YouTubeDateComparator();
            //sort the result list by date
            Collections.sort(result, cmp);
            YouTubeVideo firstVideo = result.get(0);
            //print result
            System.out.println("Date Sort Result:");
            System.out.println("--------------------");
            System.out.println("sort by date id " + firstVideo.getId() + "\n" +
                            "sort by date channel " + firstVideo.getChannel() + "\n" +
                            "sort by date title " + firstVideo.getTitle()+ "\n" +
                            "sort by date date " + firstVideo.getDate() + "\n" +
                            "sort by date views " + firstVideo.getViewCount());
            System.out.println("--------------------");
        }
        catch (YouTubeDataParserException e)
        {
            e.printStackTrace();
        }
    }
    
    
    /**
     * compare two videos using their date
     * @param video1 
     * @param video2
     * @return {@code -1} if video1's Date is earlier to video2's. {@code 0} if video1's Date is equal to video2's
     * {@code 1} if video1's Date is later to video2's.
     */
    @Override
    public int compare(Object video1, Object video2) {
        YouTubeVideo v1 = (YouTubeVideo)video1;
        YouTubeVideo v2 = (YouTubeVideo)video2;
        try{
            return this.compare(v1, v2);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return -1;
    }
    
    /**
     * compare two videos using their date
     * @param video1 
     * @param video2
     * @return {@code -1} if video1's Date is earlier to video2's. {@code 0} if video1's Date is equal to video2's
     * {@code 1} if video1's Date is later to video2's.
     */
    public int compare(YouTubeVideo v1, YouTubeVideo v2) {
        String strdate1 = v1.getDate();
        String strdate2 = v2.getDate();
        Instant date1 = Instant.parse(strdate1);
        Instant date2 = Instant.parse(strdate2);
        int result = date1.compareTo(date2);
        return result;
    }
    
}
