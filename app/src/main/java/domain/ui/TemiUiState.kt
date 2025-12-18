package domain.ui

sealed class TemiUiState{
    object Idle: TemiUiState()
    object NavigatingToEuptBikes: TemiUiState()
    object AtEuptBikes: TemiUiState()
    data class Error(val message: String): TemiUiState()
}