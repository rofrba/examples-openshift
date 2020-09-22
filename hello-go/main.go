package main

import (
	"fmt"
	"net/http"
)

func acumular() func() int {
	var num int = 0
	return func() int {
		num++
		return num
	}
}

func helloHandler(w http.ResponseWriter, r *http.Request) {
	

	fmt.Fprintln(w, "<h1>Hello OpenShift! </h1>")
	fmt.Println("Request number: " , acumular())
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