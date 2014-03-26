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
public class StableChannelsJSONPaser extends ChannelsJSONPaser {

    public StableChannelsJSONPaser(String jsonStr) {
        super(jsonStr);
    }

    @Override
    public List<Channel> paser() {
        
        List<Channel> records = new ArrayList<Channel>();
        try {

            JSONArray channels = new JSONObject(jsonStr).getJSONArray("channels");

            for (int i = 0; i < channels.length(); i++) {
                JSONObject channel = channels.getJSONObject(i);
                Channel aRecord = new Channel();
                aRecord.chineseName = channel.getString("name");
                aRecord.englishName = channel.getString("name_en");
                aRecord.channel_id = channel.getInt("channel_id");
                aRecord.addr_en = channel.getString("abbr_en");
                records.add(aRecord);
            }

        } catch (JSONException ex) {
            Logger.getLogger(StableChannelsJSONPaser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return records;
        
    }
    
}
