# drm-api-auto-update-stream

#5 Minutes Read

Drm auto stream name, mpd &amp; keys updater api in python &amp; php

#url is statics

`$url = 'https://drm.xtream-masters.com/api.php';`

#Locate your authorization token on the API panel page.

`$authToken = '';`

#Which provider is linked to this stream? You can find the provider ID on the provider panel page.

`$providerId = 1;`

#The server ID, which indicates in which LB channel should be added, can be found on the panel's servers page.

`$server_id = 123`

#this option will auto do new insert stream Parse, make sure provider setting set in panel if stream required bypass etc.
```
	import			=> only import stream don't Parse (i'll do manually)
	
	ImportParse		=> import stream and Parse only (i'll choice download quality)
	
	ImportParseBest		=> import stream and Parse also add best quality to download and start stream.
	
```

`$import_type = 'ImportParse';`


#Example of channel data
```
	unique_key =>			must be unique for each channel we'll follow this to add or update existing record.
		channel_name => 	The name of the channel.
		mpd => 			A URL to the channel's media presentation description (MPD) file.
		keys => 		A string containing the kid:key associated with the channel, if mpd without key use `-` only.
```

```
$data = [
    'Channel_Unique_ID_1' => [
        'channel_name' => 'Channel 1 new',
        'mpd' => 'http://domain.xyz/channel_link_1.mpd',
        'keys' => 'd9729feb74992cc3482b350163a1a010:a9060abf27cc347eff242813880a1c16'
    ],
    'Channel_Unique_ID_2' => [
        'channel_name' => 'Channel 2 new',
        'mpd' => 'http://domain.xyz/channel_link_2.mpd',
        'keys' => '-'
    ]
];
```


`$result = sendPostRequest($url, $authToken, $data, $providerId, $import_type, $server_id);`

```
// Check the result
echo $result;
```
