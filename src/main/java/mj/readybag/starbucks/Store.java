package mj.readybag.starbucks;

import lombok.Data;

@Data
public class Store {
    public String getAddress() {
        return address;
    }

    public String getStore_nm() {
        return store_nm;
    }

    public int getStore_cd() {
        return store_cd;
    }

    private String address;
    private String store_nm;
    private int store_cd;
}
