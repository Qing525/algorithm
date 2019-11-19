https://juejin.im/post/5c088b066fb9a049d4419985

##### 1. 添加Mysql5.7仓库

``` mysql
sudo rpm -ivh https://dev.mysql.com/get/mysql57-community-release-el7-11.noarch.rpm
```
##### 2. 确认Mysql仓库成功添加

```shell
sudo yum repolist all | grep mysql | grep enabled
```

##### 3. 开始安装Mysql5.7

```shell
sudo yum -y install mysql-community-server
```

##### 4. 启动Mysql	

```
sudo systemctl start mysqld
```

```
sudo systemctl enable mysqld
```

```
sudo systemctl status mysqld
```

##### 5. Mysql的安全设置

CentOS上的root默认密码可以在文件/var/log/mysqld.log找到，通过下面命令可以打印出来

```shell
cat /var/log/mysqld.log | grep -i 'temporary password'
```

执行下面命令进行安全设置，这个命令会进行设置root密码设置，移除匿名用户，禁止root用户远程连接等

```
mysql_secure_installation
```

##### 6. 设置数据库编码为utf8

```
sudo vim /etc/my.cnf
```

```shell
[client]
default-character-set=utf8

[mysql]
default-character-set=utf8

[mysqld]
collation-server = utf8_unicode_ci
init-connect='SET NAMES utf8'
character-set-server = utf8
```

```shell
sudo systemctl restart mysqld
```

##### 7. 修改root密码，并开启远程连接
用下命令进入mysql，并输入初始密码：
``` shell
mysql -u root -p
```
修改密码：
```
use mysql;
UPDATE user SET authentication_string=password('wanld@MQ1') WHERE user='root';
```
授以远程root登录：
``` shell
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'wanld@MQ1' WITH GRANT OPTION;
```