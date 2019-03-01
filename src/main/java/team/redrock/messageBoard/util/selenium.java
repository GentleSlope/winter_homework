package team.redrock.messageBoard.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import team.redrock.messageBoard.been.RotationUrl;
import team.redrock.messageBoard.dao.SongsDaoimpl.SongsDao;
import team.redrock.messageBoard.dao.SongsDaoimpl.SongsDaoimpl;

import java.util.ArrayList;
import java.util.List;

public class selenium {
    SongsDao songsDao = null;

    public selenium() {
        songsDao = SongsDaoimpl.getInstance();
    }

    public void updateRotationUrl() {
        List<RotationUrl> list = new ArrayList<RotationUrl>();

        String key = "webdriver.chrome.driver";
        String value = "D:\\JavaDemo\\the_last_redrock_homework\\chromedriver.exe";
        System.setProperty(key, value);

        WebDriver driver = new ChromeDriver();
        driver.get("http://music.163.com/#");

        WebElement iframe = driver.findElement(By.className("g-iframe"));
        driver.switchTo().frame(iframe);
        List<String> urlList = new ArrayList<String>();


        WebElement element = driver.findElement(By.xpath("//div[@class='ban f-pr']"));
        WebElement node1 = element.findElement(By.tagName("a"));

        while (urlList.size()<=8) {
            WebElement node2 = node1.findElement(By.tagName("img"));
            String url1 = node2.getAttribute("src");
            System.out.println(url1);
            if (!urlList.contains(url1)) {
                urlList.add(url1);
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (urlList.size() >= 9) {
                break;
            }
        }
        for (String url : urlList) {
            RotationUrl rotationUrl = new RotationUrl();
            rotationUrl.setUrl(url);
            list.add(rotationUrl);
        }
        songsDao.upadteUrl(list);


//        List<WebElement>  elements = driver.findElements(By.xpath("//div[@class='u-cover u-cover-1']"));
//        List<String> playLists = new ArrayList<String>();
//
//
//
//        for (WebElement webElement:elements){
//            webElement.findElement(By.tagName("div"));
//            WebElement node = webElement.findElement(By.tagName("a"));
//            String url = node.getAttribute("href");
//
//            System.out.println(url);
//            playLists.add(url);
//
//        }
//
//        for (String str:playLists){
//
//
//            driver.get(str);
//            WebElement ifra = driver.findElement(By.className("g-iframe"));
//            driver.switchTo().frame(ifra);
//
//            WebElement subject = driver.findElement(By.tagName("h2"));
//
//            System.out.println(subject.getText());
//        }

    }

    public static void main(String[] args) {
        selenium selenium1 = new selenium();
        selenium1.updateRotationUrl();
    }
}
