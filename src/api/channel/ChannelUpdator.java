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
abstract public class ChannelUpdator{
    
    protected ChannelUpdatorDelegate delegate;
    
    public ChannelUpdator(ChannelUpdatorDelegate delegate){
        this.delegate = delegate;
    }
    
    abstract void attemptToUpdate();
}
