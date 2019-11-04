package hhy.dao;

import hhy.entity.BuildingEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Repository
public class BuildingDao extends HibernateDaoSupport {

    @Resource(name="sessionFactory")
    private void setMySessionFactory(SessionFactory sessionFactory){
        //这个方法名可以随便写，@Resource可以通过name 或者type来装载的。
        super.setSessionFactory(sessionFactory);
    }

    public void add(BuildingEntity building){
        //Session session = sessionFactory.getCurrentSession();
        //getHibernateTemplate().find("from Building where loginname=? and password=?",loginname,password);
        getHibernateTemplate().saveOrUpdate(building);
    }

    public   List<BuildingEntity> getBuilds() {
        return  (List<BuildingEntity>) getHibernateTemplate().find("from BuildingEntity");
    }

    public BuildingEntity getBuild(int id){
        return getHibernateTemplate().get(BuildingEntity.class,id);
    }

    public boolean checkbno(String bno){
        boolean rs = true;//存在
        List<BuildingEntity> Buildings =  (List<BuildingEntity>)getHibernateTemplate().find("from BuildingEntity where bno="+bno);
        if(Buildings.size()<= 0){
            rs = false;
        }
        return rs;
    }
}
