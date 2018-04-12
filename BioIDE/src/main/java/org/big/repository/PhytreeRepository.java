package org.big.repository;

import org.big.entity.Phytree;
import org.big.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;
/**
 *<p><b>Phytree的DAO类接口</b></p>
 *<p> Phytree的DAO类接口，与Phytree有关的持久化操作方法</p>
 * @author BINZI
 *<p>Created date: 2018/04/12 11:16</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@Repository
public interface PhytreeRepository extends BaseRepository<Phytree, String> {
	// 自定义条件查询 | 修改 | 删除
}
