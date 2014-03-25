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
public interface ChannelUpdatorDelegate {
    public void didRecieveLatestChannelRecords(List<ChannelRecord> channelRecords);
}
