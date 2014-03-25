/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.channel;

import concurrent.ExecutorServiceManager;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import network.HttpClientAdaptor;
import network.HttpClientAdaptorFactory;
import org.apache.http.client.utils.URIBuilder;

/**
 *
 * @author bruce
 */
public class DynamicChannelsUpdator extends ChannelUpdator {

    private final String apiAddress = "http://douban.fm/j/explore/genre";
    private final String parameterNameGid = "gid";
    private final String parameterNameStart = "start";
    private final String parameterNameLimit = "limit";

    public DynamicChannelsUpdator(ChannelUpdatorDelegate delegate) {
        super(delegate);
    }

    @Override
    public void attemptToUpdate() {

        ExecutorServiceManager.defaultExecutor.execute(new Runnable() {

            @Override
            public void run() {
                
                List<Channel> channels = new ArrayList<Channel>();

                Map<String, String> categoryIds = getChannelCategories();

                for (String categoryId : categoryIds.keySet()) {
                    String categoryName = categoryIds.get(categoryId);
                    channels.addAll(getChannelsByCategoryId(categoryId, categoryName));
                }

                ChannelUpdateResult result = new ChannelUpdateResult(channels);
                result.setCategory(categoryIds);

                delegate.didRecieveLatestChannelRecords(result);
            }

        });

    }

    private Map<String, String> getChannelCategories() {

        Map<String, String> data = new HashMap<>();

        String url = "http://douban.fm/";
        HttpClientAdaptor clientAdaptor = HttpClientAdaptorFactory.createDefaultHttpClientAdaptor();
        String pageContentStr = clientAdaptor.doGet(url);

        String regx = "<li data-genre_id=\"(.*?)\">(.*?)</li>";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(pageContentStr);

        while (matcher.find()) {
            String categoryId = matcher.group(1);
            String categoryName = matcher.group(2);
            data.put(categoryId, categoryName);
        }

        clientAdaptor.close();

        return data;
    }

    private List<Channel> getChannelsByCategoryId(String categoryId, String categoryName) {

        List<Channel> channels = new ArrayList<>();

        try {

            URIBuilder uriBuilder = new URIBuilder(this.apiAddress);
            uriBuilder.addParameter(parameterNameGid, categoryId);
            uriBuilder.addParameter(parameterNameStart, "0");
            uriBuilder.addParameter(parameterNameLimit, "20");
            String url = uriBuilder.build().toString();
            HttpClientAdaptor clientAdaptor = HttpClientAdaptorFactory.createDefaultHttpClientAdaptor();
            String jsonStr = clientAdaptor.doGet(url);
            ChannelsJSONPaser jsonPaser = new DynamicChannelsJSONPaser(jsonStr);
            channels = jsonPaser.paser();

            for (Channel c : channels) {
                c.categoryId = categoryId;
                c.categoryName = categoryName;
            }

        } catch (URISyntaxException ex) {
            Logger.getLogger(DynamicChannelsUpdator.class.getName()).log(Level.SEVERE, null, ex);
        }

        return channels;
    }

    public static void main(String[] args) {
        new DynamicChannelsUpdator(null).attemptToUpdate();
    }
}
