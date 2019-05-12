package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface UserDao {
    /**
     * 用户名查询用户
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 保存用户
     * @param user
     */
    public void save(User user);

    /**
     * 激活用户——根据激活码查询用户
     * @param code
     * @return
     */
    public User findByCode(String code);

    /**
     * 激活用户——修改用户激活状态
     * @param user
     */
    public void updateStatus(User user);

    /**
     * 登陆验证用户名与密码
     * @param username
     * @param password
     * @return
     */
    public User findByUsernameAndPassword(String username,String password);
}
