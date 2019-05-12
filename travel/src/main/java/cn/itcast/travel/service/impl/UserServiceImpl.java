package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) {
        User u = dao.findByUsername(user.getUsername());
        if (u != null){
            //存在用户
            return false;
        }
        user.setCode(UuidUtil.getUuid());
        user.setStatus("N");
        dao.save(user);

        String content="<a href='http://localhost/travel/user/active?code="+user.getCode()+"'>点击激活【潍泷旅游网】账户</a>" +
                "<p>如果不是本人注册，请忽略</p>";
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");
        return true;
    }

    /**
     * 激活用户
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {
        User user = dao.findByCode(code);
        if (user != null){
            dao.updateStatus(user);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public User login(User user) {
        return dao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}
