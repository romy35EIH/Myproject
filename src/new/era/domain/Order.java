package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

    private String id; // 订单编号
    private double price; // 订单总价
    private String buyerAddress; // 送货地址
    private String buyerName; // 收货人姓名
    private String buyerPhone; // 收货人电话
    private int paystate; // 订单状态
    private Date ordertime; // 下单时间

    private User user; // 订单所属用户

    private List<Item> Items = new ArrayList<Item>();

    public String getReceiverAddress() {
        return buyerAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.buyerAddress = receiverAddress;
    }

    public String getReceiverName() {
        return buyerName;
    }

    public void setReceiverName(String receiverName) {
        this.buyerName = receiverName;
    }

    public String getReceiverPhone() {
        return buyerPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.buyerPhone = receiverPhone;
    }

    public List<Item> getItems() {
        return Items;
    }

    public void setItems(List<Item> Items) {
        this.Items = Items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getprice() {
        return price;
    }

    public void setprice(double price) {
        this.price = price;
    }

    public int getPaystate() {
        return paystate;
    }

    public void setPaystate(int paystate) {
        this.paystate = paystate;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", price=" + price + ", buyerAddress="
                + buyerAddress + ", buyerName=" + buyerName
                + ", buyerPhone=" + buyerPhone + ", paystate=" + paystate
                + ", ordertime=" + ordertime + ", user=" + user
                + ", Items=" + Items + "]";
    }

}
