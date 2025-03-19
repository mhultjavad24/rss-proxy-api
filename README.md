# RSS Proxy API

A Spring Boot application that fetches and parses RSS feeds. This API allows clients to retrieve structured data from any valid RSS feed URL.

## Features

- **RSS Feed Parsing**: Convert any RSS feed URL into structured JSON data
- **Episode Information**: Extract title, link, description, publication date, and GUID from RSS feeds

## API Endpoints

### RSS Feed Parser

```
GET /api/rss/episodes?url={rss_feed_url}
```

Returns a JSON array of episodes from the specified RSS feed.

**Example Response:**
```json
[
  {
    "title": "Sharper Part 1 with Dr. Oz Garcia",
    "link": "https://example.com/episode1",
    "description": "He’s here to talk about one of the most important topics we’ve ever covered and to help me make an exciting announcement.",
    "pubDate": "Mon, 27 May 2024 15:30:00 -0000",
    "guid": "zPw9Ut2OHwL6Olj3xF9RZht2MPAPJz_TYYVeRuM4r8"
  },
  ...
]