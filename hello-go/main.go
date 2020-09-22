package main

import (
	"fmt"
	"net/http"
)

func helloHandler(w http.ResponseWriter, r *http.Request) {
	
	var num int = 0

   	incrementar := func() int {
      num++
      return num
	}
	fmt.Fprintln(w, "<h1>Hello OpenShift! </h1>")
	fmt.Println("Request number: " , incrementar())
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