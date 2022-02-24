# REDIS篇章

## 一、测试服

- 192.168.10.21
- /usr/local/soft-work/redis  工作目录

## 二、参数设置方式

- redis.conf
- config set 
- redis-server --port=6379

## 三、[扩缩容](https://blog.csdn.net/brandohero/article/details/41454469)

- 总的元素个数 除 DICT桶的个数得到每个桶平均存储的元素个数(pre_num),如果 pre_num > dict_force_resize_ratio,就会触发dict 扩大操作。dict_force_resize_ratio = 5。

- 在总元素 * 10 < 桶的个数，也就是,填充率必须<10%,DICT便会进行收缩，让total / bk_num 接近 1:1。
- ![image-20220218110306269](C:\Users\dev\AppData\Roaming\Typora\typora-user-images\image-20220218110306269.png)

## 四、[常见NoSQL](https://hostingdata.co.uk/nosql-database/)

## 五、常见数据类型

### 1、string

- 支持存储：string、int、float
- 编码：int、emstr、raw

### 2、hash

- 支持存储：string
- 编码：ziplist、hashtable

### 3、list

### 4、set

### 5、zset

## 六、[淘汰策略](https://blog.csdn.net/ju_362204801/article/details/114441137)

- 查询默认策略

```shell
config get maxmemory-policy
```

- 查询内存设置大小

```shell
config get maxmemory
```

- 淘汰策略

```tex
1.noeviction(默认策略)：对于写请求不再提供服务，直接返回错误（DEL请求和部分特殊请求除外）

2.allkeys-lru：从所有key中使用LRU算法进行淘汰

3.volatile-lru：从设置了过期时间的key中使用LRU算法进行淘汰

4.allkeys-random：从所有key中随机淘汰数据

5.volatile-random：从设置了过期时间的key中随机淘汰

6.volatile-ttl：在设置了过期时间的key中，淘汰过期时间剩余最短的
```

## [哨兵高可用方案（Sentinel）](https://gper.club/articles/7e7e7f7ff3g5bgccg68)

### redis-server服务器配置

| IP             | PORT | 角色   | 备注           |
| -------------- | ---- | ------ | -------------- |
| 192.168.10.121 | 6379 | master | redis/sentinel |
| 192.168.10.122 | 6379 | slave  | redis/sentinel |
| 192.168.10.123 | 6379 | slave  | reids/sentinel |

### redis-cluster常用操作

- [参考文档一](https://juejin.cn/post/6844904126304763912)
- [参考文档二](https://www.codedream.xin/archives/redis-cluster新增节点以及重新分配槽位)

#### 1、添加集group

```shell
redis-cli --cluster new_host:new_port existing_host:existing_port(集群中任意一个)
```

#### 2、查看集群状态

```shell
redis-cli --cluster check 192.168.75.143:7001(集群中任意一个)
```

![image-20220224104331803](C:\Users\dev\AppData\Roaming\Typora\typora-user-images\image-20220224104331803.png)

#### 3、给新master添加一个replica

```shell
redis-cli --cluster add-node 192.168.10.24:7299(新加slave_node) 192.168.10.24:7291(原有master_slave_node) --cluster-slave --cluster-master-id 3d3b9747265b6ec94733ead62edbb8a822be3722(新加master_node)
```

