package org.big.config;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.big.common.IdentityVote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *<p><b>切面配置类</b></p>
 *<p> 通过该类对方法进行拦截、记录等操作</p>
 * @author WangTianshan (王天山)
 *<p>Created date: 2017/9/28 21:35</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@Aspect // 声明这是一个切面。必须的！
@Component // 让此切面成为Spring容器管理的Bean 
public class AspectConfig {
    @Autowired
    private HttpServletRequest request;
    
    /**
     *<b>删除拦截</b>
     *<p> 对service下的所有删除方法进行权限判断验证，符合权限则放行、不符合权限则终止此方法并将错误信息写入Session</p>
     * @author WangTianshan (王天山)
     * @param joinPoint
     * @param objectId 被删除的实体的id
     * @return java.lang.Object
     */
    @Around("execution(* org.big.service.*.logicRemove001(..)) && args(objectId)")	// 环绕通知
    public Object canReove(JoinPoint joinPoint,String objectId){
        ProceedingJoinPoint pjp = (ProceedingJoinPoint) joinPoint;
        IdentityVote thisIdentityVote=new IdentityVote();
        String targetName = pjp.getSignature().getDeclaringTypeName();
        //System.out.println(pjp.getTarget().getClass().getName());
        //targetName=targetName.substring(16);
        targetName=targetName.replace("org.big.service.","");
        if(thisIdentityVote.hasAuthority(targetName, objectId)){
            try {
                return pjp.proceed();
            } catch (Throwable throwable) {
                request.getSession().setAttribute("operationError","authority");
                throwable.printStackTrace();
            }
            return false;
        }else{
            request.getSession().setAttribute("operationError","authority");
            return false;
        }
    }
    /**
     *<b>修改拦截</b>
     *<p> 对service下的所有修改方法进行权限判断验证，符合权限则放行、不符合权限则终止此方法并将错误信息写入Session</p>
     * @author BINZI
     * @param joinPoint
     * @param objectId 被修改的实体的id
     * @return java.lang.Object
     */
    
    /**
     *<b>邀请拦截</b>
     *<p> 对service下的所有邀请方法进行权限判断验证，符合权限则放行、不符合权限则终止此方法并将错误信息写入Session</p>
     * @author BINZI
     * @param joinPoint
     * @param objectId 发出邀请的实体的TeamId
     * @return java.lang.Object
     */
}