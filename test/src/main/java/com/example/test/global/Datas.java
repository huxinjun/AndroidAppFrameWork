package com.example.test.global;

import com.app.annotation.Description;
import com.app.annotation.request.Dialog;
import com.app.annotation.request.Param;
import com.app.annotation.request.Params;
import com.app.annotation.request.Parser;
import com.app.annotation.request.RequestUrl;
import com.app.annotation.request.RequestUrlsPackage;
import com.app.annotation.request.UseDiscCache;
import com.app.presenter.impl.parser.JsonParser;
import com.app.test.TestDialog;

@RequestUrlsPackage(RequestUrls.class)
public class Datas {

	@Description("获取账单列表")
	@Params({@Param(key="TEST")})
	@RequestUrl(RequestUrls.PATTERN_ACCOUNTS_GET_ALL)
	@Dialog(TestDialog.class)
	@Parser(JsonParser.class)
	@UseDiscCache
	public static final String data_account_list = "get_all";
}
