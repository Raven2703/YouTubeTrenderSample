/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package youtubeindexer;

import java.util.HashSet;
import java.util.Set;
import youtubeparser.YouTubeVideo;

/**
 *
 * @author 41626
 */
public class YouTubeWordItem implements Comparable{
    private String word;
    private int count = 0;
    private Set<YouTubeVideo> videos = new HashSet<YouTubeVideo>();
    
    /**
     * construction function using a String object
     * @param word 
     */
    YouTubeWordItem(String word){
        this.word = word;
    }
    /**
     * Original construction function
     */
    YouTubeWordItem(){
        
    }
    
    /**
     * linking the word to a YouTubeVideo object
     * @param video a YouTubeVideo object contains this word in title or description
     */
    public void add(YouTubeVideo video){
        videos.add(video);
    }
    /**
     * increase the count;
     */
    public void addCount(){
        count += 1;
    }
    
    /**
     * 
     * @return this word's number of occurrences
     */
    public int getCount(){
        return count;
    }
    /**
     * 
     * @return all associated YouTubeVideo Objects
     */
    public Set<YouTubeVideo> getPosts(){
        return videos;
    }
    /**
     * 
     * @return this word
     */
    public String getWord(){
        return word;
    }
    /**
     * a short description of this wordItem object
     * @return a short description
     */
    public String toString(){
        String result = "";
        result = result + "This word is " + word + "\n" + "The number of occurrence is " + count + "\n";
        result += "The associated records' ids are:";
        for(YouTubeVideo v : videos){
            result = result + v.getId() + "\n";
        }
        return result;
    }

    /**
     * override the compareTo methos
     * @param o another YouTubeWordItem
     * @return 
     */
    @Override
    public int compareTo(Object o) {
        return this.compareTo((YouTubeWordItem)o);
    }
    
    /**
     * compare with another YouTubeWordItem
     * @param word
     * @return {@code -1} if this word's count is smaller than another's {@code 0} if the counts are equal {@code 1} if this word's count
     * is larger than another's
     */
    public int compareTo(YouTubeWordItem word){
        int result = -1;
        if(this.count < word.getCount()){
            result = -1;
        }else if(this.count == word.getCount()){
            result = 0;
        }else{
            result = 1;
        }
        return result;
    }
    
}
