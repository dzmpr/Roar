package com.gaoyun.roar.presentation.add_pet.setup

import com.gaoyun.roar.domain.pet.GetPetUseCase
import com.gaoyun.roar.presentation.BaseViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddPetSetupScreenViewModel :
    BaseViewModel<AddPetSetupScreenContract.Event, AddPetSetupScreenContract.State, AddPetSetupScreenContract.Effect>(),
    KoinComponent {

    private val getPetUseCase: GetPetUseCase by inject()

    override fun setInitialState() = AddPetSetupScreenContract.State(isLoading = false)

    override fun handleEvents(event: AddPetSetupScreenContract.Event) {
        when (event) {
            is AddPetSetupScreenContract.Event.PetInit -> getPet(event.petId)
            is AddPetSetupScreenContract.Event.ContinueButtonClicked -> setEffect {
                AddPetSetupScreenContract.Effect.Navigation.Continue
            }

            is AddPetSetupScreenContract.Event.OpenTemplatesButtonClicked -> viewState.value.pet?.id?.let {
                setEffect { AddPetSetupScreenContract.Effect.Navigation.OpenTemplates(it) }
                setState { copy(isComplete = true, pet = null) }
            }
        }
    }

    private fun getPet(petId: String) = scope.launch {
        if (viewState.value.isComplete) {
            setEffect { AddPetSetupScreenContract.Effect.Navigation.Continue }
        } else {
            getPetUseCase.getPet(petId).collect { pet ->
                setState { copy(pet = pet) }
            }
        }
    }
}