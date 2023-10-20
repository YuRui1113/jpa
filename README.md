# Spring Boot中集成JPA和使用PostgreSQL数据库

### 1、相关知识
- JPA(Java Persistence API)

> JPA是Spring应用程序模型和关系数据库之间的桥梁，它用于管理和访问Spring应用程序的对象模型和数据库之间的数据。JPA 是一种抽象，用于映射java对象与数据库。JPA 不执行任何特定操作，它提供各种类型的应用程序接口而无需实现。

- Hibernate

> Hibernate是一个Java框架和ORM（对象关系映射）工具，用于提供JPA方法的实现。


### 2、开发环境

当前项目使用以下开发环境：
- 操作系统：Windows 11
- JDK 17
- 数据库：PostgreSQL 15.2
- IDE：VS Code（版本1.83.1），并安装以下插件：
  1. Extension Pack for Java
  1. Spring Boot Extension Pack

### 3、创建测试数据库

请在运行代码之前安装PostgreSQL数据库，并创建名为test的数据库。

可在命令行使用下面命令创建数据库：

    psql -h localhost -U postgres

输入postgres用户密码后登录成功，再使用下面命令创建数据库test。

    CREATE DATABASE test;
