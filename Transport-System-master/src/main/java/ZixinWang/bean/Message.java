package Guxinyu.bean;

import java.util.Date;
import java.util.LinkedList;



public class Message {
    private String userName;
    public LinkedList<MessageContent> messageContent;

    @Override
    public String toString() {
        return "Message{" +
                "userName='" + userName + '\'' +
                ", messageContent=" + messageContent +
                '}';
    }

    public Message() {
    }

    public Message(String userName, LinkedList<MessageContent> messageContent) {
        this.userName = userName;
        this.messageContent = messageContent;
    }

    public void addMessage(String content,String date) {
        if(messageContent==null){
            messageContent=new LinkedList<>();
        }
        messageContent.add(new MessageContent(content,date));
    }

    public void deleteMessage(String date) {
        for (MessageContent temp: this.messageContent) {
            if (temp.date.equals(date))
                messageContent.remove(temp);

        }

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LinkedList<MessageContent> getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(LinkedList<MessageContent> messageContent) {
        this.messageContent = messageContent;
    }
}