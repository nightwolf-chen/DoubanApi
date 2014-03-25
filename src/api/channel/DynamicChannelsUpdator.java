/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package api.channel;

/**
 *
 * @author bruce
 */
public class DynamicChannelsUpdator extends ChannelUpdator{

    public DynamicChannelsUpdator(ChannelUpdatorDelegate delegate) {
        super(delegate);
    }

    @Override
    void attemptToUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
