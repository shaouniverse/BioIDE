package org.big.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.big.common.BuildEntity;
import org.big.common.IdentityVote;
import org.big.common.QueryTool;
import org.big.entity.Message;
import org.big.entity.User;
import org.big.entity.UserDetail;
import org.big.repository.MessageRepository;
import org.big.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 *<p><b>Message的Service类</b></p>
 *<p> Message的Service类，与Message有关的业务逻辑方法</p>
 * @author WangTianshan (王天山)
 *<p>Created date: 2017/9/6 21:35</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private LocaleService localeService;
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}") // -- 换成当前登录用户
    private String fromEmail;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

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
            String title="<a target='_blank' href='super/message/read/"+thisMessage.getId()+"'>"+thisMessage.getTitle()+"</a>";
            row.put("title",title);
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
    @Transactional
    public JSON findInfoByAddressee(HttpServletRequest request) {
        UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
        Page<Object> thisPage=this.messageRepository.searchInfoByAddressee(searchText,thisUser.getId(),QueryTool.buildPageRequest(offset_serch,limit_serch,sort,order));
        thisTable.put("total",thisPage.getTotalElements());
        thisList=thisPage.getContent();
        for(int i=0;i<thisList.size();i++){
            JSONObject row= new JSONObject();
            Message thisMessage= BuildEntity.buildMessage(thisList.get(i));
            String title="<a target='_blank' href='console/message/read/"+thisMessage.getId()+"'>"+thisMessage.getTitle()+"</a>";
            row.put("title",title);
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
    @Transactional
    public JSON findInfoBySender(HttpServletRequest request) {
        UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("User:" + thisUser);
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
        Page<Object> thisPage=this.messageRepository.searchInfoBySender(searchText,thisUser.getId(),QueryTool.buildPageRequest(offset_serch,limit_serch,sort,order));
        thisTable.put("total",thisPage.getTotalElements());
        thisList=thisPage.getContent();
        for(int i=0;i<thisList.size();i++){
            JSONObject row= new JSONObject();
            Message thisMessage= BuildEntity.buildMessage(thisList.get(i));
            String title="<a target='_blank' href='console/message/read/"+thisMessage.getId()+"'>"+thisMessage.getTitle()+"</a>";
            row.put("title",title);
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
    public void sendOne(Message thisMessage) {
    	// 生成邮件ID
        if(thisMessage.getId()==null||thisMessage.getId().equals("")||thisMessage.getId().length()<=0){
            thisMessage.setId(UUID.randomUUID().toString());
            thisMessage.setStatus(0); 				// 邮件状态
            thisMessage.setType("information");		// 邮件类型
            // 通过用户名拿到用户ID，作为团队邀请人
            thisMessage.setSendtime(new Timestamp(System.currentTimeMillis()));
            if (null != thisMessage.getTeamid() && !"".equals(thisMessage.getTeamid())) {
            	sendInviteEmail(request, response, thisMessage);
            }else {
				sendEmail(request, response, thisMessage);
			}
        }
        this.messageRepository.save(thisMessage);
    }

    @Override
    public void removeOne(String ID) {
        this.messageRepository.deleteById(ID);
    }

    @Override
    public void changeStatus(Message thisMessage,int newStatus) {
        //身份判断
        IdentityVote identityVote=new IdentityVote();
        if(identityVote.isAddresseeByMessage(thisMessage)){
            this.messageRepository.changeStatus(thisMessage.getId(),newStatus);
        }
    }

    @Override
    public int countStatus(int statusNum) {
        UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.messageRepository.countStatus(thisUser.getId(),statusNum);
    }
    /**
     * 发送邀请信
     * @param request
     * @param response
     * @return
     */
    public String sendInviteEmail(HttpServletRequest request, HttpServletResponse response, Message message) {
    	String thisLanguage=localeService.getLanguage(request,response);
        String sendMsg="error";
            //根据邮箱判断用户是否存在(只能向已有用户发出邀请)
            User thisUser= userRepository.findOneByEmail(message.getAddressee());
            if(thisUser!=null){
	            try{
	                //base_url
	                String contextPath=request.getContextPath();
	                String base_url=request.getServerName().toString()+":"+request.getServerPort();
	                if(contextPath!=null && contextPath.length()>0){
	                    base_url=base_url+contextPath;
	                }
	                ///邮件的内容
	                StringBuffer sb=new StringBuffer("AnimalSeeker<br/>");
	                if(thisLanguage.equals("zh")){
	                	// http://localhost:8081/register/active/BINZI/ccbf732b-1880-4cca-b2f5-8b3566a987d2/
	                    sb.append("物种数据采集系统<b>" + message.getTitle() + "</b>");
	                    sb.append("接受邀请，点击下面链接，即可加入团队！<br/>");
	                    sb.append("<a href=\"http://"+base_url+"/console/team/invite/");
	                    sb.append(thisUser.getUserName());
	                    sb.append("/");
	                    if (null != request.getParameter("TeamID") && !"".equals(request.getAttribute("TeamID"))) {
	                    	sb.append(message.getTeamid());
		                    sb.append("/");
	                    }
	                    sb.append("\">http://"+base_url+"/console/message/invite/");
	                    sb.append(thisUser.getUserName());
	                    sb.append("/");
	                    if (null != request.getParameter("TeamID") && !"".equals(request.getAttribute("TeamID"))) {
	                    	sb.append(message.getTeamid());
		                    sb.append("/");
	                    }
	                    sb.append("</a>");
	                }
	                else if(thisLanguage.equals("en")){
	                    sb.append("Recording lives around us, citizen science starts from here.<br/>");
	                    sb.append("Click the link below to activate the account, please activate it as soon as possible!<br/>");
	                    sb.append("<a href=\"http://"+base_url+"/console/message/invite/");
	                    sb.append(thisUser.getUserName());
	                    sb.append("/");
	                    sb.append(message.getTeamid());
	                    sb.append("/");
	                    sb.append("\">http://"+base_url+"/console/message/invite/");
	                    sb.append(thisUser.getUserName());
	                    sb.append("/");
	                    sb.append(message.getTeamid());
	                    sb.append("/");
	                    sb.append("</a>");
	                }
	                else{
	                    sb.append("Recording lives around us, citizen science starts from here.<br/>");
	                    sb.append("Click the link below to activate the account, please activate it as soon as possible!<br/>");
	                    sb.append("<a href=\"http://"+base_url+"/console/message/invite/");
	                    sb.append(thisUser.getUserName());
	                    sb.append("/");
	                    sb.append(message.getTeamid());
	                    sb.append("/");
	                    sb.append("\">http://"+base_url+"/console/message/invite/");
	                    sb.append(thisUser.getUserName());
	                    sb.append("/");
	                    sb.append(message.getTeamid());
	                    sb.append("/");
	                    sb.append("</a>");
	                }
	
	                //邮件信息
	                MimeMessage mimeMessage = mailSender.createMimeMessage();
	                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	                helper.setFrom(fromEmail);
	                helper.setTo(message.getAddressee());
	                helper.setSubject("BioIDE");
	                helper.setText(sb.toString(), true);
	
	                //发送邮件
	                mailSender.send(mimeMessage);
	
	                if(thisLanguage.equals("zh"))
	                    sendMsg="发送成功";
	                else if(thisLanguage.equals("en"))
	                    sendMsg="Send Success";
	                else
	                    sendMsg="Send Success";
	            }catch(Exception e){
	                if(thisLanguage.equals("zh"))
	                    sendMsg="邮件发送失败";
	                else if(thisLanguage.equals("en"))
	                    sendMsg="Send email failed";
	                else
	                    sendMsg="Send email failed";
	            }
            }else{
	                if(thisLanguage.equals("zh"))
	                    sendMsg="该邮箱没有注册";
	                else if(thisLanguage.equals("en"))
	                    sendMsg="This email is not registered";
	                else
	                    sendMsg="This email is not registered";
	        }
        return sendMsg;
    }
    
    /**
     * 发送邮件
     * @param request
     * @param response
     * @return
     */
    public String sendEmail(HttpServletRequest request, HttpServletResponse response, Message message) {
    	String thisLanguage=localeService.getLanguage(request,response);
        String sendMsg="error";
            //根据邮箱判断用户是否存在(只能向已有用户发出邀请)
            User thisUser= userRepository.findOneByEmail(message.getAddressee());
            if(thisUser!=null){
	            try{
	                //base_url
	                String contextPath=request.getContextPath();
	                String base_url=request.getServerName().toString()+":"+request.getServerPort();
	                if(contextPath!=null && contextPath.length()>0){
	                    base_url=base_url+contextPath;
	                }
	                ///邮件的内容
	                StringBuffer sb=new StringBuffer("AnimalSeeker<br/>");
	                if(thisLanguage.equals("zh")){
	                    sb.append("物种数据采集系统<br/>");
	                    sb.append(message.getTitle() + "<br/>");
	                }
	                else if(thisLanguage.equals("en")){
	                    sb.append("Recording lives around us, citizen science starts from here.<br/>");
	                    sb.append(message.getTitle() + "<br/>");
	                }
	                else{
	                    sb.append("Recording lives around us, citizen science starts from here.<br/>");
	                    sb.append(message.getTitle() + "<br/>");
	                }
	
	                //邮件信息
	                MimeMessage mimeMessage = mailSender.createMimeMessage();
	                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	                helper.setFrom(fromEmail);				// 邮件发送人
	                helper.setTo(message.getAddressee());	// 邮件接收人
	                helper.setSubject("BioIDE");			// 邮件主题
	                helper.setText(sb.toString() + message.getText(), true);
	
	                //发送邮件
	                mailSender.send(mimeMessage);
	
	                if(thisLanguage.equals("zh"))
	                    sendMsg="发送成功";
	                else if(thisLanguage.equals("en"))
	                    sendMsg="Send Success";
	                else
	                    sendMsg="Send Success";
	            }catch(Exception e){
	                if(thisLanguage.equals("zh"))
	                    sendMsg="邮件发送失败";
	                else if(thisLanguage.equals("en"))
	                    sendMsg="Send email failed";
	                else
	                    sendMsg="Send email failed";
	            }
            }else{
	                if(thisLanguage.equals("zh"))
	                    sendMsg="该邮箱没有注册";
	                else if(thisLanguage.equals("en"))
	                    sendMsg="This email is not registered";
	                else
	                    sendMsg="This email is not registered";
	        }
        return sendMsg;
    }
}
