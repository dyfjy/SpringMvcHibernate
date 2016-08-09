package com.alexgaoyh.test.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alexgaoyh.common.action.BaseController;
import com.alexgaoyh.test.entity.Test;
import com.alexgaoyh.test.service.TestService;
import com.alexgaoyh.common.service.BaseService;
import com.alexgaoyh.common.util.JsonUtil;
import com.alexgaoyh.redis.util.RedisClient;

/**
 * 
 * @desc 测试action控制类
 *
 * @author dyf
 * @Tue Aug 09 13:18:14 CST 2016
 */
@Controller
@RequestMapping(value = "test")
public class TestAction extends BaseController<Test> {

	private static final Logger LOGGER = Logger.getLogger(Test.class);

	@Override
	@Resource(name = "testServiceImpl")
	public void setBaseService(BaseService<Test> baseService) {
		this.baseService = baseService;
	}

	
	@Resource
	private RedisClient<String, String> redisClient;
	
	@RequestMapping(value = "login")
	public ModelAndView login() {
		// 发送邮件demo
		// EmailUtil.send("subject", "content", "924099504@qq.com");
		// redisClient.add("aaaa", "aaaa");
		// System.out.println(redisClient.get("aaaa"));
		System.out.println("sws");
		return new ModelAndView("views/test");
	}

	@RequestMapping(value = "login1")
	public ModelAndView login1(@RequestParam(value = "error", required = false) boolean error, ModelMap model) {
		// 发送邮件demo
		// EmailUtil.send("subject", "content", "924099504@qq.com");
		// redisClient.add("aaaa", "aaaa");
		// System.out.println(redisClient.get("aaaa"));
		System.out.println("model");
		model.put("test", "2222");
		return new ModelAndView("views/test");
	}

	/**
	 * 跳转到manager页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public ModelAndView manager() {

		Map<String, Object> map = new HashMap<String, Object>();
		Test te = new Test();
		te.setName("dyf");
		map.put("sysmanUser", te);

		return new ModelAndView("views/test", map);

	}

	@RequestMapping(value = "test")
	@ResponseBody
	public String saveEntity() throws Exception {
		Test te = new Test();
		te.setName("dyf");
		baseService.saveOrUpdate(te);
		System.out.println(JSONObject.valueToString(te));
		return JsonUtil.object2String(te);
	}
	@RequestMapping(value = "getOne")
	@ResponseBody
	public String getOne(@RequestParam(value = "pid", required = true) Integer pid) throws Exception {
		Test te = baseService.get(pid);
		System.out.println(JSONObject.valueToString(te));
		return JsonUtil.object2String(te);
	}
	
	
	@RequestMapping(value = "testRedis")
	public void testRedis(){
		redisClient.add("dyf", "23423423");
		System.out.println(redisClient.get("dyf"));
	}

}
