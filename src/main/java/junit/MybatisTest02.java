package junit;

import mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import poji.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.apache.ibatis.io.Resources.getResourceAsStream;

public class MybatisTest02 {

    @Test
    public void TestSelect() throws Exception {
        //创建核心配置文件
        String resource = "sqlMapConfig.xml";
        InputStream inputStream = getResourceAsStream(resource);

        //创建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //创建sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = sqlSession.getMapper(UserMapper.class).findUserById(1);
        System.out.println(user);
    }

    @Test
    public void testQueryUserOrder() throws IOException {
        //创建核心配置文件
        String resource = "sqlMapConfig.xml";
        InputStream inputStream = getResourceAsStream(resource);
        //创建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // mybatis和spring整合，整合之后，交给spring管理
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 创建Mapper接口的动态代理对象，整合之后，交给spring管理
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 使用userMapper执行根据条件查询用户
        List<User> list = userMapper.queryUserOrder();

        for (User u : list) {
            System.out.println("一对多查询: \n" + u);
        }

        // mybatis和spring整合，整合之后，交给spring管理
        sqlSession.close();
    }

}
