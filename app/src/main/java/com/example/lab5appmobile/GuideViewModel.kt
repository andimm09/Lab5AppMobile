package com.example.lab5appmobile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.lab5appmobile.data.Category
import kotlinx.coroutines.flow.StateFlow

/**
 * Starea partajată între ecrane: filtrul curent și lista de favorite.
 * Fiind legată de activitate și salvată în SavedStateHandle, supraviețuiește
 * navigării, schimbării tab-urilor și rotirii ecranului.
 */
class GuideViewModel(private val savedState: SavedStateHandle) : ViewModel() {

    /** Numele categoriei selectate sau null pentru „Toate”. */
    val filter: StateFlow<String?> = savedState.getStateFlow(KEY_FILTER, null)

    val favoriteIds: StateFlow<List<Int>> = savedState.getStateFlow(KEY_FAVORITES, emptyList())

    fun setFilter(category: Category?) {
        savedState[KEY_FILTER] = category?.name
    }

    fun toggleFavorite(id: Int) {
        val current = favoriteIds.value
        saveFavorites(if (id in current) current - id else current + id)
    }

    fun removeFavorite(id: Int) {
        saveFavorites(favoriteIds.value - id)
    }

    // Bundle acceptă ArrayList, deci salvăm explicit acest tip.
    private fun saveFavorites(ids: List<Int>) {
        savedState[KEY_FAVORITES] = ArrayList(ids)
    }

    private companion object {
        const val KEY_FILTER = "filter"
        const val KEY_FAVORITES = "favorites"
    }
}
