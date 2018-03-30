package org.big.service;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.big.entity.Team;
import org.big.entity.User;
import org.big.entity.UserTeam;
import org.big.repository.UserRepository;
import org.big.repository.UserTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *<p><b>UserTeam的Service类</b></p>
 *<p> UserTeam的Service类，与UserTeam有关的业务逻辑方法</p>
 * @author WangTianshan (王天山)
 *<p>Created date: 2017/9/6 21:35</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@Service
public class UserTeamServiceImpl implements UserTeamService  {

    @Autowired
    private UserTeamRepository userTeamRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LocaleService localeService;
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}") // -- 换成当前登录用户
    private String fromEmail;

    @Override
    public void saveOne(String userId,String teamId) {
        UserTeam thisUserTeam=new UserTeam();
        thisUserTeam.setUserId(userId);
        thisUserTeam.setTeamId(teamId);
        this.userTeamRepository.save(thisUserTeam);
    }

    
	@Override
	public String SendEmailTransPermissionAdvice(HttpServletRequest request, HttpServletResponse response, String email, Team team) {
		String thisLanguage=localeService.getLanguage(request,response);
        String sendMsg="error";
            //根据邮箱判断用户是否存在(只能向已有用户发出邀请)
            User thisUser= userRepository.findOneByEmail(email);
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
	                    sb.append("物种数据采集系统<b>" + team.getName() + "团队邮件通知：</b>");
	                    sb.append("&nbsp;&nbsp;&nbsp;&nbsp;" + thisUser.getUserName()+"，您已成为"+ team.getName() +"团队负责人！<br/>");
	                }
	                else if(thisLanguage.equals("en")){
	                    sb.append("Recording lives around us, citizen science starts from here.<br/>");
	                    sb.append("Click the link below to activate the account, please activate it as soon as possible!<br/>");
	                }
	                else{
	                    sb.append("Recording lives around us, citizen science starts from here.<br/>");
	                    sb.append("Click the link below to activate the account, please activate it as soon as possible!<br/>");
	                }
	
	                //邮件信息
	                MimeMessage mimeMessage = mailSender.createMimeMessage();
	                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	                helper.setFrom(fromEmail);
	                helper.setTo(email);
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

}
