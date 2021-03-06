package com.iycc.mybatis.v1.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by iycc on 2018/4/4.
 */
public class MapperProxy<T> implements InvocationHandler {
    private SqlSession sqlSession;
    private Class<T> mapperInterface;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getDeclaringClass().getName().equals(Configuration.TestMapperXml.nameSpace)) {
            String sql = Configuration.TestMapperXml.methodSqlMapping.get(method.getName());
            System.out.println(String.format("SQL [ %s ], parameter [%s] ", sql, args[0]));
            return sqlSession.selectOne(sql, String.valueOf(args[0]));
        }
        return null;
    }
}