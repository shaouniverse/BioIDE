package org.big.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by WangTianshan on 2017/9/19.
 */
public class UserTeamPK implements Serializable {

    private String userId;
    private String teamId;

    @Id
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "team_id")
    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTeamPK userTeam = (UserTeamPK) o;

        if (userId != null ? !userId.equals(userTeam.userId) : userTeam.userId != null) return false;
        if (teamId != null ? !teamId.equals(userTeam.teamId) : userTeam.teamId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (teamId != null ? teamId.hashCode() : 0);
        return result;
    }
}
