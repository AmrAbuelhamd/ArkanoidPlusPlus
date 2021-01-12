package com.blogspot.soyamr.arkanoidplusplus.game_stuff.model

import com.blogspot.soyamr.arkanoidplusplus.game_stuff.model.game_elements.State

interface IModel : GlobalBehavior {
    fun setMovementState(state: State)
}