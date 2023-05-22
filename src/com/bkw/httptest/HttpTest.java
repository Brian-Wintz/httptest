package com.bkw.httptest;

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.util.Base64;
import java.util.LinkedHashMap;

import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpTest {
   
   public String getOAuthToken(String clientid, String username, String password, String baseurl) throws Exception {
      String oauthToken="";
      HttpClient client = new DefaultHttpClient();
      HttpPost apiRequest = new HttpPost(baseurl+"/oauth/token");
      apiRequest.addHeader("Content-Type", "application/x-www-form-urlencoded");
      String authorization = "Basic "+Base64.getEncoder().encodeToString(new String(username+":"+password).getBytes());
      //apiRequest.addHeader("Authorization", authorization);
      
      String payload = "client_id="+clientid+"&grant_type=password"+"&username="+username+"&password="+password;
      System.out.println("payload:\n"+payload);
      
      StringEntity params = new StringEntity(payload);
      apiRequest.setEntity(params);

/***/     
      Header[] headers = apiRequest.getAllHeaders();
      for(int i=0; i<headers.length; ++i) {
         System.out.println("OAUTH REQUEST: " + i + ": " +headers[i].toString());
      }
/***/
      HttpResponse apiResponse = client.execute(apiRequest);
      ByteArrayOutputStream stream = new ByteArrayOutputStream();
      apiResponse.getEntity().writeTo(stream);
      stream.close();
      String body = stream.toString();
      int statusCode = apiResponse.getStatusLine().getStatusCode();
      
/***/
      headers = apiResponse.getAllHeaders();
      for(int i=0; i<headers.length; ++i) {
         System.out.println("OAUTH RESPONSE: " +i + ": " +headers[i].toString());
      }
/***/     
      System.out.println("OATH TOKEN: " + statusCode + " BODY: " + body);

      ObjectMapper jsonMapper = new ObjectMapper();
      LinkedHashMap map = (LinkedHashMap)jsonMapper.readValue(body, Object.class);
      
      oauthToken=(String)map.get("access_token");
      System.out.println("OAuth Token:"+oauthToken);
      return oauthToken;
   }
   
   public void testRequest(String clientid, String username, String password, String baseurl, boolean useOauth) throws Exception {
      long startTime = System.nanoTime();
      HttpClient client = new DefaultHttpClient();
      String oauthToken;
      String authorization;

      if(useOauth) {
         oauthToken=getOAuthToken(clientid,username,password,baseurl);
         authorization = "Bearer "+oauthToken;
         System.out.println("getOAuthToken: "+(System.nanoTime()-startTime)/10000000000.0);
      } else authorization = "Basic "+Base64.getEncoder().encodeToString(new String(username+":"+password).getBytes());
      
      HttpGet apiRequest = new HttpGet(baseurl+"/api/qracore/browses?browseId=urn:browse:bebrowse:com.qad.erp.sales.salesOrders&page=1&pageSize=5&pageAction=first");
      apiRequest.addHeader("x-qad-workspace-id","11CAN_11CANCO");
      apiRequest.addHeader("Content-Type", "application/json");
      apiRequest.addHeader("Authorization", authorization);
      
      // Print request headers
      Header[] headers = apiRequest.getAllHeaders();
      for(int i=0; i<headers.length; ++i) {
         System.out.println("REQUEST1: " + i + ": " +headers[i].toString());
      }

      HttpResponse apiResponse = client.execute(apiRequest);
      ByteArrayOutputStream stream = new ByteArrayOutputStream();
      apiResponse.getEntity().writeTo(stream);
      stream.close();
      String body = stream.toString();
      int statusCode = apiResponse.getStatusLine().getStatusCode();
      
      // Print response headers
      headers = apiResponse.getAllHeaders();
      for(int i=0; i<headers.length; ++i) {
         System.out.println("RESPONSE1: " +i + ": " +headers[i].toString());
      }
      System.out.println("Request1 Time: "+(System.nanoTime()-startTime)/10000000000.0);
      
      headers=apiResponse.getHeaders("SET-COOKIE");
      String sessionid = null;
      if(headers!=null && headers.length>0) {
         Header setcookie=headers[0];
         sessionid=setcookie.getValue();
      }
   
      System.out.println("SALES ORDERS: " + statusCode + " BODY: " + body);
      
      apiRequest = new HttpGet(baseurl+"/api/qracore/browses?browseId=urn:browse:bebrowse:com.qad.erp.sales.salesQuotes&page=1&pageSize=5&pageAction=first");
      apiRequest.addHeader("x-qad-workspace-id","10USA_10USACO");
      apiRequest.addHeader("Content-Type", "application/json");
      apiRequest.addHeader("Authorization", authorization);
      
      // Print request headers
      headers = apiRequest.getAllHeaders();
      for(int i=0; i<headers.length; ++i) {
         System.out.println("REQUEST2: " + i + ": " +headers[i].toString());
      }

      apiResponse = client.execute(apiRequest);
      stream = new ByteArrayOutputStream();
      apiResponse.getEntity().writeTo(stream);
      stream.close();
      body = stream.toString();
      statusCode = apiResponse.getStatusLine().getStatusCode();
      
      // Print response headers
      headers = apiResponse.getAllHeaders();
      for(int i=0; i<headers.length; ++i) {
         System.out.println("RESPONSE2: " +i + ": " +headers[i].toString());
      }

      System.out.println("Request2 Time: "+(System.nanoTime()-startTime)/10000000000.0);

      apiRequest = new HttpGet(baseurl+"/api/qracore/browses?browseId=urn:browse:mfg:so011&page=1&pageSize=5&pageAction=first");
      apiRequest.addHeader("x-qad-workspace-id","10USA_10USACO");
      apiRequest.addHeader("Content-Type", "application/json");
      apiRequest.addHeader("Authorization", authorization);
      
      // Print request headers
      headers = apiRequest.getAllHeaders();
      for(int i=0; i<headers.length; ++i) {
         System.out.println("REQUEST3: " + i + ": " +headers[i].toString());
      }

      apiResponse = client.execute(apiRequest);
      stream = new ByteArrayOutputStream();
      apiResponse.getEntity().writeTo(stream);
      stream.close();
      body = stream.toString();
      statusCode = apiResponse.getStatusLine().getStatusCode();
      
      // Print response headers
      headers = apiResponse.getAllHeaders();
      for(int i=0; i<headers.length; ++i) {
         System.out.println("RESPONSE3: " +i + ": " +headers[i].toString());
      }

      System.out.println("Request3 Time: "+(System.nanoTime()-startTime)/10000000000.0);

      apiRequest = new HttpGet(baseurl+"/api/qracore/browses?browseId=urn:browse:mfg:sa007&page=1&pageSize=5&pageAction=first");
      apiRequest.addHeader("x-qad-workspace-id","10USA_10USACO");
      apiRequest.addHeader("Content-Type", "application/json");
      apiRequest.addHeader("Authorization", authorization);
      
      // Print request headers
      headers = apiRequest.getAllHeaders();
      for(int i=0; i<headers.length; ++i) {
         System.out.println("REQUEST4: " + i + ": " +headers[i].toString());
      }

      apiResponse = client.execute(apiRequest);
      stream = new ByteArrayOutputStream();
      apiResponse.getEntity().writeTo(stream);
      stream.close();
      body = stream.toString();
      statusCode = apiResponse.getStatusLine().getStatusCode();
      
      // Print response headers
      headers = apiResponse.getAllHeaders();
      for(int i=0; i<headers.length; ++i) {
         System.out.println("RESPONSE4: " +i + ": " +headers[i].toString());
      }

      System.out.println("Request4 Time: "+(System.nanoTime()-startTime)/10000000000.0);

      long endTime = System.nanoTime();
      System.out.println("DURATION:"+(endTime-startTime)/10000000000.0);
   }

   public static void main(String[] args) {
      HttpTest test = new HttpTest();
      // vmlwjaux0001: "c412be64a10695aba2143c6218455a1c"
      // vmlbkw0009: "a276d9fa0df620bfa2147a9f784a0b85"
      String clientid="db38dc74523aeeae8f14f10df8cc2e1f";
      //vmlwjaux0001: username="bkw@qad.com", password="qad123"
      //vmlbkw0009: username="mfg@qad.com", password=""
      String username="mfg@qad.com";
      String password="";
      // vmlwjaux0001: "https://vmlwjaux0001.qad.com/clouderp"
      // vmlbkw0009: "https://vmlbkw0009.qad.com:22011/qad-central"
      String baseurl="https://vmlwjaux0001.qad.com/clouderp";
      try {
         test.testRequest(clientid,username,password,baseurl,true);
      } catch (Exception e) {
         System.out.println("ERR: " + e.toString());
         e.printStackTrace();
      }
   }
}
