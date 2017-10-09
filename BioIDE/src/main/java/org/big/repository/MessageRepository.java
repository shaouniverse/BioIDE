package org.big.repository;

import org.big.entity.Message;
import org.big.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by WangTianshan on 2017/9/6.
 */
@Repository
public interface MessageRepository extends BaseRepository<Message, String> {

//    @Query(value = "select m from Message m" +
//                    " where (" +
//                    "m.sender like %?1% " +
//                    "or m.addressee like %?1%)"
//    )
//    Page<Message> searchInfo(
//            String findText,
//            Pageable pageable
//    );

    @Query(value = "SELECT " +
            "m.id AS id," +
            "us.userName AS sender," +
            "ua.userName AS addressee," +
            "m.sendtime AS sendtime," +
            "m.title AS title," +
            "m.text AS text," +
            "m.status AS status," +
            "m.type AS type," +
            "m.mark AS mark " +
            "FROM Message m " +
            "LEFT JOIN User us " +
            "ON us.id=m.sender " +
            "LEFT JOIN User ua " +
            "ON ua.id=m.addressee " +
            "WHERE " +
            "ua.userName LIKE %?1% " +
            "OR us.userName LIKE %?1%"
    )
    Page<Object> searchInfo(
            String findText,
            Pageable pageable
    );

}
