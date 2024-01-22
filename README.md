Languages： English | [中文](README_CN.md)
# fast-spring-test
Unit testing speedup tool. Based on junit5 and drawing on spring test's support for integration testing, unit testing can also reuse spring containers to achieve the purpose of rapid single testing.
  
## Architecture

![Architecture](https://github.com/liubingmx/fast-spring-test/blob/main/doc/architecture.png)

## Effects


https://github.com/liubingmx/fast-spring-test/assets/20813546/00874ed7-e1b6-483c-8d3a-e40e46116142

- The first run requires starting the test environment, which takes a long time. The container can be reused after the second run, and the execution time of a single test can be as low as milliseconds.

## Getting started

### Maven dependency

```xml
<properties>
    <fast.spring.test.version>1.0.1</fast.spring.test.version>
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

IDE run FastSpringApplicationTests, execute the following command to start testing
```
# run com.xx.class#method
run cn.net.fasttest.FastSpringApplicationTests#test
# or
cn.net.fasttest.FastSpringApplicationTests#test
```

## Contributing

- We welcome and encourage you to contribute to our projects. If you have any questions, suggestions, or want to contribute code, please contact us via email, GitHub issues, or submit a PR directly. Your participation will make this project even better!

## Reporting bugs

- [GitHub Issue](https://github.com/liubingmx/fast-spring-test/issues/new)

## License

fast-spring-test is under the Apache 2.0 license. See the [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0) file for details.