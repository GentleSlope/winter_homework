package team.redrock.messageBoard.dao.IPSpiderDaoimpl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import team.redrock.messageBoard.been.IPBean;
import team.redrock.messageBoard.been.IPList;
import team.redrock.messageBoard.dao.CommentDao.CommentDaoImpl;
import team.redrock.messageBoard.util.HttpUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class IPSpider implements IPSpiderDao {

    private final String HTTP_API = "https://www.xicidaili.com/wt/";
    private final String HTTPS_API = "https://www.xicidaili.com/wn/";

    private List<IPBean> ipList = new ArrayList<>();

    // 爬取的页数----每页包含100个IP
    private int pages = 5;

    private static IPSpider instance = null;

    public IPSpider() {

    }

    public static IPSpider getInstance() {
        //双重校验锁 防止高并发的情况下new出来多个message_boardDao的实例
        if (instance == null) {
            synchronized (IPSpider.class) {
                if (instance == null) {
                    instance = new IPSpider();
                }
            }
        }
        return instance;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<IPBean> crawlHttp(){
        List<IPBean> ipBeans = new ArrayList<>();
        for (int page = 1; page <= pages; page++){
            ipBeans.addAll(crawl(HTTP_API, page));
        }
        return ipBeans;
    }

    public List<IPBean> crawlHttp(int pages){
        this.pages = pages;
        return crawlHttp();
    }

    public List<IPBean> crawlHttps(){
        List<IPBean> ipBeans = new ArrayList<>();
        for (int page = 1; page <= pages; page++){
            ipBeans.addAll(crawl(HTTPS_API, page));
        }
        return ipBeans;
    }

    public List<IPBean> crawlHttps(int pages){
        this.pages = pages;
        return crawlHttps();
    }

    public List<IPBean> crawl(String api, int index){
        String html = HttpUtils.getResponseContent(api + index);
        System.out.println(html);

        Document document = Jsoup.parse(html);
        Elements eles = document.select("table").select("tr");

        for (int i = 0; i < eles.size(); i++){
            if (i == 0) continue;
            Element ele = eles.get(i);
            String ip = ele.children().get(1).text();
            int port = Integer.parseInt(ele.children().get(2).text().trim());
            String typeStr = ele.children().get(5).text().trim();

            int type;
            if ("HTTP".equalsIgnoreCase(typeStr))
                type = IPBean.TYPE_HTTP;
            else
                type = IPBean.TYPE_HTTPS;

            IPBean ipBean = new IPBean(ip, port, type);
            ipList.add(ipBean);
        }
        return ipList;
    }

    public void addIp(List<IPBean> list) {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement psmt = null;
        CommentDaoImpl messageBoardDaoimpl = new CommentDaoImpl();
        connection = messageBoardDaoimpl.getConnection();
        try {

            String sql = "drop table if exists mlgb.IpList";
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            String sql2 = "CREATE TABLE mlgb.IpList ("
                    + " `ip` varchar(50) NOT NULL,"
                    + " `port` int(50) NOT NULL,"
                    + "`type` int (50) NOT NULL)";
            statement.executeUpdate(sql2);

            for (IPBean ipBean:list) {
                String sql3 = "insert into mlgb.IpList(ip,port,type) values(?,?,?);";
                psmt = connection.prepareStatement(sql3);
                psmt.setString(1,ipBean.getIp() );
                psmt.setInt(2, ipBean.getPort());
                psmt.setInt(3, ipBean.getType());
                psmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                psmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public IPList getIp(){
        CommentDaoImpl messageBoardDao = new CommentDaoImpl();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        IPList list = new IPList();
        try {
            connection = messageBoardDao.getConnection();
            String sql = "select *from mlgb.iplist";
            statement  =  connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                IPBean ipBean = new IPBean(resultSet.getString("ip"),resultSet.getInt("port"),
                        resultSet.getInt("type"));
                list.add(ipBean);
            }
            messageBoardDao.close(resultSet,statement,connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

}
