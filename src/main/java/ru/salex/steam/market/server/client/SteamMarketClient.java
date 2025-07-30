package ru.salex.steam.market.server.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.salex.steam.market.server.client.dto.GetItemOrdersHistogramResponse;
import ru.salex.steam.market.server.client.dto.SteamMarketSearchResponse;

@FeignClient(url = "${steam.market.url}", name = "${steam.market.name}")
public interface SteamMarketClient {

    @GetMapping("/market/listings/{appId}/{itemName}")
    String getItemHtmlPage(@PathVariable("appId") Integer appId,
                           @PathVariable("itemName") String itemName);

    @GetMapping("/market/itemordershistogram")
    GetItemOrdersHistogramResponse getItemOrdersHistogram(@RequestParam("language") String language,
                                                          @RequestParam("currency") Integer currency,
                                                          @RequestParam("item_nameid") Long itemMameId,
                                                          @RequestParam(value = "norender", defaultValue = "1") Integer norender);

    @GetMapping("/market/search/render/")
    SteamMarketSearchResponse searchItems(
            @RequestParam(value = "query", defaultValue = "") String query,
            @RequestParam(value = "start", defaultValue = "0") Integer start,
            @RequestParam(value = "count", defaultValue = "10") Integer count,
            @RequestParam(value = "search_descriptions", defaultValue = "0") Integer searchDescriptions,
            @RequestParam(value = "sort_column", defaultValue = "popular") String sortColumn,
            @RequestParam(value = "sort_dir", defaultValue = "desc") String sortDir,
            @RequestParam(value = "appid", defaultValue = "730") Integer appId,
            @RequestParam(value = "norender", defaultValue = "1") Integer noRender
    );
}
