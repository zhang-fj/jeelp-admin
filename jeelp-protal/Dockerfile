FROM java:8
#SpringBoot项目必须使用/tmp目录
VOLUME /tmp
#暴露的端口
EXPOSE 9002
#将项目的jar包拷贝并命名
COPY jeelp-protal-1.0-SNAPSHOT.jar app.jar
RUN bash -c "touch /app.jar"
#执行命令运行项目
ENTRYPOINT ["java","-jar","/app.jar"]
#解决时间不正确的问题
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
  && echo 'Asia/Shanghai' >/etc/timezone \