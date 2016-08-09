package com.alexgaoyh.admin.login.shiro.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.alexgaoyh.sysman.admin.entity.SysmanResource;
import com.alexgaoyh.sysman.admin.entity.SysmanRole;
import com.alexgaoyh.sysman.admin.entity.SysmanUser;
import com.alexgaoyh.sysman.admin.service.SysmanUserService;

public class ShiroDbRealm extends AuthorizingRealm {
	
	@Resource
	private SysmanUserService sysmanUserService;
	
	
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SysmanUser user = (SysmanUser) principals.fromRealm(getName()).iterator().next();
		user = this.sysmanUserService.get(user.getPid());
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		List<SysmanRole> roles = user.getRoles();
		
		
		Set<String>  permissions = new HashSet<String>();
		
		for(SysmanRole role : roles ){
			info.addRole(role.getName());
			for(SysmanResource resource : role.getResource()){
				if(SysmanResource.TYPE_MENU == resource.getResourceType() ){
					setMenuPerms(permissions, resource.getParent());
					permissions.add(resource.getHref());
				}
			}
		}
		
		info.addStringPermissions(permissions);
		
		return info;
	}
	
	private void setMenuPerms(Set<String> permissions, SysmanResource resource) {
		if(resource != null) {
			permissions.add(resource.getHref());
			if(resource.getParent()!=null){
				setMenuPerms(permissions, resource.getParent());
			}
		}
	}

	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToke = (UsernamePasswordToken)token;
		
		String username = usernamePasswordToke.getUsername();
		
		SysmanUser user  = sysmanUserService.findByName(username);
		
		sysmanUserService.evict(user);
		//user.setRoles(null);
		
		if(user==null){
			throw new  UnknownAccountException("用户帐号不存在！");
		}
		
		if(user.getStatus()!=SysmanUser.STATUS_NORMAL){
			throw new  LockedAccountException("用户帐号不正常，请联系系统管理员！");
		}
		
		 //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
//		return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getPassword()),getName());
		return authenticationInfo;
	}
	
	
	
	
	
	
	
	
	
	
//
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//
//        String username = (String)token.getPrincipal();
//
//        User user = userService.findByUsername(username);
//
//        if(user == null) {
//            throw new UnknownAccountException();//没找到帐号
//        }
//
//        if(Boolean.TRUE.equals(user.getLocked())) {
//            throw new LockedAccountException(); //帐号锁定
//        }
//
//        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
//                user.getUsername(), //用户名
//                user.getPassword(), //密码
//                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
//                getName()  //realm name
//        );
//        return authenticationInfo;
//    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
	

}
