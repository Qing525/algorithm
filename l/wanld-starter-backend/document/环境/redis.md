# 安装redis

参考：[https://www.linode.com/docs/databases/redis/install-and-configure-redis-on-centos-7/]()

1. 安装 EPEL  

```shell
sudo yum install epel-release
sudo yum update
```

2. 安装redis

```shell
sudo yum install redis
```

3. 启动redis

```shell
sudo systemctl start redis
```

4. 重启redis

```shell
sudo systemctl restart redis
```

6. 配置开机启动

```shell
sudo systemctl enable redis
```



# 检查安装

```shell
redis-cli ping
```

安装成功则返回：

```shell
PONG
```



# 配置redis

1. redis配置文件

```shell
vi /etc/redis.conf
```

2. 配置redis可远程连接
* 修改 protected-mode  yes 改为：protected-mode no

* 注释掉  127.0.0.1

* 修改 requirepass ，以配置连接密码

   

