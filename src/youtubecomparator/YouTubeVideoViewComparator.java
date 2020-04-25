/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package youtubecomparator;

import youtubeparser.YouTubeDataParserException;
import youtubeparser.YouTubeVideo;
import youtubeparser.YouTubeDataParser;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author 41626
 */
public class YouTubeVideoViewComparator implements Comparator{
    public static void test1(){
        System.out.println("Performing YouTubeVideoViewComparator Test 1");
        String filename = "data/youtubedata_15_50.json";
        System.out.println("Testing the file: " + filename);
        System.out.println("Excepting Result:");
        System.out.println("--------------------");      
        System.out.println("sort by views id UCPckaTvXvCmfCSZhFIIhi0g\n" +
                            "sort by views channel Kane Stevenson\n" +
                            "sort by views title Chautauqua 4K - CHAIRMANS SPRINT\n" +
                            "sort by views date 2016-05-01T11:26:33.000Z\n" +
                            "sort by views views 4406");
        System.out.println("--------------------");
        try{
            //Construct a YouTubeVideo list
            YouTubeDataParser dataparser = new YouTubeDataParser();
            List<YouTubeVideo> result = dataparser.parse(filename);
            YouTubeVideoViewComparator cmp = new YouTubeVideoViewComparator();
            //sort the result list by date
            Collections.sort(result, cmp);
            YouTubeVideo firstVideo = result.get(0);
            //print result
            System.out.println("Views Sort Result:");
            System.out.println("--------------------");
            System.out.println("sort by views id " + firstVideo.getId() + "\n" +
                            "sort by views channel " + firstVideo.getChannel() + "\n" +
                            "sort by views title " + firstVideo.getTitle()+ "\n" +
                            "sort by views date " + firstVideo.getDate() + "\n" +
                            "sort by views views " + firstVideo.getViewCount());
            System.out.println("--------------------");
        }
        catch (YouTubeDataParserException e)
        {
            e.printStackTrace();
        }
    }
    
    
    /**
     * compare two videos using their viewCount
     * @param video1 
     * @param video2
     * @return {@code -1} if video1's viewCount is smaller to video2's. {@code 0} if video1's viewCount is equal to video2's
     * {@code 1} if video1's viewCount is larger to video2's.
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
     * @return {@code -1} if video1's viewCount is smaller to video2's. {@code 0} if video1's viewCount is equal to video2's
     * {@code 1} if video1's viewCount is larger to video2's.
     */
    public int compare(YouTubeVideo v1, YouTubeVideo v2) {
        int viewCount1 = v1.getViewCount();
        int viewCount2 = v2.getViewCount();
        int result = -1;
        if(viewCount1 < viewCount2){
            result = -1;
        }else if(viewCount1 == viewCount2){
            result = 0;
        }else{
            result = 1;
        }
        return result;
    }
}
