package ru.salex.steam.market.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;
import ru.salex.steam.market.server.client.SteamMarketClient;
import ru.salex.steam.market.server.client.dto.GetItemOrdersHistogramResponse;
import ru.salex.steam.market.server.client.dto.SteamMarketSearchResponse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class SteamMarketService {
    private final SteamMarketClient steamMarketClient;

    @Tool(name = "getSteamMarketData",
            description = "Fetch current price and order data for an item on Steam Market. " +
                    "Returns JSON with sell/buy orders, graphs, and other market stats."
    )
    public GetItemOrdersHistogramResponse getPriceItem(
            @ToolParam(description = "Id of the item on Steam Market (e.g., 'CS:GO Weapon Case')")
            Long itemNameId,
            @ToolParam(description = "Language (e.g., 'russian', 'english')")
            String language,
            @ToolParam(description = "Currency ID (1=USD, 5=EUR)")
            Integer currency) {
        return steamMarketClient.getItemOrdersHistogram(language, currency, itemNameId, 1);
    }

    @Tool(name = "getItemNameId",
            description = "Get item_name_id on Steam Market by ItemName " +
                    "Returns HTML with full info about item. But here post processing HTML page for extract item_name_id"
    )
    public String getItemNameId(
            @ToolParam(description = "Application id (730= CS2 or 570= Dota2)") Integer appId,
            @ToolParam(description = "Item name on Steam Market (e.g., 'AK-47 | VariCamo Grey (Field-Tested)')") String itemName) {
        String itemHtmlPage = steamMarketClient.getItemHtmlPage(appId, itemName);
        Pattern pattern = Pattern.compile("Market_LoadOrderSpread\\(\\s*(\\d+)\\s*\\)");
        Matcher matcher = pattern.matcher(itemHtmlPage);
        if (matcher.find()) {
            // Извлекаем найденное число
            String id = matcher.group(1);
            System.out.println("Найденный ID: " + id);
            return id;
        } else {
            System.out.println("ID не найден");
            return "ID не найден";
        }
    }

    @Tool(
            name = "searchSteamMarketItems",
            description = "Search for items on Steam Market. " +
                    "Returns paginated results with names, prices and images."
    )
    public SteamMarketSearchResponse searchItems(
            @ToolParam(description = "Search item (e.g., 'AK', 'Case')") String query,
            @ToolParam(description = "Pagination start index (default 0)") Integer start,
            @ToolParam(description = "Number of items per page (default 10)") Integer count,
            @ToolParam(description = "Search in descriptions (0/1)") Integer searchDescriptions,
            @ToolParam(description = "Sort column: 'popular', 'price', 'name'") String sortColumn,
            @ToolParam(description = "Sort direction: 'asc' or 'desc'") String sortDir,
            @ToolParam(description = "Application id ('730'= CS2 or 570= Dota2)") Integer appId
    ) {
        return steamMarketClient.searchItems(
                query, start, count, searchDescriptions,
                sortColumn, sortDir, appId, 1
        );
    }
}
