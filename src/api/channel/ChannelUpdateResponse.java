/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package api.channel;

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
public class ChannelUpdateResponse {
    
    private final List<ChannelRecord> records = new ArrayList<ChannelRecord>();
    
    public ChannelUpdateResponse(String jsonStr){
        
        try {   
            JSONArray channels = new JSONObject(jsonStr).getJSONArray("channels");
            
            for(int i = 0 ; i < channels.length() ;i++){
                JSONObject channel = channels.getJSONObject(i);
                ChannelRecord aRecord = new ChannelRecord();
                aRecord.chineseName = channel.getString("name");
                aRecord.englishName = channel.getString("name_en");
                aRecord.channel_id = channel.getInt("channel_id");
                aRecord.addr_en = channel.getString("abbr_en");
                records.add(aRecord);
            }
            
        } catch (JSONException ex) {
            Logger.getLogger(ChannelUpdateResponse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<ChannelRecord> getRecords() {
        return records;
    }
    
    
}
