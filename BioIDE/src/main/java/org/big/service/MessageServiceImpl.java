package org.big.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.big.common.BuildEntity;
import org.big.common.QueryTool;
import org.big.entity.Message;
import org.big.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by WangTianshan on 2017/9/6.
 */
@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRepository messageRepository;

    @Override
    @Transactional
    public JSON findbyInfo(HttpServletRequest request) {
        String this_language="en";
        Locale this_locale= LocaleContextHolder.getLocale();
        if(this_locale.getLanguage().equals("zh")){
            this_language="zh";
        }
        if(this_locale.getLanguage().equals("en")){
            this_language="en";
        }
        JSON json= null;
        String searchText=request.getParameter("search");
        if(searchText==null || searchText.length()<=0){
            searchText="";
        }
        int limit_serch=Integer.parseInt(request.getParameter("limit"));
        int offset_serch=Integer.parseInt(request.getParameter("offset"));
        String sort="desc";
        String order="date";
        sort=request.getParameter("sort");
        order=request.getParameter("order");
        if(sort==null || sort.length()<=0){
            sort="sendtime";
        }
        if(order==null || order.length()<=0){
            order="desc";
        }
        JSONObject thisTable= new JSONObject();
        JSONArray rows = new JSONArray();
        List<Object> thisList=new ArrayList<>();
        Page<Object> thisPage=this.messageRepository.searchInfo(searchText,QueryTool.buildPageRequest(offset_serch,limit_serch,sort,order));
        thisTable.put("total",thisPage.getTotalElements());
        thisList=thisPage.getContent();
        for(int i=0;i<thisList.size();i++){
            JSONObject row= new JSONObject();
            Message thisMessage= BuildEntity.buildMessage(thisList.get(i));
            row.put("sender",thisMessage.getSender());
            row.put("addressee",thisMessage.getAddressee());
            String type=thisMessage.getType();
            switch(type){
                case "information":
                    type="通知";
                    break;
                case "invitation":
                    type="邀请信";
                    break;
                default:
                    break;
            }
            row.put("type",type);
            String status="";
            int statusCode=thisMessage.getStatus();
            switch(statusCode){
                case 0:
                    status="未读";
                    break;
                case 1:
                    status="已读";
                    break;
                case 2:
                    status="同意";
                    break;
                case 3:
                    status="拒绝";
                    break;
                default:
                    break;
            }
            row.put("status",status);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sendTime="";
            try {
                sendTime=formatter.format(thisMessage.getSendtime());
            } catch (Exception e) {
                //e.printStackTrace();
            }
            row.put("sendtime",sendTime);
            rows.add(i,row);
        }
        thisTable.put("rows",rows);
        json=thisTable;
        return json;
    }

    @Override
    public Message findbyID(String ID) {
        return this.messageRepository.getOne(ID);
    }

    @Override
    public void saveOne(Message thisMessage) {
        if(thisMessage.getId()==null||thisMessage.getId().equals("")||thisMessage.getId().length()<=0){
            thisMessage.setId(UUID.randomUUID().toString());
            thisMessage.setSendtime(new Timestamp(System.currentTimeMillis()));
        }
        this.messageRepository.save(thisMessage);
    }

    @Override
    public void removeOne(String ID) {
        this.messageRepository.deleteById(ID);
    }

}
