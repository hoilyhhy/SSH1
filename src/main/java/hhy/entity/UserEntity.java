package hhy.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sysuser")
public class UserEntity {
    @Id
    @Column(unique=true,nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int uid;

    @Column(unique=true,nullable = false)
    private String loginname;

    @Column(nullable = false)
    private String username;

    @Column
    private String password;

    @Column(nullable = false)
    private String type;//类型：0管理员、2房东、3租客

    @Column(nullable = false)
    private Date createtime;

    @Column(nullable = false)
    private String handleusername;

    @ManyToMany
    private List<BuildingEntity> buildings = new ArrayList<BuildingEntity>();

    public UserEntity() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getHandleusername() {
        return handleusername;
    }

    public void setHandleusername(String handleusername) {
        this.handleusername = handleusername;
    }

}
