package org.big.config;


import com.google.code.kaptcha.Constants;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.big.common.MD5Utils;
import org.big.entity.UserDetail;
import org.big.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 *<p><b>自定义用户登陆验证类</b></p>
 *<p> 自定义用户登陆判定</p>
 * @author WangTianshan (王天山)
 *<p>Created date: 2017/9/12 21:35</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailService userService;

    @Autowired
    private HttpServletRequest request;

    /**
     *<b>用户登陆验证</b>
     *<p> 根据传入的验证实体来比对数据库及验证码判定是否通过验证</p>
     * @author WangTianshan (王天山)
     * @param authentication
     * @return org.springframework.security.core.Authentication
     */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		CustomWebAuthenticationDetails details = (CustomWebAuthenticationDetails) authentication.getDetails(); // 如上面的介绍，这里通过authentication.getDetails()获取详细信息
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
        // 下面是验证逻辑，验证通过则返回UsernamePasswordAuthenticationToken，
        // 否则，可直接抛出错误（AuthenticationException的子类，在登录验证不通过重定向至登录页时可通过session.SPRING_SECURITY_LAST_EXCEPTION.message获取具体错误提示信息）
		// 验证当前登录用户状态
		
		request.getSession().setAttribute("loginError", "");
		if (details.getToken()
				.equalsIgnoreCase(request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY).toString())) {
			UserDetail user = (UserDetail) userService.loadUserByUsername(name);
			if (user == null) {
				throw new BadCredentialsException("没有该用户");
			}
			if (!MD5Utils.MD532(password).equals(user.getPassword())) {
				request.getSession().setAttribute("loginError", "password");
				throw new BadCredentialsException("密码错误");
			}
			Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
			return new UsernamePasswordAuthenticationToken(user, password, authorities);
		} else {
			request.getSession().setAttribute("loginError", "token");
			throw new BadCredentialsException("验证码不正确");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}