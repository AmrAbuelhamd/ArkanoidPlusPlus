package com.blogspot.soyamr.arkanoidplusplus.model

import com.blogspot.soyamr.arkanoidplusplus.model.game_elements.State

interface IModel : GlobalBehavior {
    fun setMovementState(state: State)
}