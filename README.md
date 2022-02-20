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



















