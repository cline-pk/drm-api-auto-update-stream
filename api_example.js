const axios = require('axios');
const https = require('https');

async function sendRequest() {
    const url = 'https://drm.xtream-masters.com/api.php';
    const data = {
        Channel_Unique_ID_1: {
            channel_name: 'Channel 1',
            mpd: 'http://domain.xyz/channel_link_1.mpd',
            keys: 'd9729feb74992cc3482b350163a1a010:a9060abf27cc347eff242813880a1c16'
        }
    };

    try {
        const response = await axios.post(url, data, {
            headers: {
                'Authorization': `Bearer YOUR_AUTH_TOKEN`,
                'Content-Type': 'application/json',
                'Provider-ID': '1',
                'Import-Type': 'ImportParse',
                'Server-ID': '480'
            },
            httpsAgent: new https.Agent({  
                rejectUnauthorized: false // Disable SSL verification
            }),
            timeout: 30000,
            maxRedirects: 5
        });
        console.log(response.data);
    } catch (error) {
        console.error('Error:', error.message);
    }
}

sendRequest();
