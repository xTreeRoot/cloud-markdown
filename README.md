# 云markdown-server

#### 介绍
云端上的markdown工具，server端

#### 使用【docker服务器篇】

1. 先把项目拉下来，查看resources下的application.yaml文件注意一下代码

```yaml
file:
  markdown: /docker/cloud-markdown/data/static/markdown/
  image: /markdown/data/static/image/
    
```

其中 /docker/cloud-markdown/data/static/markdown/是你将要存储的位置，本系统所有的markdown文件都将存储在这。忽略掉**image，因为目前并没有使用**

1. 项目执行打包、clean-install-packade
2. 在target包将markdown-server-0.1.jar拖到你的服务器下，application.yaml也拖到你的服务器下
3. 作者存储的位置是 **/docker/cloud-markdown/**
4. 在本目录下复制以下DockerFiles、随后执行打包、启动
5. 关于docker 的指令这里不做任何讲解

DockerFiles文件

```shell
FROM openjdk:11
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
COPY application.yml /docker/cloud-markdown/application.yml
COPY markdown-server-0.1.jar /docker/cloud-markdown/markdown-server-0.1.jar
ENTRYPOINT ["java","-jar","/docker/cloud-markdown/markdown-server-0.1.jar","--spring.config.location=/docker/cloud-markdown/application.yml"]
```

打包

```shell
docker build -t markdown-cloud:v1 .
```

启动

```shell
docker run -dti --name=markdown-cloud -p 8991:8991 --restart=always -v /docker/cloud-markdown/data/static/markdown:/docker/cloud-markdown/data/static/markdown/ markdown-cloud:vtest
```