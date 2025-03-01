curl -X POST "https://drm.xtream-masters.com/api.php" \
  -H "Authorization: Bearer YOUR_AUTH_TOKEN" \
  -H "Content-Type: application/json" \
  -H "Provider-ID: 1" \
  -H "Import-Type: ImportParse" \
  -H "Server-ID: 123" \
  -d '{
    "Channel_Unique_ID_1": {
        "channel_name": "Channel 1",
        "mpd": "http://domain.xyz/channel_link_1.mpd",
        "keys": "d9729feb74992cc3482b350163a1a010:a9060abf27cc347eff242813880a1c16"
    },
    "Channel_Unique_ID_2": {
        "channel_name": "Channel 2",
        "mpd": "http://domain.xyz/channel_link_2.mpd",
        "keys": "-"
    }
  }' \
  --silent \
  --location
