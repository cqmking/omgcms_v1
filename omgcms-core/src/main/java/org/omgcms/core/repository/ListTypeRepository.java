package org.omgcms.core.repository;

import org.omgcms.core.model.ListType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 0:42 2018/7/27
 * @Modified By
 */
@Repository
public interface ListTypeRepository extends JpaSpecificationExecutor<ListType>, JpaRepository<ListType, Long> {

    List<ListType> findAllByType(String type);

}
