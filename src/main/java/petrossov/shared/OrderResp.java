package petrossov.shared;

import java.io.Serializable;
import java.util.Objects;

//DTO (data transfer object) Client entity


public class OrderResp implements Serializable {
    private int id;
    private String orderName;
    private String clientName;
    private String date;
    private String address;
    private String phoneNumber;
    private String price;
    private String prepay;

    public OrderResp(int id, String orderName, String clientName, String date, String address, String phoneNumber, String price, String prepay) {
        this.id = id;
        this.orderName = orderName;
        this.clientName = clientName;
        this.date = date;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.price = price;
        this.prepay = prepay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrepay() {
        return prepay;
    }

    public void setPrepay(String prepay) {
        this.prepay = prepay;
    }

    public OrderResp() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderResp orderResp = (OrderResp) o;
        return id == orderResp.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderName, clientName, date, address, phoneNumber, price, prepay);
    }

    @Override
    public String toString() {
        return "OrderResp{" +
                "id=" + id +
                ", orderName='" + orderName + '\'' +
                ", clientName='" + clientName + '\'' +
                ", date='" + date + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", price='" + price + '\'' +
                ", prepay='" + prepay + '\'' +
                '}';
    }
}
