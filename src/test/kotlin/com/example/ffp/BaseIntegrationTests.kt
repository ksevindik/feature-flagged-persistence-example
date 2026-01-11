package com.example.ffp

import com.zaxxer.hikari.pool.HikariProxyConnection
import jakarta.persistence.EntityManager
import org.h2.jdbc.JdbcConnection
import org.h2.tools.Server
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.datasource.DataSourceUtils
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import javax.sql.DataSource

@SpringBootTest
@Transactional
@ActiveProfiles("test")
abstract class BaseIntegrationTests {

    @Autowired
    protected lateinit var dataSource: DataSource

    @Autowired
    protected lateinit var entityManager: EntityManager

    fun openDBConsole() {
        Server.startWebServer(
            (DataSourceUtils.getConnection(dataSource) as HikariProxyConnection)
                .unwrap(JdbcConnection::class.java))
    }

    fun flushAndClear() {
        entityManager.flush()
        entityManager.clear()
    }

}
