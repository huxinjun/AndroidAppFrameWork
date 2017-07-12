package com.app.test;

import com.app.annotation.request.AccessSettings;
import com.app.annotation.request.ConnectionSet;
import com.app.annotation.request.LocalJsonPackage;
import com.app.annotation.request.MappingLocalJson;
import com.app.annotation.request.RequestBaseUrl;
import com.app.annotation.request.AccessSettings.RequestMethods;
import com.app.annotation.Description;

@RequestBaseUrl("xxx")
@LocalJsonPackage("com.xxx.xxx")
public class RequestUrlPattener {

	@Description("获取登陆验证码")
	@MappingLocalJson(useTempleteJson = false,fileName = "")
	@AccessSettings(accessMethod = RequestMethods.GET,persistence = false)
	@ConnectionSet(connectionTimeOut=5000,serverTimeOut=1000,retryCount=3)
	public static final String PATTERN_GET_CODE = "/index.php/member1/register/captcha";
}
