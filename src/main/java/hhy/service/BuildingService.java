package hhy.service;

import hhy.dao.BuildingDao;
import hhy.entity.BuildingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BuildingService {
    @Autowired
    BuildingDao bDao;

    @Transactional
   public void add(BuildingEntity building){
       bDao.add(building);
   }

   @Transactional
    public List<BuildingEntity> getBuilds() {
      return bDao.getBuilds();
    }

    @Transactional
    public BuildingEntity getBuild(int id){
        return bDao.getBuild(id);
    }
}
