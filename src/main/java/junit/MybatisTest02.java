package junit;

import mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import poji.User;

import java.io.InputStream;

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
}
