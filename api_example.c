using System;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

class Program {
    static async Task Main() {
        var handler = new HttpClientHandler() {
            ServerCertificateCustomValidationCallback = 
                (sender, cert, chain, sslPolicyErrors) => true // Disable SSL check
        };

        using var client = new HttpClient(handler);
        client.Timeout = TimeSpan.FromSeconds(30);

        var url = "https://drm.xtream-masters.com/api.php";
        var data = @"{
            ""Channel_Unique_ID_1"": {
                ""channel_name"": ""Channel 1"",
                ""mpd"": ""http://domain.xyz/channel_link_1.mpd"",
                ""keys"": ""d9729feb74992cc3482b350163a1a010:a9060abf27cc347eff242813880a1c16""
            }
        }";

        var request = new HttpRequestMessage(HttpMethod.Post, url) {
            Content = new StringContent(data, Encoding.UTF8, "application/json")
        };

        request.Headers.Add("Authorization", "Bearer YOUR_AUTH_TOKEN");
        request.Headers.Add("Provider-ID", "1");
        request.Headers.Add("Import-Type", "ImportParse");
        request.Headers.Add("Server-ID", "480");

        var response = await client.SendAsync(request);
        Console.WriteLine(await response.Content.ReadAsStringAsync());
    }
}
