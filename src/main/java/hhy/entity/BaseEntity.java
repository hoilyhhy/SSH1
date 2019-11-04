package hhy.entity;

import javax.persistence.*;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Date;
@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(unique=true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(updatable = false)
    private Date createtime;

    @Column(updatable = false)
    private String creater;

    @Column
    private Date lasthandledatetime;

    @Column
    private String lasthandleuser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLasthandledatetime() {
        return lasthandledatetime;
    }

    public void setLasthandledatetime(Date lasthandledatetime) {
        this.lasthandledatetime = lasthandledatetime;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public void setLasthandleuser(String lasthandleuser) {
        this.lasthandleuser = lasthandleuser;
    }

    public BaseEntity() {
    }

    public String getCreater() {
        return creater;
    }

    public String getLasthandleuser() {
        return lasthandleuser;
    }
}
