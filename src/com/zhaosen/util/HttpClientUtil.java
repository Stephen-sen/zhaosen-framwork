//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zhaosen.util;

import com.zhaosen.util.CryptUtils;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
    public HttpClientUtil() {
    }

    public static void main(String[] args) throws ClientProtocolException, IOException {
        String jsonStr = "  { \"content\": \"ggggggg\", \"userName\":\"ggggggg\", \"customerLicense\":\"48865f7f-d305-4865-8ae7-ed582e59407a\", \"resolveUser\":\"gggggg\", \"userJob\":\"ggg\", \"userOrgan\":\"ggggg\", \"errorCode\":\"ggg\", \"requestUrl\":\"gggg\" }";
        String posturl = "http://localhost:8080/CRM/service.html?action=CUSTOMER.ADDEX";
        String secureKey = "asdfrewq";
        String dataStr = CryptUtils.encrypt(jsonStr, secureKey);
        String result = httpPost(posturl, dataStr);
        System.out.println(result);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static String httpPost(String url, String param) throws ClientProtocolException, IOException {
        new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        ArrayList params = new ArrayList();
        params.add(new BasicNameValuePair("data", param));
        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        HttpResponse response = (new DefaultHttpClient()).execute(httpPost);
        if(response.getStatusLine().getStatusCode() == 200) {
            String result = EntityUtils.toString(response.getEntity());
            return result;
        } else {
            return "";
        }
    }
}
