package main


import (
	"fmt"
	"net/http"
	"time"
)

func helloHandler(w http.ResponseWriter, r *http.Request) {
	var now = time.Now()
	fmt.Println("Time: " , now)
	fmt.Fprintln(w, "<h1>Hello OpenShift! </h1>")
	
}

func listenAndServe(port string) {
	fmt.Printf("serving on %s\n", port)
	err := http.ListenAndServe(":"+port, nil)
	if err != nil {
		panic("ListenAndServe: " + err.Error())
	}
}

func main() {
	http.HandleFunc("/", helloHandler)
	go listenAndServe("8080")
	select {}
}