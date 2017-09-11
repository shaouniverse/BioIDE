package org.big.repository.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * Created by WangTianshan on 2017/9/6.
 */
//@NoRepositoryBean
//public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
//}

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>,PagingAndSortingRepository<T, ID> {
}
