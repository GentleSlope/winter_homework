package team.redrock.messageBoard.dao.SmsDaoImpl;

public interface SmsDao {
    public String getCode();

    public String getSmsCode(String phone);

    public  String getTimeStamp();

    public  String pinJie(String accounSid,String smsContent,String to,String timestamp,String sig,String respDataType);



}
