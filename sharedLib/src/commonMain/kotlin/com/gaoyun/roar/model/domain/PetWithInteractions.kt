package com.gaoyun.roar.model.domain

import com.gaoyun.roar.model.domain.interactions.InteractionGroup
import com.gaoyun.roar.model.domain.interactions.InteractionWithReminders
import com.gaoyun.roar.util.randomUUID
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class PetWithInteractions(
    val id: String = randomUUID(),
    val petType: PetType,
    val breed: String,
    val name: String,
    val avatar: String,
    val userId: String,
    val birthday: LocalDate,
    val isSterilized: Boolean,
    val gender: Gender,
    val chipNumber: String,
    val dateCreated: LocalDate,
    val interactions: Map<InteractionGroup, List<InteractionWithReminders>>
)

fun Pet.withInteractions(interactions: Map<InteractionGroup, List<InteractionWithReminders>>) = PetWithInteractions(
    id = id,
    petType = petType,
    breed = breed,
    name = name,
    avatar = avatar,
    userId = userId,
    birthday = birthday,
    isSterilized = isSterilized,
    gender = gender,
    chipNumber = chipNumber,
    dateCreated = dateCreated,
    interactions = interactions,
)

fun PetWithInteractions.withoutInteractions() = Pet(
    id = id,
    petType = petType,
    breed = breed,
    name = name,
    avatar = avatar,
    userId = userId,
    birthday = birthday,
    isSterilized = isSterilized,
    gender = gender,
    chipNumber = chipNumber,
    dateCreated = dateCreated,
)

