package org.big.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Created by WangTianshan on 2017/9/19.
 */
public class UserDetail extends User implements UserDetails {

    //private List<Team> teams;

    public UserDetail(User user){
        super(user);
        //this.teams = teams;
    }
//    public UserDetail(User user, List<Team> teams){
//        super(user);
//        this.teams = teams;
//    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.getRole() == null || this.getRole().length() <1){
            return AuthorityUtils.commaSeparatedStringToAuthorityList("");
        }
        else{
            return AuthorityUtils.commaSeparatedStringToAuthorityList(this.getRole());
        }
//        if(teams == null || teams.size() <1){
//            return AuthorityUtils.commaSeparatedStringToAuthorityList("");
//        }
//        StringBuilder commaBuilder = new StringBuilder();
//        for(Team team : teams){
//            commaBuilder.append(team.getName()).append(",");
//        }
//        String authorities = commaBuilder.substring(0,commaBuilder.length()-1);
//        return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
    }

    @Override
    public String getUsername() {
        return super.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getName() {
        return super.getNickname();
    }
}
