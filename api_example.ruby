require 'net/http'
require 'uri'
require 'json'

uri = URI.parse("https://drm.xtream-masters.com/api.php")
data = {
    "Channel_Unique_ID_1" => {
        "channel_name" => "Channel 1",
        "mpd" => "http://domain.xyz/channel_link_1.mpd",
        "keys" => "d9729feb74992cc3482b350163a1a010:a9060abf27cc347eff242813880a1c16"
    }
}

http = Net::HTTP.new(uri.host, uri.port)
http.use_ssl = true
http.verify_mode = OpenSSL::SSL::VERIFY_NONE # Disable SSL verification

request = Net::HTTP::Post.new(uri.request_uri)
request['Authorization'] = 'Bearer YOUR_AUTH_TOKEN'
request['Content-Type'] = 'application/json'
request['Provider-ID'] = '1'
request['Import-Type'] = 'ImportParse'
request['Server-ID'] = '480'
request.body = data.to_json

response = http.request(request)
puts response.body
