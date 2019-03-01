package team.redrock.messageBoard.dao.SmsDaoImpl;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class SmsDaoImpl implements SmsDao {
    //这里的3个String 属性对应的用户中心里的开发者信息里面的ACCOUNT SID 和 AUTHTOKEN 还有验证码通知短信接口中的请求地址这3个属性待会需要用到
    private String sendUrl="https://api.miaodiyun.com/20150822/industrySMS/sendSMS";
    private String ACCOUNTSID="fe7e8a238b9547578d95a1f09afe9f07";
    private String AUTHTOKEN="666fae7fb1f541c4b5a9e2029f51cd0b";
    OutputStreamWriter osw=null;
    BufferedReader br=null;
    private String code="";


    public String getCode(){
        return code;
    }
    public String getSmsCode(String phone) {
        String smsCode=smsCode();//获取随机短信验证码
        code=smsCode;
        String timeStamp=getTimeStamp();//获取时间戳
        String md5=DigestUtils.md5Hex(ACCOUNTSID+AUTHTOKEN+timeStamp);//获取签名
        String smsMoban="【红岩网校】登录验证码："+smsCode+"，如非本人操作，请忽略此短信。";
        StringBuilder sb=new StringBuilder();
        try {
            URL url=new URL(sendUrl);//导net的包
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");//设置传送数据的方式
            connection.setDoInput(true);//设置是否允许数据写入
            connection.setDoOutput(true);//设置是否允许数据输出
            connection.setConnectTimeout(5000);//设置连接时间
            connection.setReadTimeout(10000);//设置读取参数的时间
            connection.setRequestProperty("Content-type","application/x-www-form-urlencoded");//设置请求头
            String args=pinJie(ACCOUNTSID,smsMoban,phone,timeStamp,md5,"JSON");
            System.out.println(args);
            osw=new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
            osw.write(args);
            osw.flush();
            //读取返回参数
            br=new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
            String temp="";
            while((temp=br.readLine())!=null){
                sb.append(temp);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public  String getTimeStamp(){
        return  new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }
 static String smsCode(){
        String code=new Random().nextInt(1000000)+"";
        if(code.length()!=6){
            return smsCode();//不够6位，再次调用自己的方法，递归
        }else{
            return code;
        }
    }

    public  String pinJie(String accounSid,String smsContent,String to,String timestamp,String sig,String respDataType){
//accountSid=a14f6bfd43ce44c9b019de57f4e2de4b&smsContent=【秒嘀科技 to】您的验证码是345678，30分钟输入有效。
//&to=13896543210&timestamp=20150821100312&sig=a14f6bfd43ue44c9b019du57f4e2ee4r&respDataType=JSON
        return "accountSid="+accounSid+"&smsContent="+smsContent+"&to="+to+"&timestamp="+timestamp+"&sig="+sig+"&respDataType="+respDataType;
    }

    public static void main(String[] args) {
        SmsDaoImpl smsDao = new SmsDaoImpl();
        System.out.println(smsDao.getSmsCode("17358458653"));
    }

}