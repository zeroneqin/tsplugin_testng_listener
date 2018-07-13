package com.qinjun.autotest.tsplugin.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

public class HttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static HttpResponse sendGet(String url, Map<String, String> headerMap, Map<String,String> queryParams, boolean encode) {
        HttpResponse httpResponse = new HttpResponse();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String queryStr="";
        if (queryParams!=null && queryParams.size()>0) {
            url=url+"?";
            boolean firstQueryParam = true;
            for (String queryParamName: queryParams.keySet()) {
                String queryParamValue  = queryParams.get(queryParamName);
                if (encode) {
                    queryParamValue = URLEncoder.encode(queryParamValue);

                }
                if (firstQueryParam) {
                    queryStr = queryStr + queryParamName+"="+queryParamValue;
                    firstQueryParam = false;
                }
                else {
                    queryStr = queryStr+"&"+queryParamName+"="+queryParamValue;
                }
            }
            url = url+queryStr;
        }

        try {
            final HttpGet httpGet = new HttpGet(url);
            logger.info("Send request:" + httpGet.getRequestLine());
            if (headerMap != null) {
                for (final String headerKey : headerMap.keySet()) {
                    final String headerValue = headerMap.get(headerKey);
                    if (!StringUtils.isEmpty(headerValue)) {
                        logger.info("Request header name:[{}], value:[{}]", headerKey, headerValue);
                        httpGet.addHeader(headerKey, headerValue);
                    }
                }
            }
            final CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);
            final int status = closeableHttpResponse.getStatusLine().getStatusCode();
            httpResponse.setStatus(status);
            logger.info("Response status code:[{}]", status);
            final HttpEntity httpEntity = closeableHttpResponse.getEntity();
            if (httpEntity != null) {
                logger.info("Response body length: " + httpEntity.getContentLength());
                final String body = EntityUtils.toString(httpEntity);
                logger.info("Response body:"+body);
                EntityUtils.consume(httpEntity);
                httpResponse.setBody(body);
            }

        } catch (final Exception e) {
            logger.error("Get exception when send the http request:"+e);
        }
        finally {
            try {
                httpClient.close();
            }
            catch (final IOException ioe){
                logger.error("Get exception when close the httpclient:"+ioe);
            }
        }
        return httpResponse;
    }



    public static HttpResponse sendPost(String url, Map<String, String> headerMap, Map<String,String> queryParams, boolean encode,String requestBody,ContentType contentType) {
        HttpResponse httpResponse = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String queryStr=null;
        if (queryParams!=null && queryParams.size()>0) {
            url=url+"?";
            boolean firstQueryParam = true;
            for (String queryParamName: queryParams.keySet()) {
                String queryParamValue  = queryParams.get(queryParamName);
                if (encode) {
                    queryParamValue = URLEncoder.encode(queryParamValue);

                }
                if (firstQueryParam) {
                    queryStr = queryStr + queryParamName+"="+queryParamValue;
                    firstQueryParam = false;
                }
                else {
                    queryStr = queryStr+"&"+queryParamName+"="+queryParamValue;
                }
            }
            url = url+queryStr;
        }

        try {
            final HttpPost httpPost = new HttpPost(url);
            logger.info("Send request:" + httpPost.getRequestLine());
            if (headerMap != null) {
                for (final String headerKey : headerMap.keySet()) {
                    final String headerValue = headerMap.get(headerKey);
                    if (!StringUtils.isEmpty(headerValue)) {
                        logger.info("Request header name:[{}], value:[{}]", headerKey, headerValue);
                        httpPost.addHeader(headerKey, headerValue);
                    }
                }
            }
            if (!StringUtils.isEmpty(requestBody)) {
                final StringEntity stringEntity = new StringEntity(requestBody,contentType);
                httpPost.setEntity(stringEntity);
                logger.debug("Request content type:[{}]",contentType);
                logger.info("Request body:[{}]",requestBody);
            }

            final CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);
            final int status = closeableHttpResponse.getStatusLine().getStatusCode();
            httpResponse.setStatus(status);
            logger.info("Response status code:[{}]", status);
            final HttpEntity httpEntity = closeableHttpResponse.getEntity();
            if (httpEntity != null) {
                logger.info("Response body length: " + httpEntity.getContentLength());
                final String body = EntityUtils.toString(httpEntity);
                logger.info("Response body:"+body);
                EntityUtils.consume(httpEntity);
                httpResponse.setBody(body);
            }

        } catch (final Exception e) {
            logger.error("Get exception when send the http request:"+e);
        }
        finally {
            try {
                httpClient.close();
            }
            catch (final IOException ioe){
                logger.error("Get exception when close the httpclient:"+ioe);
            }
        }
        return httpResponse;
    }
}
