package com.alexgaoyh.admin.login.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alexgaoyh.admin.login.shiro.captcha.constant.CaptchaConstant;
import com.alexgaoyh.common.util.JsonUtil;
import com.alexgaoyh.common.vo.Result;
import com.alexgaoyh.common.vo.TreeNode;
import com.alexgaoyh.sysman.admin.entity.SysmanResource;
import com.alexgaoyh.sysman.admin.entity.SysmanUser;
import com.alexgaoyh.sysman.admin.service.SysmanResourceService;
import com.alexgaoyh.sysman.admin.service.SysmanUserService;
import com.github.zhangkaitao.shiro.spring.PasswordHelper;

@Controller
@RequestMapping(value="admin")
public class AdminController {

	private static final Logger LOGGER = Logger.getLogger(AdminController.class);
	
	 private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	@Resource(name = "sysmanResourceServiceImpl")
	private SysmanResourceService sysmanResourceService;
	
	@Resource(name = "sysmanUserServiceImpl")
	private SysmanUserService  sysmanUserService;
	
	/**
	 * 登陆页
	 * @param error
	 * @param model
	 * @return
	 */
	@RequestMapping(value="login")
    public ModelAndView login(@RequestParam(value = "error", required = false) boolean error,ModelMap model){
		
		
		Subject subject = SecurityUtils.getSubject();
		//可以使用 subject.isAuthenticated() 以判断当前用户已经登录过了 此时可以直接通过subject.getSession()去获取我们放入session的信息了。
		SysmanUser user = (SysmanUser) subject.getPrincipal();
		
		
		if(user != null){
			model.put("userName", user.getUserName());
		}
		
		//String literals should not be duplicated
		String errorStr = "error";
		if (error) {
			model.put(errorStr,"You have entered an invalid username or password!");
		} else {
			model.put(errorStr, "");
		}
        return new ModelAndView("views/admin/login");
    }
	
	/**
	 * 指定无访问额权限页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public ModelAndView denied() {
		Subject subject = SecurityUtils.getSubject();
		
		return new ModelAndView("views/admin/denied");
		
	}
	
	/**
	 * 跳转到manager页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public ModelAndView manager() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		Subject subject = SecurityUtils.getSubject();
		SysmanUser user = (SysmanUser) subject.getPrincipal();
		
		map.put("sysmanUser", user);
		
		return new ModelAndView("views/admin/manager", map);

	}
	
	/**
	 * 后台shiro登陆方法
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public ModelAndView doLogin(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		
		boolean loginStatus = false;
		boolean captchaStatus = false;
		String loginInfo = "";
		
		String captcha = request.getParameter("Captcha");
		String exitCode = (String) request.getSession().getAttribute(CaptchaConstant.KEY_CAPTCHA);
		
//		if (null != captcha && captcha.equalsIgnoreCase(exitCode)) {
			if (true) {
			captchaStatus = true;
			
			String username = request.getParameter("userName");
			String password = request.getParameter("password");
			String rememberMe = request.getParameter("rememberMe");
			
			SysmanUser user = sysmanUserService.findByName(username);
			if(user == null) {
	            throw new UnknownAccountException();//没找到帐号
	        }
//			Md5Hash md5Hash = new Md5Hash(password);
//		        String newPassword = new SimpleHash(
//		        		"md5",
//		        		password,
//		                ByteSource.Util.bytes(username + user.getSalt()),
//		                2).toHex();
//		        user.setPassword(newPassword);
			
			UsernamePasswordToken token = new UsernamePasswordToken(username, password, Boolean.parseBoolean(rememberMe));
			
			try {
				Subject subject = SecurityUtils.getSubject();
				subject.login(token);
				token.clear();
				user = (SysmanUser) subject.getPrincipal();
				subject.getSession().setAttribute("adminCurrentUser", user);
				loginStatus = true;
				
			} catch (UnknownAccountException ex) {
				LOGGER.info("context", ex); 
				loginInfo = "无此账号";
				
			} catch (IncorrectCredentialsException ex) {
				LOGGER.info("context", ex); 
				loginInfo = "账号验证失败";
			}
			catch (Exception ex) {
				LOGGER.info("context", ex); 
				loginInfo = "登陆异常，请联系管理员";
			}
		}
		
		map.put("loginStatus", loginStatus);
		map.put("captchaStatus", captchaStatus);
		map.put("loginInfo", loginInfo);
		
		ModelAndView mav = new ModelAndView("views/admin/index", map);

		return mav;
	}
	 public void encryptPassword(SysmanUser user) {

	        user.setSalt(randomNumberGenerator.nextBytes().toHex());

	        String newPassword = new SimpleHash(
	        		"md5",
	                user.getPassword(),
	                ByteSource.Util.bytes(user.getCredentialsSalt()),
	                2).toHex();

	        user.setPassword(newPassword);
	    }
	
	/**
	 * shiro 登出方法
	 * @return
	 */
	@RequestMapping(value="logout")
	public ModelAndView doLogout() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			//"登出用户：" + ((SysmanUser) subject.getPrincipal()).getUserName()
			// session 会销毁，在SessionListener监听session销毁，清理权限缓存
			subject.logout(); 
		}
		
		return new ModelAndView("views/admin/login");
	}
	
	
	/**
	 * 获取惨淡数据
	 * @return
	 */
	@RequestMapping(value="getMenus")
    @ResponseBody
	public String getMenus() {
		
		Subject subject = SecurityUtils.getSubject();

		List<SysmanResource> sysmanResourceList = sysmanResourceService.getRootResourceList();
		
		return JSONObject.valueToString(resourceToTreeNode(sysmanResourceList));
	}
	
	/**
	 * 将SysmanResource类型的数据集合转化为前端较好识别的TreeNode
	 * @param resource
	 * @return
	 */
	private List<TreeNode> resourceToTreeNode(List<SysmanResource> resource) {

		//Use isEmpty() to check whether the collection is empty or not.
		if (resource != null && !resource.isEmpty() && resource.get(0).getResourceType() == SysmanResource.TYPE_MENU) {
			List<TreeNode> ch = new ArrayList<TreeNode>();
			for (SysmanResource rr : resource) {
				
				TreeNode node = new TreeNode();
				
				if(rr.getHref()==null){
					node.setId(rr.getPid());
				}else{
					node.setId(rr.getPid());
				}
				
				node.setState("open");
				node.setText(rr.getName());
				
				Map<String, Object> _temp = new HashMap<String, Object>();
				_temp.put("href", rr.getHref());
				node.setAttributes(_temp);
				
				ch.add(node);
				node.setChildren(resourceToTreeNode(rr.getSubResource()));
			}

			return ch;
		}
		//Empty arrays and collections should be returned instead of null
		return Collections.emptyList();
	}
	
	/**
	 * 权限判断
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="permissionsCheck")
    @ResponseBody
	private void permissionsCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//Define and throw a dedicated exception instead of using a generic one.
		Result result = null;
		
		String url = request.getParameter("url");
		
		//权限校验。判断是否包含权限。
		Subject subject = SecurityUtils.getSubject();
		//具体响应ShiroDbRealm。doGetAuthorizationInfo，判断是否包含此url的响应权限
		boolean isPermitted = subject.isPermitted(url);
		if(isPermitted) {
			result = new Result(true, "包含权限");
		}else{
			result = new Result(false, "不包含权限");
		}
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.write(JsonUtil.object2String(result)) ;
		writer.flush();
	}
}
