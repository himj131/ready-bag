package mj.readybag.starbucks;

import lombok.Data;

import java.util.List;

@Data
public class Info {
    List<Store> storeList;
    List<BagStock> stockList;
}
