package org.omgcms.core.repository;

import org.omgcms.core.model.ResourceAction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 0:24 2018/5/30
 * @Modified By
 */
@Repository
public interface ResourceActionRepository extends JpaSpecificationExecutor<ResourceAction>, JpaRepository<ResourceAction, Long> {

    List<ResourceAction> findByResourceName(String resourceName);

    Page<ResourceAction> findByType(String type, Pageable pageable);

    List<ResourceAction> findDistinctResourceNameByType(String type);

}
