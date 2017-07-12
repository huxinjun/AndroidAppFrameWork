package com.app.test;

import com.app.annotation.Description;
import com.app.annotation.request.Dialog;
import com.app.annotation.request.Param;
import com.app.annotation.request.Params;
import com.app.annotation.request.Parser;
import com.app.annotation.request.RequestUrl;
import com.app.annotation.request.RequestUrlsPackage;
import com.app.annotation.request.UseDiscCache;
import com.app.presenter.impl.parser.JsonParser;

@RequestUrlsPackage("com.app.test.RequestUrlPattener")
public class Datas {

	@Description("获取验证码")
	@Params({@Param(key="bbb")})
	@RequestUrl(RequestUrlPattener.PATTERN_GET_CODE)
	@Dialog(TestDialog.class)
	@Parser(JsonParser.class)
	@UseDiscCache
	public static final String DATA_GET_CODE = "get_valid_code";
}
