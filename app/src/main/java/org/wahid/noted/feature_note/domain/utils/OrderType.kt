package org.wahid.noted.feature_note.domain.utils

sealed class OrderType {
    data object Ascending:OrderType()
    data object Descending:OrderType()
}
