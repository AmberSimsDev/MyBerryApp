# MyBerryApp – Android Pokémon Berry Explorer

An Android application that consumes the **PokéAPI** to display and explore Pokémon berries. Built with **Kotlin, Jetpack Compose, Retrofit, and Coil**, the app demonstrates asynchronous data handling, API integration, and modern Android UI development.

---

## ✨ Features

*  Fetches real-time data from the PokéAPI
*  Displays a scrollable grid of Pokémon berries
*  Detailed view for each berry (growth time, size, flavor, etc.)
*  Dynamic image loading using Coil
*  Asynchronous state handling with loading and error states

---

##  Architecture

This project follows a **layered (Clean Architecture-inspired) structure**, separating API logic, UI, and business intent:

```
data/
 ├── remote/
 │    ├── api/
 │    └── model/
 └── repository/ (planned)

domain/
 ├── model/ (planned)
 └── usecase/ (planned)

presentation/
 ├── screens/
 ├── viewmodel/
 └── MainActivity

navigation/
```

> Note: The architecture is partially implemented. Repository and domain layers are scaffolded for future expansion.

---

##  API Integration

Uses **Retrofit** to interact with the PokéAPI:

```kotlin
@GET("berry")
suspend fun getPokeBerries(): BerryResponseDTO

@GET("berry/{berryName}/")
suspend fun getBerryDetail(@Path("berryName") berryName: String): BerryDetailDTO
```

* Base URL: `https://pokeapi.co/api/v2/`
* Gson used for JSON parsing

---

##  Data Models

* DTOs (`BerryDTO`, `BerryDetailDTO`) map API responses
* Uses `@SerializedName` to align JSON fields with Kotlin properties

---

##  State Management

UI state handled inside ViewModels:

```kotlin
data class PokeState(
    var loading: Boolean = true,
    var list: List<BerryDTO> = emptyList(),
    var error: String? = null
)
```

* Loading state → shows progress indicator
* Success → displays grid
* Error → fallback UI

---

##  UI Layer (Jetpack Compose)

* `LazyVerticalGrid` for efficient scrolling
* `AsyncImage` (Coil) for remote images
* Navigation between list and detail screens

Example:

```kotlin
LazyVerticalGrid(GridCells.Fixed(2)) {
    items(pokeBerries) { berry ->
        berryItemView(berry)
    }
}
```

---

##  Navigation

Handled using Jetpack Navigation:

* `pokeScreen` → list of berries
* `berryDetailScreen/{berryName}` → detail view

---

##  Limitations & Future Improvements

*  Repository layer not fully implemented
*  Domain models and use cases not yet utilized
*  ViewModel directly accesses API (tight coupling)

### Planned Improvements:

* Introduce Repository pattern between API and ViewModel
* Add domain models and use cases
* Implement dependency injection (Hilt)
* Add caching and error handling strategies

---

##  Tech Stack

* Kotlin
* Jetpack Compose
* Retrofit + Gson
* Coil
* Coroutines

---

##  Screenshots

https://github.com/user-attachments/assets/36f8e897-1378-4f28-952a-1c2a06d4bc63

<img width="360" height="735" alt="Screenshot_20260426-120401" src="https://github.com/user-attachments/assets/1ee976a7-f9e2-47ff-b334-201396f41153" />
<img width="360" height="750" alt="Screenshot_20260426-120334" src="https://github.com/user-attachments/assets/8b9919eb-f728-4adc-b400-729c481966be" />
<img width="360" height="722" alt="Screenshot_20260426-120342" src="https://github.com/user-attachments/assets/63907a01-474a-4b8d-ab7e-294607dc727e" />


---

##  Getting Started

1. Clone the repository
2. Open in Android Studio
3. Run the app on emulator or device

---

## What I Learned

* Consuming REST APIs in Android using Retrofit
* Handling asynchronous data and UI states
* Structuring Compose UIs for dynamic data
* Managing navigation between screens

---

## 📌 Author

Developed by Amber Sims

---
