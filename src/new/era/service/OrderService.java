package service;
import java.sql.SQLException;
import java.util.List;
import Dao.OrderDao;
import Dao.ItemDao;
import Dao.BookDao;
import domain.Order;
import domain.Item;

import domain.User;
import utils.DataSourceUtils;
public class OrderService {
    private ItemDao oidao = new ItemDao();
    private OrderDao odao = new OrderDao();
    private BookDao pdao = new BookDao();
    // 1.添加订单
    public void addOrder(Order order) {
        try {
            // 1.开启事务
            DataSourceUtils.startTransaction();
            // 2.完成操作
            // 2.1向orders表中添加数据
            odao.addBook(order);
            // 2.2向Item表中添加数据
            oidao.addItem(order);
            // 2.3修改商品表中数据.
            pdao.changeBookNum(order);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                DataSourceUtils.rollback(); // 事务回滚
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                // 关闭，释放以及提交事务
                DataSourceUtils.releaseAndCloseConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // 根据用户查找订单
    public List<Order> findOrderByUser(User user) {
        List<Order> orders = null;
        try {
            // 查找出订单信息
            orders = odao.findOrderByUser(user);

             // 查找出订单项信息.
             for (Order order : orders) {
             List<Item> items = oidao.findItemByOrder(order);
             //查找到订单中的订单项信息

             order.setItems(items);
             }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    // 根据id查找订单
    public Order findOrderById(String id) {
        Order order = null;
        try {
            order = odao.findOrderById(id);
            List<Item> items = oidao.findItemByOrder(order);
            order.setItems(items);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }
    // 查找所有订单
    public List<Order> findAllOrder() {
        List<Order> orders = null;
        try {
            // 查找出订单信息
            orders = odao.findAllOrder();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    // 支付成功后修改订单状态
    public void updateState(String id) {
        try {
            odao.updateOrderState(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 多条件查询订单信息
    public List<Order> findOrderBymul(String id, String receiverName) {
        List<Order> orders = null;
        try {
            orders = odao.findOrderBymul(id, receiverName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    //根据id删除订单 管理员删除订单
    public void delOrderById(String id) {
        try {
            DataSourceUtils.startTransaction();//开启事务
            oidao.delItems(id);
            odao.delOrderById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                DataSourceUtils.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally{
            try {
                DataSourceUtils.releaseAndCloseConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //普通用户删除订单
    public void delOrderByIdWithClient(String id) {
        try {
            DataSourceUtils.startTransaction();//开启事务
            //从订单项中查找商品购买数量
            Order order=new Order();
            order.setId(id);
            List<Item> items=oidao.findItemByOrder(order);
            //修改商品数量
            pdao.updateBookNum(items);
            oidao.delItems(id); //删除订单项
            odao.delOrderById(id); //删除订单
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                DataSourceUtils.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally{
            try {
                DataSourceUtils.releaseAndCloseConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}