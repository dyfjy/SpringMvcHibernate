package com.alexgaoyh.admin.login.shiro.common;

import org.apache.shiro.crypto.hash.Md5Hash;
/**
 * Test
 * @author Administrator
 *
 */
public class ShiroMD5Test {

	public static void main(String[] args) {
		Md5Hash md5 = new Md5Hash("admin");
		System.out.println(md5.toHex());
	}

}
