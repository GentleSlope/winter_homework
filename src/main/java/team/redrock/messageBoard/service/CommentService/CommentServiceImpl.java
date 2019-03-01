package team.redrock.messageBoard.service.CommentService;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import team.redrock.messageBoard.been.Message;
import team.redrock.messageBoard.dao.CommentDao.CommentDao;
import team.redrock.messageBoard.dao.CommentDao.CommentDaoImpl;

import java.sql.ResultSet;
import java.util.List;

public class CommentServiceImpl implements CommentService {

    private static CommentService instance = null;

    private CommentDao commentDao = null;


    public CommentServiceImpl() {
        commentDao = CommentDaoImpl.getInstance();
    }

    public static CommentService getInstance() {
        //双重校验锁
        if (instance == null) {
            synchronized (CommentServiceImpl.class) {
                if (instance == null) {
                    instance = new CommentServiceImpl();
                }
            }
        }
        return instance;
    }

    private List<Message> findContentChild(Message content) {
        List<Message> list = commentDao.findMessagesByPid(content.getId(),content);

        for (Message message : list) {
            List<Message> childList = findContentChild(message);
            message.setChildContent(childList);
        }
        return list;
    }

    public List<Message> findAllMessages(Message content) {

        List<Message> list = commentDao.findMessagesByPid(0,content);

        for (Message message : list) {
            List<Message> childList = findContentChild(message);
            message.setChildContent(childList);
        }

        return list;
    }
    @Override
    public String messagesToJson(List<Message> messageList) {
        StringBuffer sb = new StringBuffer();

        sb.append("{\"status\":10000,\"data\":[");

        //如果它有子节点
        if (messageList != null && messageList.size() != 0) {

            for (Message content : messageList) {
                sb.append(createJson(content));
                sb.append(",");
            }

            if (sb.charAt(sb.length() - 1) == ',') {
                sb.delete(sb.length() - 1, sb.length());
            }
        }
        sb.append("]}");

        return sb.toString();
    }

    @Override
    public boolean insertContent(Message message) {

        if (message.getUsername() != null && message.getText() != null) {

            commentDao.insertMessage(message);
            return true;

        }

        return false;

    }
    private String createJson(Message message) {

        StringBuffer sb = new StringBuffer();

        sb.append("{\"id\":").append(message.getId())
                .append(",\"username\":\"").append(message.getUsername())
                .append("\",\"text\":\"").append(message.getText())
                .append("\"").append(",\"child\":[");


        List<Message> child = message.getChildContent();

        for (Message content : child) {

            String json = createJson(content);

            sb.append(json).append(",");

        }

        if (sb.charAt(sb.length() - 1) == ',') {
            sb.delete(sb.length() - 1, sb.length());
        }

        sb.append("]}");

        return sb.toString();
    }

    public JsonArray covJson1(ResultSet resultSet) throws Exception {
        ResultSetMetaData metaData = null;
            JsonArray array = new JsonArray();
            metaData = (ResultSetMetaData) resultSet.getMetaData();

            int cloumCount = metaData.getColumnCount();
            while (resultSet.next()) {
                JsonObject jsonObject = new JsonObject();
                for (int i = 1; i <= cloumCount; i++) {
                    String cloumName = metaData.getColumnLabel(i);
                    String value = resultSet.getString(cloumName);
                    jsonObject.addProperty(cloumName, value);
                }
                array.add(jsonObject);
            }
            return array;
    }

    public JsonObject covJson2(ResultSet resultSet) throws Exception {
        ResultSetMetaData metaData = null;
        JsonObject jsonObject = new JsonObject();
        metaData = (ResultSetMetaData) resultSet.getMetaData();

        int cloumCount = metaData.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= cloumCount; i++) {
                String cloumName = metaData.getColumnLabel(i);
                String value = resultSet.getString(cloumName);
                jsonObject.addProperty(cloumName, value);
            }
        }
        return jsonObject;
    }
}

