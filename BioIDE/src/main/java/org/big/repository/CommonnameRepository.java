package org.big.repository;

import org.big.entity.Commonname;
import org.big.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * Created by WangTianshan on 2017/9/6.
 */
@Repository
public interface CommonnameRepository extends BaseRepository<Commonname, String> {

    @Query(value = "select c from Commonname c" +
            " where (" +
            "c.commonname like %?1% " +
            "or c.language like %?1% " +
            "or c.status like %?1%)"
    )
    Page<Commonname> searchInfo(
        String findText,
        Pageable pageable
    );


    //条件查询，使用原生的 SQL
//    @Query(value=
//            "select * from Commoname c" +
//                    " where (c.commonname like %:findText% or c.language like %:findText% or c.status like %:findText%) " +
//                    " order by :sort :order " +
//                    " limit :offset_serch , :limit_serch",
//            nativeQuery=true
//    )
//    List<Commonname> searchInfoQuery(
//            @Param("findText") String findText,
//            @Param("sort") String sort,
//            @Param("order") String order,
//            @Param("offset_serch") int offset_serch,
//            @Param("limit_serch") int limit_serch
//    );

    //条件查询+分页+排序的计数
//    @Query("select count(*) from Commonname c where (c.commonname like %:findText% or c.language like %:findText% or c.status like %:findText%)")
//    int countSearchInfoQuery(
//            @Param("findText") String findText
//    );

}
