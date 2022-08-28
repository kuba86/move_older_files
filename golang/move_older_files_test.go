package main

import "testing"

func Test_hello(t *testing.T) {

	t.Run("Say Hello to people", func(t *testing.T) {
		got := Hello("Kuba", "English")
		want := "Hello, Kuba!"
		assertCorrectMessage(t, got, want)
	})

	t.Run("Say 'Hello, World!' when an empty string is supplied", func(t *testing.T) {
		got := Hello("", "English")
		want := "Hello, World!"
		assertCorrectMessage(t, got, want)
	})

	t.Run("in Spanish", func(t *testing.T) {
		got := Hello("Kuba", "Spanish")
		want := "Hola, Kuba!"
		assertCorrectMessage(t, got, want)
	})

	t.Run("in French", func(t *testing.T) {
		got := Hello("Kuba", "French")
		want := "Bonjour, Kuba!"
		assertCorrectMessage(t, got, want)
	})

}

func assertCorrectMessage(t testing.TB, got, want string) {
	t.Helper()
	if got != want {
		t.Errorf("got %q want %q", got, want)
	}
}
