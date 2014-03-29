/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nirvawolf.douban.api.song;

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
public class RequestResponse {

    private boolean result;
    private int versionMax;
    private final List<Song> songs = new ArrayList<>();

    public RequestResponse(String jsonStr) {

        try {
//            System.out.print(jsonStr);
            JSONObject obj = new JSONObject(jsonStr);
            result = obj.getInt("r") == 1 ? false : true;
            versionMax = obj.getInt("version_max");
            JSONArray songsArray = obj.getJSONArray("song");

            if (songsArray != null) {

                for (int i = 0; i < songsArray.length(); i++) {

                    JSONObject songObj = songsArray.getJSONObject(i);
                    Song aSong = new Song();
                    aSong.albumPageUrl = songObj.getString("album");
                    aSong.pictureUrl = songObj.getString("picture");
//                    aSong.ssid = songObj.getString("ssid");
                    aSong.artist = songObj.getString("artist");
                    aSong.songUrl = songObj.getString("url");
                    aSong.company = songObj.getString("company");
                    aSong.songTitle = songObj.getString("title");
                    aSong.ratingAverage = songObj.getDouble("rating_avg");
                    aSong.length = songObj.getInt("length");
                    aSong.subtype = songObj.getString("subtype");
                    aSong.sid = songObj.getString("sid");
                    aSong.aid = songObj.getString("aid");
                    aSong.sha256 = songObj.getString("sha256");
                    aSong.albumTitle = songObj.getString("albumtitle");
                    aSong.like = songObj.getInt("like");

                    this.songs.add(aSong);
                }

            }

        } catch (JSONException ex) {
            Logger.getLogger(RequestResponse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Song> getSongs() {
        return songs;
    }

    public boolean isSuccess() {
        return result;
    }
}
