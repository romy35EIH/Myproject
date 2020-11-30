package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import domain.Order;
import domain.Item;
import domain.Book;
import utils.DataSourceUtils;

public class ItemDao {

    // 添加订单项
    public void addItem(Order order) throws SQLException {
        // 1.生成sql语句
        String sql = "insert into Item values(?,?,?)";

        QueryRunner runner = new QueryRunner();

        List<Item> items = order.getItems();

        Object[][] params = new Object[items.size()][3];

        for (int i = 0; i < params.length; i++) {
            params[i][0] = items.get(i).getOrder().getId();
            params[i][1] = items.get(i).getP().getId();
            params[i][2] = items.get(i).getsalenum();
        }

        runner.batch(DataSourceUtils.getConnection(), sql, params);
    }

    // 根据订单查找订单项.并将订单项中商品查找到。
    public List<Item> findItemByOrder(final Order order)
            throws SQLException {
        String sql = "select * from Item,Books where Books.id=Item.bookid and orderid=?";

        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

        return runner.query(sql, new ResultSetHandler<List<Item>>() {
            public List<Item> handle(ResultSet rs) throws SQLException {

                List<Item> items = new ArrayList<Item>();
                while (rs.next()) {
                    Item item = new Item();

                    item.setOrder(order);
                    item.setsalenum(rs.getInt("salenum"));

                    Book p = new Book();
                    p.setCategory(rs.getString("category"));
                    p.setId(rs.getString("id"));
                    p.setDescription(rs.getString("description"));
                    p.setimgurl(rs.getString("imgurl"));
                    p.setName(rs.getString("name"));
                    p.setbnum(rs.getInt("bnum"));
                    p.setPrice(rs.getDouble("price"));
                    item.setP(p);

                    items.add(item);
                }

                return items;
            }
        }, order.getId());
    }
    //根据订单id删除订单项
    public void delItems(String id) throws SQLException {
        String sql="delete from Item where orderid=?";

        QueryRunner runner=new QueryRunner();

        runner.update(DataSourceUtils.getConnection(),sql,id);
    }

}
