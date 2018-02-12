package com.example.test.global;

import com.app.annotation.Description;
import com.app.annotation.request.AccessSettings;
import com.app.annotation.request.AccessSettings.RequestMethods;
import com.app.annotation.request.ConnectionSet;
import com.app.annotation.request.Dialog;
import com.app.annotation.request.EntityType;
import com.app.annotation.request.LocalJsonPackage;
import com.app.annotation.request.MappingLocalJson;
import com.app.annotation.request.Param;
import com.app.annotation.request.Params;
import com.app.annotation.request.Parser;
import com.app.annotation.request.RequestBaseUrl;
import com.app.annotation.request.UseDiscCache;
import com.app.presenter.impl.parser.JsonParser;
import com.example.test.dialog.LoadingDialog;
import com.example.test.model.Accounts;
import com.example.test.model.Rooms;

@RequestBaseUrl("https://xzbenben.cn/AccountBook")
@LocalJsonPackage("")
public class Urls {

    @Description("获取账单列表")
    @Params({@Param(key = "TEST")})
    @EntityType(Accounts.class)
    @Dialog(LoadingDialog.class)
    @Parser(JsonParser.class)
    @UseDiscCache
    @MappingLocalJson(useTempleteJson = false, fileName = "")
    @AccessSettings(accessMethod = RequestMethods.GET, persistence = false)
    @ConnectionSet(connectionTimeOut = 5000, serverTimeOut = 1000, retryCount = 3)
    public static final String PATTERN_ACCOUNTS_GET_ALL = "/account/getAll";


    @Description("获取房间列表")
    @EntityType(Rooms.class)
    @Dialog(LoadingDialog.class)
    @Parser(JsonParser.class)
    @UseDiscCache
    @MappingLocalJson(useTempleteJson = false, fileName = "")
    @AccessSettings(accessMethod = RequestMethods.GET, persistence = false)
    @ConnectionSet(connectionTimeOut = 5000, serverTimeOut = 1000, retryCount = 3)
    @RequestBaseUrl("")
    public static final String PATTERN_HOT_ROOM = "http://api.mengliaoba.cn/apiv5/live/liveshow.php?cmd=hotroom";
}
