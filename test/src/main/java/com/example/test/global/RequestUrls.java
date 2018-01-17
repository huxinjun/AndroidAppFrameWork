package com.example.test.global;

import com.app.annotation.Description;
import com.app.annotation.request.AccessSettings;
import com.app.annotation.request.AccessSettings.RequestMethods;
import com.app.annotation.request.ConnectionSet;
import com.app.annotation.request.LocalJsonPackage;
import com.app.annotation.request.MappingLocalJson;
import com.app.annotation.request.RequestBaseUrl;

@RequestBaseUrl("https://xzbenben.cn/AccountBook")
@LocalJsonPackage("")
public class RequestUrls {

	@MappingLocalJson(useTempleteJson = false,fileName = "")
	@AccessSettings(accessMethod = RequestMethods.GET,persistence = false)
	@ConnectionSet(connectionTimeOut=5000,serverTimeOut=1000,retryCount=3)
	public static final String PATTERN_ACCOUNTS_GET_ALL = "/account/getAll";


	@MappingLocalJson(useTempleteJson = false,fileName = "")
	@AccessSettings(accessMethod = RequestMethods.GET,persistence = false)
	@ConnectionSet(connectionTimeOut=5000,serverTimeOut=1000,retryCount=3)
	@RequestBaseUrl("")
	public static final String PATTERN_HOT_ROOM = "http://api.mengliaoba.cn/apiv5/live/liveshow.php?cmd=hotroom";
}
