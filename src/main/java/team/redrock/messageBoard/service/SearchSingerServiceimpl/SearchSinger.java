package team.redrock.messageBoard.service.SearchSingerServiceimpl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import team.redrock.messageBoard.been.Singer;
import team.redrock.messageBoard.been.SingerList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class SearchSinger {
    public static String getURLContent(String urlStr){
        System.out.println(urlStr);
        StringBuilder sb = new StringBuilder();
//        java.net.Proxy proxy = new java.net.Proxy(Proxy.Type.HTTP,new InetSocketAddress("115.151.6.5",9999));
        try {
            URLConnection connection = new URL(urlStr).openConnection();
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
                String temp = "";
                while ((temp = reader.readLine())!=null){
                    sb.append(temp+'\n');
                }
                reader.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }

    public  SingerList returnSingerList(Singer singer){
        SingerList singerList = new SingerList();
        SearchSinger searchSinger =new SearchSinger();
        try {
            String str = java.net.URLEncoder.encode(singer.getName(),"utf-8");
            String string = "https://api.bzqll.com/music/netease/search?key=579621905&s="+str
                    +"&type=singer";
            String  result = searchSinger.getURLContent(string);
            JsonObject obj = new JsonParser().parse(result).getAsJsonObject();

            JsonObject jsonObject = (JsonObject) obj.get("data");

            singerList.setArtistCount(jsonObject.get("artistCount").getAsInt());
            JsonArray jsonArray = (JsonArray) jsonObject.get("artists");
            for (int i = 0;i<singerList.getArtistCount();i++){
                JsonObject json = (JsonObject) jsonArray.get(i);
                Gson gson = new Gson();
                Singer singer2 = gson.fromJson(json, Singer.class);
                singerList.add(singer2);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return singerList;
    }

}
