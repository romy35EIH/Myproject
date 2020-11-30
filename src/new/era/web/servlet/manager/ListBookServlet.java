package web.servlet.manager;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import domain.Book;
import exception.ListBookException;
import service.BookService;
/**
 * 后台系统
 * 查询所有商品信息的servlet
 */
public class ListBookServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1.创建service层的对象
        BookService service = new BookService();
        try {
            // 2.调用service层用于查询所有商品的方法
            List<Book> ps = service.listAll();
            // 3.将查询出的所有商品放进request域中
            request.setAttribute("ps", ps);
            // 4.重定向到list.jsp页面
            request.getRequestDispatcher("/admin/Books/list.jsp").forward(
                    request, response);
            return;
        } catch (ListBookException e) {
            e.printStackTrace();
            response.getWriter().write(e.getMessage());
            return;
        }
    }
}
