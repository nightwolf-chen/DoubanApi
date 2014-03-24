/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package api.user;

/**
 *
 * @author bruce
 */
public interface UserAuthenticationDelegate {
    public void didRecieveLoginResponse(AuthResponse authResp);
}
