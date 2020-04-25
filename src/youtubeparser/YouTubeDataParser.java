/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package youtubeparser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
/**
 *
 * @author 41626
 */
public class YouTubeDataParser{
    
    public static void test1() throws YouTubeDataParserException {

        System.out.println("Performing YouTubeDataParser Test 1");
        String filename = "data/youtubedata_15_501.json";
        System.out.println("Testing the file: " + filename);
        System.out.println("Excepting Result:");
        System.out.println("--------------------");        
        System.out.println("first video id UCehf4850q1L3ng7s7L54ATA\n" +
                            "first video channel Sean Naber\n" +
                            "first video title Guy cuts down tree, but there's a surprise inside\n" +
                            "first video date 2016-04-20T23:15:17.000Z\n" +
                            "first video views 14187775");
        System.out.println("--------------------");
        YouTubeDataParser dataparser = new YouTubeDataParser();
        try{
            List<YouTubeVideo> result = dataparser.parse(filename);
            YouTubeVideo video = result.get(0);
            System.out.println("Parsing Result:");
            System.out.println("--------------------");
            System.out.println("first video id " + video.getId() + "\n" +
                                "first video channel " + video.getChannel() + "\n" +
                                "first video title " + video.getTitle() + "\n" +
                                "first video date " + video.getDate() + "\n" +
                                "first video views " + video.getViewCount());
            System.out.println("--------------------");
        }
        catch (YouTubeDataParserException e)
        {
            e.printStackTrace();
        }
    }
    
    
    /**
     * Parsing YouTubeVideo List from file
     * @param filename the file to parse
     * @return  ArrayList of constructed YouTubeVideos
     * @throws YouTubeDataParserException Parsing Json Error!
     */
    public ArrayList<YouTubeVideo> parse(String filename) throws YouTubeDataParserException{
        System.out.println("Excuting Parsing " + filename);
        ArrayList<YouTubeVideo> Result = new ArrayList<YouTubeVideo>();
        // Read data
        try{
            JsonReader jsonReader = Json.createReader(new FileInputStream(filename));
            JsonObject jobj = jsonReader.readObject();
            // read the values of the item field
            JsonArray items = jobj.getJsonArray("items");
            // construct every YouTubeVideo object
            for(int i = 0; i < items.size(); i++){
                // a new YouTubeVideo object
                YouTubeVideo video = new YouTubeVideo();

                JsonObject objvideo = items.getJsonObject(i);
                JsonObject snippet = objvideo.getJsonObject("snippet");
                JsonObject statis = objvideo.getJsonObject("statistics");
                //id 
                String id = snippet.getString("channelId");
                //channel 
                String channel = snippet.getString("channelTitle");
                //date
                String date = snippet.getString("publishedAt");
                // title
                String title = snippet.getString("title");
                // description
                String description = snippet.getString("description");
                // viewCount
                int viewCount = Integer.parseInt(statis.getString("viewCount"));

                //set all values
                video.setChannel(channel);
                video.setDate(date);
                video.setDescription(description);
                video.setId(id);
                video.setTitle(title);
                video.setViewCount(viewCount);
                //add the video to list
                Result.add(video);
            }
            return Result;
        }
        catch(FileNotFoundException e){
            throw new YouTubeDataParserException("File " + filename + " Not Found!");
        }
        catch(Exception e)
        {
            throw new YouTubeDataParserException("Parsing " + filename + " Error!");
        }
    }
}
