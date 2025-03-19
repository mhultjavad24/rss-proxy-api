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
    "title": "Episode Title",
    "link": "https://example.com/episode1",
    "description": "Episode description text",
    "pubDate": "2023-06-01T12:00:00.000+00:00",
    "guid": "unique-episode-identifier"
  },
  ...
]