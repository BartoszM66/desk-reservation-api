# Desk Reservation API 🏢

Zaawansowane REST API do zarządzania rezerwacjami biurek w biurze (system Hot-Desking). Projekt koncentruje się na solidnej logice biznesowej, walidacji danych i obsłudze przypadków brzegowych.

## 🚀 Technologie
* **Java 17+**
* **Spring Boot 3** (Spring Web, Spring Data JPA, Validation)
* **Baza Danych:** H2 (In-Memory)
* **Narzędzia:** Maven, Lombok, Git

## 🛡️ Logika biznesowa i Zabezpieczenia (Wyróżnik projektu)
Aplikacja to nie tylko prosty CRUD. Zaimplementowano w niej zaawansowane reguły biznesowe:
* **Unikalność:** Zabezpieczenie na poziomie bazy danych (`@Column(unique = true)`) oraz serwisu, zapobiegające dodaniu dwóch biurek o tym samym numerze.
* **Fail-Fast Validation:** Niemożliwość zarezerwowania biurka bez podania imienia pracownika.
* **Ochrona rezerwacji:** Zabezpieczenie przed nadpisaniem (`PUT`) już zajętego biurka przez inną osobę. Jedyną legalną operacją na zajętym biurku jest jego uprzednie zwolnienie.
* **Niemutowalność identyfikatorów:** Zablokowanie możliwości fizycznej zmiany numeru biurka w biurze po jego utworzeniu (ignorowanie numeru w żądaniach PUT).
* **Automatyzacja:** Automatyczne czyszczenie pola `employeeName` przy zwalnianiu biurka.

## ⚙️ Endpointy API

| Metoda HTTP | Endpoint | Opis |
| :--- | :--- | :--- |
| `GET` | `/desks` | Pobiera listę wszystkich biurek i ich status |
| `POST` | `/desks` | Dodaje nowe, wolne biurko do systemu |
| `PUT` | `/desks/{id}` | Rezerwuje lub zwalnia biurko |
| `DELETE` | `/desks/{id}` | Usuwa biurko z systemu |

## 🛠️ Jak uruchomić?
1. Sklonuj repozytorium: `git clone [TUTAJ_LINK_DO_TWOJEGO_GITHUB]`
2. Uruchom aplikację z użyciem Mavena: `mvn spring-boot:run` lub otwórz w IDE.
3. API będzie dostępne pod adresem: `http://localhost:8080`
4. Dostęp do konsoli bazy danych H2: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:testdb` lub zgodnie z `application.properties`, User: `sa`, bez hasła).

## 💻 Przykładowe żądanie (Rezerwacja biurka)
```http
PUT http://localhost:8080/desks/1
Content-Type: application/json

{
  "available": false,
  "employeeName": "Jan Kowalski"
}