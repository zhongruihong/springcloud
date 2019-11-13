package com.rui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * 
 Spring Cloud Gateway 提供了一种默认转发的能力，只要将 Spring Cloud Gateway 注册到服务中心，
 Spring Cloud Gateway 默认就会代理服务中心的所有服务
 Spring Cloud Gateway 是通过 Spring WebFlux 的 HandlerMapping做为底层支持来匹配到转发路由，
 Spring Cloud Gateway 内置了很多 Predicates 工厂
  Spring Cloud Gateway 注册到服务中心之后，网关会自动代理所有的在注册中心的服务
   这些 Predicates 工厂通过不同的 HTTP 请求参数来匹配，多个 Predicates 工厂可以组合使用。
 Predicate 来源于 Java 8，是 Java 8 中引入的一个函数，Predicate 接受一个输入参数，返回一个布尔值结果。
  该接口包含多种默认方法来将 Predicate 组合成其他复杂的逻辑
  在 Spring Cloud Gateway 中 Spring 利用 Predicate 的特性实现了各种路由匹配规则，
  有通过 Header、请求参数等不同的条件来进行作为条件匹配到对应的路由:
  1、通过时间匹配
  Predicate 支持设置一个时间，在请求进行转发的时候，可以通过判断在这个时间之前或者之后进行转发。
     比如我们现在设置只有在2019年1月1日才会转发到我的网站，在这之前不进行转发，我就可以这样配置：
spring:
  cloud:
    gateway:
      routes:
       - id: time_route
        uri: http://ityouknow.com
        predicates:
         - After=2018-01-20T06:06:06+08:00[Asia/Shanghai]
   Spring 是通过 ZonedDateTime 来对时间进行的对比，ZonedDateTime 是 Java 8 中日期时间功能里，
        用于表示带时区的日期与时间信息的类，ZonedDateTime 支持通过时区来设置时间，中国的时区是：Asia/Shanghai
   After Route Predicate 是指在这个时间之后的请求都转发到目标地址。
   Before Route Predicate 刚好相反，在某个时间之前的请求的请求都进行转发：- Before=2018-01-20T06:06:06+08:00[Asia/Shanghai]
       除过在时间之前或者之后外，Gateway 还支持限制路由请求在某一个时间段范围内，可以使用 Between Route Predicate 来实现：
    - Between=2018-01-20T06:06:06+08:00[Asia/Shanghai], 2019-01-20T06:06:06+08:00[Asia/Shanghai]
  2、通过 Cookie 匹配
spring:
  cloud:
    gateway:
      routes:
       - id: cookie_route
         uri: http://ityouknow.com
         predicates:
         - Cookie=ityouknow, kee.e
 Cookie Route Predicate 可以接收两个参数，一个是 Cookie name ,一个是正则表达式，
   路由规则会通过获取对应的 Cookie name 值和正则表达式去匹配，如果匹配上就会执行路由，如果没有匹配上则不执行。
  3、通过 Header 属性匹配
spring:
  cloud:
    gateway:
      routes:
      - id: header_route
        uri: http://ityouknow.com
        predicates:
        - Header=X-Request-Id, \d+
  Header Route Predicate 和 Cookie Route Predicate 一样，也是接收 2 个参数，一个 header 中属性名称和一个正则表达式，这个属性值和正则表达式匹配则执行。
  4、通过 Host 匹配
spring:
  cloud:
    gateway:
      routes:
      - id: host_route
        uri: http://ityouknow.com
        predicates:
        - Host=**.ityouknow.com
 Host Route Predicate 接收一组参数，一组匹配的域名列表，这个模板是一个 ant 分隔的模板，用.号作为分隔符。它通过参数中的主机地址作为匹配规则。
  5、通过请求方式匹配
  可以通过是 POST、GET、PUT、DELETE 等不同的请求方式来进行路由。
spring:
  cloud:
    gateway:
      routes:
      - id: method_route
        uri: http://ityouknow.com
        predicates:
        - Method=GET
 6、通过请求路径匹配
 Path Route Predicate 接收一个匹配路径的参数来判断是否走路由。 
spring:
  cloud:
    gateway:
      routes:
      - id: host_route
        uri: http://ityouknow.com
        predicates:
        - Path=/foo/{segment}
  如果请求路径符合要求，则此路由将匹配，例如：/foo/1 或者 /foo/bar。
 7、通过请求参数匹配
 Query Route Predicate 支持传入两个参数，一个是属性名一个为属性值，属性值可以是正则表达式。
spring:
  cloud:
    gateway:
      routes:
      - id: query_route
        uri: http://ityouknow.com
        predicates:
        - Query=smile
  还可以将 Query 的值以键值对的方式进行配置，这样在请求过来时会对属性值和正则进行匹配，匹配上才会走路由。
  spring:
  cloud:
    gateway:
      routes:
      - id: query_route
        uri: http://ityouknow.com
        predicates:
        - Query=keep, pu.
 8、通过请求 ip 地址进行匹配
 Predicate 也支持通过设置某个 ip 区间号段的请求才会路由，
 RemoteAddr Route Predicate 接受 cidr 符号(IPv4 或 IPv6 )字符串的列表(最小大小为1)，例如 192.168.0.1/16 (其中 192.168.0.1 是 IP 地址，16 是子网掩码)。
spring:
  cloud:
    gateway:
      routes:
      - id: remoteaddr_route
        uri: http://ityouknow.com
        predicates:
        - RemoteAddr=192.168.1.1/24
 
 9、组合使用
spring:
  cloud:
    gateway:
      routes:
       - id: host_foo_path_headers_to_httpbin
        uri: http://ityouknow.com
        predicates:
        - Host=**.foo.org
        - Path=/headers
        - Method=GET
        - Header=X-Request-Id, \d+
        - Query=foo, ba.
        - Query=baz
        - Cookie=chocolate, ch.p
        - After=2018-01-20T06:06:06+08:00[Asia/Shanghai]
 *
 */
@SpringBootApplication
public class GateWayApplication {
	public static void main(String[] args) {
		SpringApplication.run(GateWayApplication.class, args);
	}
	/**
	 * byJava
	 */
	//http://localhost:8888/about  ->http://ityouknow.com/about
	  @Bean public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
	  return builder.routes().route("path_route", r ->
	  r.path("/about").uri("http://ityouknow.com")).build(); }
}