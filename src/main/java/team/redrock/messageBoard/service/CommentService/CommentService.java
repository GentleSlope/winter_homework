package team.redrock.messageBoard.service.CommentService;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import team.redrock.messageBoard.been.Message;

import java.sql.ResultSet;
import java.util.List;


public interface CommentService {


    List<Message> findAllMessages(Message content);


    String messagesToJson(List<Message> messageList);


    boolean insertContent(Message message);


    JsonArray covJson1(ResultSet resultSet) throws Exception;


    JsonObject covJson2(ResultSet resultSet) throws Exception;
}
