package com.example.lab5appmobile.navigation

import kotlinx.serialization.Serializable

// Toate rutele sunt type-safe: obiecte / clase marcate @Serializable, fără șiruri.

@Serializable
object Login

/** Graf imbricat care grupează lista și detaliul (tab-ul Categorii). */
@Serializable
object CategoriesGraph

@Serializable
object PlaceList

/** Detaliul primește doar id-ul locului; restul se citește din PlacesRepository. */
@Serializable
data class PlaceDetail(val id: Int)

@Serializable
object Favorites

@Serializable
object About
