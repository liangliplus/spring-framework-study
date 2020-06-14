package indi.liangli.springframework.ioc.overview.domain;

/**
 * 超级用户
 * @author liangli
 * @Date: 2020/6/14 9:26
 */
public class SuperUser extends User {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SuperUser{");
        sb.append(super.toString()).append(",");
        sb.append("address='").append(address).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
