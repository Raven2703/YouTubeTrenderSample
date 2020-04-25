/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package youtubeindexer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import youtubecomparator.YouTubeDateComparator;
import youtubeparser.YouTubeDataParser;
import youtubeparser.YouTubeDataParserException;
import youtubeparser.YouTubeVideo;

/**
 *
 * @author 41626
 */
public class YouTubeVideoIndexer {
    private List<YouTubeVideo> items = new ArrayList<YouTubeVideo>();
    private Map<String, YouTubeWordItem> words = new HashMap<String, YouTubeWordItem>();
    private String mostUsedWord = "";
    private List<String> sortedWordsByCount = new ArrayList<String>();
    public static void test1(){
        System.out.println("Performing YouTubeVideoIndexer Test 1");
        String filename = "data/youtubedata_indextest.json";
//        String filename = "data/youtubedata_15_50.json";
//        String word = "the";
        String word = "ONE";
        System.out.println("Testing the file: " + filename);
        
        System.out.println("Testing the word:" + word);
//        System.out.println("Excepting Result:");
//        System.out.println("--------------------");      
//        System.out.println("indexed word, occurrence counts, no. associated records, [ associated record ids ]\n" +
//"the, 97, 27, [ UCpko_-a4wgz2u_DgDgd9fqA UChGJGhZ9SOOHvBB0Y4DOO_w UCYzDs47-m70QeXRy_3uw4BQ UC5Ti4_DVp7LW34PjEwB13Xg UC5lMzpZvCLpwyvu348B8zYw UC6E2mP01ZLH_kbAyeazCNdg UCilwZiBBfI9X6yiZRzWty8Q UCe_3CoEeinvPMze2u_aENBg UCQ1wh06VhbbQXqjqhIjJk9w UCsooa4yRKGN_zEE8iknghZA UC8HQ5RQsiUTvgDkSmkVzfvg UCDRbNGFusqlXX4a5vwi9ouQ UCp0hYYBW6IMayGgR-WeoCvQ UCPTgHz-ZYiYvmArYkmC-5tQ UCPckaTvXvCmfCSZhFIIhi0g UCE7FNFLbwr3zQjbt3bDs3EA UCUX3uZBKoHvWqJAbxWDHFsA UCelMeixAOTs2OQAAi9wU8-g UCJkWoS4RsldA1coEIot5yDA UCPRlGA2w7C_DVw-1ynolJYw UCJykHJfN9FHtf79IgYE00zg UCZfHgDbd7PJGxXx7kCxH8ow UC6Je0KLSDuKLfKs1lEBzKrQ UCkH1uDkyuO9sVjSqdqBygOg UClgUaoj44wxi7rCQEO8EOcA UCYFo16eFDNhD3W_POj9gKFw UCPDis9pjXuqyI7RYLJ-TTSA ]");
//        System.out.println("--------------------");
        try{
            
            //Construct a YouTubeVideo list
            YouTubeDataParser dataparser = new YouTubeDataParser();
            List<YouTubeVideo> result = dataparser.parse(filename);
            //Indexing this List
            YouTubeVideoIndexer indexer = new YouTubeVideoIndexer(result);
            indexer.index();
            //Judge if the word is in
            if(!indexer.Exists(word)){
                System.out.println("The word " + word + " not exists.");
                return;
            }
            // Output like the info.txt
//            System.out.println("The word " + word + " exists");
//            //print result
//            Set<YouTubeVideo> videoItems = indexer.getVideoItems(word);
//            List<String> videoIds = new ArrayList<String>();
//            for(YouTubeVideo vI : videoItems){
//                videoIds.add(vI.getId());
//            }
//            
//            System.out.println("Result:");
//            System.out.println("--------------------");
//            System.out.println("indexed word, occurrence counts, no. associated records, [ associated record ids ]");
//            System.out.println(word + ", " + indexer.getCount(word) + ", " + indexer.getRecordsLength(word) + ", " + videoIds);
//            System.out.println("--------------------");
//            
            System.out.println("*****************************");
            //testing1 quickly find a word
            String noword = "aaaaaaaaaaaaaaa";
            System.out.println("testing1: quickly find a word:" + noword);
            System.out.println("Expecting result: false");
            System.out.println("Testing result: " + indexer.Exists(noword));
            //testing2 find the count associated with a word
            System.out.println("testing2: find the count associated with a word:" + word);
            System.out.println("Expecting result:");
            System.out.println("Testing result: " + indexer.getCount(word));
            //testing3 find all the videos that use the word
            System.out.println("testing3: find all the videos that use the word:" + word);
            System.out.println("Expecting result:");
            Set<YouTubeVideo> vItems = indexer.getVideoItems(word);
            List<String> Ids = new ArrayList<String>();
            for(YouTubeVideo vI : vItems){
                Ids.add(vI.getId());
            }
            System.out.println("Testing result: " + Ids);
            //testing4 find the word that used the most
            System.out.println("testing4: find the word that used the most:");
            System.out.println("Expecting result:");
            System.out.println("Testing result: " + indexer.getMostUsedWord());
            //testing5 create a list of words sorted by their counts 
            System.out.println("testing5: create a list of words sorted by their counts ");
            System.out.println("Expecting result:");
            System.out.println("Testing result: " + indexer.getWordsSortedByCounts());
            
            System.out.println("*****************************");
        }
        catch (YouTubeDataParserException e)
        {
            e.printStackTrace();
        }
    }
    /**
     * Construction Function using a List<YouTueVideo>
     * @param videos 
     */
    YouTubeVideoIndexer(List<YouTubeVideo> videos){
        this.items = videos;
    }
    
