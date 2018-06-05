package org.big.controller;

import org.big.entity.Dataset;
import org.big.entity.Taxaset;
import org.big.entity.Taxon;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *<p><b>TaxonController类</b></p>
 *<p> Taxon的Controller</p>
 * @author WangTianshan (王天山)
 *<p>Created date: 2018/5/11 09:24</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@Controller
@RequestMapping("/console/taxon")
public class TaxonController {
    /**
     *<b>默认管理页面</b>
     *<p> 默认管理页面</p>
     * @author WangTianshan (王天山)
     * @return java.lang.String
     */
    @RequestMapping(value="", method = {RequestMethod.GET})
    public String Index(Model model) {
        return "taxon/index";
    }

    /**
     *<b>新建页面</b>
     *<p> 新建页面</p>
     * @author WangTianshan (王天山)
     * @return java.lang.String
     */
    @RequestMapping(value="/add", method = {RequestMethod.GET})
    public String Add(Model model) {

        Taxon thisTaxon =new Taxon();
        thisTaxon.setScientificname("123123123");
        model.addAttribute("thisTaxon",thisTaxon);
        Dataset thisDataset = new Dataset();
        Taxaset thisTaxaset = new Taxaset();
        model.addAttribute("thisDataset", thisDataset);
        model.addAttribute("thisTaxaset", thisTaxaset);
        return "taxon/add";
    }
 
}
