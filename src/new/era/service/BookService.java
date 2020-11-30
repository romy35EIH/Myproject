package service;
import java.sql.SQLException;
import java.util.List;
import Dao.BookDao;
import domain.PageBean;
import domain.Book;
import exception.AddBookException;
import exception.FindBookException;
import exception.ListBookException;
public class BookService {
    private BookDao dao = new BookDao();
    // 添加商品
    public void addBook(Book p) throws AddBookException {
        try {
            dao.addBook(p);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AddBookException("添加商品失败");
        }
    }
    // 查找所有商品信息
    public List<Book> listAll() throws ListBookException {
        try {
            return dao.listAll();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ListBookException("查询商品失败");
        }
    }
    // 分页操作
    public PageBean findBookByPage(int currentPage, int currentCount,
                                   String category) {
        PageBean bean = new PageBean();
        // 封装每页显示数据条数
        bean.setCurrentCount(currentCount);
        // 封装当前页码
        bean.setCurrentPage(currentPage);
        // 封装当前查找类别
        bean.setCategory(category);
        try {
            // 获取总条数
            int totalCount = dao.findAllCount(category);
            bean.setTotalCount(totalCount);
            // 获取总页数
            int totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
            bean.setTotalPage(totalPage);
            // 获取当前页数据
            List<Book> ps = dao.findByPage(currentPage, currentCount,
                    category);
            bean.setPs(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }
    // 根据id查找商品
    public Book findBookById(String id) throws FindBookException {
        try {
            return dao.findBookById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FindBookException("根据ID查找商品失败");
        }
    }
    // 下载销售榜单
    public List<Object[]> download(String year, String month) {
        List<Object[]> salesList = null;
        try {
            salesList = dao.salesList(year, month);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesList;
    }
    // 多条件查询
    public List<Book> findBookBymul(String id, String name,
                                              String category, String minprice, String maxprice) {
        List<Book> ps = null;
        try {
            ps = dao.findBookBymul(id, name, category, minprice,
                    maxprice);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }
    // 修改商品信息
    public void editBook(Book p) {
        try {
            dao.editBook(p);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //前台，获取本周热销商品
    public List<Object[]> getWeekHotBook() {
        try {
            return dao.getWeekHotBook();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("前台获取本周热销商品失败！");
        }
    }
    //前台，用于搜索框根据书名来模糊查询相应的图书
    public PageBean findBookByName(int currentPage, int currentCount,
                                   String searchfield) {
        PageBean bean = new PageBean();
        // 封装每页显示数据条数
        bean.setCurrentCount(currentCount);
        // 封装当前页码
        bean.setCurrentPage(currentPage);
        // 封装模糊查询的图书名
        bean.setSearchfield(searchfield);
        try {
            // 获取总条数
            int totalCount = dao.findBookByNameAllCount(searchfield);
            bean.setTotalCount(totalCount);

            // 获取总页数
            int totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
            bean.setTotalPage(totalPage);

            //满足条件的图书
            List<Book> ps = dao.findBookByName(currentPage,currentCount,searchfield);
            bean.setPs(ps);
            return bean;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("前台搜索框根据书名查询图书失败！");
        }
    }
    //后台系统，根据id删除商品信息
    public void deleteBook(String id) {
        try {
            dao.deleteBook(id);
        } catch (SQLException e) {
            throw new RuntimeException("后台系统根据id删除商品信息失败！");
        }
    }
}