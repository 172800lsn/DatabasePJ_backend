2025-05-31T13:05:38.549+08:00  INFO 20340 --- [vehicle-repair-system] [           main] c.e.v.VehicleRepairSystemApplication     : Starting VehicleRepairSystemApplication using Java 17.0.10 with PID 20340 (E:\temp\database\vehicle-repair-system\target\classes started by 29380 in E:\temp\database\vehicle-repair-system)
2025-05-31T13:05:38.549+08:00  INFO 20340 --- [vehicle-repair-system] [           main] c.e.v.VehicleRepairSystemApplication     : No active profile set, falling back to 1 default profile: "default"
2025-05-31T13:05:39.123+08:00  INFO 20340 --- [vehicle-repair-system] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2025-05-31T13:05:39.177+08:00  INFO 20340 --- [vehicle-repair-system] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 57 ms. Found 1 JPA repository interface.
2025-05-31T13:05:39.754+08:00  INFO 20340 --- [vehicle-repair-system] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 9090 (http)
2025-05-31T13:05:39.770+08:00  INFO 20340 --- [vehicle-repair-system] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2025-05-31T13:05:39.770+08:00  INFO 20340 --- [vehicle-repair-system] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.41]
2025-05-31T13:05:39.866+08:00  INFO 20340 --- [vehicle-repair-system] [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2025-05-31T13:05:39.866+08:00  INFO 20340 --- [vehicle-repair-system] [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1276 ms
2025-05-31T13:05:40.025+08:00  INFO 20340 --- [vehicle-repair-system] [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2025-05-31T13:05:40.074+08:00  INFO 20340 --- [vehicle-repair-system] [           main] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 6.6.15.Final
2025-05-31T13:05:40.105+08:00  INFO 20340 --- [vehicle-repair-system] [           main] o.h.c.internal.RegionFactoryInitiator    : HHH000026: Second-level cache disabled
2025-05-31T13:05:40.331+08:00  INFO 20340 --- [vehicle-repair-system] [           main] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
2025-05-31T13:05:40.360+08:00  INFO 20340 --- [vehicle-repair-system] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2025-05-31T13:05:40.535+08:00  INFO 20340 --- [vehicle-repair-system] [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection com.mysql.cj.jdbc.ConnectionImpl@57bdceaa
2025-05-31T13:05:40.535+08:00  INFO 20340 --- [vehicle-repair-system] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2025-05-31T13:05:40.583+08:00  WARN 20340 --- [vehicle-repair-system] [           main] org.hibernate.orm.deprecation            : HHH90000025: MySQLDialect does not need to be specified explicitly using 'hibernate.dialect' (remove the property setting and it will be selected by default)
2025-05-31T13:05:40.598+08:00  INFO 20340 --- [vehicle-repair-system] [           main] org.hibernate.orm.connections.pooling    : HHH10001005: Database info:
Database JDBC URL [Connecting through datasource 'HikariDataSource (HikariPool-1)']
Database driver: undefined/unknown
Database version: 8.0.26
Autocommit mode: undefined/unknown
Isolation level: undefined/unknown
Minimum pool size: undefined/unknown
Maximum pool size: undefined/unknown
2025-05-31T13:05:41.323+08:00  INFO 20340 --- [vehicle-repair-system] [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
2025-05-31T13:05:41.367+08:00  INFO 20340 --- [vehicle-repair-system] [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2025-05-31T13:05:41.572+08:00  WARN 20340 --- [vehicle-repair-system] [           main] ConfigServletWebServerApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'authController' defined in file [E:\temp\database\vehicle-repair-system\target\classes\com\example\vehiclerepairsystem\controller\AuthController.class]: Unsatisfied dependency expressed through constructor parameter 0: Error creating bean with name 'userService' defined in file [E:\temp\database\vehicle-repair-system\target\classes\com\example\vehiclerepairsystem\service\UserService.class]: Unsatisfied dependency expressed through constructor parameter 0: Error creating bean with name 'userRepository' defined in com.example.vehiclerepairsystem.repository.UserRepository defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Could not create query for public abstract java.util.Optional com.example.vehiclerepairsystem.repository.UserRepository.findByUsername(java.lang.String,java.lang.String); Reason: Failed to create query for method public abstract java.util.Optional com.example.vehiclerepairsystem.repository.UserRepository.findByUsername(java.lang.String,java.lang.String); At least 2 parameter(s) provided but only 1 parameter(s) present in query
2025-05-31T13:05:41.572+08:00  INFO 20340 --- [vehicle-repair-system] [           main] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'
2025-05-31T13:05:41.572+08:00  INFO 20340 --- [vehicle-repair-system] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...
2025-05-31T13:05:41.572+08:00  INFO 20340 --- [vehicle-repair-system] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.
2025-05-31T13:05:41.572+08:00  INFO 20340 --- [vehicle-repair-system] [           main] o.apache.catalina.core.StandardService   : Stopping service [Tomcat]
2025-05-31T13:05:41.587+08:00  INFO 20340 --- [vehicle-repair-system] [           main] .s.b.a.l.ConditionEvaluationReportLogger :

Error starting ApplicationContext. To display the condition evaluation report re-run your application with 'debug' enabled.
2025-05-31T13:05:41.603+08:00 ERROR 20340 --- [vehicle-repair-system] [           main] o.s.boot.SpringApplication               : Application run failed

org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'authController' defined in file [E:\temp\database\vehicle-repair-system\target\classes\com\example\vehiclerepairsystem\controller\AuthController.class]: Unsatisfied dependency expressed through constructor parameter 0: Error creating bean with name 'userService' defined in file [E:\temp\database\vehicle-repair-system\target\classes\com\example\vehiclerepairsystem\service\UserService.class]: Unsatisfied dependency expressed through constructor parameter 0: Error creating bean with name 'userRepository' defined in com.example.vehiclerepairsystem.repository.UserRepository defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Could not create query for public abstract java.util.Optional com.example.vehiclerepairsystem.repository.UserRepository.findByUsername(java.lang.String,java.lang.String); Reason: Failed to create query for method public abstract java.util.Optional com.example.vehiclerepairsystem.repository.UserRepository.findByUsername(java.lang.String,java.lang.String); At least 2 parameter(s) provided but only 1 parameter(s) present in query
at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:804) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.ConstructorResolver.autowireConstructor(ConstructorResolver.java:240) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.autowireConstructor(AbstractAutowireCapableBeanFactory.java:1395) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1232) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:569) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:529) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:339) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:373) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:337) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.DefaultListableBeanFactory.instantiateSingleton(DefaultListableBeanFactory.java:1222) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingleton(DefaultListableBeanFactory.java:1188) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:1123) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:987) ~[spring-context-6.2.7.jar:6.2.7]
at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:627) ~[spring-context-6.2.7.jar:6.2.7]
at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:146) ~[spring-boot-3.5.0.jar:3.5.0]
at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:753) ~[spring-boot-3.5.0.jar:3.5.0]
at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:439) ~[spring-boot-3.5.0.jar:3.5.0]
at org.springframework.boot.SpringApplication.run(SpringApplication.java:318) ~[spring-boot-3.5.0.jar:3.5.0]
at org.springframework.boot.SpringApplication.run(SpringApplication.java:1362) ~[spring-boot-3.5.0.jar:3.5.0]
at org.springframework.boot.SpringApplication.run(SpringApplication.java:1351) ~[spring-boot-3.5.0.jar:3.5.0]
at com.example.vehiclerepairsystem.VehicleRepairSystemApplication.main(VehicleRepairSystemApplication.java:11) ~[classes/:na]
Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'userService' defined in file [E:\temp\database\vehicle-repair-system\target\classes\com\example\vehiclerepairsystem\service\UserService.class]: Unsatisfied dependency expressed through constructor parameter 0: Error creating bean with name 'userRepository' defined in com.example.vehiclerepairsystem.repository.UserRepository defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Could not create query for public abstract java.util.Optional com.example.vehiclerepairsystem.repository.UserRepository.findByUsername(java.lang.String,java.lang.String); Reason: Failed to create query for method public abstract java.util.Optional com.example.vehiclerepairsystem.repository.UserRepository.findByUsername(java.lang.String,java.lang.String); At least 2 parameter(s) provided but only 1 parameter(s) present in query
at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:804) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.ConstructorResolver.autowireConstructor(ConstructorResolver.java:240) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.autowireConstructor(AbstractAutowireCapableBeanFactory.java:1395) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1232) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:569) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:529) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:339) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:373) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:337) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1682) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1628) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.ConstructorResolver.resolveAutowiredArgument(ConstructorResolver.java:913) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:791) ~[spring-beans-6.2.7.jar:6.2.7]
... 21 common frames omitted
Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'userRepository' defined in com.example.vehiclerepairsystem.repository.UserRepository defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Could not create query for public abstract java.util.Optional com.example.vehiclerepairsystem.repository.UserRepository.findByUsername(java.lang.String,java.lang.String); Reason: Failed to create query for method public abstract java.util.Optional com.example.vehiclerepairsystem.repository.UserRepository.findByUsername(java.lang.String,java.lang.String); At least 2 parameter(s) provided but only 1 parameter(s) present in query
at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1826) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:607) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:529) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:339) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:373) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:337) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1682) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1628) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.ConstructorResolver.resolveAutowiredArgument(ConstructorResolver.java:913) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:791) ~[spring-beans-6.2.7.jar:6.2.7]
... 34 common frames omitted
Caused by: org.springframework.data.repository.query.QueryCreationException: Could not create query for public abstract java.util.Optional com.example.vehiclerepairsystem.repository.UserRepository.findByUsername(java.lang.String,java.lang.String); Reason: Failed to create query for method public abstract java.util.Optional com.example.vehiclerepairsystem.repository.UserRepository.findByUsername(java.lang.String,java.lang.String); At least 2 parameter(s) provided but only 1 parameter(s) present in query
at org.springframework.data.repository.query.QueryCreationException.create(QueryCreationException.java:101) ~[spring-data-commons-3.5.0.jar:3.5.0]
at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.lookupQuery(QueryExecutorMethodInterceptor.java:120) ~[spring-data-commons-3.5.0.jar:3.5.0]
at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.mapMethodsToQuery(QueryExecutorMethodInterceptor.java:104) ~[spring-data-commons-3.5.0.jar:3.5.0]
at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.lambda$new$0(QueryExecutorMethodInterceptor.java:92) ~[spring-data-commons-3.5.0.jar:3.5.0]
at java.base/java.util.Optional.map(Optional.java:260) ~[na:na]
at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.<init>(QueryExecutorMethodInterceptor.java:92) ~[spring-data-commons-3.5.0.jar:3.5.0]
at org.springframework.data.repository.core.support.RepositoryFactorySupport.getRepository(RepositoryFactorySupport.java:434) ~[spring-data-commons-3.5.0.jar:3.5.0]
at org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport.lambda$afterPropertiesSet$4(RepositoryFactoryBeanSupport.java:350) ~[spring-data-commons-3.5.0.jar:3.5.0]
at org.springframework.data.util.Lazy.getNullable(Lazy.java:135) ~[spring-data-commons-3.5.0.jar:3.5.0]
at org.springframework.data.util.Lazy.get(Lazy.java:113) ~[spring-data-commons-3.5.0.jar:3.5.0]
at org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport.afterPropertiesSet(RepositoryFactoryBeanSupport.java:356) ~[spring-data-commons-3.5.0.jar:3.5.0]
at org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean.afterPropertiesSet(JpaRepositoryFactoryBean.java:132) ~[spring-data-jpa-3.5.0.jar:3.5.0]
at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1873) ~[spring-beans-6.2.7.jar:6.2.7]
at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1822) ~[spring-beans-6.2.7.jar:6.2.7]
... 44 common frames omitted
Caused by: java.lang.IllegalArgumentException: Failed to create query for method public abstract java.util.Optional com.example.vehiclerepairsystem.repository.UserRepository.findByUsername(java.lang.String,java.lang.String); At least 2 parameter(s) provided but only 1 parameter(s) present in query
at org.springframework.data.jpa.repository.query.PartTreeJpaQuery.<init>(PartTreeJpaQuery.java:107) ~[spring-data-jpa-3.5.0.jar:3.5.0]
at org.springframework.data.jpa.repository.query.JpaQueryLookupStrategy$CreateQueryLookupStrategy.resolveQuery(JpaQueryLookupStrategy.java:128) ~[spring-data-jpa-3.5.0.jar:3.5.0]
at org.springframework.data.jpa.repository.query.JpaQueryLookupStrategy$CreateIfNotFoundQueryLookupStrategy.resolveQuery(JpaQueryLookupStrategy.java:260) ~[spring-data-jpa-3.5.0.jar:3.5.0]
at org.springframework.data.jpa.repository.query.JpaQueryLookupStrategy$AbstractQueryLookupStrategy.resolveQuery(JpaQueryLookupStrategy.java:99) ~[spring-data-jpa-3.5.0.jar:3.5.0]
at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.lookupQuery(QueryExecutorMethodInterceptor.java:116) ~[spring-data-commons-3.5.0.jar:3.5.0]
... 56 common frames omitted
Caused by: java.lang.IllegalArgumentException: At least 2 parameter(s) provided but only 1 parameter(s) present in query
at org.springframework.util.Assert.isTrue(Assert.java:135) ~[spring-core-6.2.7.jar:6.2.7]
at org.springframework.data.jpa.repository.query.QueryParameterSetterFactory$CriteriaQueryParameterSetterFactory.create(QueryParameterSetterFactory.java:294) ~[spring-data-jpa-3.5.0.jar:3.5.0]
at org.springframework.data.jpa.repository.query.ParameterBinderFactory.createQueryParameterSetter(ParameterBinderFactory.java:146) ~[spring-data-jpa-3.5.0.jar:3.5.0]
at org.springframework.data.jpa.repository.query.ParameterBinderFactory.createSetters(ParameterBinderFactory.java:135) ~[spring-data-jpa-3.5.0.jar:3.5.0]
at org.springframework.data.jpa.repository.query.ParameterBinderFactory.createSetters(ParameterBinderFactory.java:127) ~[spring-data-jpa-3.5.0.jar:3.5.0]
at org.springframework.data.jpa.repository.query.ParameterBinderFactory.createCriteriaBinder(ParameterBinderFactory.java:73) ~[spring-data-jpa-3.5.0.jar:3.5.0]
at org.springframework.data.jpa.repository.query.PartTreeJpaQuery$QueryPreparer.getBinder(PartTreeJpaQuery.java:350) ~[spring-data-jpa-3.5.0.jar:3.5.0]
at org.springframework.data.jpa.repository.query.PartTreeJpaQuery$QueryPreparer.<init>(PartTreeJpaQuery.java:225) ~[spring-data-jpa-3.5.0.jar:3.5.0]
at org.springframework.data.jpa.repository.query.PartTreeJpaQuery$CountQueryPreparer.<init>(PartTreeJpaQuery.java:370) ~[spring-data-jpa-3.5.0.jar:3.5.0]
at org.springframework.data.jpa.repository.query.PartTreeJpaQuery.<init>(PartTreeJpaQuery.java:103) ~[spring-data-jpa-3.5.0.jar:3.5.0]
... 60 common frames omitted

[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  6.124 s
[INFO] Finished at: 2025-05-31T13:05:41+08:00
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.springframework.boot:spring-boot-maven-plugin:3.5.0:run (default-cli) on project vehicle-repair-system: Process terminated with exit code: 1 -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoExecutionException