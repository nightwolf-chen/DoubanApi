/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.nirvawolf.douban.api.channel;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author bruce
 */
public class DynamicChannelsJSONPaser extends ChannelsJSONPaser{

    public DynamicChannelsJSONPaser(String jsonStr) {
        super(jsonStr);
    }

    @Override
    public List<Channel> paser() {
        
        List<Channel> channels = new ArrayList<Channel>();
        
        try {
            
            JSONArray jsonChannels = new JSONObject(jsonStr).getJSONObject("data").getJSONArray("channels");
            
            for(int i = 0 ; i < jsonChannels.length() ;i++){
                JSONObject jsonChannel = jsonChannels.getJSONObject(i);
                
                Channel channel = new Channel();
                channel.chineseName = jsonChannel.getString("name");
                channel.channel_id = jsonChannel.getInt("id");
                channel.englishName = jsonChannel.getString("name");
                channel.coverImgUrl = jsonChannel.getString("cover");
                channel.intro = jsonChannel.getString("intro");
//                channel.songNum = Integer.valueOf(jsonChannel.getString("song_num"));
                
                channels.add(channel);
            }
            
        } catch (JSONException ex) {
            Logger.getLogger(DynamicChannelsJSONPaser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return channels;
    }
    
}
