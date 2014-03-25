/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package api.channel;

import java.util.List;
import java.util.Map;

/**
 *
 * @author bruce
 */
public class ChannelUpdateResult {
    
    private final List<Channel> channels;
    private Map<String,String> category = null;
    
    public ChannelUpdateResult(List<Channel> channels) {
        this.channels = channels;
    }

    public void setCategory(Map<String, String> category) {
        this.category = category;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public Map<String, String> getCategory() {
        return category;
    }
    
    
}
