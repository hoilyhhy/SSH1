package hhy.service;

import hhy.dao.UserDao;
import hhy.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@Service
public class UserService {

    @Autowired
    UserDao userDao ;

    @Transactional
    public UserEntity checklogin(@RequestParam String loginname,@RequestParam String password) {

        ModelAndView mv = new ModelAndView();

        UserEntity userEntity = userDao.checklogin(loginname,password);

        return userEntity;
    }

    //public UserEntity savelogin(@RequestParam S)

    @Transactional
    public List<UserEntity> usersInfo(){

        return userDao.usersInfo();
    }

    @Transactional
    public UserEntity userInfo(int uid){
        return userDao.userInfo(uid);
    }

    @Transactional
    public void updateuserinfo(UserEntity userEntity){
        userDao.updateuserinfo(userEntity);
    }

    @Transactional
    public int deleteUser(int uid) {
        return  userDao.deleteUser(uid);
    }

    @Transactional
    public boolean checkloginname(String loginname) {
        return userDao.checkloginname(loginname);
    }
}
