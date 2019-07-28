/**
 * GitHub. Inc.
 *
 * <p>Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.dal.batch.mapper;

import java.lang.reflect.Method;
import java.util.List;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.util.CollectionUtils;

/**
 * MyBatis批量操作
 *
 * @author zhanggangbo
 * @version v 0.1 2019/7/28 21:23
 */
public class BatchMapperImpl implements BatchMapper {
  /** sqlSessionFactoryBean */
  private SqlSessionFactory sqlSessionFactory;

  /**
   * Mapper批量操作
   *
   * @param mClass
   * @param method
   * @param data
   * @param <C>
   * @param <T>
   * @return
   */
  @Override
  public <C, T> boolean batch(Class<C> mClass, String method, List<T> data) {
    if (CollectionUtils.isEmpty(data)) {
      return false;
    }

    // 新获取一个模式为BATCH,自动提交为false的SqlSession
    SqlSession batchSqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
    // 通过新的SqlSession获取Mapper
    C mapper = batchSqlSession.getMapper(mClass);
    // 获取处理数据类型
    Class paramType = data.get(0).getClass();
    try {
      Method mapperMethod = mapper.getClass().getDeclaredMethod(method, paramType);

      int batchCount = 1000;
      for (int index = 1; index <= data.size(); index++) {
        mapperMethod.invoke(mapper, data.get(index - 1));
        // 每批commit的个数
        if (index % batchCount == 0 || index == data.size()) {
          // 手动提交,提交后无法回滚
          batchSqlSession.commit();
          // 清除缓存防止溢出
          batchSqlSession.clearCache();
        }
      }

    } catch (Exception e) {
      batchSqlSession.rollback();
      return false;
    } finally {
      if (batchSqlSession != null) {
        batchSqlSession.close();
      }
    }

    return true;
  }

  /**
   * sqlSessionFactory注入
   *
   * @param sqlSessionFactory
   */
  public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }
}
