package org.omgcms.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 17:31 2018/4/5
 * @Modified By
 */
@Table(name = "user_")
@Entity
public class User implements Serializable {

    @TableGenerator(name = "ID_GENERATOR", table = "idgenerator", initialValue = 1000, allocationSize = 1,
            pkColumnName = "name", pkColumnValue = "userId", valueColumnName = "value")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_GENERATOR")
    @Id
    private Long userId;

    @Column(unique = true, nullable = false)
    private String screenName;

    private String userName;

    @JsonIgnore
    private String password;

    private String sex;

    @Column(unique = true, nullable = false)
    private String email;

    private String jobTitle;    //职位

    private Integer age;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    private String phone;

    private Date lastLoginDate;

    @Column(length = 1024)
    private String description;

    private String address;

    private String salt;

    private Date createDate;

    private Date modifyDate;

//    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
//    @JoinTable(name = "users_roles", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "userId")},
//            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName ="roleId")})
//    private Set<Role> roles;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private Set<UserRole> userRoles;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", screenName='" + screenName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", phone='" + phone + '\'' +
                ", lastLoginDate=" + lastLoginDate +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", salt='" + salt + '\'' +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                '}';
    }
}
