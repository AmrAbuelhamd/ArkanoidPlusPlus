package com.blogspot.soyamr.arkanoidplusplus.game_stuff.model

import android.graphics.Bitmap


interface BrickTypeI{
    fun getBrick(brickHardness: BrickHardness): Bitmap
}