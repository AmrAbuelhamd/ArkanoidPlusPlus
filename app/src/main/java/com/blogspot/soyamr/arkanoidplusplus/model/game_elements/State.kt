package com.blogspot.soyamr.arkanoidplusplus.model.game_elements


interface IState {
    fun update(paddle: Paddle, fps: Int)
}

enum class State : IState {
    STOPPED {
        override fun update(paddle: Paddle, fps: Int) {
        }
    },
    LEFT {
        override fun update(paddle: Paddle, fps: Int) {
            paddle.setPaddleX(paddle.getPaddleX() - paddle.paddleSpeed / fps)
        }
    },
    RIGHT {
        override fun update(paddle: Paddle, fps: Int) {
            paddle.setPaddleX(paddle.getPaddleX() + paddle.paddleSpeed / fps)

        }
    }
}