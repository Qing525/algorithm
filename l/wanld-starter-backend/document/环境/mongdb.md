参考： https://juejin.im/post/5cbe73f86fb9a0320b40d687

参考：https://docs.mongodb.com/manual/tutorial/install-mongodb-on-red-hat/#install-mongodb-community-edition

[TOC]



# 安装Mongodb

#### 1. 配置系统yum源：创建.repo文件，生成mongodb的源

```shell
vi /etc/yum.repos.d/mongodb-org-4.0.repo
```

#### 2. 添加以下配置信息：

```shell
[mongodb-org-4.0]
name=MongoDB Repository
baseurl=https://repo.mongodb.org/yum/redhat/#releasever/mongodb-org/4.0/x86_64/
gpgcheck=1
enabled=1
gpgkey=https://www.mongodb.org/static/pgp/server-4.0.asc
```
#### 3. 保存退出

```
wq # 退出保存
```
----
-  详解
```shell
name         # 名称
baseurl      # 获得下载的路径
gpkcheck=1   # 表示对从这个源下载的rpm包进行校验；
enable=1     # 表示启用这个源。
gpgkey       # gpg验证
```

# 使用yum安装MongoDB
#### 1. 安装MongoDB

```
sudo yum install -y mongodb-org
```

#### 2. 验证安装结果

```
rpm -qa |grep mongodb
```
```
rpm -ql mongodb-org-server
```

#### 3. 启动MongoDB

```
systemctl start mongod.service
```

MongoDB默认端口是27017，查看是否开启

```
netstat -natp | grep 27017
```

检查数据库是否安装成功

```
ps -aux | grep mongod    # 查看数据库的进程是否存在
```

#### 4. 验证服务开启

```
mongo
```

- 常用命令清单

```
// 1、开启MongoDB
sudo service mongod start  或者 systemctl start mongod.service  # 开启MongoDB
sudo chkconfig mongod on  # 加入开机启动
sudo service mongod restart # 重启MongoDB

// 2、关闭MongoDB
sudo service mongod stop  # 关闭防火墙

// 3、卸载MongoDB
sudo yum erase $(rpm -qa | grep mongodb-org)    # 卸载MongoDB
sudo rm -r /var/log/mongodb  # 删除日志文件
sudo rm -r /var/lib/mongo    # 删除数据文件
```

#  远程连接Mongodb

#### 1. 修改配置文件mongodb.conf

```
vi /etc/mongod.conf

# network interfaces
net:
  port: 27017
  bindIp: 0.0.0.0  # Enter 0.0.0.0,:: to bind to all IPv4 and IPv6 addresses or, alternatively, use the net.bindIpAll setting.
```

> 修改绑定ip默认127.0.0.1只允许本地连接， 所以修改为bindIp:0.0.0.0, 退出保存

#### 2. 重启mongodb服务

``` shell
sudo service mongod restart
```

#### 3. 开放对外端口

> 方法一

``` shell
systemctl status firewalld  # 查看防火墙状态
firewall-cmd --zone=public --add-port=27017/tcp --permanent # mongodb默认端口号
firewall-cmd --reload  # 重新加载防火墙

firewall-cmd --zone=public --query-port=27017/tcp # 查看端口号是否开放成功，输出yes开放成功，no则失败
```

> 方法二

``` shell
iptables -A INPUT -p tcp -m state --state NEW -m tcp --dport 27017 -j ACCEPT
```

#### 4. 远程连接

**默认连接**

```
mongo 10.128.218.14:27017
复制代码
```

**连接到自定义的用户**

1. 创建用户，设置账号，密码，权限

```
// admin数据库
> use admin
switched to db admin
> db.createUser({ user:"root", pwd:"123456", roles:["root"] })
Successfully added user: { "user" : "root", "roles" : [ "root" ] }

// 其他数据库
> use test
switched to db test
> db.createUser({ user:"admin", pwd:"123456", roles:["readWrite", "dbAdmin"] })
Successfully added user: { "user" : "root", "roles" : [ "root" ] }
复制代码
```

1. 修改mongodb.conf文件，启用身份验证

```
vi /etc/mongod.conf

security:
  authorization: "enabled"   # disable or enabled
复制代码
```

1. 重启MongoDB

```
sudo service mongod restart 
复制代码
```

1. 用户认证

```
> use admin
switched to db admin
> db.auth("root", "123456")
1 // 授权成功
复制代码
// 其他常用命令
db.updateUser(user, writeConcern) # 更新用户
db.dropUser('test') # 删除用户
复制代码
```

1. 远程连接

``` shell
// 终端连接
mongo 10.128.218.14:27017:27017/database -u username -p password

// mongoose方式连接
mongoose.connect('mongodb://username:password@host:port/database?options...', {useNewUrlParser: true});

// 通过客户端连接
复制代码
```

### 用户权限角色说明

| 规则                 | 说明                                                         |
| -------------------- | ------------------------------------------------------------ |
| root                 | 只在admin数据库中可用。超级账号，超级权限                    |
| Read                 | 允许用户读取指定数据库                                       |
| readWrite            | 允许用户读写指定数据库                                       |
| dbAdmin              | 允许用户在指定数据库中执行管理函数，如索引创建、删除，查看统计或访问system.profile |
| userAdmin            | 允许用户向system.users集合写入，可以找指定数据库里创建、删除和管理用户 |
| clusterAdmin         | 只在admin数据库中可用，赋予用户所有分片和复制集相关函数的管理权限 |
| readAnyDatabase      | 只在admin数据库中可用，赋予用户所有数据库的读权限            |
| readWriteAnyDatabase | 只在admin数据库中可用，赋予用户所有数据库的读写权限          |
| userAdminAnyDatabase | 只在admin数据库中可用，赋予用户所有数据库的userAdmin权限     |
| dbAdminAnyDatabase   | 只在admin数据库中可用，赋予用户所有数据库的dbAdmin权限       |
