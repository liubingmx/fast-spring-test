
Languages： 中文 [English](README.md)
# fast-spring-test
单元测试提速，以junit5为基础， 借鉴spring test对集成测试的支持，使单元测试也可以复用spring容器，不用重启spring容器，以达到快速单测的目的

## 使用效果


https://github.com/liubingmx/fast-spring-test/assets/20813546/00874ed7-e1b6-483c-8d3a-e40e46116142

- 第一次运行需要启动测试环境，耗时较长， 第二次往后可复用容器，单测执行时间可至毫秒级别

## 快速开始

### Maven 依赖

```xml
<properties>
    <fast.spring.test.version>0.0.2</fast.spring.test.version>
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

### 增加应用测试入口

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

在你的IDE里运行 FastSpringApplicationTests，然后通过以下命令行执行单元测试
```
# run com.xx.class#method
run cn.net.fasttest.FastSpringApplicationTests#test
# 或者
cn.net.fasttest.FastSpringApplicationTests#test
```

## Contributing

- 我们非常欢迎并鼓励您为我们的项目做出贡献。如果您有任何问题、建议或想要贡献代码，请通过邮件、GitHub issues或直接提交PR与我们联系。您的参与将使这个项目更加完美！

## 提交bug

- [GitHub Issue](https://github.com/liubingmx/fast-spring-test/issues/new)
