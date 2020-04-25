/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package youtubecomparator;

import youtubeparser.YouTubeDataParserException;
import youtubeparser.YouTubeVideo;
import youtubeparser.YouTubeDataParser;
import java.time.Instant;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author 41626
 */
public class YouTubeVideoChannelComparator implements Comparator{
    public static void test1(){
        System.out.println("Performing YouTubeVideoChannelComparator Test 1");
        String filename = "data/youtubedata_15_50.json";
        System.out.println("Testing the file: " + filename);
        System.out.println("Excepting Result:");
        System.out.println("--------------------");      
        System.out.println("sort by channel name id UC1DeG8ZTab89_XNNqWxlDqA\n" +
                            "sort by channel name channel AllHamishandAndyVids\n" +
                            "sort by channel name title Hamish & Andy - Kangaroo Lady\n" +
                            "sort by channel name date 2016-05-01T06:00:01.000Z\n" +
                            "sort by channel name views 5313");
        System.out.println("--------------------");
        try{
            //Construct a YouTubeVideo list
            YouTubeDataParser dataparser = new YouTubeDataParser();
            List<YouTubeVideo> result = dataparser.parse(filename);
            YouTubeVideoChannelComparator cmp = new YouTubeVideoChannelComparator();
            //sort the result list by date
            Collections.sort(result, cmp);
            YouTubeVideo firstVideo = result.get(0);
            //print result
            System.out.println("Channel Sort Result:");
            System.out.println("--------------------");
            System.out.println("sort by channel name id " + firstVideo.getId() + "\n" +
                            "sort by channel name channel " + firstVideo.getChannel() + "\n" +
                            "sort by channel name title " + firstVideo.getTitle()+ "\n" +
                            "sort by channel name date " + firstVideo.getDate() + "\n" +
                            "sort by channel name views " + firstVideo.getViewCount());
            System.out.println("--------------------");
        }
        catch (YouTubeDataParserException e)
        {
            e.printStackTrace();
        }
    }
    
    
    /**
     * compare two videos using their channel
     * @param video1 
     * @param video2
     * @return {@code -1} if video1's channel is smaller to video2's. {@code 0} if video1's channel is equal to video2's
     * {@code 1} if video1's channel is larger to video2's.
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
     * compare two videos using their channel
     * @param video1 
     * @param video2
     * @return {@code -1} if video1's channel is smaller to video2's. {@code 0} if video1's channel is equal to video2's
     * {@code 1} if video1's channel is larger to video2's.
     */
    public int compare(YouTubeVideo v1, YouTubeVideo v2) {
        String channel1 = v1.getChannel();
        String channel2 = v2.getChannel();
        int result = channel1.compareTo(channel2);
        return result;
    }
}
