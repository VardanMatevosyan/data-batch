# data-batch
Spring Batch data process


#### Only for local use cases. 
#### Uncomment when debugging batch processing with ProxyDataSourceInterceptor class.

``` groovy
    implementation 'com.github.gavlyukovskiy:datasource-proxy-spring-boot-starter:1.9.1'
```
``` java
    @Component
    public class ProxyDataSourceInterceptor {
    private final DataSource dataSource;
        public ProxyDataSourceInterceptor(final DataSource dataSource) {
            this.dataSource = ProxyDataSourceBuilder.create(dataSource)
            .name("Batch-Insert-Logger")
            .asJson().countQuery().logQueryToSysOut().build();
        }
    }
```
