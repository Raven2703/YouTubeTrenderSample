/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package youtubetrender;

import youtubeindexer.YouTubeVideoIndexer;
import youtubecomparator.YouTubeVideoDescriptionComparator;
import youtubeparser.YouTubeDataParserException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import youtubecomparator.YouTubeDateComparator;
import youtubecomparator.YouTubeVideoChannelComparator;
import youtubecomparator.YouTubeVideoViewComparator;
import youtubeparser.YouTubeDataParser;

/**
 *
 * @author trent
 */
public class YouTubeTrender {

    public static void test1() throws FileNotFoundException {

        System.out.println("Performing Test 1");
        String filename = "data/youtubedata_15_50.json";
        int expectedSize = 50;

        System.out.println("Testing the file: " + filename);
        System.out.println("Expecting size of: " + expectedSize);

        // Read data
        JsonReader jsonReader = Json.createReader(new FileInputStream(filename));
        JsonObject jobj = jsonReader.readObject();

        // read the values of the item field
        JsonArray items = jobj.getJsonArray("items");

        System.out.println("Size of input: " + items.size());
        System.out.println("Sucess: " + (expectedSize == items.size()));


    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, YouTubeDataParserException {
        System.out.println("YouTube Trender Application");
//        YouTubeDataParser.test1();
//          YouTubeDateComparator.test1();
//          YouTubeVideoChannelComparator.test1();
//          YouTubeVideoViewComparator.test1();
//          YouTubeVideoDescriptionComparator.test1();
          YouTubeVideoIndexer.test1();
//        test1();

    }

}
