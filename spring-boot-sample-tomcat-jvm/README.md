# spring-boot-sample-tomcat
JVM调优实战

## 技术点
### 1. Spring Boot
### 2. tomcat
### 3. 压测结果
### 4. JVM

## GC常用参数

### 长链接

[对于长连接，push一类的海量服务端应用，16G内存8核心，推荐的JVM参数如下](https://mp.weixin.qq.com/s/oSO0PsM9ChMMkIPmJL9yTA)

jdk1.7


`-Xms13g -Xmx13g -Xss512k -XX:PermSize=384m -XX:MaxPermSize=384m -XX:NewSize=12g -XX:MaxNewSize=12g 
 -XX:SurvivorRatio=18 -XX:MaxDirectMemorySize=2g -XX:+UseParNewGC -XX:ParallelGCThreads=4 -XX:MaxTenuringThreshold=15 -XX:+CMSParallelRemarkEnabled -XX:+CMSScavengeBeforeRemark -XX:+UseConcMarkSweepGC
 -XX:+DisableExplicitGC -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 -XX:+ScavengeBeforeFullGC -XX:+UseCMSCompactAtFullCollection -XX:CMSFullGCsBeforeCompaction=9  -XX:+CMSClassUnloadingEnabled  -XX:CMSInitiatingPermOccupancyFraction=70 -XX:+ExplicitGCInvokesConcurrent  
 -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCApplicationConcurrentTime -XX:+PrintHeapAtGC 
 -Xloggc:/data/applogs/heap_trace.txt -XX:-HeapDumpOnOutOfMemoryError 
 -XX:HeapDumpPath=/data/applogs/HeapDumpOnOutOfMemoryError`
 
 
 jdk1.8
 
 `-Xms13g -Xmx13g -Xss512k -XX:MetaspaceSize=384m -XX:MaxMetaspaceSize=384m -XX:NewSize=11g -XX:MaxNewSize=11g -XX:SurvivorRatio=18 -XX:MaxDirectMemorySize=2g -XX:+UseParNewGC -XX:ParallelGCThreads=4 -XX:MaxTenuringThreshold=15 -XX:+UseConcMarkSweepGC -XX:+DisableExplicitGC -XX:+UseCMSInitiatingOccupancyOnly -XX:+ScavengeBeforeFullGC -XX:+CMSScavengeBeforeRemark -XX:+CMSParallelRemarkEnabled -XX:CMSInitiatingOccupancyFraction=70 -XX:+CMSClassUnloadingEnabled -XX:SoftRefLRUPolicyMSPerMB=0 -XX:-ReduceInitialCardMarks -XX:+CMSClassUnloadingEnabled -XX:+ExplicitGCInvokesConcurrent -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCApplicationConcurrentTime -XX:+PrintHeapAtGC -Xloggc:/data/applogs/heap_trace.txt -XX:-HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/data/applogs/HeapDumpOnOutOfMemoryError"`
## 工具

### GCViewer

[GCViewer](https://github.com/chewiebug/GCViewer)

#### First

1. command
`java -Xmx32m -Xss256k -verbosegc -Xlog:gc*,gc+ref=debug,gc+heap=debug,gc+age=trace:file=gc-%p-%t.log:tags,uptime,time,level:filecount=2,filesize=100m -jar spring-boot-sample-tomcat-jvm-1.0.0-SNAPSHOT.jar`

2. log `gc-16892-2019-09-02_13-56-42.log`



#### Second

1. command
`java -Xmx2048m -Xss256k -verbosegc -Xlog:gc*,gc+ref=debug,gc+heap=debug,gc+age=trace:file=gc-%p-%t.log:tags,uptime,time,level:filecount=2,filesize=100m -jar spring-boot-sample-tomcat-jvm-1.0.0-SNAPSHOT.jar`

2. log `gc-16400-2019-09-02_14-16-04.log`


## JPROFILER

[THE AWARD-WINNING ALL-IN-ONE JAVA PROFILER](https://www.ej-technologies.com/products/jprofiler/overview.html)

[JProfiler for Mac V11.0.1特别版 苹果电脑版(附注册码+破解教程)]()https://www.jb51.net/softs/609957.html)
L-J11-Everyone#speedzodiac-327a9wrs5dxvz#463a59

A-J11-Everyone#admin-3v7hg353d6idd5#9b4
