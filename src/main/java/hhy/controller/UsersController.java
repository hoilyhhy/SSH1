package hhy.controller;

import hhy.entity.UserEntity;
import hhy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
@Controller
public class UsersController {
    @Autowired
    UserService userService;


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
    public UserEntity userInfo(int uid){
        return  userService.userInfo(uid);

    }
    @RequestMapping("/updateuserinfo")
    public void updateuserinfo(UserEntity userEntity, HttpServletRequest req){

        userEntity.setCreatetime(new Date());
        UserEntity handleUser = (UserEntity) req.getSession().getAttribute("user");
        userEntity.setHandleusername(handleUser.getUsername());
        userService.updateuserinfo(userEntity);
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public int deleteUser(int uid){
        return  userService.deleteUser(uid);
    }

    @ResponseBody
    @RequestMapping("/checkloginname")
    public boolean checkloginname(String loginname){
        return userService.checkloginname(loginname);
    }
}
