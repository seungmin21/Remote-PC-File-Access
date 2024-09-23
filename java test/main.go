package main

import (
    "encoding/json"
    "net/http"
    "log"
)

type FileResponse struct {
    FileName string `json:"fileName"`
    Content  string `json:"content"`
}

// 파일 이름과 내용 제공하는 API
func GetFileHandler(w http.ResponseWriter, r *http.Request) {
    fileName := r.URL.Query().Get("filename")

    fileContent := "This is the content of file: " + fileName

    response := FileResponse{
        FileName: fileName,
        Content:  fileContent,
    }

    w.Header().Set("Content-Type", "application/json")
    json.NewEncoder(w).Encode(response)
}

func main() {
    http.HandleFunc("/files", GetFileHandler)

    log.Println("Go server is running on http://localhost:8080")
    log.Fatal(http.ListenAndServe(":8080", nil))
}
