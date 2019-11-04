package hhy.controller;

import hhy.entity.BuildingEntity;
import hhy.entity.UserEntity;
import hhy.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Controller
public class UsersController {
    @Autowired
    UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @RequestMapping("/usersinfo.do")
    public ModelAndView usersInfo(){
        ModelAndView mv = new ModelAndView();
        List<UserEntity> userEntityList =  userService.usersInfo();;
        mv.addObject("userEntityList",userEntityList);
        mv.setViewName("user/usersinfo");
        return mv;
    }

    @ResponseBody
    @RequestMapping("/getUserinfo")
    public UserEntity userInfo(int id){
        UserEntity user = userService.userInfo(id);
        logger.info("这个用户的名字是:{},密码是{}" , user.getUsername(), user.getPassword());
        logger.error("这个只是个测试:{}" , user.getUsername());
        List<Integer> bids = new ArrayList<Integer>();
        for(BuildingEntity bd:user.getBuildings()){
            bids.add(bd.getId());
        }
        user.setBid(bids);
        return  user;

    }
    @RequestMapping("/updateuserinfo")
    public String updateuserinfo(UserEntity userEntity, HttpServletRequest req){

        UserEntity handleUser = (UserEntity) req.getSession().getAttribute("user");
        userEntity.setLasthandleuser(handleUser.getUsername());
        userEntity.setLasthandledatetime(new Date());
        userService.updateuserinfo(userEntity);
        return "forward:/usersinfo.do";
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public int deleteUser(int id){
        return  userService.deleteUser(id);
    }

    @ResponseBody
    @RequestMapping("/checkloginname")
    public boolean checkloginname(String loginname){
        return userService.checkloginname(loginname);
    }
}
