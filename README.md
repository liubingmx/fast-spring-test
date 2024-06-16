Languages： English | [中文](README_CN.md)
# fast-spring-test
The unit test speed-up tool draws on spring test's support for integration testing, so that unit tests can also reuse the spring container without restarting the spring container to achieve the purpose of rapid unit testing. It supports multiple test engines junit4, junit5, testNG, etc.
  
## Architecture

![Architecture](https://github.com/liubingmx/fast-spring-test/blob/main/doc/architecture.png)

## Effects


https://github.com/liubingmx/fast-spring-test/assets/20813546/00874ed7-e1b6-483c-8d3a-e40e46116142

- The first run requires starting the running environment, which takes a long time. After the second run, the container can be reused, and the unit test execution time can be down to the millisecond level. There is no need to restart when adding a single test case. The test can be hot loaded and executed directly.

## Getting started

### Maven dependency

```xml
<properties>
    <fast.spring.test.version>1.0.3</fast.spring.test.version>
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
    Configuration configuration = Configuration.ConfigurationBuilder.builder()
            .prompt("fast-spring-test-demo => ")
            .build();
    FastSpringTest.run(configuration);
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
### All Commands
- run : Run test case,example:
    - run cn.net.fasttest.FastSpringTests#test

- show : Show the results of the last run

- rerun : Rerun last test case

- history : Show the most recently executed command

- help : Print help information for all commands

- quit : Quit.

## Contributing

- We welcome and encourage you to contribute to our projects. If you have any questions, suggestions, or want to contribute code, please contact us via email, GitHub issues, or submit a PR directly. Your participation will make this project even better!

## Reporting bugs

- [GitHub Issue](https://github.com/liubingmx/fast-spring-test/issues/new)

## License

fast-spring-test is under the Apache 2.0 license. See the [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0) file for details.