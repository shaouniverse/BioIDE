package org.big.common;

import org.hibernate.dialect.MySQLDialect;

import java.sql.Types;

/**
 * Created by WangTianshan on 2017/9/8.
 */
public class JsonMySQLDialect extends MySQLDialect {

    public JsonMySQLDialect() {
        this.registerColumnType(Types.VARCHAR, "json");
    }

}