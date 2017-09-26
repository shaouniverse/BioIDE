package org.big.repository;

import org.big.entity.User;
import org.big.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by WangTianshan on 2017/9/6.
 */
@Repository
public interface UserRepository extends BaseRepository<User, String> {

    @Query(value = "select u from User u" +
                    " where (" +
                    "u.userName like %?1% " +
                    "or u.email like %?1% " +
                    "or u.phone like %?1%)"
    )
    Page<User> searchInfo(
        String findText,
        Pageable pageable
    );

    User findOneByUserName(String name);

}
