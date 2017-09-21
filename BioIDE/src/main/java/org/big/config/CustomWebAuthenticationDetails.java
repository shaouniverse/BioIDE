package org.big.config;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Tianshan on 17/5/25.
 */
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {
    /**
     *
     */
    private static final long serialVersionUID = 6975601077710753878L;
    private final String token;

    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        token = request.getParameter("token");
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("; Token: ").append(this.getToken());
        return sb.toString();
    }
}