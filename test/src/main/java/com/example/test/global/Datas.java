package com.example.test.global;

import com.app.annotation.Description;
import com.app.annotation.request.Dialog;
import com.app.annotation.request.EntityType;
import com.app.annotation.request.Param;
import com.app.annotation.request.Params;
import com.app.annotation.request.Parser;
import com.app.annotation.request.RequestUrl;
import com.app.annotation.request.RequestUrls;
import com.app.annotation.request.UseDiscCache;
import com.app.presenter.impl.parser.JsonParser;
import com.example.test.dialog.LoadingDialog;
import com.example.test.model.Accounts;
import com.example.test.model.Rooms;

@RequestUrls(com.example.test.global.RequestUrls.class)
public class Datas {

	@Description("获取账单列表")
	@Params({@Param(key="TEST")})
	@EntityType(Accounts.class)
	@RequestUrl(com.example.test.global.RequestUrls.PATTERN_ACCOUNTS_GET_ALL)
	@Dialog(LoadingDialog.class)
	@Parser(JsonParser.class)
	@UseDiscCache
	public static final String data_account_list = "get_all";



	@Description("获取房间列表")
	@EntityType(Rooms.class)
	@RequestUrl(com.example.test.global.RequestUrls.PATTERN_HOT_ROOM)
	@Dialog(LoadingDialog.class)
	@Parser(JsonParser.class)
	@UseDiscCache
	public static final String data_room_list = "rooms";
}
