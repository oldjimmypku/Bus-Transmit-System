package Guxinyu.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.LinkedList;

@Controller
public class HistoryController {

    public static HashMap<String, LinkedList<String>> history=new HashMap<>();

    @RequestMapping("/user/history")
    public String history(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        model.addAttribute("history",history.get(userDetails.getUsername()));
        return "user/history";

    }
}
