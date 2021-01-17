package com.blogspot.soyamr.arkanoidplusplus.game_stuff.model


enum class BrickHardness {
    DEAD {
        override fun next(): BrickHardness {
            return DEAD
        }
    },
    ONE {
        override fun next(): BrickHardness {
            return DEAD
        }
    },
    TWO {
        override fun next(): BrickHardness {
            return ONE
        }
    },
    THREE {
        override fun next(): BrickHardness {
            return TWO
        }
    },
    FOUR {
        override fun next(): BrickHardness {
            return THREE
        }
    },
    FIVE {
        override fun next(): BrickHardness {
            return FOUR
        }
    },
    DIAMOND {
        override fun next(): BrickHardness {
            return DIAMOND
        }
    };

    abstract fun next(): BrickHardness
}