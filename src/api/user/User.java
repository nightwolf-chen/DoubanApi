/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package api.user;

import java.io.Serializable;

/**
 *
 * @author bruce
 */
public class User implements Serializable{
    public String username;
    public String password;
    public String email;
    public String token;
    public String expire;
    public String userid;
}
