package mapper;

import poji.User;

import java.util.List;

public interface UserMapper {

    public User findUserById(Integer id);

    List<User> queryUserOrder();
}