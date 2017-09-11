package org.big.repository;

import org.big.entity.User;
import org.big.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by WangTianshan on 2017/9/6.
 */
@Repository
public interface UserRepository extends BaseRepository<User, String> {

//    @Query(value = "select u from User u" +
//                    " where (" +
//                    "u.userName like %?1% " +
//                    "or u.email like %?1% " +
//                    "or u.phone like %?1%)"
//    )
//    List<User> searchInfo(
//            String findText,
//            Sort sort
//    );
//
//    @Query(value = "select u from User u" +
//            " where (u.userName like %:findText% or u.email like %:findText% or u.phone like %:findText%)")
//    Page<User> searchInfo(
//            String findText,
//            PageRequest pageRequest
//    );


    //条件查询，使用原生的 SQL
    @Query(value=
            "select * from user u" +
                    " where (u.user_name like %:findText% or u.email like %:findText% or u.phone like %:findText%) " +
                    " order by :sort :order " +
                    " limit :offset_serch , :limit_serch",
            nativeQuery=true
    )
    List<User> searchInfo(
            @Param("findText") String findText,
            @Param("sort") String sort,
            @Param("order") String order,
            @Param("offset_serch") int offset_serch,
            @Param("limit_serch") int limit_serch
    );

    //条件查询+分页+排序的计数
    @Query("select count(*) from User u where (u.userName like %:findText% or u.email like %:findText% or u.phone like %:findText%)")
    int countSearchInfo(
            @Param("findText") String findText
    );

}
