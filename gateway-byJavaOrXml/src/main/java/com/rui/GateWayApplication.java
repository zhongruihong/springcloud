package com.rui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * 
 Spring Cloud Gateway �ṩ��һ��Ĭ��ת����������ֻҪ�� Spring Cloud Gateway ע�ᵽ�������ģ�
 Spring Cloud Gateway Ĭ�Ͼͻ����������ĵ����з���
 Spring Cloud Gateway ��ͨ�� Spring WebFlux �� HandlerMapping��Ϊ�ײ�֧����ƥ�䵽ת��·�ɣ�
 Spring Cloud Gateway �����˺ܶ� Predicates ����
  Spring Cloud Gateway ע�ᵽ��������֮�����ػ��Զ��������е���ע�����ĵķ���
   ��Щ Predicates ����ͨ����ͬ�� HTTP ���������ƥ�䣬��� Predicates �����������ʹ�á�
 Predicate ��Դ�� Java 8���� Java 8 �������һ��������Predicate ����һ���������������һ������ֵ�����
  �ýӿڰ�������Ĭ�Ϸ������� Predicate ��ϳ��������ӵ��߼�
  �� Spring Cloud Gateway �� Spring ���� Predicate ������ʵ���˸���·��ƥ�����
  ��ͨ�� Header����������Ȳ�ͬ��������������Ϊ����ƥ�䵽��Ӧ��·��:
  1��ͨ��ʱ��ƥ��
  Predicate ֧������һ��ʱ�䣬���������ת����ʱ�򣬿���ͨ���ж������ʱ��֮ǰ����֮�����ת����
     ����������������ֻ����2019��1��1�ղŻ�ת�����ҵ���վ������֮ǰ������ת�����ҾͿ����������ã�
spring:
  cloud:
    gateway:
      routes:
       - id: time_route
        uri: http://ityouknow.com
        predicates:
         - After=2018-01-20T06:06:06+08:00[Asia/Shanghai]
   Spring ��ͨ�� ZonedDateTime ����ʱ����еĶԱȣ�ZonedDateTime �� Java 8 ������ʱ�书���
        ���ڱ�ʾ��ʱ����������ʱ����Ϣ���࣬ZonedDateTime ֧��ͨ��ʱ��������ʱ�䣬�й���ʱ���ǣ�Asia/Shanghai
   After Route Predicate ��ָ�����ʱ��֮�������ת����Ŀ���ַ��
   Before Route Predicate �պ��෴����ĳ��ʱ��֮ǰ����������󶼽���ת����- Before=2018-01-20T06:06:06+08:00[Asia/Shanghai]
       ������ʱ��֮ǰ����֮���⣬Gateway ��֧������·��������ĳһ��ʱ��η�Χ�ڣ�����ʹ�� Between Route Predicate ��ʵ�֣�
    - Between=2018-01-20T06:06:06+08:00[Asia/Shanghai], 2019-01-20T06:06:06+08:00[Asia/Shanghai]
  2��ͨ�� Cookie ƥ��
spring:
  cloud:
    gateway:
      routes:
       - id: cookie_route
         uri: http://ityouknow.com
         predicates:
         - Cookie=ityouknow, kee.e
 Cookie Route Predicate ���Խ�������������һ���� Cookie name ,һ����������ʽ��
   ·�ɹ����ͨ����ȡ��Ӧ�� Cookie name ֵ��������ʽȥƥ�䣬���ƥ���Ͼͻ�ִ��·�ɣ����û��ƥ������ִ�С�
  3��ͨ�� Header ����ƥ��
spring:
  cloud:
    gateway:
      routes:
      - id: header_route
        uri: http://ityouknow.com
        predicates:
        - Header=X-Request-Id, \d+
  Header Route Predicate �� Cookie Route Predicate һ����Ҳ�ǽ��� 2 ��������һ�� header ���������ƺ�һ��������ʽ���������ֵ��������ʽƥ����ִ�С�
  4��ͨ�� Host ƥ��
spring:
  cloud:
    gateway:
      routes:
      - id: host_route
        uri: http://ityouknow.com
        predicates:
        - Host=**.ityouknow.com
 Host Route Predicate ����һ�������һ��ƥ��������б����ģ����һ�� ant �ָ���ģ�壬��.����Ϊ�ָ�������ͨ�������е�������ַ��Ϊƥ�����
  5��ͨ������ʽƥ��
  ����ͨ���� POST��GET��PUT��DELETE �Ȳ�ͬ������ʽ������·�ɡ�
spring:
  cloud:
    gateway:
      routes:
      - id: method_route
        uri: http://ityouknow.com
        predicates:
        - Method=GET
 6��ͨ������·��ƥ��
 Path Route Predicate ����һ��ƥ��·���Ĳ������ж��Ƿ���·�ɡ� 
spring:
  cloud:
    gateway:
      routes:
      - id: host_route
        uri: http://ityouknow.com
        predicates:
        - Path=/foo/{segment}
  �������·������Ҫ�����·�ɽ�ƥ�䣬���磺/foo/1 ���� /foo/bar��
 7��ͨ���������ƥ��
 Query Route Predicate ֧�ִ�������������һ����������һ��Ϊ����ֵ������ֵ������������ʽ��
spring:
  cloud:
    gateway:
      routes:
      - id: query_route
        uri: http://ityouknow.com
        predicates:
        - Query=smile
  �����Խ� Query ��ֵ�Լ�ֵ�Եķ�ʽ�������ã��������������ʱ�������ֵ���������ƥ�䣬ƥ���ϲŻ���·�ɡ�
  spring:
  cloud:
    gateway:
      routes:
      - id: query_route
        uri: http://ityouknow.com
        predicates:
        - Query=keep, pu.
 8��ͨ������ ip ��ַ����ƥ��
 Predicate Ҳ֧��ͨ������ĳ�� ip ����Ŷε�����Ż�·�ɣ�
 RemoteAddr Route Predicate ���� cidr ����(IPv4 �� IPv6 )�ַ������б�(��С��СΪ1)������ 192.168.0.1/16 (���� 192.168.0.1 �� IP ��ַ��16 ����������)��
spring:
  cloud:
    gateway:
      routes:
      - id: remoteaddr_route
        uri: http://ityouknow.com
        predicates:
        - RemoteAddr=192.168.1.1/24
 
 9�����ʹ��
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