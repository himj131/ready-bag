package mj.readybag.starbucks;

import lombok.Data;

@Data
public class StoreInfo {
    private String address;
    private String name;
    private int storeCode;
    private int pinkBagCount;
    private int greenBagCount;
}
