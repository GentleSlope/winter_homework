package team.redrock.messageBoard.dao.CrawlerThreadDaoimpl;

import com.google.gson.Gson;
import team.redrock.messageBoard.been.IPBean;
import team.redrock.messageBoard.been.IPList;
import team.redrock.messageBoard.dao.IPSpiderDaoimpl.IPSpider;
import team.redrock.messageBoard.util.IPUtils;

import java.util.List;

public class IpCrawler {
    public static void main(String[] args) {
        System.out.println("Start: ");

        IPSpider spider = new IPSpider();
        List<IPBean> list = spider.crawlHttp(3);

        System.out.println("爬取数量：" + list.size());

        Gson gson = new Gson();
        for (IPBean ipBean : list) {
            System.out.println(gson.toJson(ipBean));

            new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean valid = IPUtils.isValid(ipBean);
                    if (valid) {
                        IPList.add(ipBean);
                    }
                    IPList.increase();
                }
            }).start();
        }


        while (true) {
            // 判断所有副线程是否完成
            if (IPList.getCount() == list.size()) {
                System.out.println("有效数量：" + IPList.getSize());
                break;
            }
        }
        spider.addIp(IPList.getList());
    }
}

