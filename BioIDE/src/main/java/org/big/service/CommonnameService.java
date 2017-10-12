package org.big.service;

import com.alibaba.fastjson.JSON;
import org.big.entity.Commonname;

import javax.servlet.http.HttpServletRequest;

/**
 *<p><b>Commonname的Service类接口</b></p>
 *<p> Commonname的Service类接口，与Commonname有关的业务逻辑方法</p>
 * @author WangTianshan (王天山)
 *<p>Created date: 2017/9/6 21:35</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
public interface CommonnameService {
    Commonname findbyID(String ID);
    void removeOne(String ID);
    void saveOne(Commonname thisPerson);
    JSON findbyInfo(HttpServletRequest request);
}
