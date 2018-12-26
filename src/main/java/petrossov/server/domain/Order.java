package petrossov.server.domain;



import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
/*@Component*/
@Table(name = "orders")
@Entity(name = "order")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String orderName;
    private String clientName;
    private String date;
    private String address;
    private String phoneNumber;
    private String price;
    private String prepay;

    public Order(String orderName, String clientName, String date, String address, String phoneNumber, String price, String prepay) {
        this.orderName = orderName;
        this.clientName = clientName;
        this.date = date;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.price = price;
        this.prepay = prepay;
    }

    public Order() {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderName, clientName, date, address, phoneNumber, price, prepay);
    }

    @Override
    public String toString() {
        return "Order{" +
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
