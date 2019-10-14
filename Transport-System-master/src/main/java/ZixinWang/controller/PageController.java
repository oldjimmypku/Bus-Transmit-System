package Guxinyu.controller;

import Guxinyu.bean.SUser;
import Guxinyu.service.SecurityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import Guxinyu.service.MapDataService;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 请求页面分发，注意和WebMvcConfig的对比，功能类似
 * @author Veiking
 */
@Controller
public class PageController {
	@Autowired
	SecurityDataService securityDataService;



	
	@RequestMapping("/admin")
	@PreAuthorize("hasAuthority('R_ADMIN')")
	public String admin(Model model, String tt) {
		return "admin";
	}

	@RequestMapping("/user/new")
	public String test(Model model, String tt) {
		return "user/new";
	}
	
	@RequestMapping("/hello")
	public String hello(Model model, String tt) {
		return "hello";
	}
	@RequestMapping("/register")
	public String re(Model m) {
		return "register";
	}

	@RequestMapping("/register/user")
	public String register(Model model, String userName,String password) {
		SUser sUser=securityDataService.findSUserByName(userName);
		//System.out.println(sUser.toString());
		System.out.println(sUser);

		if (sUser==null){
			securityDataService.addUser(userName,password);
			model.addAttribute("error","注册成功！三秒钟自动跳转登陆页面");
			return "register/success";
		}

		else{
			model.addAttribute("error","用户名已被占用，请重新注册!三秒钟自动跳转注册页面");
			return "register/error";
		}

	}


	/**
	 *        @ResponseBody
	 * 	@RequestMapping(value = "announcement/user",method = RequestMethod.GET)
	 * 	public String announcementRead(){
	 * 		return announcementList.toString();
	 * 	}
	 *
	 * 	@ResponseBody
	 * 	@RequestMapping(value = "announcement/admin",method = RequestMethod.GET)
	 * 	public String announcementModify(){
	 * 		return announcementList.toString();
	 * 	}
	 * @return
	 */


}
