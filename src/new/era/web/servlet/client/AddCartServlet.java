package web.servlet.client;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import domain.Book;
import exception.FindBookException;
import service.BookService;

public class AddCartServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.得到商品id
		String id = request.getParameter("id");
		// 2.调用service层方法，根据id查找商品
		BookService service = new BookService();
		try {
			Book p = service.findBookById(id);
			//3.将商品添加到购物车
			//3.1获得session对象
			HttpSession session = request.getSession();
			//3.2从session中获取购物车对象
			Map<Book, Integer> cart = (Map<Book, Integer>)session.getAttribute("cart");
			//3.3如果购物车为null,说明没有商品存储在购物车中，创建出购物车
			if (cart == null) {
				cart = new HashMap<Book, Integer>();
			}
			//3.4向购物车中添加商品
			Integer count = cart.put(p, 1);
			//3.5如果商品数量不为空，则商品数量+1，否则添加新的商品信息
			if (count != null) {
				cart.put(p, count + 1);
			}			
			session.setAttribute("cart", cart);
			response.sendRedirect(request.getContextPath() + "/client/cart.jsp");
			return;
		} catch (FindBookException e) {
			e.printStackTrace();
		}
	}
}
