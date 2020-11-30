package Dao;
import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import domain.User;
import utils.DataSourceUtils;

public class UserDao {
//    添加新用户
    public void addUser(User user) throws SQLException {
        String sql = "insert into user(username,password,gender,email,telephone,introduce,activecode) values(?,?,?,?,?,?,?)";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        int row = runner.update(sql, user.getUsername(), user.getPassword(),
                user.getGender(), user.getEmail(), user.getTelephone(),
                user.getIntroduce());
        if (row == 0) {
            throw new RuntimeException();
        }
    }

//    根据激活码寻找客户
    public User findUserByActiveCode(String activeCode) throws SQLException {
        String sql = "select * from user where activecode=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new BeanHandler<User>(User.class), activeCode);

    }

//    激活用户
    public void activeUser(String activeCode) throws SQLException {
        String sql = "update user set state=? where activecode=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        runner.update(sql, 1, activeCode);
    }

//    根据用户名和密码寻找用户————用于登录功能
    public User findUserByUsernameAndPassword(String username, String password) throws SQLException {
        String sql="select * from user where username=? and password=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new BeanHandler<User>(User.class),username,password);
    }

}
