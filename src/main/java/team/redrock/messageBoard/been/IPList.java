package team.redrock.messageBoard.been;

import java.util.ArrayList;
import java.util.List;


public class IPList {
    private static List<IPBean> ipBeanList = new ArrayList<>();
    private static int count = 0;

    public static synchronized void add(IPBean bean) {
        ipBeanList.add(bean);
    }

    public static int getSize() {
        return ipBeanList.size();
    }

    public static List<IPBean> getList(){
        return ipBeanList;
    }
    public static IPBean getBean(int index){
        return ipBeanList.get(index);
        }

    public static synchronized void increase() {
        count++;
    }

    public static synchronized int getCount(){
        return count;
    }
}
