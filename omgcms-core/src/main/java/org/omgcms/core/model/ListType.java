package org.omgcms.core.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 0:23 2018/7/27
 * @Modified By
 */
@Table(name = "listtype", uniqueConstraints = {
        @UniqueConstraint(name = "listType_Unique", columnNames = {"name", "type_"})})
@Entity
public class ListType  implements Serializable {

    @TableGenerator(name = "ID_GENERATOR", table = "idgenerator", initialValue = 1000, allocationSize = 1,
            pkColumnName = "name", pkColumnValue = "listTypeId", valueColumnName = "value")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_GENERATOR")
    @Id
    private Long listTypeId;

    private String name;

    @Column(name="type_")
    private String type;

    public Long getListTypeId() {
        return listTypeId;
    }

    public void setListTypeId(Long listTypeId) {
        this.listTypeId = listTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
