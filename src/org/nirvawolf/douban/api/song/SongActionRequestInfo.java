/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.nirvawolf.douban.api.song;

import org.nirvawolf.douban.api.channel.Channel;
import org.nirvawolf.douban.api.user.User;

/**
 *
 * @author bruce
 */
public class SongActionRequestInfo {
    
    public static enum ActionType{BYE,END,SKIP,RATE,UNRATE};
    
    public Song song = null;
    public User user = null;
    public Channel channel=null;
    public ActionType type;

      
}