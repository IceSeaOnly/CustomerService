package Controllers;

import Entity.User;
import Service.CommonService;
import Utils.MD5;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/1/25.
 */
@Controller
@RequestMapping("auth")
public class Auth {

    @Resource
    CommonService commonService;

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(@RequestParam String email,
                        @RequestParam String pass,
                        HttpSession session,
                        ModelMap map){
        if(email.length()<1 || pass.length()<1){
            map.put("err","输入不完整");
            return "index";
        }
        User user = commonService.getUserByEmailPass(email, MD5.encryption(pass));
        if(user == null){
            map.put("err","用户名/密码错误");
            return "index";
        }
        session.setAttribute("user",user);
        return "redirect:main.do";
    }

    @RequestMapping("main")
    public String main(HttpSession session,ModelMap map){
        User u = (User) session.getAttribute("user");
        if(u == null)
            return "redirect:/index.jsp";
        map.put("user",u);
        return "main";
    }

    @RequestMapping(value = "reg",method = RequestMethod.POST)
    public String reg(
            @RequestParam String email,
            @RequestParam String name,
            @RequestParam String pass,
            @RequestParam String confirm_pass,
            ModelMap map
                      ){
        if(email.length()<1 ||name.length()<1||pass.length()<1||confirm_pass.length()<1){
            map.put("err","输入不完整");
            return "index";
        }
        if(!pass.equals(confirm_pass)){
            map.put("err","两次密码不一致");
            return "index";
        }
        if(!checkEmail(email)){
            map.put("err","邮箱格式错误");
            return "index";
        }
        if(!commonService.uniqueEmail(email)){
            map.put("err","邮箱已被注册");
            return "index";
        }
        String appkey = MD5.encryption(String.valueOf(System.currentTimeMillis()));
        String secret = MD5.encryption(appkey+name);
        User user = (User) commonService.merge(new User(name,email.trim(),MD5.encryption(pass),appkey,secret));
        map.put("user",user);
        return "main";
    }

    /**
     * 验证邮箱
     * @param email
     * @return
     */
    public static boolean checkEmail(String email){
        boolean flag = false;
        try{
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }
}
