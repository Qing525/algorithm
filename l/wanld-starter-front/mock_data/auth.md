

#### 登录授权接口
>接口描述：登录请求数据接口；前端负责人：王增光；修改时间：2019-8-5   
>后台接口地址：/admin/login；负责人：？？？；时间：？？？   

* 请求参数 
```
{
    "username":"String",  //用户名
    "password":"String" //密码
}
```

* 返回数据： 
```
{
    "code": 200,
    "message": "String",
    "data": {
        "accessToken": "String",
        "tokenHead": "String",
        "expireInSeconds": 0
    }
}
```
