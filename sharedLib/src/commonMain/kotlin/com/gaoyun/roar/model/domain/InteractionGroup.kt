package com.gaoyun.roar.model.domain

enum class InteractionGroup {
    HEALTH,
    CARE,
    ROUTINE;

    companion object {
        const val HEALTH_STRING = "health"
        const val CARE_STRING = "care"
        const val ROUTINE_STRING = "routine"
    }
}

fun String.toInteractionGroup(): InteractionGroup {
    return when(this) {
        InteractionGroup.HEALTH_STRING -> InteractionGroup.HEALTH
        InteractionGroup.CARE_STRING -> InteractionGroup.CARE
        InteractionGroup.ROUTINE_STRING -> InteractionGroup.ROUTINE
        else -> throw IllegalArgumentException("Wrong group type $this")
    }
}