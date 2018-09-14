# oauth2.0-Learn
## 1、为什么需要oauth
 a、避免客户端保存用户名与密码，因为保存用户名和密码不安全  
 b、单纯的密码登陆不安全   
 c、授权后服务器端没法限制客户端用户的获得授权范围和有效期  
 d、用户只有收回密码才能回收客户端的权限，这样会使得所有获得用户授权的第三方应用程序全部失效  
 e、用户密码很容易被破解与泄漏  
## 2、oauth2.0协议及理解
1)、oauth2.0运行流程  
->用户打开第三方应用客户端，第三方应用客户端会要求用户给予授权  
->用户同意给予授权  
->第三方客户端获得授权，向认证服务器申请令牌  
->认证服务器对客户端进行认证后，发放令牌  
->客户端使用令牌向资源服务器申请获取资源  
->资源服务器确认授权令牌后，同意开放资源  
2)、授权模式  
a、授权码模式(authorization code)  
&emsp;用户通过浏览器输入服务器分配的授权码获取地址进行授权码申请;申请通过后地址会进行重定向地址，重定向后的地址会带有授权码，客户端获得授权码可以再次访问认证服务器获取访问码及刷新码。  
&emsp;例如:GET /authorize?response_type=code&client_id=s6BhdRkqt3&redirect_uri=https%3A%2F%2Fclient%2Eexample%2Ecom%2Fcb HTTP/1.1
 
访问码信息：  
&emsp;access_token：表示访问令牌，必选项。  
&emsp;token_type：表示令牌类型，该值大小写不敏感，必选项，可以是bearer类型或mac类型。  
&emsp;expires_in：表示过期时间，单位为秒。如果省略该参数，必须其他方式设置过期时间。  
&emsp;refresh_token：表示更新令牌，用来获取下一次的访问令牌，可选项。  
&emsp;scope：表示权限范围，如果与客户端申请的范围一致，此项可省略。    
b、简化模式(implicit grant type)  
&emsp;不需要申请授权令牌  
c、密码模式(Resource Owner Password Credentials Grant)  
&emsp;客户端直接通过密码获取访问码  
d、客户端模式（Client Credentials Grant）  
&emsp;客户端向认证服务器进行身份认证client_id,client_secret获取访问码

## 3、如何构建oauth2.0服务端
1)、spring security oauth2
