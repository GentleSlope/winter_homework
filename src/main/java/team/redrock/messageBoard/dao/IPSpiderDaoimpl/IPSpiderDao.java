package team.redrock.messageBoard.dao.IPSpiderDaoimpl;

import team.redrock.messageBoard.been.IPBean;
import team.redrock.messageBoard.been.IPList;

import java.util.List;

public interface IPSpiderDao {
    public void setPages(int pages);

    public List<IPBean> crawlHttp();

    public List<IPBean> crawlHttp(int pages);

    public List<IPBean> crawlHttps();

    public List<IPBean> crawlHttps(int pages);

    public List<IPBean> crawl(String api, int index);

    public void addIp(List<IPBean> list);

    public IPList getIp();


}
