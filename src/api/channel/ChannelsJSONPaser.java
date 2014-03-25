/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package api.channel;

import java.util.List;

/**
 *
 * @author bruce
 */
abstract class ChannelsJSONPaser {
    
    protected String jsonStr;
    
    public ChannelsJSONPaser(String jsonStr){
        this.jsonStr = jsonStr;
    }
    abstract public List<Channel> paser();
}
