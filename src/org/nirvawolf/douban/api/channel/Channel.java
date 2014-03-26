/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.nirvawolf.douban.api.channel;

import java.io.Serializable;

/**
 *
 * @author bruce
 */
public class Channel implements Serializable{
    public int channel_id;
    public String chineseName;
    public String addr_en;
    public String englishName;
    public String categoryId;
    public String categoryName;
    public String coverImgUrl;
    public String intro;
    public int songNum;
}
