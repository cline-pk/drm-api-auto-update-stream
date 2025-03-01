# DRM API Auto-Update Stream

A simple Python/PHP implementation for automatically updating DRM-protected streams via API. Manage your channel's media presentation descriptions (MPD) and encryption keys through automated API calls.

## Features

- Update multiple channels in a single request
- Three different import modes
- Simple configuration setup
- Supports both PHP and Python
- Easy integration with existing systems

## Prerequisites

- API authorization token
- Provider ID from your DRM panel
- Server ID from your load balancer configuration
- PHP 7.4+ or Python 3.6+

## Quick Start

### PHP Setup
```php
<?php
function sendPostRequest($url, $authToken, $data, $providerId, $import_type, $server_id, $timeout = 30) {
    $ch = curl_init($url);
    curl_setopt_array($ch, [
        CURLOPT_POST => true,
        CURLOPT_HTTPHEADER => [
            'Authorization: Bearer ' . $authToken,
            'Content-Type: application/json',
            'Provider-ID: ' . $providerId,
            'Import-Type: ' . $import_type,
            'Server-ID: ' . $server_id,
        ],
        CURLOPT_POSTFIELDS => json_encode($data),
        CURLOPT_RETURNTRANSFER => true,
        CURLOPT_TIMEOUT => $timeout,
        CURLOPT_SSL_VERIFYPEER => false
    ]);
    $response = curl_exec($ch);
    curl_close($ch);
    return $response;
}

// Configuration
$url = 'https://drm.xtream-masters.com/api.php';
$authToken = 'your_auth_token_here';
$providerId = 1;
$server_id = 123;
$import_type = 'ImportParse';

// Channel Data
$data = [
    'Channel_Unique_ID_1' => [
        'channel_name' => 'Channel 1',
        'mpd' => 'http://domain.xyz/channel_link_1.mpd',
        'keys' => 'kid:key_pair_here'
    ]
];

// Execute
$result = sendPostRequest($url, $authToken, $data, $providerId, $import_type, $server_id);
echo $result;
```

### Python Setup
```python
import requests
import json

def send_post_request(url, auth_token, data, provider_id, import_type, server_id, timeout=30):
    headers = {
        'Authorization': f'Bearer {auth_token}',
        'Content-Type': 'application/json',
        'Provider-ID': str(provider_id),
        'Import-Type': import_type,
        'Server-ID': str(server_id)
    }
    
    response = requests.post(
        url,
        headers=headers,
        json=data,
        timeout=timeout,
        verify=False
    )
    return response.text

# Configuration
url = 'https://drm.xtream-masters.com/api.php'
auth_token = 'your_auth_token_here'
provider_id = 1
server_id = 123
import_type = 'ImportParse'

# Channel Data
data = {
    'Channel_Unique_ID_1': {
        'channel_name': 'Channel 1',
        'mpd': 'http://domain.xyz/channel_link_1.mpd',
        'keys': 'kid:key_pair_here'
    }
}

# Execute
result = send_post_request(url, auth_token, data, provider_id, import_type, server_id)
print(result)
```

## Configuration Parameters

| Parameter       | Description                                                                 | Example Value            |
|-----------------|-----------------------------------------------------------------------------|--------------------------|
| `authToken`     | API authorization token from your panel                                     | `abc123xyz789`           |
| `providerId`    | Provider ID linking to your stream source                                   | `1`                      |
| `server_id`     | Load balancer Server ID                                                     | `123`                    |
| `import_type`   | Processing mode (`import`, `ImportParse`, `ImportParseBest`)                | `ImportParse`            |
| `unique_key`    | Unique identifier for each channel                                          | `Channel_Unique_ID_1`    |
| `channel_name`  | Display name for the channel                                                | `Premium Sports HD`      |
| `mpd`           | URL to Media Presentation Description file                                  | `http://example.com/stream.mpd` |
| `keys`          | DRM key pair in `kid:key` format (use `-` for unencrypted streams)          | `d9729feb74:abf27cc347e` |

## Import Modes

1. **import** - Basic import without processing
2. **ImportParse** - Import with metadata parsing
3. **ImportParseBest** - Import + parsing + automatic quality selection

## Usage

1. Replace placeholder values with your actual credentials
2. Modify channel data according to your streams
3. Choose appropriate import mode
4. Run script:
   ```bash
   # PHP
   php update_streams.php
   
   # Python
   python update_streams.py
   ```

## Security Notes

- 🔒 Always keep your auth token secure
- 🛡️ Use environment variables for sensitive data

---

*This project is not affiliated with any DRM service provider. Use at your own discretion.*
