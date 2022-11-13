package com.gaoyun.roar.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.feature_pet_screen.PetScreenDestination
import com.gaoyun.common.NavigationKeys
import com.gaoyun.common.theme.RoarTheme
import com.gaoyun.feature_add_pet.AddPetAvatarDestination
import com.gaoyun.feature_add_pet.AddPetDataDestination
import com.gaoyun.feature_add_pet.AddPetPetTypeDestination
import com.gaoyun.feature_add_pet.AddPetSetupDestination
import com.gaoyun.feature_create_reminder.AddReminderDestination
import com.gaoyun.feature_create_reminder.SetupReminderDestination
import com.gaoyun.feature_home_screen.HomeScreenDestination
import com.gaoyun.feature_user_registration.UserRegistrationDestination

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RoarTheme {
                GlobalDestinationState()
            }
        }

    }

    @Composable
    fun GlobalDestinationState() {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = NavigationKeys.RouteGlobal.HOME_ROUTE
        ) {
            composable(NavigationKeys.RouteGlobal.HOME_ROUTE) {
                HomeScreenDestination(
                    navHostController = navController
                )
            }

            composable(NavigationKeys.RouteGlobal.REGISTER_USER_ROUTE) {
                UserRegistrationDestination(navHostController = navController)
            }

            composable(NavigationKeys.RouteGlobal.ADD_PET_ROUTE) {
                AddPetPetTypeDestination(navHostController = navController)
            }

            composable(
                route = NavigationKeys.RouteGlobal.ADD_PET_AVATAR_ROUTE,
                arguments = listOf(navArgument(NavigationKeys.Arg.PET_TYPE_KEY) { type = NavType.StringType })
            ) {
                AddPetAvatarDestination(
                    navHostController = navController,
                    petType = it.arguments?.getString(NavigationKeys.Arg.PET_TYPE_KEY) ?: ""
                )
            }

            composable(
                route = NavigationKeys.RouteGlobal.ADD_PET_DATA_ROUTE,
                arguments = listOf(
                    navArgument(NavigationKeys.Arg.PET_TYPE_KEY) { type = NavType.StringType },
                    navArgument(NavigationKeys.Arg.AVATAR_KEY) { type = NavType.StringType },
                )
            ) {
                AddPetDataDestination(
                    navHostController = navController,
                    petType = it.arguments?.getString(NavigationKeys.Arg.PET_TYPE_KEY) ?: "",
                    avatar = it.arguments?.getString(NavigationKeys.Arg.AVATAR_KEY) ?: "",
                )
            }

            composable(
                route = NavigationKeys.RouteGlobal.ADD_PET_SETUP_ROUTE,
                arguments = listOf(navArgument(NavigationKeys.Arg.PET_ID_KEY) { type = NavType.StringType })
            ) {
                AddPetSetupDestination(
                    navHostController = navController,
                    petId = it.arguments?.getString(NavigationKeys.Arg.PET_ID_KEY) ?: ""
                )
            }

            composable(
                route = NavigationKeys.RouteGlobal.PET_DETAIL_ROUTE,
                arguments = listOf(navArgument(NavigationKeys.Arg.PET_ID_KEY) { type = NavType.StringType })
            ) {
                PetScreenDestination(navController, petId = it.arguments?.getString(NavigationKeys.Arg.PET_ID_KEY) ?: "")
            }

            composable(
                route = NavigationKeys.RouteGlobal.ADD_REMINDER_ROUTE,
                arguments = listOf(navArgument(NavigationKeys.Arg.PET_ID_KEY) { type = NavType.StringType })
            ) {
                AddReminderDestination(navController, petId = it.arguments?.getString(NavigationKeys.Arg.PET_ID_KEY) ?: "")
            }

            composable(
                route = NavigationKeys.RouteGlobal.SETUP_REMINDER_ROUTE,
                arguments = listOf(
                    navArgument(NavigationKeys.Arg.PET_ID_KEY) { type = NavType.StringType },
                    navArgument(NavigationKeys.Arg.TEMPLATE_ID_KEY) { type = NavType.StringType }
                )
            ) {
                SetupReminderDestination(
                    navController,
                    petId = it.arguments?.getString(NavigationKeys.Arg.PET_ID_KEY) ?: "",
                    templateId = it.arguments?.getString(NavigationKeys.Arg.TEMPLATE_ID_KEY) ?: "custom"
                )
            }
        }
    }

}