# 微服务

## 1. spring-cloud升级

- 服务注册中心：~~Eureka~~ Zookeeper Consul Nacos
- 服务调用：Ribbon LoadBalancer ~~Feign~~ OpenFeign
- 服务降级：~~Hystrix~~ sentienl
- 服务网关：~~Zuul~~ gateway
- 服务配置：~~Config~~ Apollo Nacos
- 服务总线：~~Bus~~ Nacos

## 2. 版本选择

- spring cloud netflix 停更
- spring-boot和spring-cloud和spring-cloud-alibaba版本选择[](https://start.spring.io/actuator/info)

- sprin-cloud Hoxton.SR3对应版本spring-boot

  [](https://cloud.spring.io/spring-cloud-static/Hoxton.SR3/reference/html/spring-cloud.html)

  ![image-20200324164545910](imgs/image-20200324164545910.png)

- 

## 3. springboot整合springcloud和springcloud alibaba

- Doubbo->spring cloud netflix->springcloud alibaba

- Maven DependenceManagement 在父工程中统一声明子工程依赖jar包的版本

- 用DevTools热部署项目，这样就可以你不用管每次改完项目都重启项目了

- 提取项目中公共的部分，作为一个公共的Module，在其他子工程中引入这个共用的Module

- 关键依赖

  ```xml
  	   <spring.boot.version>2.2.5.RELEASE</spring.boot.version>
          <spring.cloud.version>Hoxton.SR3</spring.cloud.version>
          <spring.cloud.alibaba.version>2.2.0.RELEASE</spring.cloud.alibaba.version>          
  			<dependency>
                  <groupId>org.springframework.boot</groupId>
                  <artifactId>spring-boot-dependencies</artifactId>
                  <version>${spring.boot.version}</version>
                  <type>pom</type>
                  <scope>import</scope>
              </dependency>
              <dependency>
                  <groupId>org.springframework.cloud</groupId>
                  <artifactId>spring-cloud-dependencies</artifactId>
                  <version>${spring.cloud.version}</version>
                  <type>pom</type>
                  <scope>import</scope>
              </dependency>
              <dependency>
                  <groupId>com.alibaba.cloud</groupId>
                  <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                  <version>${spring.cloud.alibaba.version}</version>
                  <type>pom</type>
                  <scope>import</scope>
              </dependency>
  ```

## 4. 服务注册

### 1. Eureka

- 服务治理：管理服务与服务之间的依赖关系，可以实现服务调用、负载均衡、容错等，实现服务的注册与发现

-  Spring Cloud封装了Netflix公司的Eureka模块来实现服务治理

- Eureka Server 提供服务注册服务  （默认最多等90秒）

- Eureka Client 通过注册中心进行访问 （默认心跳周期30秒）

- 微服务RPC远程调用的核心：高可用

- @EnableEurekaServer @EnableEurekaClient @LoadBalanced

- 集群：关键让注册中心互相发现

- Eureka自我保护：某时刻某个微服务不可用了，不回被立刻清理（AP）

### 2. Zookeeper

### 3. Consul

- go语言开发
- 服务注册与发现
- 可视化web界面

### 4. 对比

| 服务注册  | 语言 | CAP            | 控制台管理 | 社区活跃度 |
| --------- | ---- | -------------- | ---------- | ---------- |
| Eureka    | java | AP             | 支持       | 低         |
| Zookeeper | java | CP             | 不支持     | 中         |
| Consul    | go   | CP             | 支持       | 高         |
| nacos     | java | 支持AP和CP切换 | 支持       | 高         |

## 5. 服务配置

- 每个服务都需要必要的配置文件，所以集中式、动态的配置管理是必要的
- 为不同的环境做不同的配置

### 1. SpringCloud Config

- 与GitHub整合

- ***坑爹的6666端口***  chrome报错ERR_UNSAFE_PORT

- 最好使用5xxx 7xxx 8xxx 9xxx端口

- ssh 密钥格式（PEM），否则Spring Cloud Config不会很好地读取Git配置

- 动态刷新配置客户端

  ```shell
  @RefreshScope 
  手动 curl -X POST "http://localhost:5001/actuator/refresh"
  ```

- 配合 Bus消息总线实现配置动态刷新

### 2. Nacos

- 阿里巴巴 2018
- 超过10万用户，经住了双11的考验
- 服务注册与发现 配置管理 消息驱动
- 默认8848端口
- 支持切换AP和Cp 在做服务注册中心选型时，就不必操心AP选什么组件，CP选什么组件
- @RefreshScope 动态刷新客户端取得的配置
- 分类配置：Namespace+Group+Data ID
- Nacos+nginx+mysql集群配置
- 最新发布的Nacos 1.2.0版本已经支持了服务发现和配置管理的权限控制

### 3. Apollo

- 携程2016 分布式配置中心
- 默认端口 8070 8080 8090
- 涉及端口太多，容易搞混
- 配置修改实时生效（热发布）
- 版本发布管理
- 灰度发布
- 权限管理、发布审核、操作审计
- Config Server 配置读取、推送，服务对象是apollo客户端
- Admin Server 配置修改、发布，服务对象是Apollo Portal（管理页面 ）
- Meta Server  **简单来说，我们可以把 Apollo Meta Server 当成注册中心，通过它可以获得到所有 Admin Service 和 Config Service 的地址。不过要注意哈，Meta Server 也是一个环境对应一个。（Apollo官网）**
- FAQ
  
  - apollo报：系统出错，请重试或联系系统负责人 在ApolloConfigDB中修改eureka.server.uri
  
  - idea application.yml不自动补全apollo的配置
- 核心概念 应用->环境->集群

-  Apollo相比于Spring Cloud Config有什么优势？（来自Apollo官网）

Spring Cloud Config的精妙之处在于它的配置存储于Git，这就天然的把配置的修改、权限、版本等问题隔离在外。通过这个设计使得Spring Cloud Config整体很简单，不过也带来了一些不便之处。

下面尝试做一个简单的小结：

| 功能点           | Apollo                                                       | Spring Cloud Config                                  | 备注                                                         |
| ---------------- | ------------------------------------------------------------ | ---------------------------------------------------- | ------------------------------------------------------------ |
| 配置界面         | 一个界面管理不同环境、不同集群配置                           | 无，需要通过git操作                                  |                                                              |
| 配置生效时间     | 实时                                                         | 重启生效，或手动refresh生效                          | Spring Cloud Config需要通过Git webhook，加上额外的消息队列才能支持实时生效 |
| 版本管理         | 界面上直接提供发布历史和回滚按钮                             | 无，需要通过git操作                                  |                                                              |
| 灰度发布         | 支持                                                         | 不支持                                               |                                                              |
| 授权、审核、审计 | 界面上直接支持，而且支持修改、发布权限分离                   | 需要通过git仓库设置，且不支持修改、发布权限分离      |                                                              |
| 实例配置监控     | 可以方便的看到当前哪些客户端在使用哪些配置                   | 不支持                                               |                                                              |
| 配置获取性能     | 快，通过数据库访问，还有缓存支持                             | 较慢，需要从git clone repository，然后从文件系统读取 |                                                              |
| 客户端支持       | 原生支持所有Java和.Net应用，提供API支持其它语言应用，同时也支持Spring annotation获取配置 | 支持Spring应用，提供annotation获取配置               | Apollo的适用范围更广一些                                     |



## 5. 实操

github地址：[](https://github.com/tishq/springcloud-springcloud-alibaba-apollo)

## 6. 总结

- 重点学习了阿里的Nacos和携程的apollo，有中文官方文档，很方便学习。16年有了携程的Apollo，18年有了阿里的Nacos。其实很早之前，阿里就有Double，因为种种原因，Double停更了五年，让SpringCloud有了机会。又因为种种原因SpringCloudNetflix停更了，阿里的机会又来了。18年SpringCloudAlibaba发布，Nacos就是其中重要的组件之一。

- Apollo和Nacos都可以作为分布式配置中心，Nacos还能作为微服务注册中心。不仅如此，Nacos作为注册中心还支持AP和CP切换，一个组件实现了两个组件的功能，Nacos他能不香吗。
- Apollo和Nacos都有配置管理界面，nacos的界面更加简洁一些，不过apollo的权限管理做得更好一些。1.2.0以前的Nacos不支持权限管理。
- 部署apollo有一点让人很不舒服，你部署一套apollo至少需要（默认8080 8070 8090）三个端口 ，两个数据库。
- Apollo比较好的一点是官方文档写的十分细致，对新手特别友好。Nacos的官方文档则十分简洁。
- Apollo和Nacos都只依赖Mysql

综上，虽然Apollo很有优秀，github start数20k，但是我更看好Nacos一些。
