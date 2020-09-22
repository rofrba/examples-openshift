package main

import (
	"fmt"
	"net/http"
)

func helloHandler(w http.ResponseWriter, r *http.Request) {
	
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
	num := 0
	fmt.Println("Request number: " , i+1)
	http.HandleFunc("/", helloHandler)
	go listenAndServe("8080")
	select {}
}