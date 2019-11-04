package hhy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "building")
public class BuildingEntity extends BaseEntity implements Serializable {

    @Column(unique = true,nullable = false)
    private String bno;//编号

    @Column(unique = true,nullable = false)
    private String addr;//地址

    @Column
    private int floors;//楼层

    @Column
    private String type;//1高档房，2普通房，3混合

    @Column(nullable = true)
    @ManyToMany(targetEntity=UserEntity.class,mappedBy = "buildings",fetch = FetchType.EAGER)
    /*@JoinTable(name="user_building",joinColumns = {@JoinColumn(name="user_id")})*/
    private List<UserEntity> users = new ArrayList<UserEntity>();//所属房东，支持多人，多对多

    public String getBno() {
        return bno;
    }

    public void setBno(String bno) {
        this.bno = bno;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public BuildingEntity(String bno, String addr, int floors, String type) {
        this.bno = bno;
        this.addr = addr;
        this.floors = floors;
        this.type = type;
    }

    public BuildingEntity(String bno, String addr, int floors, String type, List<UserEntity> users) {
        this.bno = bno;
        this.addr = addr;
        this.floors = floors;
        this.type = type;
        this.users = users;
    }

    public BuildingEntity() {
    }
}
