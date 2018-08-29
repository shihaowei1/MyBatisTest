package junit;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import poji.User;


import java.io.InputStream;
import java.util.Date;
import java.util.List;

import static org.apache.ibatis.io.Resources.getResourceAsStream;

public class MyBatisFirstTest {

    @Test
    public void TestSelect() throws Exception {

        //创建核心配置文件
        String resource = "sqlMapConfig.xml";
        InputStream inputStream = getResourceAsStream(resource);

        //创建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //创建sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //执行sql语句
        User user = sqlSession.selectOne("test.findUserById", 10);

        System.out.println("根据用户id查询用户: \n" + user);

        List<User> userList = sqlSession.selectList("test.findUserByUsername", '五');
        System.out.println("根据用户名查询用户列表：");
        for (User list : userList) {
            System.out.println(list);
        }
    }

    @Test
    public void TestInsert() throws Exception {

        //创建核心配置文件
        String resource = "sqlMapConfig.xml";
        InputStream inputStream = getResourceAsStream(resource);

        //创建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //创建sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setId(31);
        user.setUsername("大浩浩");
        user.setBirthday(new Date());
        user.setSex("1");
        user.setAddress("江苏南通");

        int i = sqlSession.insert("test.updateUserById", user);
        sqlSession.commit();//提交事务
        System.out.println("更新用户的结果：" + i);
    }

    @Test
    public void TestUpdate() throws Exception {

        //创建核心配置文件
        String resource = "sqlMapConfig.xml";
        InputStream inputStream = getResourceAsStream(resource);

        //创建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //创建sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setUsername("小浩浩");
        user.setBirthday(new Date());
        user.setSex("1");
        user.setAddress("江苏南京");

        int i = sqlSession.insert("test.insertUser", user);
        sqlSession.commit();//提交事务
        System.out.println("插入用户的结果：" + i);
        System.out.println("用户返回的id：" + user.getId());
    }

    @Test
    public void TestDelete() throws Exception {

        //创建核心配置文件
        String resource = "sqlMapConfig.xml";
        InputStream inputStream = getResourceAsStream(resource);

        //创建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //创建sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //执行sql语句
        int i = sqlSession.delete("test.deleteUserByUsername", "大浩浩");

        sqlSession.commit();

        System.out.println("根据用户名删除用户: " + i);

    }
}