package ru.salex.steam.market.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.salex.steam.market.server.client.dto.GetItemOrdersHistogramResponse;
import ru.salex.steam.market.server.client.dto.SteamMarketSearchResponse;
import ru.salex.steam.market.server.service.SteamMarketService;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class SteamMarketController {
    private final SteamMarketService steamMarketService;

    @GetMapping("/market/listings/{appId}/{itemName}")
    String getItemHtmlPage(@PathVariable("appId") Integer appId,
                           @PathVariable("itemName") String itemName) {
        return steamMarketService.getItemNameId(appId, itemName);
    }

    @GetMapping("/market/itemordershistogram")
    GetItemOrdersHistogramResponse getItemOrdersHistogram(@RequestParam("language") String language,
                                  @RequestParam("currency") Integer currency,
                                  @RequestParam("item_nameid") Long itemMameId) {
        return steamMarketService.getPriceItem(itemMameId, language, currency);
    }


    @GetMapping("/market/items/")
    SteamMarketSearchResponse searchItems(
            @RequestParam(value = "query", defaultValue = "") String query,
            @RequestParam(value = "start", defaultValue = "0") Integer start,
            @RequestParam(value = "count", defaultValue = "10") Integer count,
            @RequestParam(value = "search_descriptions", defaultValue = "0") Integer searchDescriptions,
            @RequestParam(value = "sort_column", defaultValue = "popular") String sortColumn,
            @RequestParam(value = "sort_dir", defaultValue = "desc") String sortDir,
            @RequestParam(value = "appid", defaultValue = "730") Integer appId
    ) {
        return steamMarketService.searchItems(query, start, count, searchDescriptions, sortColumn, sortDir, appId);
    }
}

