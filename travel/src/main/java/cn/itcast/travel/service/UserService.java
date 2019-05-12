package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

public interface UserService {
    /**
     * 注册用户
     * @param user
     * @return
     */
    public boolean regist(User user);

    /**
     * 激活用户
     * @param code
     * @return
     */
    public boolean active(String code);

    /**
     * 登陆验证
     * @param user
     * @return
     */
    public User login(User user);
}
