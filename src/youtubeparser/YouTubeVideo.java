/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package youtubeparser;

/**
 * a class to describe a YouTubeVideo Ojbect
 * @author 
 */
public class YouTubeVideo {
    /**
     * the video's channelTitle
     */
    private String channel;
    /**
     * the vides's Date
     */
    private String date;
    /**
     * the video's title
     */
    private String title;
    /**
     * the videos's description
     */
    private String description;
    /**
     * the video's viewCount
     */
    private int viewCount;
    /**
     * the video's channelId
     */
    private String id;
    
    /**
     * 
     * @return this object's channelTitle
     */
    public String getChannel(){
        return channel;
    }
    
    /**
     * 
     * @param channel the parsing result's channelTitle
     */
    public void setChannel(String channel){
        this.channel = channel;
    }
    
    /**
     * 
     * @return this object's date
     */
    public String getDate(){
        return date;
    }
    
    /**
     * 
     * @param channel the parsing result's date
     */
    public void setDate(String date){
        this.date = date;
    }
    
    /**
     * 
     * @return this object's title
     */
    public String getTitle(){
        return title;
    }
    
    /**
     * 
     * @param channel the parsing result's title
     */
    public void setTitle(String title){
        this.title = title;
    }
    
    /**
     * 
     * @return this object's description
     */
    public String getDescription(){
        return description;
    }
    
    /**
     * 
     * @param channel the parsing result's description
     */
    public void setDescription(String description){
        this.description = description;
    }
    
    /**
     * 
     * @return this object's viewCount
     */
    public int getViewCount(){
        return viewCount;
    }
    
    /**
     * 
     * @param channel the parsing result's viewCount
     */
    public void setViewCount(int viewCount){
        this.viewCount = viewCount;
    }
    
    /**
     * 
     * @return this object's id
     */
    public String getId(){
        return id;
    }
    
    /**
     * 
     * @param channel the parsing result's channelId
     */
    public void setId(String id){
        this.id = id;
    }
    
    /**
     * 
     * @return this object's detailed description including all fields
     */
    public String toString(){
        return "id: " + id + "\n" +
                "channel: " + channel + "\n" +
                "date: " + date + "\n" +
                "title: " + title + "\n" + 
                "description: " + description + "\n" +
                "viewCount: " + viewCount + "\n";
    }
}
