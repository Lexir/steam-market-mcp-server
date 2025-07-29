package ru.salex.steam.market.server.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GetItemOrdersHistogramResponse {
    private int success;

    @JsonProperty("sell_order_count")
    private String sellOrderCount;

    @JsonProperty("sell_order_price")
    private String sellOrderPrice;

    @JsonProperty("sell_order_table")
    private List<SellOrder> sellOrderTable;

    @JsonProperty("buy_order_count")
    private String buyOrderCount;

    @JsonProperty("buy_order_price")
    private String buyOrderPrice;

    @JsonProperty("buy_order_table")
    private List<BuyOrder> buyOrderTable;

    @JsonProperty("highest_buy_order")
    private String highestBuyOrder;

    @JsonProperty("lowest_sell_order")
    private String lowestSellOrder;

    @JsonProperty("buy_order_graph")
    private List<List<String>> buyOrderGraph;

    @JsonProperty("sell_order_graph")
    private List<List<String>> sellOrderGraph;

    @JsonProperty("graph_max_y")
    private Integer graphMaxY;

    @JsonProperty("graph_min_x")
    private Double graphMinX;

    @JsonProperty("graph_max_x")
    private Double graphMaxX;

    @JsonProperty("price_prefix")
    private String pricePrefix;

    @JsonProperty("price_suffix")
    private String priceSuffix;


    @Data
    public static class SellOrder {
        private String price;
        private String price_with_fee;
        private String quantity;

    }

    @Data
    public static class BuyOrder {
        private String price;
        private String quantity;
    }

}
