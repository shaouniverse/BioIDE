package org.big.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.big.common.BuildEntity;
import org.big.common.IdentityVote;
import org.big.common.MD5Utils;
import org.big.common.QueryTool;
import org.big.entity.Dataset;
import org.big.entity.Team;
import org.big.entity.User;
import org.big.entity.UserDetail;
import org.big.entity.UserTeam;
import org.big.repository.DatasetRepository;
import org.big.repository.TeamRepository;
import org.big.repository.UserRepository;
import org.big.repository.UserTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.Constants;

/**
 *<p><b>User的Service类</b></p>
 *<p> User的Service类，与User有关的业务逻辑方法</p>
 * @author WangTianshan (王天山)
 *<p>Created date: 2017/9/6 21:35</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserTeamRepository userTeamRepository;
    @Autowired
    private LocaleService localeService;
    @Value("${spring.mail.username}") // -- BINZIDYB@163.com
    private String fromEmail;
    @Autowired
    private DatasetRepository datasetRepository;
    
    @Override
	@Transactional
	public JSON findbyInfo(HttpServletRequest request) {
		String this_language = "en";
		Locale this_locale = LocaleContextHolder.getLocale();
		if (this_locale.getLanguage().equals("zh")) {
			this_language = "zh";
		}
		if (this_locale.getLanguage().equals("en")) {
			this_language = "en";
		}
		JSON json = null;
		String searchText = request.getParameter("search");
		if (searchText == null || searchText.length() <= 0) {
			searchText = "";
		}
		int limit_serch = Integer.parseInt(request.getParameter("limit"));
		int offset_serch = Integer.parseInt(request.getParameter("offset"));
		String sort = "desc";
		String order = "date";
		sort = request.getParameter("sort");
		order = request.getParameter("order");
		if (sort == null || sort.length() <= 0) {
			sort = "adddate";
		}
        if(order==null || order.length()<=0){
            order="desc";
        }
        JSONObject thisTable= new JSONObject();
        JSONArray rows = new JSONArray();
        List<User> thisList=new ArrayList<>();
        Page<User> thisPage=this.userRepository.searchInfo(searchText,QueryTool.buildPageRequest(offset_serch,limit_serch,sort,order));
        thisTable.put("total",thisPage.getTotalElements());
        thisList=thisPage.getContent();
        for(int i=0;i<thisList.size();i++){
            JSONObject row= new JSONObject();
            String thisSelect="<input type='checkbox' name='checkbox' id='sel_"+thisList.get(i).getId()+"' />";
            String thisEdit=
                    "<a class=\"wts-table-edit-icon\" onclick=\"editThisObject('"+thisList.get(i).getId()+"','user')\" >" +
                    	"<span class=\"glyphicon glyphicon-edit\"></span>" +
                    "</a>" +
                    "<a class=\"wts-table-edit-icon\" onclick=\"removeThisObject('"+thisList.get(i).getId()+"','user')\" >" +
                    	"<span class=\"glyphicon glyphicon-remove\"></span>" +
                    "</a>";
            row.put("select",thisSelect);
            row.put("userName",thisList.get(i).getUserName());
            row.put("nickname",thisList.get(i).getNickname());
            row.put("email",thisList.get(i).getEmail());
            row.put("phone",thisList.get(i).getPhone());
            row.put("role",thisList.get(i).getRole());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String addTime="";
            try {
                addTime=formatter.format(thisList.get(i).getAdddate());
            } catch (Exception e) {
                //e.printStackTrace();
            }
            row.put("adddate",addTime);
            row.put("edit",thisEdit);
            rows.add(i,row);
        }
        thisTable.put("rows",rows);
        json=thisTable;
        return json;
    }

    @Override
    public User findbyID(String ID) {
        return this.userRepository.getOne(ID);
    }

    @Override
    public void saveOne(User thisUser) {
        if(thisUser.getId()==null||thisUser.getId().equals("")||thisUser.getId().length()<=0){
            thisUser.setId(UUID.randomUUID().toString());
            thisUser.setAdddate(new Timestamp(System.currentTimeMillis()));
        }
        this.userRepository.save(thisUser);
    }

    @Override
    public void removeOne(String ID) {
        this.userRepository.deleteById(ID);
    }

    @Override
    public User findOneByName(String user_name) {
        return this.userRepository.findOneByUserName(user_name);
    }
    
    @Override
    public User findOneByNickName(String nickname) {
        return this.userRepository.findOneByNickname(nickname);
    }

    @Override
    public User findOneByEmail(String email) {
    	return this.userRepository.findOneByEmail(email);
    }

	@Override
	@Transactional
	public JSON findAllUser(HttpServletRequest request) {
		String findText = request.getParameter("find");
		if (findText == null || findText.length() <= 0) {
			findText = "";
		}
		int findPage = 1;
		try {
			findPage = Integer.valueOf(request.getParameter("page"));
		} catch (Exception e) {
		}
		int limit_serch = 30;
		int offset_serch = (findPage - 1) * 30;
		String sort = "email";
		String order = "asc";
        // 创建JSONObject对象
        JSONObject thisSelect = new JSONObject();	
        // 创建JSON数组
        JSONArray items = new JSONArray();
        List<Object> thisList=new ArrayList<>();
        // 获取当前登录用户
        // 传入页码起始页、页面大小、排序字段和排序类型关键参数返回SpringData规定排序PageRequest类型
        UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Page<Object> thisPage=this.userRepository.findUsersByEmail(findText, thisUser.getEmail(), 
        		QueryTool.buildPageRequest(offset_serch,limit_serch,sort,order));
        thisSelect.put("total_count",thisPage.getTotalElements());	// 总记录数
        Boolean incompleteResulte=true;
        
        if((thisPage.getTotalElements()/30)>findPage){
            incompleteResulte=false;
        }
        thisSelect.put("incompleteResulte",incompleteResulte);
        thisList = thisPage.getContent();

       /* AllUsers
        if(findPage==1){
            JSONObject row = new JSONObject();
            row.put("id","");
            row.put("full_name", messageSource.getMessage("all_udata", null, LocaleContextHolder.getLocale()));
            items.add(0,row);
        }
        */
        
        for(int i=0;i<thisList.size();i++){
            JSONObject row= new JSONObject();
            row.put("id",BuildEntity.buildUdataNameString(thisList.get(i)));
            row.put("full_name",BuildEntity.buildUdataString(thisList.get(i)));
            items.add(row);
        }
        thisSelect.put("items",items);
        return thisSelect;
    }
    
    @Override
    @Transactional
    public JSON findbyTeamId(HttpServletRequest request) {
        String this_language="en";
        Locale this_locale= LocaleContextHolder.getLocale();
        if(this_locale.getLanguage().equals("zh")){
            this_language="zh";
        }
        if(this_locale.getLanguage().equals("en")){
            this_language="en";
        }
        JSON json= null;
        // -- 复选框 -- 
        String searchText=request.getParameter("search");
        if(searchText==null || searchText.length()<=0){
            searchText="";
        }
        int limit_serch=Integer.parseInt(request.getParameter("limit"));
        int offset_serch=Integer.parseInt(request.getParameter("offset"));
        String teamId=request.getParameter("teamId");
        String sort="desc";
        String order="date";
        sort=request.getParameter("sort");
        order=request.getParameter("order");
        if(sort==null || sort.length()<=0){
            sort="adddate";
        }
        if(order==null || order.length()<=0){
            order="desc";
        }
        JSONObject thisTable = new JSONObject();
        JSONArray rows = new JSONArray();
        List<User> thisList=new ArrayList<>();
        Page<User> thisPage=this.userRepository.searchByTeamId(teamId,searchText,QueryTool.buildPageRequest(offset_serch,limit_serch,sort,order));
        thisTable.put("total",thisPage.getTotalElements());
        thisList=thisPage.getContent();
        for(int i=0;i<thisList.size();i++){
            JSONObject row= new JSONObject();
            String thisEdit=
                            "<a class=\"wts-table-edit-icon\" onclick=\"removeThisMember('"+teamId+"','"+thisList.get(i).getId()+"')\" >" +
                            	"<span class=\"glyphicon glyphicon-remove\"></span>" +
                            "</a> &nbsp;" + 
                            "<a class=\"wts-table-edit-icon\" onclick=\"transThisMember('"+teamId+"','"+thisList.get(i).getId()+"')\" >" +
                        		"<span class=\"glyphicon glyphicon-adjust\"></span>" +
                            "</a> &nbsp;" +
				            "<a class=\"wts-table-edit-icon\" onclick=\"inviteThisObject('"+teamId+"','"+thisList.get(i).getId()+"')\" >" +
				            	"<span class=\"glyphicon glyphicon-plus\"></span>" +
				            "</a>";
            row.put("userName",thisList.get(i).getUserName());
            row.put("nickname",thisList.get(i).getNickname());
            row.put("email",thisList.get(i).getEmail());
            // -- 团队角色 -- 
            String role="成员";
            if(this.teamRepository.getOne(teamId).getLeader().equals(thisList.get(i).getId())){
                role="<span class=\"badge bg-light-blue\">负责人</span>";
            }
            row.put("role",role);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // -- 时间 --
            String addTime="";
            try {
                addTime=formatter.format(thisList.get(i).getAdddate());
            } catch (Exception e) {
                //e.printStackTrace();
            }
            // -- 团队创建日期 --
            row.put("adddate",addTime);
            IdentityVote identityVote=new IdentityVote();
            if(identityVote.isTeamLeaderByTeamId(teamId))
                row.put("edit",thisEdit);
            
            rows.add(i,row);
        }
        thisTable.put("rows",rows);
        json=thisTable;
        return json;
    }

    @Override
    public void changeStatus(User thisUser,int status) {
        thisUser.setStatus((byte)status);
        this.userRepository.save(thisUser);
    }
    
    // 用户注册 -- 同时发送邮件
	@Override
	public String registerNewOne(HttpServletRequest request, HttpServletResponse response, User newUser) {
		String registerMsg="none";
		String thisLanguage=localeService.getLanguage(request,response);
        //Token判断
        if(request.getParameter("token").equals(request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY).toString())){
            //username判断
            if(this.findOneByName(newUser.getUserName())==null) {
                //email判断
                if (this.findOneByEmail(newUser.getEmail()) == null) {
                    //设置用户信息
                    newUser.setStatus((byte)0);									// 注册用户状态默认为0 -- 未激活状态
                    newUser.setScore(0);
                    newUser.setLevel(0);
                    newUser.setUploadnum(0);
                    newUser.setIdnum(0);
                    newUser.setAvatar("");
                    newUser.setMark(UUID.randomUUID().toString());				// 为当前注册用户生成用户激活码 -- 用于激活
                    newUser.setRole("ROLE_USER");								// 用户角色
                    newUser.setPassword(MD5Utils.MD532(newUser.getPassword()));	// 密码加密
                    this.saveOne(newUser);

                    //设置激活流程
                    try{
                        //base_url
                        String contextPath=request.getContextPath();
                        String base_url=request.getServerName().toString()+":"+request.getServerPort();
                        if(contextPath!=null && contextPath.length()>0){
                            base_url=base_url+contextPath;
                        }
                        ///邮件的内容
                        StringBuffer sb=new StringBuffer("BioIDE<br/>");
                        if(thisLanguage.equals("zh")){
                            sb.append("物种数据采集系统<br/>");
                            sb.append("点击下面链接激活账号，请尽快激活！<br/>");
                            sb.append("<a href=\"http://"+base_url+"/register/active/");
                            sb.append(newUser.getUserName());
                            sb.append("/");
                            sb.append(newUser.getMark());
                            sb.append("/");
                            sb.append("\">http://"+base_url+"/register/active/");
                            sb.append(newUser.getUserName());
                            sb.append("/");
                            sb.append(newUser.getMark());
                            sb.append("/");
                            sb.append("</a>");
                        }else if(thisLanguage.equals("en")){
                            sb.append("Discover and record small animals around you<br/>");
                            sb.append("Click the link below to activate the account, please activate it as soon as possible!<br/>");
                            sb.append("<a href=\"http://"+base_url+"/register/active/");
                            sb.append(newUser.getUserName());
                            sb.append("/");
                            sb.append(newUser.getMark());
                            sb.append("/");
                            sb.append("\">http://"+base_url+"/register/active/");
                            sb.append(newUser.getUserName());
                            sb.append("/");
                            sb.append(newUser.getMark());
                            sb.append("/");
                            sb.append("</a>");
                        }else{
                            sb.append("Discover and record small animals around you<br/>");
                            sb.append("Click the link below to activate the account, please activate it as soon as possible!<br/>");
                            sb.append("<a href=\"http://"+base_url+"/register/active/");
                            sb.append(newUser.getUserName());
                            sb.append("/");
                            sb.append(newUser.getMark());
                            sb.append("/");
                            sb.append("\">http://"+base_url+"/register/active/");
                            sb.append(newUser.getUserName());
                            sb.append("/");
                            sb.append(newUser.getMark());
                            sb.append("/");
                            sb.append("</a>");
                        }

                        //邮件信息
                        MimeMessage mimeMessage = mailSender.createMimeMessage();
                        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                        helper.setFrom(fromEmail);
                        helper.setTo(newUser.getEmail());
                        helper.setSubject("BioIDE");
                        helper.setText(sb.toString(), true);

                        //发送邮件
                        mailSender.send(mimeMessage);
                    }catch(Exception e){
                    }
                    registerMsg="success";
                }
                else{
                    if(thisLanguage.equals("zh"))
                        registerMsg = "邮箱不可用，请更换";
                    else if(thisLanguage.equals("en"))
                        registerMsg = "The email address is not available, please replace the email address";
                    else
                        registerMsg = "The email address is not available, please replace the email address";
                }
            }
            else{
                if(thisLanguage.equals("zh"))
                    registerMsg = "用户名不可用，请更换";
                else if(thisLanguage.equals("en"))
                    registerMsg = "The username is not available, please replace the username";
                else
                    registerMsg = "The username is not available, please replace the username";
            }
        }
        else{
            if(thisLanguage.equals("zh"))
                registerMsg = "验证码错误";
            else if(thisLanguage.equals("en"))
                registerMsg = "Verification is error";
            else
                registerMsg = "Verification is error";
        }
        return registerMsg;
    }

	
	@Override
	public String sendActiveEmail(HttpServletRequest request, HttpServletResponse response) {
		String thisLanguage=localeService.getLanguage(request,response);
        String sendMsg="error";
        //验证码判断
        if(request.getParameter("token").equals(request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY).toString())){
            //邮箱判断
            User thisUser=this.findOneByEmail(request.getParameter("email"));
            if(thisUser!=null){
                //判断是否激活
                if(thisUser.getStatus()==0){
                    try{
                        //base_url
                        String contextPath=request.getContextPath();
                        String base_url=request.getServerName().toString()+":"+request.getServerPort();
                        if(contextPath!=null && contextPath.length()>0){
                            base_url=base_url+contextPath;
                        }
                        ///邮件的内容
                        StringBuffer sb=new StringBuffer("BioIDE<br/>");
                        if(thisLanguage.equals("zh")){
                        	// http://localhost:8081/register/active/BINZI/ccbf732b-1880-4cca-b2f5-8b3566a987d2/
                            sb.append("物种数据采集系统<br/>");
                            sb.append("点击下面链接激活账号，请尽快激活！<br/>");
                            sb.append("<a href=\"http://"+base_url+"/register/active/");
                            sb.append(thisUser.getUserName());
                            sb.append("/");
                            sb.append(thisUser.getMark());
                            sb.append("/");
                            sb.append("\">http://"+base_url+"/register/active/");
                            sb.append(thisUser.getUserName());
                            sb.append("/");
                            sb.append(thisUser.getMark());
                            sb.append("/");
                            sb.append("</a>");
                        }
                        else if(thisLanguage.equals("en")){
                            sb.append("Recording lives around us, citizen science starts from here.<br/>");
                            sb.append("Click the link below to activate the account, please activate it as soon as possible!<br/>");
                            sb.append("<a href=\"http://"+base_url+"/register/active/");
                            sb.append(thisUser.getUserName());
                            sb.append("/");
                            sb.append(thisUser.getMark());
                            sb.append("/");
                            sb.append("\">http://"+base_url+"/register/active/");
                            sb.append(thisUser.getUserName());
                            sb.append("/");
                            sb.append(thisUser.getMark());
                            sb.append("/");
                            sb.append("</a>");
                        }
                        else{
                            sb.append("Recording lives around us, citizen science starts from here.<br/>");
                            sb.append("Click the link below to activate the account, please activate it as soon as possible!<br/>");
                            sb.append("<a href=\"http://"+base_url+"/register/active/");
                            sb.append(thisUser.getUserName());
                            sb.append("/");
                            sb.append(thisUser.getMark());
                            sb.append("/");
                            sb.append("\">http://"+base_url+"/register/active/");
                            sb.append(thisUser.getUserName());
                            sb.append("/");
                            sb.append(thisUser.getMark());
                            sb.append("/");
                            sb.append("</a>");
                        }

                        //邮件信息
                        MimeMessage mimeMessage = mailSender.createMimeMessage();
                        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                        helper.setFrom(fromEmail);
                        helper.setTo(thisUser.getEmail());
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
                }
                else{
                    if(thisLanguage.equals("zh"))
                        sendMsg="此用户已激活，请直接登录";
                    else if(thisLanguage.equals("en"))
                        sendMsg="This user has been activated, please login directly";
                    else
                        sendMsg="This user has been activated, please login directly";
                }
            }
            else{
                if(thisLanguage.equals("zh"))
                    sendMsg="该邮箱没有注册";
                else if(thisLanguage.equals("en"))
                    sendMsg="This email is not registered";
                else
                    sendMsg="This email is not registered";
            }
        }
        else{
            if(thisLanguage.equals("zh"))
                sendMsg="验证码错误";
            else if(thisLanguage.equals("en"))
                sendMsg="Verification code is error";
            else
                sendMsg="Verification code is error";
        }
        return sendMsg;
    }


	@Override
    public String activeUser(String userName, String mark, HttpServletRequest request, HttpServletResponse response) {
		String thisLanguage = localeService.getLanguage(request, response);
		String activeMsg = "error";
        // 用户点击激活链接 -- http://localhost:8081/register/active/BINZI/ccbf732b-1880-4cca-b2f5-8b3566a987d2/
		User thisUser = this.findOneByName(userName);
		if (thisUser != null) {	// 是否有此用户
			if (thisUser.getStatus() == 0) { // 是否能够正常激活
                if(thisUser.getMark().equals(mark)){ // 创建默认Team及该Team下的数据集
                	Team newTeam = new Team();
                	newTeam.setId(UUID.randomUUID().toString());
                	newTeam.setName(thisUser.getNickname());
                	newTeam.setLeader(thisUser.getId());
                	newTeam.setNote("Default");
                	newTeam.setAdddate(new Timestamp(System.currentTimeMillis()));
                	
                	UserTeam newUserTeam = new UserTeam();
                	newUserTeam.setTeamId(newTeam.getId());
                	newUserTeam.setUserId(thisUser.getId());
                	
                	Dataset newDataset = new Dataset();
                	newDataset.setId(UUID.randomUUID().toString());
                	newDataset.setDsname(thisUser.getUserName());
                	newDataset.setDsabstract("Default");
                    newDataset.setCreator(thisUser.getId());
                    newDataset.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                    newDataset.setSynchdate(new Timestamp(System.currentTimeMillis()));
                    newDataset.setSynchstatus(0);
                    newDataset.setStatus(1);
                    newDataset.setTeam(newTeam);
                    
                    this.teamRepository.save(newTeam);
                    this.datasetRepository.save(newDataset);
                    this.userTeamRepository.save(newUserTeam);
                    //设置默认观测记录集
					this.changeStatus(thisUser, 1);
                    activeMsg="账户已激活";
                }
                else{
                    //激活失败
                    activeMsg="无效激活链接";
                }
            }
            else if(thisUser.getStatus()==1){
                //已经激活
                activeMsg="账户已激活";
            }
            else{
                //账户异常
                activeMsg="无效激活链接";
            }

        }
        else{
            //无效激活链接
            activeMsg="无效激活链接";
        }
        return activeMsg;
    }
	
	/**
	 * 逻辑：token正确 --> 邮箱判断 --> 设置找回流程
	 */
	@Override
    public String sendPasswordEmail(HttpServletRequest request, HttpServletResponse response) {
        String errorMsg="none";
        String thisLanguage=localeService.getLanguage(request,response);
        //Token判断
        if(request.getParameter("token").equals(request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY).toString())){
            //email判断
            User thisUser=this.userRepository.findOneByEmail(request.getParameter("email"));
            if(thisUser!=null){
                //设置找回流程
                try{
                    //base_url
                    String contextPath=request.getContextPath();
                    String base_url=request.getServerName().toString()+":"+request.getServerPort();
                    if(contextPath!=null && contextPath.length()>0){
                        base_url=base_url+contextPath;
                    }
                    
                    thisUser.setResetmark((UUID.randomUUID().toString().replace("-","")));
                    //过期时间
                    Calendar c = Calendar.getInstance();
                    c.setTime(new Date());
                    c.add(Calendar.MINUTE, 10);
                    thisUser.setResettime(c.getTime());
                    this.userRepository.save(thisUser);
                    ///邮件的内容
                    StringBuffer sb=new StringBuffer("BioIDE<br/>");
                    if(thisLanguage.equals("zh")){
                    	sb.append("物种数据采集系统<br/>");
                        sb.append("点击下面链接重设密码，请在10分钟内完成！<br/>");
                        sb.append("<a href=\"http://"+base_url+"/password/reset/");
                        sb.append(thisUser.getUserName());
                        sb.append("/");
                        sb.append(thisUser.getResetmark());
                        sb.append("/");
                        sb.append("\">http://"+base_url+"/password/reset/");
                        sb.append(thisUser.getUserName());
                        sb.append("/");
                        sb.append(thisUser.getResetmark());
                        sb.append("/");
                        sb.append("</a>");
                    }
                    else if(thisLanguage.equals("en")){
                        sb.append("The system of biology data.<br/>");
                        sb.append("Click the link below to reset the password, please complete it in 10 minutes!<br/>");
                        sb.append("<a href=\"http://"+base_url+"/password/reset/");
                        sb.append(thisUser.getUserName());
                        sb.append("/");
                        sb.append(thisUser.getResetmark());
                        sb.append("/");
                        sb.append("\">http://"+base_url+"/password/reset/");
                        sb.append(thisUser.getUserName());
                        sb.append("/");
                        sb.append(thisUser.getResetmark());
                        sb.append("/");
                        sb.append("</a>");
                    }
                    else{
                        sb.append("The system of biology data.<br/>");
                        sb.append("Click the link below to reset the password, please complete it in 10 minutes!<br/>");
                        sb.append("<a href=\"http://"+base_url+"/password/reset/");
                        sb.append(thisUser.getUserName());
                        sb.append("/");
                        sb.append(thisUser.getResetmark());
                        sb.append("/");
                        sb.append("\">http://"+base_url+"/password/reset/");
                        sb.append(thisUser.getUserName());
                        sb.append("/");
                        sb.append(thisUser.getResetmark());
                        sb.append("/");
                        sb.append("</a>");
                    }

                    //邮件信息
                    MimeMessage mimeMessage = mailSender.createMimeMessage();
                    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                    helper.setFrom(fromEmail);
                    helper.setTo(thisUser.getEmail());
                    helper.setSubject("物种数据采集系统");
                    helper.setText(sb.toString(), true);

                    //发送邮件
                    mailSender.send(mimeMessage);
                }catch(Exception e){
                    errorMsg="error";
                }
            }
            else{
                errorMsg="email";
            }
        }
        else{
            errorMsg="token";
        }
        return errorMsg;
    }

	@Override
    public Boolean canRestPassword(String username,String mark) {
        try{
            User thisUser=this.userRepository.findOneByUserName(username);
            //mark验证
            if(thisUser.getResetmark().equals(mark)){
                //时间验证
                Date nowTime=new Date();
                Date pastTime=thisUser.getResettime();
                if(nowTime.compareTo(pastTime)==-1){
                    return true;
                }
            }
            return false;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean restPassword(String username,String password) {
        try{
            User thisUser=this.userRepository.findOneByUserName(username);
            thisUser.setPassword(MD5Utils.MD532(password));
            thisUser.setResetmark("");
            this.userRepository.save(thisUser);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    
}