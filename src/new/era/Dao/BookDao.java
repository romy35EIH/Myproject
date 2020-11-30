package Dao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
//import com.sun.faces.taglib.jsf_core.MaxMinValidatorTag;
import domain.Order;
import domain.Item;
import domain.Book;
import utils.DataSourceUtils;

public class BookDao {
    // 添加商品
    public void addBook(Book p) throws SQLException {

        String sql = "insert into Books values(?,?,?,?,?,?,?)";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        runner.update(sql, p.getId(), p.getName(), p.getPrice(),
                p.getCategory(), p.getbnum(), p.getimgurl(), p.getDescription());
    }
    // 查找所有商品
    public List<Book> listAll() throws SQLException {
        String sql = "select * from Books";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new BeanListHandler<Book>(Book.class));
    }
    // 获取数据总条数
    public int findAllCount(String category) throws SQLException {
        String sql = "select count(*) from Books";

        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

        if (!"全部商品".equals(category)) {
            sql += " where category=?";

            Long count = (Long) runner
                    .query(sql, new ScalarHandler(), category);
            return count.intValue();
        } else {
            Long count = (Long) runner.query(sql, new ScalarHandler());

            return count.intValue();
        }
    }
    // 获取当前页数据
    public List<Book> findByPage(int currentPage, int currentCount,
                                 String category) throws SQLException {
        // 要执行的sql语句
        String sql = null;
        // 参数
        Object[] obj = null;
        // 如果category不为null,代表是按分类查找
        if (!"全部商品".equals(category)) {
            sql = "select * from Books  where category=? limit ?,?";
            obj = new Object[] { category, (currentPage - 1) * currentCount,
                    currentCount, };
        } else {
            sql = "select * from Books  limit ?,?";
            obj = new Object[] { (currentPage - 1) * currentCount,
                    currentCount, };
        }
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new BeanListHandler<Book>(Book.class),
                obj);
    }

    // 根据id查找商品
    public Book findBookById(String id) throws SQLException {
        String sql = "select * from Books where id=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new BeanHandler<Book>(Book.class), id);
    }

    // 生成订单时，将商品数量减少
    public void changeBookNum(Order order) throws SQLException {
        String sql = "update Books set bnum=bnum-? where id=?";
        QueryRunner runner = new QueryRunner();
        List<Item> items = order.getItems();
        Object[][] params = new Object[items.size()][2];

        for (int i = 0; i < params.length; i++) {
            params[i][0] = items.get(i).getsalenum();
            params[i][1] = items.get(i).getP().getId();
        }

        runner.batch(DataSourceUtils.getConnection(), sql, params);
    }

    // 销售榜单
    public List<Object[]> salesList(String year, String month)
            throws SQLException {
        String sql = "SELECT Books.name,SUM(Item.salenum) totalsalnum FROM orders,Books,Item WHERE orders.id=Item.orderid AND Books.id=Item.Bookid AND orders.paystate=1 and year(ordertime)=? and month(ordertime)=? GROUP BY Books.name ORDER BY totalsalnum DESC";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new ArrayListHandler(), year, month);
    }

    // 多条件查询
    public List<Book> findBookBymul(String id, String name,
                                              String category, String minprice, String maxprice)
            throws SQLException {
        List<Object> list = new ArrayList<Object>();
        String sql = "select * from Books where 1=1 ";

        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

        if (id != null && id.trim().length() > 0) {
            sql += " and id=?";
            list.add(id);
        }

        if (name != null && name.trim().length() > 0) {
            sql += " and name=?";
            list.add(name);
        }
        if (category != null && category.trim().length() > 0) {
            sql += " and category=?";
            list.add(category);
        }
        if (minprice != null && maxprice != null
                && minprice.trim().length() > 0 && maxprice.trim().length() > 0) {
            sql += " and price between ? and ?";
            list.add(minprice);
            list.add(maxprice);
        }

        Object[] params = list.toArray();

        return runner.query(sql, new BeanListHandler<Book>(Book.class),
                params);
    }
    // 修改商品信息
    public void editBook(Book p) throws SQLException {
        //1.创建集合并将商品信息添加到集合中
        List<Object> obj = new ArrayList<Object>();
        obj.add(p.getName());
        obj.add(p.getPrice());
        obj.add(p.getCategory());
        obj.add(p.getbnum());
        obj.add(p.getDescription());
        //2.创建sql语句，并拼接sql
        String sql  = "update Books " +
                "set  name=?,price=?,category=?,bnum=?,description=? ";
        //判断是否有图片
        if (p.getimgurl() != null && p.getimgurl().trim().length() > 0) {
            sql += " ,imgurl=?";
            obj.add(p.getimgurl());
        }
        sql += " where id=?";
        obj.add(p.getId());
        System.out.println(sql);
        System.out.println(obj);
        //3.创建QueryRunner对象
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        //4.使用QueryRunner对象的update()方法更新数据
        runner.update(sql, obj.toArray());
    }
    //删除订单时，修改商品数量
    public void updateBookNum(List<Item> items) throws SQLException {

        String sql = "update Books set bnum=bnum+? where id=?";
        QueryRunner runner = new QueryRunner();

        Object[][] params = new Object[items.size()][2];

        for (int i = 0; i < params.length; i++) {
            params[i][0] = items.get(i).getsalenum();
            params[i][1] = items.get(i).getP().getId();
        }

        runner.batch(DataSourceUtils.getConnection(), sql, params);
    }

    //前台，获取本周热销商品
    public List<Object[]> getWeekHotBook() throws SQLException {
        String sql = "SELECT Books.id,Books.name, "+
                " Books.imgurl,SUM(Item.salenum) totalsalnum "+
                " FROM Item,orders,Books "+
                " WHERE Item.orderid = orders.id "+
                " AND Books.id = Item.bookid "+
                " AND orders.paystate=1 "+
                " AND orders.ordertime > DATE_SUB(NOW(), INTERVAL 7 DAY) "+
                " GROUP BY Books.id,Books.name,Books.imgurl "+
                " ORDER BY totalsalnum DESC "+
                " LIMIT 0,2 ";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new ArrayListHandler());
    }

    //前台，用于搜索框根据书名来模糊查询相应的图书
    public List<Book> findBookByName(int currentPage, int currentCount,
                                     String searchfield) throws SQLException {
        //根据名字模糊查询图书
        String sql = "SELECT * FROM Books WHERE name LIKE '%"+searchfield+"%' LIMIT ?,?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
//		//用于分页查询的数据
//		Object obj = new Object[] { (currentPage - 1) * currentCount, currentCount };
        return runner.query(sql,
                new BeanListHandler<Book>(Book.class),currentPage-1,currentCount);
    }

    //前台搜索框，根据书名模糊查询出的图书总数量
    public int findBookByNameAllCount(String searchfield) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Books WHERE name LIKE '%"+searchfield+"%'";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        //查询出满足条件的总数量，为long类型
        Long count = (Long)runner.query(sql, new ScalarHandler());
        return count.intValue();
    }

    //后台系统，根据id删除商品信息
    public void deleteBook(String id) throws SQLException {
        String sql = "DELETE FROM Books WHERE id = ?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        runner.update(sql, id);
    }
}