package junit;

import mapper.OrderMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import poji.Order;

import java.io.IOException;
import java.io.InputStream;

import static org.apache.ibatis.io.Resources.getResourceAsStream;

public class OrderTest {

    @Test
    public void TestSelect() throws IOException {
        String resource = "sqlMapConfig.xml";
        InputStream inputStream = getResourceAsStream(resource);

        //创建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //创建sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        Order order = sqlSession.getMapper(OrderMapper.class).findOrderById(3);
//        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
//        Order order = orderMapper.findOrderById(3);
        System.out.println("根据id查找订单: \n" + order);
    }
}
