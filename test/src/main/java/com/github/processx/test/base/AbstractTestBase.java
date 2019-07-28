/**
 * GitHub. Inc.
 *
 * <p>Copyright (c) 2018-2019 All Rights Reserved.
 */
package com.github.processx.test.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 基类test 主要负责把test.xml加载起来
 *
 * @author zhanggangbo
 * @version v 0.1 2019/7/28 15:16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/test.xml"})
public abstract class AbstractTestBase {}
