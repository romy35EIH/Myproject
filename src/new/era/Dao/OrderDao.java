package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import domain.Order;
import domain.User;
import utils.DataSourceUtils;
/**
 * 订单
 * @author admin
 *
 */
public class OrderDao {
//    生成一个订单
    public void addBook(Order order) throws SQLException {

        // 1.生成Sql语句
        String sql = "insert into orders values(?,?,?,?,?,0,?,?)";
        // 2.生成执行sql语句的QueryRunner,不传递参数
        QueryRunner runner = new QueryRunner();
        // 3.执行update()方法插入数据
        runner.update(DataSourceUtils.getConnection(), sql, order.getId(),
                order.getprice(), order.getReceiverAddress(), order
                        .getReceiverName(), order.getReceiverPhone(),order.getOrdertime(),order
                        .getUser().getId());
    }
//   查找用户的订单————购物车功能
    public List<Order> findOrderByUser(final User user) throws SQLException {
        String sql = "select * from orders where userid=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new ResultSetHandler<List<Order>>() {
            public List<Order> handle(ResultSet rs) throws SQLException {
                List<Order> orders = new ArrayList<Order>();
                while (rs.next()) {
                    Order order = new Order();
                    order.setId(rs.getString("id"));
                    order.setprice(rs.getDouble("price"));
                    order.setOrdertime(rs.getDate("ordertime"));
                    order.setPaystate(rs.getInt("paystate"));
                    order.setReceiverAddress(rs.getString("buyerAddress"));
                    order.setReceiverName(rs.getString("buyerName"));
                    order.setReceiverPhone(rs.getString("buyerPhone"));
                    order.setUser(user);
                    orders.add(order);
                }
                return orders;
            }
        }, user.getId());
    }
//    根据ID查找订单
    public Order findOrderById(String id) throws SQLException {
        String sql = "select * from orders,user where orders.userid=user.id and orders.id=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new ResultSetHandler<Order>() {
            public Order handle(ResultSet rs) throws SQLException {
                Order order = new Order();
                while (rs.next()) {
                    order.setId(rs.getString("orders.id"));
                    order.setprice(rs.getDouble("orders.price"));
                    order.setOrdertime(rs.getDate("orders.ordertime"));
                    order.setPaystate(rs.getInt("orders.paystate"));
                    order.setReceiverAddress(rs.getString("orders.buyerAddress"));
                    order.setReceiverName(rs.getString("orders.buyerName"));
                    order.setReceiverPhone(rs.getString("orders.buyerPhone"));

                    User user = new User();
                    user.setId(rs.getInt("user.id"));
                    user.setEmail(rs.getString("user.email"));
                    user.setGender(rs.getString("user.gender"));
                    user.setIntroduce(rs.getString("user.introduce"));
                    user.setPassword(rs.getString("user.password"));
                    user.setRegistTime(rs.getDate("user.registtime"));
                    user.setRole(rs.getString("user.role"));
                    user.setState(rs.getInt("user.state"));
                    user.setTelephone(rs.getString("user.telephone"));
                    user.setUsername(rs.getString("user.username"));
                    order.setUser(user);
                }
                return order;
            }
        }, id);
    }
//    查找所有的订单
    public List<Order> findAllOrder() throws SQLException {
        //1.创建sql
        String sql = "select orders.*,user.* from orders,user where user.id=orders.userid order by orders.userid";
        //2.创建QueryRunner对象
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        //3.返回QueryRunner对象query()方法的查询结果
        return runner.query(sql, new ResultSetHandler<List<Order>>() {
            public List<Order> handle(ResultSet rs) throws SQLException {
                //创建订单集合
                List<Order> orders = new ArrayList<Order>();
                //循环遍历订单和用户信息
                while (rs.next()) {
                    Order order = new Order();
                    order.setId(rs.getString("orders.id"));
                    order.setprice(rs.getDouble("orders.price"));
                    order.setOrdertime(rs.getDate("orders.ordertime"));
                    order.setPaystate(rs.getInt("orders.paystate"));
                    order.setReceiverAddress(rs.getString("orders.buyerAddress"));
                    order.setReceiverName(rs.getString("orders.buyerName"));
                    order.setReceiverPhone(rs.getString("orders.buyerPhone"));
                    orders.add(order);

                    User user = new User();
                    user.setId(rs.getInt("user.id"));
                    user.setEmail(rs.getString("user.email"));
                    user.setGender(rs.getString("user.gender"));

                    user.setIntroduce(rs.getString("user.introduce"));
                    user.setPassword(rs.getString("user.password"));
                    user.setRegistTime(rs.getDate("user.registtime"));
                    user.setRole(rs.getString("user.role"));
                    user.setState(rs.getInt("user.state"));
                    user.setTelephone(rs.getString("user.telephone"));
                    user.setUsername(rs.getString("user.username"));
                    order.setUser(user);
                }
                return orders;
            }
        });
    }
//    根据订单号查到订单修改订单状态
    public void updateOrderState(String id) throws SQLException {
        String sql = "update orders set paystate=1 where id=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        runner.update(sql, id);
        System.out.println(runner.update(sql, id));
    }
//    多条件查询订单
    public List<Order> findOrderBymul(String id, String receiverName)
            throws SQLException {
        //1.创建集合对象
        List<Object> objs = new ArrayList<Object>();
        //2.定义查询sql
        String sql = "select orders.*,user.* from orders,user where user.id=orders.userid ";
        //3.根据参数拼接sql语句
        if (id != null && id.trim().length() > 0) {
            sql += " and orders.id=?";
            objs.add(id);
        }
        if (receiverName != null && receiverName.trim().length() > 0) {
            sql += " and buyerName=?";
            objs.add(receiverName);
        }
        sql += " order by orders.userid";
        //4.创建QueryRunner对象
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        //5.返回QueryRunner对象query方法的执行结果
        return runner.query(sql, new ResultSetHandler<List<Order>>() {
            public List<Order> handle(ResultSet rs) throws SQLException {
                List<Order> orders = new ArrayList<Order>();
                //循环遍历出订单和用户信息
                while (rs.next()) {
                    Order order = new Order();
                    order.setId(rs.getString("orders.id"));
                    order.setprice(rs.getDouble("orders.price"));
                    order.setOrdertime(rs.getDate("orders.ordertime"));
                    order.setPaystate(rs.getInt("orders.paystate"));
                    order.setReceiverAddress(rs
                            .getString("orders.buyerAddress"));
                    order.setReceiverName(rs.getString("orders.buyerName"));
                    order.setReceiverPhone(rs.getString("orders.buyerPhone"));
                    orders.add(order);
                    User user = new User();
                    user.setId(rs.getInt("user.id"));
                    user.setEmail(rs.getString("user.email"));
                    user.setGender(rs.getString("user.gender"));
                    user.setIntroduce(rs.getString("user.introduce"));
                    user.setPassword(rs.getString("user.password"));
                    user.setRegistTime(rs.getDate("user.registtime"));
                    user.setRole(rs.getString("user.role"));
                    user.setState(rs.getInt("user.state"));
                    user.setTelephone(rs.getString("user.telephone"));
                    user.setUsername(rs.getString("user.username"));
                    order.setUser(user);

                }

                return orders;
            }
        }, objs.toArray());
    }
//    根据订单ID号删除订单
    public void delOrderById(String id) throws SQLException {
        String sql="delete from orders where id=?";
        QueryRunner runner = new QueryRunner();
        runner.update(DataSourceUtils.getConnection(),sql,id);
    }
}
