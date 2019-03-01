package team.redrock.messageBoard.dao.CrawlerThreadDaoimpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import team.redrock.messageBoard.been.IPBean;
import team.redrock.messageBoard.been.IPList;
import team.redrock.messageBoard.dao.CommentDao.CommentDaoImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class songListCrawler {
    public static String getURLContent(String urlStr, IPBean ipBean) {
        System.out.println(urlStr);
        StringBuilder sb = new StringBuilder();
        Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress(ipBean.getIp(),ipBean.getPort()));
        try {
            URLConnection connection = new URL(urlStr).openConnection(proxy);
            connection.setConnectTimeout(2000);
            connection.setReadTimeout(2000);
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
                String temp = "";
                while ((temp = reader.readLine()) != null) {
                    sb.append(temp + '\n');

                }
                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();

    }

    public static void ListCrawler(IPList list, int id, CommentDaoImpl messageBoardDao) throws SQLException {

        try {
            IPBean ipBean = list.getBean(id % list.getSize());
            String result = (String) songListCrawler.getURLContent("https://api.bzqll.com/music/netease/SongList?key=579621905&id=" + id + "&limit=100&offset=0", ipBean);
            try {
                Connection con = messageBoardDao.getConnection();
                insertCrawler main = new insertCrawler(con);
                JSONObject obj = JSONObject.fromObject(result);
                JSONObject object = (JSONObject) obj.get("data");
                JSONArray obj2 = JSONArray.fromObject(object);
                Map<String, Object> map = new HashMap<>();
                for (int i = 0; i < obj2.size(); i++) {
                    JSONObject json = (JSONObject) obj2.get(i);
                    Set<String> keys = json.keySet();
                    for (String key : keys) {
                        Object value = json.get(key);
                        map.put(key, value);
                    }
                    main.insertSongList(map.get("songListId"), map.get("songListName"), map.get("songListPic"), map.get("songListCount"), map.get("songListPlayCount"), map.get("songListDescription"),
                            map.get("songListUserId"));
                }
                JSONArray obj3 = JSONArray.fromObject(object.get("songs"));
                Map<String, Object> map2 = new HashMap<>();
                for (int j = 0; j < obj3.size(); j++) {
                    JSONObject json2 = (JSONObject) obj3.get(j);
                    Set<String> keys2 = json2.keySet();
                    for (String key2 : keys2) {
                        Object value2 = json2.get(key2);
                        map2.put(key2, value2);
                    }
                    main.insert(map2.get("id"), map2.get("name"), map2.get("Singer"), map2.get("pic"), map2.get("lrc"), map2.get("url"), map2.get("time"), map.get("songListId"));

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}