    /**
     * performing index operation
     * for every word in video's title and description, construct a key-value of word and wordItme which contains the word's information
     */
    public void index(){
        for(YouTubeVideo v : items){
            String allWordsStr = v.getTitle() + " " + v.getDescription();
            String[] allWords = allWordsStr.split("\\s++");
            for(String word : allWords){
                if( !words.containsKey(word)){
                    words.put(word, new YouTubeWordItem(word));
                }
                words.get(word).addCount();
                words.get(word).add(v);
            }
        }
        //Caculate the most used words;
        CalMostUsedWord();
        //sort the words by their counts;
        SortWordsByCount();
    }
    /**
     * sort all wordItems by count
     * @return 
     */
    public List<YouTubeWordItem> getSortedYouTubeWordItems(){
        List<YouTubeWordItem> result = (ArrayList<YouTubeWordItem>) words.values();
        Collections.sort(result);
        
        return result;
    }

    
    /**
     * get this word's items length
     * @param word a word wish to get its' number of associated records
     * @return items' length
     */
    public int getRecordsLength(String word){
        return words.get(word).getPosts().size();
    }
    
    /**
     * 1.quickly find a word
     * @param word 
     * @return {@code true} if the word in the indexer's map {@code false} the word not in
     */
    public boolean Exists(String word){
        if (words.containsKey(word)){
            return true;
        }
        else{
            return false;
        }
    }
        
    /**
     * 2.find the count associated with the word
     * @param word a word wish to get its' count
     * @return the word's count
     */
    public int getCount(String word){
        return words.get(word).getCount();
    }
    
     /**
     * 3. find all the videos that use the word
     * @param word a word wish to get its' associated video items
     * @return videoItems
     */
    public Set<YouTubeVideo> getVideoItems(String word){
        return words.get(word).getPosts();
    }
    
    /**
     * Caculate the most used words
     */
    private void CalMostUsedWord(){
        int maxCount = 0;
        for(Entry<String, YouTubeWordItem> entry : words.entrySet()){
            if(entry.getValue().getCount() > maxCount){
                mostUsedWord = entry.getKey();
                maxCount = entry.getValue().getCount();
            }
        }
    }
    /**
     * 4. find the word that used the most
     * @return the most used word
     */
    public String getMostUsedWord(){
        return mostUsedWord;
    }

    /**
     * sort words by count
     */
    private void SortWordsByCount(){
        List<YouTubeWordItem> ytI = new ArrayList<YouTubeWordItem>(words.values());
        Collections.sort(ytI);
        Collections.reverse(ytI);
        for(YouTubeWordItem wt : ytI){
            sortedWordsByCount.add(wt.getWord());
        }
        
    }
    /**
     * 5. create a list of words sorted by their counts
     * @return 
     */
    public List<String> getWordsSortedByCounts(){
        return sortedWordsByCount;
    }
}
