package org.big.common;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * Created by WangTianshan on 2017/9/12.
 */
public final class QueryTool {
    public static Sort buildSort(String sortRow, String sortType){
        Sort sort = null;
        if("asc".equals(sortType)) {
            sort = new Sort(Sort.Direction.ASC, sortRow);
        } else {
            sort = new Sort(Sort.Direction.DESC, sortRow);
        }
        return sort;
    }

    public static PageRequest buildPageRequest(int offsetNumber, int pageSize, String sortRow, String sortType){
        int pageNumber=offsetNumber/pageSize;
        Sort sort = null;
        if("asc".equals(sortType)) {
            sort = new Sort(Sort.Direction.ASC, sortRow);
        } else {
            sort = new Sort(Sort.Direction.DESC, sortRow);
        }
        return new PageRequest(pageNumber, pageSize, sort);
    }
}
