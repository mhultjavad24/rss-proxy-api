package io.github.mhultjavad24.rss_proxy_api.service;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import io.github.mhultjavad24.rss_proxy_api.model.Episode;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RssService {

    public List<Episode> fetchAndParseRssFeed(String feedUrl) throws Exception {
        URL url = new URL(feedUrl);
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(url));
        
        return feed.getEntries().stream()
                .map(this::convertToEpisode)
                .collect(Collectors.toList());
    }
    
    private Episode convertToEpisode(SyndEntry entry) {
        return new Episode(
                entry.getTitle(),
                entry.getLink(),
                entry.getDescription() != null ? entry.getDescription().getValue() : null,
                entry.getPublishedDate(),
                entry.getUri()
        );
    }
} 