package Guxinyu.service;

import Guxinyu.controller.HistoryController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

@Service
public class HistoryService {
    public String addHistory(String inquiryType, String inquiryContent, String inquiryResult){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        String userName=userDetails.getUsername();
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(inquiryType);
        stringBuffer.append(":  ");
        stringBuffer.append(inquiryContent);
        stringBuffer.append(" 查询结果：  ");
        stringBuffer.append(inquiryResult);
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        stringBuffer.append("  查询时间： ");
        stringBuffer.append(dateString);
        if (HistoryController.history.containsKey(userName)){
            HistoryController.history.get(userName).add(stringBuffer.toString());
        }
        else {
            LinkedList<String> linkedList=new LinkedList<>();
            linkedList.add(stringBuffer.toString());
            HistoryController.history.put(userName,(LinkedList<String>) linkedList.clone());
        }

        return stringBuffer.toString();

    }
}
