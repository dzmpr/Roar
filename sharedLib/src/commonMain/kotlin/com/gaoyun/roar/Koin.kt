package com.gaoyun.roar

import com.gaoyun.roar.domain.AppPreferencesUseCase
import com.gaoyun.roar.domain.backup.CreateBackupUseCase
import com.gaoyun.roar.domain.backup.ImportBackupUseCase
import com.gaoyun.roar.domain.interaction.GetInteraction
import com.gaoyun.roar.domain.interaction.InsertInteraction
import com.gaoyun.roar.domain.interaction.RemoveInteraction
import com.gaoyun.roar.domain.interaction.SetInteractionIsActive
import com.gaoyun.roar.domain.interaction_template.GetInteractionTemplate
import com.gaoyun.roar.domain.interaction_template.GetInteractionTemplatesForPetType
import com.gaoyun.roar.domain.interaction_template.InsertInteractionTemplate
import com.gaoyun.roar.domain.interaction_template.RemoveInteractionTemplates
import com.gaoyun.roar.domain.onboarding.CompleteOnboardingUseCase
import com.gaoyun.roar.domain.pet.AddPetUseCase
import com.gaoyun.roar.domain.pet.GetPetBreedsUseCase
import com.gaoyun.roar.domain.pet.GetPetUseCase
import com.gaoyun.roar.domain.pet.RemovePetUseCase
import com.gaoyun.roar.domain.pet.SetPetAvatar
import com.gaoyun.roar.domain.reminder.AddNextReminder
import com.gaoyun.roar.domain.reminder.GetReminder
import com.gaoyun.roar.domain.reminder.InsertReminder
import com.gaoyun.roar.domain.reminder.RemoveReminder
import com.gaoyun.roar.domain.reminder.SetReminderComplete
import com.gaoyun.roar.domain.repeat_config.RepeatConfigUseCase
import com.gaoyun.roar.domain.sync.SynchronisationUseCase
import com.gaoyun.roar.domain.user.CheckUserExistingUseCase
import com.gaoyun.roar.domain.user.EditUserUseCase
import com.gaoyun.roar.domain.user.GetCurrentUserUseCase
import com.gaoyun.roar.domain.user.LogoutUseCase
import com.gaoyun.roar.domain.user.RegisterUserUseCase
import com.gaoyun.roar.migrations.MigrationsExecutor
import com.gaoyun.roar.model.entity.RoarDatabase
import com.gaoyun.roar.network.InteractionTemplatesApi
import com.gaoyun.roar.network.PetsApi
import com.gaoyun.roar.network.SynchronisationApi
import com.gaoyun.roar.repository.InteractionRepository
import com.gaoyun.roar.repository.InteractionRepositoryImpl
import com.gaoyun.roar.repository.InteractionTemplateRepository
import com.gaoyun.roar.repository.InteractionTemplateRepositoryImpl
import com.gaoyun.roar.repository.PetRepository
import com.gaoyun.roar.repository.PetRepositoryImpl
import com.gaoyun.roar.repository.ReminderRepository
import com.gaoyun.roar.repository.ReminderRepositoryImpl
import com.gaoyun.roar.repository.UserRepository
import com.gaoyun.roar.repository.UserRepositoryImpl
import com.gaoyun.roar.util.DriverFactory
import com.gaoyun.roar.util.PlatformHttpClient
import com.gaoyun.roar.util.Preferences
import com.gaoyun.roar.util.platformModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(platformModule(), repositoryModule, useCaseModule, dbModule, networkModule, preferencesModule)
}

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl() }
    single<PetRepository> { PetRepositoryImpl() }
    single<InteractionTemplateRepository> { InteractionTemplateRepositoryImpl() }
    single<InteractionRepository> { InteractionRepositoryImpl() }
    single<ReminderRepository> { ReminderRepositoryImpl() }
}

val useCaseModule = module {
    single { RegisterUserUseCase() }
    single { GetCurrentUserUseCase() }
    single { CheckUserExistingUseCase() }
    single { EditUserUseCase() }
    single { LogoutUseCase() }
    single { SynchronisationUseCase() }

    single { GetPetUseCase() }
    single { AddPetUseCase() }
    single { GetPetBreedsUseCase() }
    single { SetPetAvatar() }
    single { RemovePetUseCase() }

    single { GetInteractionTemplatesForPetType() }
    single { GetInteractionTemplate() }
    single { InsertInteractionTemplate() }
    single { RemoveInteractionTemplates() }

    single { GetInteraction() }
    single { InsertInteraction() }
    single { RemoveInteraction() }
    single { SetInteractionIsActive() }
    single { RepeatConfigUseCase() }

    single { GetReminder() }
    single { InsertReminder() }
    single { RemoveReminder() }
    single { SetReminderComplete() }
    single { AddNextReminder() }

    single { CreateBackupUseCase() }
    single { ImportBackupUseCase() }
    single { CompleteOnboardingUseCase() }
    single { AppPreferencesUseCase() }
}

val networkModule = module {
    single { PlatformHttpClient.httpClient() }
    single { InteractionTemplatesApi() }
    single { PetsApi() }
    single { SynchronisationApi() }
}

val dbModule = module {
    single { get<DriverFactory>().createDriver() }
    single { RoarDatabase(get()) }
    single { MigrationsExecutor() }
}

val preferencesModule = module {
    single { Preferences("app_prefs") }
}