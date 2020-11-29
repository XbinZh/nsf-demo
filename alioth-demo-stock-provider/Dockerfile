FROM tomcat:7.0.77-jre8

#add application jar
ADD ./nsf-demo-stock-provider/target/*.jar /opt/nsf/

#add nsf agent jar and config file
RUN mkdir -p /opt/nsf && cd /opt/nsf && \
    wget "http://console-cust56.netease.com/download/nsf-agent-v2.6.6-ae06cc43-20200417-150504.jar" -O nsf-agent.jar
ADD ./nsf-demo-stock-provider/nsf.yml /opt/nsf/nsf.yml

#add apm agent jar and config file
RUN mkdir -p /opt/apm && cd /opt/apm && \
    wget "http://console-cust56.netease.com/download/napm-java-agent-v5.3.3-190825-36860798.tar.gz" -O napm-java-agent.tar.gz && \
    tar zxvf napm-java-agent.tar.gz && \
    cd ./napm-java-agent/conf && \
    echo "endpoint=http://apm-collector-cust56.netease.com" >> napm-agent.properties && \
    echo "productId=133-poczxb" >> napm-agent.properties && \
    echo "service=stock-provider" >> napm-agent.properties

#add run command
ADD ./nsf-demo-stock-provider/run.sh  /opt/nsf/
RUN chmod a+x /opt/nsf/run.sh

CMD ["/opt/nsf/run.sh"]