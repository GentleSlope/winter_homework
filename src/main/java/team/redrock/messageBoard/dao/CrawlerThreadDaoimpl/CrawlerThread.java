package team.redrock.messageBoard.dao.CrawlerThreadDaoimpl;

import team.redrock.messageBoard.been.IPList;
import team.redrock.messageBoard.dao.IPSpiderDaoimpl.IPSpider;
import team.redrock.messageBoard.dao.CommentDao.CommentDaoImpl;

import java.sql.SQLException;

public class CrawlerThread extends Thread {
    private int id = 3800371;
    CommentDaoImpl messageBoardDao = (CommentDaoImpl) CommentDaoImpl.getInstance();
    IPSpider ipSpider = IPSpider.getInstance();
    IPList list = ipSpider.getIp();

    public void run() {
            while (id <= 4000000) {
                try {
                    songListCrawler.ListCrawler(list, id++, messageBoardDao);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

        }
    }

    public static void main(String[] args) {
        CrawlerThread crawlerThread = new CrawlerThread();
        crawlerThread.start();
    }
}