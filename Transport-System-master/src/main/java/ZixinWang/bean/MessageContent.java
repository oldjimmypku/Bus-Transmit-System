package Guxinyu.bean;

import java.util.Date;


    public class MessageContent{
        public String content;
        public String date;

        @Override
        public String toString() {
            return "MessageContent{" +
                    "content='" + content + '\'' +
                    ", date='" + date + '\'' +
                    '}';
        }

        MessageContent(String content, String date) {
            this.content = content;
            this.date = date;
        }
    }
