package mj.readybag.starbucks;

import lombok.Data;

@Data
public class BagStock {
    private int STORE_CD;
    private int BAG_PINK_COUNT;
    private int BAG_GREEN_COUNT;

    public int getSTORE_CD() {
        return STORE_CD;
    }

    public int getBAG_PINK_COUNT() {
        return BAG_PINK_COUNT;
    }

    public int getBAG_GREEN_COUNT() {
        return BAG_GREEN_COUNT;
    }
}
