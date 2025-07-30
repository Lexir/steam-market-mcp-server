package ru.salex.steam.market.server.configuration;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.salex.steam.market.server.service.SteamMarketService;

@Configuration
public class McpServerConfig {

    @Bean
    public ToolCallbackProvider steamMarketTools(SteamMarketService steamMarketService) {
        return MethodToolCallbackProvider.builder().toolObjects(steamMarketService).build();
    }
}
