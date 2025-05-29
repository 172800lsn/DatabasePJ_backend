PS E:\temp\database\vehicle-repair-system> mvn spring-boot:run
[INFO] Scanning for projects...
[INFO]
[INFO] -----------------< com.example:vehicle-repair-system >------------------
[INFO] Building vehicle-repair-system 0.0.1-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] >>> spring-boot:3.5.0:run (default-cli) > test-compile @ vehicle-repair-system >>>
[INFO]
[INFO] --- resources:3.3.1:resources (default-resources) @ vehicle-repair-system ---
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO] Copying 0 resource from src\main\resources to target\classes
[INFO]
[INFO] --- compiler:3.14.0:compile (default-compile) @ vehicle-repair-system ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] --- resources:3.3.1:testResources (default-testResources) @ vehicle-repair-system ---
[INFO] skip non existing resourceDirectory E:\temp\database\vehicle-repair-system\src\test\resources
[INFO]
[INFO] --- compiler:3.14.0:testCompile (default-testCompile) @ vehicle-repair-system ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] <<< spring-boot:3.5.0:run (default-cli) < test-compile @ vehicle-repair-system <<<
[INFO]
[INFO]
[INFO] --- spring-boot:3.5.0:run (default-cli) @ vehicle-repair-system ---
[INFO] Attaching agents: []

.   ____          _            __ _ _
/\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
\\/  ___)| |_)| | | | | || (_| |  ) ) ) )
'  |____| .__|_| |_|_| |_\__, | / / / /
=========|_|==============|___/=/_/_/_/

:: Spring Boot ::                (v3.5.0)

2025-05-29T20:46:43.585+08:00  INFO 20612 --- [vehicle-repair-system] [           main] c.e.v.VehicleRepairSystemApplication     : Starting VehicleRepairSystemApplication using Java 17.0.10 with PID 20612 (E:\temp\database\vehicle-repair-system\target\classes started by 29380 in E:\temp\database\vehicle-repair-system)
2025-05-29T20:46:43.587+08:00  INFO 20612 --- [vehicle-repair-system] [           main] c.e.v.VehicleRepairSystemApplication     : No active profile set, falling back to 1 default profile: "default"
2025-05-29T20:46:44.107+08:00  INFO 20612 --- [vehicle-repair-system] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2025-05-29T20:46:44.159+08:00  INFO 20612 --- [vehicle-repair-system] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 44 ms. Found 1 JPA repository interface.
2025-05-29T20:46:44.640+08:00  INFO 20612 --- [vehicle-repair-system] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2025-05-29T20:46:44.654+08:00  INFO 20612 --- [vehicle-repair-system] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2025-05-29T20:46:44.655+08:00  INFO 20612 --- [vehicle-repair-system] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.41]
2025-05-29T20:46:44.751+08:00  INFO 20612 --- [vehicle-repair-system] [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2025-05-29T20:46:44.753+08:00  INFO 20612 --- [vehicle-repair-system] [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1128 ms
2025-05-29T20:46:44.913+08:00  INFO 20612 --- [vehicle-repair-system] [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2025-05-29T20:46:44.947+08:00  INFO 20612 --- [vehicle-repair-system] [           main] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 6.6.15.Final
2025-05-29T20:46:44.969+08:00  INFO 20612 --- [vehicle-repair-system] [           main] o.h.c.internal.RegionFactoryInitiator    : HHH000026: Second-level cache disabled
2025-05-29T20:46:45.172+08:00  INFO 20612 --- [vehicle-repair-system] [           main] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
2025-05-29T20:46:45.194+08:00  INFO 20612 --- [vehicle-repair-system] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2025-05-29T20:46:45.332+08:00  INFO 20612 --- [vehicle-repair-system] [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection com.mysql.cj.jdbc.ConnectionImpl@41143873
2025-05-29T20:46:45.334+08:00  INFO 20612 --- [vehicle-repair-system] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2025-05-29T20:46:45.383+08:00  WARN 20612 --- [vehicle-repair-system] [           main] org.hibernate.orm.deprecation            : HHH90000025: MySQLDialect does not need to be specified explicitly using 'hibernate.dialect' (remove the property setting and it will be selected by default)
2025-05-29T20:46:45.401+08:00  INFO 20612 --- [vehicle-repair-system] [           main] org.hibernate.orm.connections.pooling    : HHH10001005: Database info:
Database JDBC URL [Connecting through datasource 'HikariDataSource (HikariPool-1)']
Database driver: undefined/unknown
Database version: 8.0.26
Autocommit mode: undefined/unknown
Isolation level: undefined/unknown
Minimum pool size: undefined/unknown
Maximum pool size: undefined/unknown
2025-05-29T20:46:46.081+08:00  INFO 20612 --- [vehicle-repair-system] [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
2025-05-29T20:46:46.120+08:00  INFO 20612 --- [vehicle-repair-system] [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2025-05-29T20:46:46.339+08:00  WARN 20612 --- [vehicle-repair-system] [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2025-05-29T20:46:46.594+08:00  INFO 20612 --- [vehicle-repair-system] [           main] r$InitializeUserDetailsManagerConfigurer : Global AuthenticationManager configured with UserDetailsService bean with name inMemoryUserDetailsManager
2025-05-29T20:46:46.719+08:00  INFO 20612 --- [vehicle-repair-system] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/'
2025-05-29T20:46:46.727+08:00  INFO 20612 --- [vehicle-repair-system] [           main] c.e.v.VehicleRepairSystemApplication     : Started VehicleRepairSystemApplication in 3.534 seconds (process running for 3.886)