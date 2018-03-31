package com.hbf.sys;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;


@Component
public class UserRealm extends AbstractUserRealm {

	@Override
	public UserRolesAndPermissions doGetGroupAuthorizationInfo(Operator userInfo) {
		Set<String> userRoles = new HashSet<>();
        Set<String> userPermissions = new HashSet<>();
        //TODO 获取当前用户下拥有的所有角色列表,及权限
        return new UserRolesAndPermissions(userRoles, userPermissions);
	}

	@Override
	public UserRolesAndPermissions doGetRoleAuthorizationInfo(Operator userInfo) {
		Set<String> userRoles = new HashSet<>();
        Set<String> userPermissions = new HashSet<>();
        //TODO 获取当前用户下拥有的所有角色列表,及权限
        return new UserRolesAndPermissions(userRoles, userPermissions);
	}

}
