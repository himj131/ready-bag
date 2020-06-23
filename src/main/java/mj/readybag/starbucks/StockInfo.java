package mj.readybag.starbucks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockInfo {
    private String storeAddr;
    private String storeName;
    private int pinkCount;
    private int greenCount;

    public String getStoreAddr() {
        return storeAddr;
    }

    public String getStoreName() {
        return storeName;
    }

    public int getPinkCount() {
        return pinkCount;
    }

    public int getGreenCount() {
        return greenCount;
    }

    public void setStoreAddr(String storeAddr) {
        this.storeAddr = storeAddr;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setPinkCount(int pinkCount) {
        this.pinkCount = pinkCount;
    }

    public void setGreenCount(int greenCount) {
        this.greenCount = greenCount;
    }
}
