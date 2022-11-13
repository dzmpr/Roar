package com.gaoyun.roar.presentation.add_reminder.setup_reminder

import com.gaoyun.roar.model.domain.interactions.InteractionTemplate
import com.gaoyun.roar.model.domain.Pet
import com.gaoyun.roar.presentation.ViewEvent
import com.gaoyun.roar.presentation.ViewSideEffect
import com.gaoyun.roar.presentation.ViewState

class SetupReminderScreenContract {

    sealed class Event : ViewEvent {
//        class TemplateChosen(val templateId: String) : Event()
    }

    data class State(
        val isLoading: Boolean = false,
        val pet: Pet? = null,
        val template: InteractionTemplate? = null
    ) : ViewState

    sealed class Effect : ViewSideEffect {
//        class TemplateChosen(val templateId: String) : Effect()

        sealed class Navigation : Effect() {
//            class ToReminderSetup(val templateId: String) : Navigation()
            object NavigateBack : Navigation()
        }
    }
}