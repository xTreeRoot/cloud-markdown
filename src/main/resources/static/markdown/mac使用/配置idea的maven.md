## 一、mac安装配置maven

1）官网下载（http://maven.apache.org/download.cgi）

binary  .tar.gz

下载解压到某处

### 2）配置环境变量

$ vi ~/.zshrc

加入以下，并保存退出：

```shell
\# Maven
export PATH="/Users/xx/apache-maven-3.x.x:$PATH"
export PATH="/Users/xx/apache-maven-3.x.x/bin:$PATH"


```

$ source ~/.bash_profile

### 3）测试

$ mvn -v

### 二、Idea上配置Maven

打开刚刚在 /usr/local/apache-maven-3.x.x/conf/settings.xml 文件在\<mirrors> \</mirrors>标签中加入



```xml
      <mirror>
           <id>nexus-aliyun</id>
           <mirrorOf>*</mirrorOf>
           <name>Nexus aliyun</name>
           <url>http://maven.aliyun.com/nexus/content/groups/public</url>
      </mirror>
```

### 下载仓库到你刚才指定的文件夹

输入

```shell
mvn help:system
```

![image](https://img2020.cnblogs.com/blog/2333250/202103/2333250-20210318204840784-1573421148.png)

> 

你可以在 /usr/local/apache-maven-3.6.3 这个目录下面发现多了一个文件夹repository 里面会有很多的文件 

在这里设置55行仓库

>    <!-- localRepository
>    | The path to the local repository maven will use to store artifacts.
>    |
>    | Default: ${user.home}/.m2/repository
>   <localRepository>/path/to/local/repo</localRepository>
>   -->
>    <localRepository>/Users/xugenyin/file/apache-maven-3.8.4/repository</localRepository>

```shell
Users/xugenyin/file/apache-maven-3.8.4/repository  是需要自己创建的
```



这些文件就是我们从中央仓库下载到本地仓库的文件。

到此，Mac下Maven的环境就配置好了。

![image](https://img2020.cnblogs.com/blog/2333250/202103/2333250-20210318210216894-494512429.png)

https://img2020.cnblogs.com/blog/2333250/202103/2333250-20210318210230764-1674659548.png

![image](https://img2020.cnblogs.com/blog/2333250/202103/2333250-20210318210325332-785004099.png)