package service;
import java.sql.SQLException;
import java.util.Date;
import javax.security.auth.login.LoginException;
import Dao.UserDao;
import domain.User;
import exception.RegisterException;
import utils.MailUtils;

public class UserService {
    private UserDao dao = new UserDao();
    // 注册操作
    public void register(User user) throws RegisterException {
        // 调用dao完成注册操作
        try {
            dao.addUser(user);
            String emailMsg = "感谢您注册山中事";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RegisterException("注冊失败");
        }
    }

    // 登录操作
    public User login(String username, String password) throws LoginException {
        try {
            //根据登录时表单输入的用户名和密码，查找用户
            User user = dao.findUserByUsernameAndPassword(username, password);
            //如果找到
            if (user != null) {
                return user;
            }
            throw new LoginException("用户名或密码错误");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new LoginException("登录失败");
        }
    }
}
