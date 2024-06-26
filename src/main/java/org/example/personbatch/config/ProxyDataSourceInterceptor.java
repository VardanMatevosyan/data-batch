package org.example.personbatch.config;

import javax.sql.DataSource;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.springframework.stereotype.Component;

@Component
public class ProxyDataSourceInterceptor {
  private final DataSource dataSource;
  public ProxyDataSourceInterceptor(final DataSource dataSource) {
    this.dataSource = ProxyDataSourceBuilder.create(dataSource)
        .name("Batch-Insert-Logger")
        .asJson().countQuery().logQueryToSysOut().build();
  }
}
