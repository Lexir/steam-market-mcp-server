package ru.salex.steam.market.server.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SteamMarketSearchResponse {
    private Boolean success;
    private Integer start;
    private Integer pagesize;
    private Integer total_count;

    @JsonProperty("results")
    private List<MarketItem> items;


    @Data
    public static class MarketItem {
        private String hash_name;
        private String name;
        private String image;
        private String price;
        private String app_name;
        private String sell_price_text;
        @JsonProperty("asset_description")
        private SteamAssetDescription steamAssetDescription;
    }

    @Data
    public static class SteamAssetDescription {
        @JsonProperty("appid")
        private int appId;
        @JsonProperty("classid")
        private String classId;
        @JsonProperty("instanceid")
        private String instanceId;
        @JsonProperty("background_color")
        private String backgroundColor;
        @JsonProperty("icon_url")
        private String iconUrl;
        @JsonProperty("tradable")
        private int tradable; // 1 = true, 0 = false
        private String name;
        @JsonProperty("name_color")
        private String nameColor;
        private String type;
        @JsonProperty("market_name")
        private String marketName;
        @JsonProperty("market_hash_name")
        private String marketHashName;
        @JsonProperty("commodity")
        private int commodity;
    }
}
