package Guxinyu.controller;

import Guxinyu.bean.Message;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Controller
public class MessageController {
    public static HashMap<String, Message> messages=new HashMap<>();
    @RequestMapping("/user/message")
    public String userMessageShow(Model m){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        m.addAttribute("messages",messages);
        System.out.println(messages.toString());
        return "user/message/show";
    }

    @RequestMapping("/admin/message")
    public String adminMessageShow(Model m){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        m.addAttribute("messages",messages);
        System.out.println(messages.toString());
        return "admin/message/show";
    }


    @RequestMapping("/user/message/post")
    public String userMessagePostRedirect(){
        return "user/message/post";
    }

    @RequestMapping("/user/post")
    public String userMessagePost(String newMessage, Model m){


        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        String userName=userDetails.getUsername();
        Message temp;
        if (!messages.containsKey(userName)){
            temp=new Message();
        }
        else
            temp=messages.get(userName);
        temp.setUserName(userName);
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        temp.addMessage(newMessage,dateString );
        messages.put(userName,temp);

        return "redirect:/user/message";
    }

    @RequestMapping("/user/message/delete")
    public String userMessageDelete(Model m,String date){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        messages.get(userDetails.getUsername()).deleteMessage(date);
        return "redirect:/user/message";
    }

    @RequestMapping("/admin/message/delete")
    public String adminMessageDelete(Model m,String userName, String date){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        messages.get(userName).deleteMessage(date);
        return "redirect:/admin/message";
    }
}
