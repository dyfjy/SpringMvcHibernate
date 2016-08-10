package com.github.zhangkaitao.shiro.chapter16.web.shiro.filter;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;

import com.alexgaoyh.sysman.admin.service.SysmanUserService;
import com.github.zhangkaitao.shiro.chapter16.Constants;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-15
 * <p>Version: 1.0
 */
public class SysUserFilter extends PathMatchingFilter {

	@Resource(name = "sysmanUserServiceImpl")
	private SysmanUserService  sysmanUserService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        String username = (String)SecurityUtils.getSubject().getPrincipal();
        request.setAttribute(Constants.CURRENT_USER, sysmanUserService.findByName(username));
        return true;
    }
}
