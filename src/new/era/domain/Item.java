package domain;

public class Item {

    private Order order;
    private Book p;
    private int salenum;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Book getP() {
        return p;
    }

    public void setP(Book p) {
        this.p = p;
    }

    public int getsalenum() {
        return salenum;
    }

    public void setsalenum(int salenum) {
        this.salenum = salenum;
    }

}
