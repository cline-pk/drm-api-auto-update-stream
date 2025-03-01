import requests
import json

def send_post_request(url, auth_token, data, provider_id, import_type, server_id, timeout=30):
    headers = {
        'Authorization': f'Bearer {auth_token}',
        'Content-Type': 'application/json',
        'Provider-ID': str(provider_id),
        'Import-Type': import_type,
        'Server-ID': str(server_id),
    }
    
    response = requests.post(
        url,
        headers=headers,
        data=json.dumps(data),
        timeout=timeout,
        allow_redirects=True,
        #verify=False
    )
    
    return response.text


# Static URL
url = 'https://drm.xtream-masters.com/api.php'

# Authorization token (locate on API panel page)
auth_token = ''

# Provider ID (find on provider panel page)
provider_id = 1

# Server ID (find on servers page)
server_id = 123

'''
#this option will auto do new insert stream Parse, make sure provider setting set in panel if stream required bypass etc.
	import			=> only import stream don't Parse (i'll do manually)
	ImportParse		=> import stream and Parse only (i'll choice download quality)
	ImportParseBest		=> import stream and Parse also add best quality to download and start stream.
'''
import_type = 'ImportParse'

# Example channel data

'''
#Example of channel data
	unique_key =>		    	must be unique for each channel we'll follow this to add or update existing record.
		channel_name => 	The name of the channel.
		mpd => 			A URL to the channel's media presentation description (MPD) file.
		keys => 		A string containing the kid:key associated with the channel, if mpd without key use `-` only.
'''

data = {
    'Channel_Unique_ID_1': {
        'channel_name': 'Channel 1',
        'mpd': 'http://domain.xyz/channel_link_1.mpd',
        'keys': 'd9729feb74992cc3482b350163a1a010:a9060abf27cc347eff242813880a1c16'
    },
    'Channel_Unique_ID_2': {
        'channel_name': 'Channel 2',
        'mpd': 'http://domain.xyz/channel_link_2.mpd',
        'keys': '-'
    }
}

result = send_post_request(url, auth_token, data, provider_id, import_type, server_id)

print(result)
