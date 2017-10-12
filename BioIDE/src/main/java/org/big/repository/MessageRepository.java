package org.big.repository;

import org.big.entity.Message;
import org.big.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *<p><b>Message的DAO类接口</b></p>
 *<p> Message的DAO类接口，与Message有关的持久化操作方法</p>
 * @author WangTianshan (王天山)
 *<p>Created date: 2017/9/6 21:35</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@Repository
public interface MessageRepository extends BaseRepository<Message, String> {

    /**
     *<b>带分页排序的条件查询</b>
     *<p> 带分页排序的条件查询</p>
     * @author WangTianshan (王天山)
     * @param findText 条件关键词，这里是模糊匹配
     * @param pageable 分页排序方案实体
     * @return org.springframework.data.domain.Page<java.lang.Object>
     */
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

    /**
     *<b>带分页排序的条件查询(收信箱)</b>
     *<p> 带分页排序的条件查询，只选择收信者和传入用户id一致的实体</p>
     * @author WangTianshan (王天山)
     * @param findText 条件关键词，这里是模糊匹配
     * @param user_id 收信者的id
     * @param pageable 分页排序方案实体
     * @return org.springframework.data.domain.Page<java.lang.Object>
     */
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
            "m.addressee=?2 "+
            "AND (" +
            "ua.userName LIKE %?1% " +
            "OR us.userName LIKE %?1%)"
    )
    Page<Object> searchInfoByAddressee(
            String findText,
            String user_id,
            Pageable pageable
    );

    /**
     *<b>带分页排序的条件查询(发信箱)</b>
     *<p> 带分页排序的条件查询，只选择发信者和传入用户id一致的实体</p>
     * @author WangTianshan (王天山)
     * @param findText 条件关键词，这里是模糊匹配
     * @param user_id 发信者的id
     * @param pageable 分页排序方案实体
     * @return org.springframework.data.domain.Page<java.lang.Object>
     */
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
            "m.sender=?2 "+
            "AND (" +
            "ua.userName LIKE %?1% " +
            "OR us.userName LIKE %?1%)"
    )
    Page<Object> searchInfoBySender(
            String findText,
            String user_id,
            Pageable pageable
    );
}
