package mx.com.amuhlia.model;

/**
 * Created by virusventor on 6/08/17.
 */
public class ClsExampleDTO {

    private int total;
    private int	id;
    private Integer	userId;
    private String	userName;
    private String	fecha;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "ClsExampleDTO{" +
                "total=" + total +
                ", id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
