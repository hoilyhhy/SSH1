package hhy.controller;



import hhy.entity.UserEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import hhy.service.UserService;


import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 类上加上@SessionAttributes({"username"}) 同时也会存放在 session域中
 * @SessionAttributes 除了可以通过属性名指定需要存放到会话中的属性外(使用的是value属性值)
 * 还可以通过模型属性的对象类型指定哪些模型属性需要放到会话中(实际上使用的是types属性值),
 */
//@SessionAttributes(value = "username")
@Controller
public class LoginController {

    private static final Log logger = LogFactory.getLog(LoginController.class);

    @Resource
    private UserService userService;

    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    public ModelAndView login(@RequestParam String loginname,@RequestParam String password/*HttpServletRequest req,Map<String, Object> map*/,HttpServletRequest req) throws ServletException{
        logger.info("开始验证：验证是否能登录");
        ModelAndView mv = new ModelAndView();
     //  mv.setViewName("/login/loginin.jsp");

        UserEntity userEntity =  userService.checklogin(loginname,password);
        if(userEntity == null){
            mv.setViewName("redirect:/index.jsp");
            mv.addObject("msg","登录名或密码错误");
           logger.info("登录fail");
        }else{
            mv.setViewName("login/loginin");
            mv.addObject("userEntity",userEntity);
            mv.addObject("msg","登录success");
            req.getSession().setAttribute("user",userEntity);
           // map.put("username", username);//存放在request请求域中
            logger.info("登录success");
        }
        return mv;
    }


    public ModelAndView insert(){
        return null;
    }

}
