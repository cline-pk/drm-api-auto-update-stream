package main

import (
    "bytes"
    "crypto/tls"
    "fmt"
    "net/http"
    "time"
)

func main() {
    url := "https://drm.xtream-masters.com/api.php"
    jsonData := []byte(`{
        "Channel_Unique_ID_1": {
            "channel_name": "Channel 1",
            "mpd": "http://domain.xyz/channel_link_1.mpd",
            "keys": "d9729feb74992cc3482b350163a1a010:a9060abf27cc347eff242813880a1c16"
        }
    }`)

    // Configure HTTP client
    client := &http.Client{
        Timeout: 30 * time.Second,
        Transport: &http.Transport{
            TLSClientConfig: &tls.Config{InsecureSkipVerify: true},
        },
    }

    req, _ := http.NewRequest("POST", url, bytes.NewBuffer(jsonData))
    req.Header.Set("Authorization", "Bearer YOUR_AUTH_TOKEN")
    req.Header.Set("Content-Type", "application/json")
    req.Header.Set("Provider-ID", "1")
    req.Header.Set("Import-Type", "ImportParse")
    req.Header.Set("Server-ID", "480")

    resp, err := client.Do(req)
    if err != nil {
        fmt.Println("Error:", err)
        return
    }
    defer resp.Body.Close()

    buf := new(bytes.Buffer)
    buf.ReadFrom(resp.Body)
    fmt.Println(buf.String())
}
