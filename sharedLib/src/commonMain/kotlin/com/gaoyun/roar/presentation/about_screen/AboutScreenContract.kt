package com.gaoyun.roar.presentation.about_screen

import com.gaoyun.roar.presentation.ViewEvent
import com.gaoyun.roar.presentation.ViewSideEffect
import com.gaoyun.roar.presentation.ViewState

class AboutScreenContract {
    sealed class Event : ViewEvent {
        object NavigateBack : Event()
    }

    class State : ViewState

    sealed class Effect : ViewSideEffect {
        object NavigateBack : Effect()
    }
}