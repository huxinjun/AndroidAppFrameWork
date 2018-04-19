package com.app.presenter.impl.request;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;


import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import com.app.ULog;
import com.app.annotation.request.AccessSettings.RequestMethods;
import com.app.presenter.INetWorkPresenter;
import com.app.presenter.INetWorkPresenterBridge;
import com.app.presenter.IParserPresenter;
import com.app.presenter.IParserPresenterBridge;
import com.app.presenter.IRequestPresenter;
import com.app.presenter.PresenterManager;
import com.app.presenter.impl.request.CustomMultipartEntity.ProgressInfo;
import com.app.presenter.impl.request.CustomMultipartEntity.ProgressListener;

/**
 * 使用HttpClient实现请求网络数据功能
 *
 * @author xinjun
 */
@SuppressWarnings("deprecation")
public class HttpClientRequest extends RequestPresenter {

    @Override
    public Bitmap getImage(RequestInfo info) {
        ULog.out("getImage:" + info);
        HttpGet get = new HttpGet(info.mRequestUrl);
        HttpResponse response = null;

        HttpParams httpParams = new BasicHttpParams();
        httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 8000);
        HttpConnectionParams.setSoTimeout(httpParams, 8000);

        get.setParams(httpParams);
        try {
            response = new DefaultHttpClient().execute(get);
            if (response != null
                    && response.getStatusLine().getStatusCode() == 200) {
                response.getEntity().getContentLength();
                return BitmapFactory.decodeStream(response.getEntity().getContent());
            } else {
                if (info.mExcuteCount++ < info.mRetryCount) {
//					MyLog.outInnerLogDetail("加载图片" + uri + "重试第" + (reqCount + 1)
//							+ "次");
                    return getImage(info);
                } else
                    return null;
            }
        } catch (Exception e) {
            if (info.mExcuteCount++ < info.mRetryCount) {
//				MyLog.outInnerLogDetail("加载图片" + uri + "重试第" + (reqCount + 1)
//						+ "次");
                return getImage(info);
            } else
                return null;
        }
    }

    @Override
    public File getFile(RequestInfo info) {
        //TODO
        return null;
    }

    @Override
    public Object getData(RequestInfo info) {
        ULog.out("getData:" + info);
        ULog.out("getData:" + IRequestPresenter.GLOBLE);
        HttpRequestBase method = null;
        if (info.mRequestMethod == RequestMethods.GET)
            method = getGet(info);
        else
            method = getPost(info);

        HttpClient client = getClient(info);
        String result = "";
        try {
            HttpResponse response = client.execute(method);

            if (response != null && response.getStatusLine().getStatusCode() == 200) {
                // Entity转换JSON格式
                result = EntityUtils.toString(response.getEntity(), ENCODING);
                //转换为实体
                ULog.out("请求的数据：" + result);

                info.mServerResultStr=result;
                Object parseObj = getParser().parse(result, info.mEntityType);
                notifyRequestStatusListenner(info.mRequestName, RequestStatus.SUCCESS, result);
                return parseObj;

            } else if (response != null) {
                int code = response.getStatusLine().getStatusCode();
                //服务器错误
                notifyRequestStatusListenner(info.mRequestName, RequestStatus.SERVER_ERROR, code);


            } else {
                //没有网络时会执行到这里
                notifyRequestStatusListenner(info.mRequestName, RequestStatus.NO_NETWORK, "无网络");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 执行到这里说明连接超时了
            notifyRequestStatusListenner(info.mRequestName, RequestStatus.CONNECTION_TIME_OUT, "连接超时");
        }
        return null;
    }


    private HttpClient getClient(RequestInfo requestInfo) {

        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, "UTF-8");
        HttpProtocolParams.setUseExpectContinue(params, true);
        SchemeRegistry schReg = new SchemeRegistry();
        schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schReg.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        ClientConnectionManager conMgr = new ThreadSafeClientConnManager(params, schReg);

        HttpClient client = new DefaultHttpClient(conMgr, new BasicHttpParams());

        int network = getNetUtil().checkNetWork();

        if (network == INetWorkPresenter.NET_TYPE_MOBILE && !TextUtils.isEmpty(getNetUtil().getProxyIP())) {
            HttpHost host = new HttpHost(getNetUtil().getProxyIP(), getNetUtil().getProxyPort());
            client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, host);
        } else
            client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, null);


        return client;
    }


    private HttpGet getGet(RequestInfo info) {
        StringBuffer urlStr = new StringBuffer(info.mRequestUrl);
        if (info.mParamPool != null && info.mParamPool.getParams() != null && info.mParamPool.getParams().size() > 0) {
            List<Param> params = info.mParamPool.getParams();
            if (info.mParamPool.getParams().size() > 0) {
                if (!urlStr.toString().contains("?"))
                    urlStr.append("?");
                else
                    urlStr.append("&");
                for (Param param : params) {

                    urlStr.append(param.getKey()).append("=")
                            .append(param.getValue());
                    urlStr.append("&");
                }
                urlStr.delete(urlStr.length() - 1, urlStr.length());
            }
        }


        HttpGet get = new HttpGet(urlStr.toString());

        HttpParams httpParams = new BasicHttpParams();
        httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, info.mConnectionTimeOut);
        HttpConnectionParams.setSoTimeout(httpParams, info.mServerTimeOut);

        return get;

    }


    private HttpPost getPost(final RequestInfo requestInfo) {
        HttpPost post = new HttpPost(requestInfo.mRequestUrl);
        HttpParams httpParams = new BasicHttpParams();
        httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, requestInfo.mConnectionTimeOut);
        HttpConnectionParams.setSoTimeout(httpParams, requestInfo.mServerTimeOut);

        /**
         * 设置参数
         */
        if (requestInfo.mParamPool != null && requestInfo.mParamPool.getParams() != null && requestInfo.mParamPool.getParams().size() > 0) {
            List<Param> params = requestInfo.mParamPool.getParams();
            CustomMultipartEntity mpEntity = null;//文件对象
            for (Param param : params) {
                //创建一个混合参数的参数体，可能会包含file
                mpEntity = mpEntity == null ? new CustomMultipartEntity(new ProgressListener() {

                    @Override
                    public void transferred(ProgressInfo info) {
                        notifyRequestStatusListenner(requestInfo.mRequestName, RequestStatus.UPLOADING, info);
                    }
                }) : mpEntity;


                if (param.getType() == ParamType.FILE) {
                    //这个参数是文件对象
                    File file = new File(param.getValue());
                    ContentBody cbFile = new FileBody(file);
                    // 文件传输
                    mpEntity.addPart(param.getKey().trim(), cbFile);
                } else {
                    try {
                        String value = param.getValue();
                        if (TextUtils.isEmpty(value))
                            value = "";
                        mpEntity.addPart(param.getKey().trim(), new StringBody(value, Charset.forName(ENCODING)));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

            }
            if (mpEntity != null)
                post.setEntity(mpEntity);
        }


        return post;
    }


    private INetWorkPresenter getNetUtil() {
        return PresenterManager.getInstance().findPresenter(getContext(), INetWorkPresenterBridge.class);
    }

    private IParserPresenter getParser() {
        return PresenterManager.getInstance().findPresenter(getContext(), IParserPresenterBridge.class);
    }


}
