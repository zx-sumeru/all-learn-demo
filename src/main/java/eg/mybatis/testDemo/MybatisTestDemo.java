package eg.mybatis.testDemo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.Reader;

import static org.junit.Assert.assertEquals;

/**
 * @author zx
 * @date 2018/10/4 下午5:48
 *
 * mybatis 简单测试环境搭建
 */
public class MybatisTestDemo {
    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void setUp() throws Exception {
        // create an SqlSessionFactory
        try (Reader reader = Resources.getResourceAsReader("org/apache/ibatis/submitted/ancestor_ref/mybatis-config.xml")) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }

        // populate in-memory database
        BaseDataTest.runScript(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource(),
                "org/apache/ibatis/submitted/ancestor_ref/CreateDB.sql");
    }

    @Test
    public void testCircularAssociation() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            TestMapper mapper = sqlSession.getMapper(TestMapper.class);
//            User user = mapper.getUserAssociation(1);
//            assertEquals("User2", user.getFriend().getName());
        }
    }
}
