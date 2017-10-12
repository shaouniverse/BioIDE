package org.big.repository;

import org.big.entity.Commonname;
import org.big.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *<p><b>Commonname的DAO类接口</b></p>
 *<p> Commonname的DAO类接口，与Commonname有关的持久化操作方法</p>
 * @author WangTianshan (王天山)
 *<p>Created date: 2017/9/6 21:35</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@Repository
public interface CommonnameRepository extends BaseRepository<Commonname, String> {

    /**
     *<b>带分页排序的条件查询</b>
     *<p> 带分页排序的条件查询</p>
     * @author WangTianshan (王天山)
     * @param findText 条件关键词，这里是模糊匹配
     * @param pageable 分页排序方案实体
     * @return org.springframework.data.domain.Page<org.big.entity.Commonname>
     */
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

}
