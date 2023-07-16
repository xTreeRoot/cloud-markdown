# 云markdown-server

# 前端

https://gitee.com/xu-genyin/cloud-markdown-web.git

## 介绍

云端上的markdown工具，server端

## 部署依赖环境

Docker

#### 使用Docker去部署

本产品使用场景更倾向于局域云端，面对个人用户也支持直接在本机一直启动所以使用docker去部署是最好的选择

1. 先把项目拉下来，查看resources下的application.yaml文件注意一下代码

```yaml
file:
  markdown: /docker/cloud-markdown/data/static/markdown/
  image: /markdown/data/static/image/
    
```

其中 /docker/cloud-markdown/data/static/markdown/是你将要存储的位置，本系统所有的markdown文件都将存储在这。

在后面的dokcer run指令中为了防止数据丢失做了指定了挂载路径,`-v至:`一段是本地（服务器储存位置/mac储存位置/windows储存位置）会同步到`:`后面一段的docker容器内的路径 也就是`file:
markdown: /docker/cloud-markdown/data/static/markdown/`的位置

```dockerfile
-v /docker/cloud-markdown/data/static/markdown:/docker/cloud-markdown/data/static/markdown/
```

忽略掉**image，因为目前并没有使用**

1. 项目执行打包、clean-install-packade
2. 在target包将markdown-server-0.1.jar拖到你的服务器下/windows下/mac下，application.yaml也拖到你的服务器下
3. 作者存储的位置(linux)是 **/docker/cloud-markdown/**
4. 在本目录下复制以下DockerFiles、随后执行打包、启动
5. 关于docker 的指令这里不做任何讲解

DockerFiles文件

执行这段指令的时候你一定在处在你储存的路径下也就是`/docker/cloud-markdown/`，否则请将这一段路径写成你所储存的绝对路径

`/docker/cloud-markdown/markdown-server-0.1.jar`形式【注意，这一段绝对路径是我的环境，请按事实修改】

```doc
COPY application.yml /docker/cloud-markdown/application.yml
COPY markdown-server-0.1.jar /docker/cloud-markdown/markdown-server-0.1.jar
```

这一段中前面的`application.yml`和`COPY markdown-server-0.1.jar`写成绝对路径，也就是

> 如果上述COPY指令的后半段位置 也就是容器内位置被修改了，请同步修改ENTRYPOINT的文件位置，因为他读取的是你容器内的jar文件和yaml文件

```shell
FROM openjdk:11
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
COPY application.yaml /docker/cloud-markdown/application.yaml
COPY markdown-server-0.1.jar /docker/cloud-markdown/markdown-server-0.1.jar
ENTRYPOINT ["java","-jar","/docker/cloud-markdown/markdown-server-0.1.jar","--spring.config.location=/docker/cloud-markdown/application.yaml"]
```

打包如果报错`ERROR [internal] load metadata for docker.io/library/openjdk:11`先去执行`docker pull openjdk:11`



```shell
docker build -t markdown-cloud:v1 .
```

启动

```shell
docker run -dti --name=markdown-cloud -p 8991:8991 --restart=always -v /docker/cloud-markdown/data/static/markdown:/docker/cloud-markdown/data/static/markdown/ markdown-cloud:v1
```

