package hhy.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name="UserEntity")
@Table(name = "sysuser")
public class UserEntity  extends BaseEntity implements Serializable {

    @Column(unique=true,nullable = false)
    private String loginname;

    @Column(nullable = false)
    private String username;

    @Column
    private String password;

    @Column(nullable = false)
    private String type;//类型：0管理员、2房东、3租客

    /*用jsonigonre，user对象json后无法获取buildings信息。不用jsonignore，报延迟加载的错,加上立即加载FetchType.EAGER，循环引用，该如何取舍？希望两边都能关联到对方的信息及保存*/
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore/*现在java拿到buildings，但不会序列化过去*/
    private List<BuildingEntity> buildings = new ArrayList<BuildingEntity>();

    @Transient
    private List<Integer> bid = new ArrayList<Integer>();

    public List<Integer> getBid() {
        return bid;
    }

    public void setBid(List<Integer> bid) {
        this.bid = bid;
    }

    public UserEntity() {
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBuildings(List<BuildingEntity> buildings) {
        this.buildings = buildings;
    }

    public List<BuildingEntity> getBuildings() {
        return buildings;
    }


}
