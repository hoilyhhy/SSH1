package hhy.dao;

import hhy.entity.UserEntity;
import hhy.utils.ConnectionMamager;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import hhy.utils.DbHelp;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


@Repository
public class UserDao /*extends HibernateDaoSupport*/{
    //HibernateDaoSupport来操作数据库更加方便,Spring为HibernateDaoSupport类提供了setSessionFactory方法，我们将通过这个setter方法向DAO类注入SessionFactory，因此无需在dao中注入sessionFactory。。？？？

    //注入已在spring.xml中配制好的sessionFactory
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    /*public Session getSession() {
        return sessionFactory.getCurrentSession();
    }*/

    public UserEntity checklogin(String loginname,String password) {
        Query query =sessionFactory.getCurrentSession().createQuery("from UserEntity where loginname=? and password=?");
        query.setParameter(0,loginname);
        query.setParameter(1,password);
        return  (UserEntity) query.uniqueResult();

       /* List<?> users = getHibernateTemplate().find("from UserEntity where loginname=? and password=?",loginname,password);
        return users.size()>0?(UserEntity) users.get(0):null;//假数据，后期要通过Hibernate去数据库取*/


     //   String sql = "select * from userEntity where loginname=? and password=?";
       // ResultSet rs = dbHelp.execSQL(sql,loginname,password);
     //   UserEntity userEntity = new UserEntity();
       /* try {
            if (rs.next()){
                int uid = rs.getInt("uid");
                String username = rs.getString("username");
                String type=rs.getString("type");
                Date createtime=rs.getDate("createtime");
                String handleusername=rs.getString("handleusername");
                userEntity.setCreatetime(createtime);
                userEntity.setHandleusername(handleusername);
                userEntity.setLoginname(loginname);
                userEntity.setPassword(password);
                userEntity.setType(type);
                userEntity.setUid(uid);
                userEntity.setUsername(username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    //    return userEntity;
    }

    public List<UserEntity> usersInfo(){
        Query q = sessionFactory.getCurrentSession().createQuery("from UserEntity");
        List<UserEntity> userEntityList = q.list();
        return userEntityList;
    }


    public UserEntity userInfo(int uid){
        Query query = sessionFactory.getCurrentSession().createQuery("from UserEntity where uid=?");
        query.setParameter(0,uid);
        return (UserEntity) query.uniqueResult();
    }

    public void updateuserinfo(UserEntity userEntity){
        /*if(userEntity.getUid() >0) {
            sessionFactory.getCurrentSession().update(userEntity);
        }else{
           // userEntity.setUid(null);
            sessionFactory.getCurrentSession().save(userEntity);
        }*/
        sessionFactory.getCurrentSession().saveOrUpdate(userEntity);
    }

    public int deleteUser(int uid) {
        Query query = sessionFactory.getCurrentSession().createQuery("delete UserEntity where uid=?");
        query.setInteger(0,uid);
       return  query.executeUpdate();

    }

    public boolean checkloginname(String loginname) {
       boolean re = true;
        Query query =sessionFactory.getCurrentSession().createQuery("from UserEntity where loginname=?");
        query.setParameter(0,loginname);
        if(query.uniqueResult() == null){
            re = false;
        }
        return re;
    }
}
