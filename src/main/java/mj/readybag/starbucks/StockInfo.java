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
    private int totalBagCnt;
}
