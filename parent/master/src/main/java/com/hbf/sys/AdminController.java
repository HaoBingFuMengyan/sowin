package com.hbf.sys;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping(value="/")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@GetMapping(value="login.html")
	public String login(Model model){
		model.addAttribute("debug","");
		logger.info("欢迎登录");
		return "sys/login";
	}
	
	@PostMapping(value="login.html")
	public String login(@Valid Operator operator,RedirectAttributes redirectAttributes,BindingResult bindingResult){
		 if (bindingResult.hasErrors()) {
	            return "sys/login";
	        }
	        String loginName = operator.getSusername();
	        logger.info("准备登陆用户 => {}", loginName);
	        UsernamePasswordToken token = new UsernamePasswordToken(loginName,operator.getSpassword());
	        //获取当前的Subject
	        Subject currentUser = SecurityUtils.getSubject();
	        try {
	            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
	            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
	            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
	            logger.info("对用户[" + loginName + "]进行登录验证..验证开始");
	            currentUser.login(token);
	            logger.info("对用户[" + loginName + "]进行登录验证..验证通过");
	        } catch (UnknownAccountException uae) {
	            logger.info("对用户[" + loginName + "]进行登录验证..验证未通过,未知账户");
	            redirectAttributes.addFlashAttribute("message", "未知账户");
	        } catch (IncorrectCredentialsException ice) {
	            logger.info("对用户[" + loginName + "]进行登录验证..验证未通过,错误的凭证");
	            redirectAttributes.addFlashAttribute("message", "密码不正确");
	        } catch (LockedAccountException lae) {
	            logger.info("对用户[" + loginName + "]进行登录验证..验证未通过,账户已锁定");
	            redirectAttributes.addFlashAttribute("message", "账户已锁定");
	        } catch (ExcessiveAttemptsException eae) {
	            logger.info("对用户[" + loginName + "]进行登录验证..验证未通过,错误次数过多");
	            redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
	        } catch (AuthenticationException ae) {
	            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
	            logger.info("对用户[" + loginName + "]进行登录验证..验证未通过,堆栈轨迹如下");
	            ae.printStackTrace();
	            redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
	        }
	        //验证是否登录成功
	        if (currentUser.isAuthenticated()) {
	            logger.info("用户[" + loginName + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
	            return "redirect:/index.shtml";
	        } else {
	            token.clear();
	            return "redirect:/login.html";
	        }
	}
	/**
	 * 退出
	 * @return
	 */
	@GetMapping(value = "logout.shtml")
	public String logout() {
		return "sys/login";
	}
	/**
	 * 首页
	 * @param model
	 * @param request
	 * @return
	 */
	@GetMapping(value="index.shtml")
	public String index(Model model,HttpServletRequest request){
		return "sys/index";
	}
	@GetMapping(value = "main.shtml")
	public String main(Model model,HttpServletRequest request){
		return "sys/main";
	}


	@RequestMapping(value = "news/newsList.shtml")
	public String newsList(Model model,HttpServletRequest request){
		return "sys/newsList";
	}
}
