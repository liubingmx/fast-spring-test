# fast-spring-test
  单元测试提速，以junit5为基础， 借鉴spring test对集成测试的支持，使单元测试也可以复用spring容器，不用重启spring容器，以达到快速单测的目的

  
## Architecture

![Architecture](https://github.com/liubingmx/fast-spring-test/blob/main/doc/architecture.png)

## Effects


https://github.com/liubingmx/fast-spring-test/assets/20813546/00874ed7-e1b6-483c-8d3a-e40e46116142

- 第一次运行需要启动测试环境，耗时较长， 第二次往后可复用容器，单测执行时间可至毫秒级别

## Features

* 单元测试类热加载
* 支持Junit4、Junit5测试引擎


## Getting started

### Maven dependency

```xml
<properties>
    <fast.spring.test.version>0.0.1</fast.spring.test.version>
</properties>

<dependencies>
    <dependency>
        <groupId>cn.net.fasttest</groupId>
        <artifactId>fast-spring-test</artifactId>
        <scope>test</scope>
        <version>${fast.spring.test.version}</version>
    </dependency>
</dependencies>
```

### Add test entry

```java
public class FastSpringApplicationTests {

  @Test
  public void test() {

    System.out.println("test...");
    Assertions.assertTrue(true);
  }

  public static void main(String[] args) {
    FastSpringTest.run();
  }
}
```

IDE run FastSpringApplicationTests
