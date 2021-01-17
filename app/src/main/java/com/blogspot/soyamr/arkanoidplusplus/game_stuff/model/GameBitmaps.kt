package com.blogspot.soyamr.arkanoidplusplus.game_stuff.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.blogspot.soyamr.arkanoidplusplus.R
import com.blogspot.soyamr.arkanoidplusplus.game_stuff.Dimensions

class GameBitmaps(private val context: Context, private val dimensions: Dimensions) {

    var paddleImg: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.paddle_blu)
    var paddleImgSmall: Bitmap =
        BitmapFactory.decodeResource(context.resources, R.drawable.paddle_blu_small)
    var paddleImgBig: Bitmap =
        BitmapFactory.decodeResource(context.resources, R.drawable.paddle_blu_big)

    var ball: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ball_blue)

    var brick: Bitmap =
        BitmapFactory.decodeResource(context.resources, R.drawable.element_purple_polygon_glossy)

    var squareBrick: Bitmap =//todo make scaled one
        BitmapFactory.decodeResource(context.resources, R.drawable.element_blue_square_glossy)

    var star: Bitmap =
        BitmapFactory.decodeResource(context.resources, R.drawable.particle_star)
    var star2: Bitmap =
        BitmapFactory.decodeResource(context.resources, R.drawable.particle_small_star)
    var star3: Bitmap =
        BitmapFactory.decodeResource(context.resources, R.drawable.particle_cartoon_star)
    var bonusImg: Bitmap =
        BitmapFactory.decodeResource(context.resources, R.drawable.element_green_square)

    var greenBullet = arrayOf(
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_0),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_1),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_2),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_3),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_4),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_5),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_6),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_7),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_8),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_9),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_10),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_11),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_12),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_13),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_14),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_15),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_16),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_17),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_18),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_19),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_20),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_21),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_22),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_23),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_24),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_25),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_26),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_27),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_28),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_29),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_30),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_31),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_32),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_33),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_34),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_35),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_36),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_37),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_38),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_39),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_40),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_41),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_42),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_43),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_44),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_45),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_46),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_47),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_48),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_49),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_50),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_51),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_52),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_53),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_54),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_55),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_56),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_57),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_58),
        BitmapFactory.decodeResource(context.resources, R.drawable.g1_59),
    )

    var whiteBullet = arrayOf(
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_0),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_1),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_2),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_3),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_4),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_5),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_6),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_7),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_8),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_9),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_10),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_11),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_12),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_13),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_14),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_15),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_16),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_17),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_18),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_19),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_20),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_21),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_22),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_23),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_24),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_25),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_26),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_27),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_28),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_29),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_30),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_31),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_32),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_33),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_34),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_35),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_36),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_37),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_38),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_39),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_40),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_41),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_42),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_43),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_44),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_45),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_46),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_47),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_48),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_49),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_50),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_51),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_52),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_53),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_54),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_55),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_56),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_57),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_58),
        BitmapFactory.decodeResource(context.resources, R.drawable.w1_59),
    )

    var orangeBullet = arrayOf(
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_0),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_1),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_2),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_3),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_4),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_5),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_6),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_7),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_8),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_9),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_10),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_11),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_12),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_13),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_14),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_15),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_16),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_17),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_18),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_19),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_20),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_21),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_22),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_23),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_24),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_25),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_26),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_27),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_28),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_29),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_30),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_31),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_32),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_33),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_34),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_35),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_36),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_37),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_38),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_39),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_40),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_41),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_42),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_43),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_44),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_45),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_46),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_47),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_48),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_49),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_50),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_51),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_52),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_53),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_54),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_55),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_56),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_57),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_58),
        BitmapFactory.decodeResource(context.resources, R.drawable.o1_59),
    )

    var pinkBullet = arrayOf(
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_0),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_1),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_2),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_3),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_4),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_5),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_6),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_7),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_8),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_9),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_10),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_11),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_12),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_13),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_14),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_15),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_16),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_17),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_18),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_19),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_20),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_21),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_22),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_23),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_24),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_25),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_26),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_27),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_28),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_29),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_30),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_31),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_32),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_33),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_34),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_35),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_36),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_37),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_38),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_39),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_40),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_41),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_42),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_43),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_44),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_45),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_46),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_47),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_48),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_49),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_50),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_51),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_52),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_53),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_54),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_55),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_56),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_57),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_58),
        BitmapFactory.decodeResource(context.resources, R.drawable.p1_59),
    )

    var bluBullet = arrayOf(
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_0),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_1),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_2),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_3),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_4),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_5),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_6),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_7),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_8),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_9),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_10),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_11),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_12),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_13),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_14),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_15),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_16),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_17),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_18),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_19),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_20),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_21),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_22),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_23),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_24),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_25),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_26),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_27),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_28),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_29),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_30),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_31),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_32),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_33),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_34),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_35),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_36),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_37),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_38),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_39),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_40),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_41),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_42),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_43),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_44),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_45),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_46),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_47),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_48),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_49),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_50),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_51),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_52),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_53),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_54),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_55),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_56),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_57),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_58),
        BitmapFactory.decodeResource(context.resources, R.drawable.b1_59),
    )

    var yellowBullet = arrayOf(
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_0),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_1),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_2),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_3),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_4),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_5),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_6),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_7),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_8),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_9),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_10),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_11),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_12),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_13),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_14),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_15),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_16),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_17),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_18),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_19),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_20),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_21),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_22),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_23),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_24),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_25),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_26),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_27),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_28),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_29),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_30),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_31),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_32),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_33),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_34),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_35),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_36),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_37),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_38),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_39),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_40),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_41),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_42),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_43),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_44),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_45),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_46),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_47),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_48),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_49),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_50),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_51),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_52),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_53),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_54),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_55),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_56),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_57),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_58),
        BitmapFactory.decodeResource(context.resources, R.drawable.y1_59),
    )


    var paddleImgLife: Bitmap

    init {
        //green
        for (el in greenBullet.withIndex()) {
            greenBullet[el.index] = Bitmap.createScaledBitmap(
                el.value,
                dimensions.bulletWidth,
                dimensions.bulletHeight,
                false
            )
        }
        //white
        for (el in whiteBullet.withIndex()) {
            whiteBullet[el.index] = Bitmap.createScaledBitmap(
                el.value,
                dimensions.bulletWidth,
                dimensions.bulletHeight,
                false
            )
        }
        //orange
        for (el in orangeBullet.withIndex()) {
            orangeBullet[el.index] = Bitmap.createScaledBitmap(
                el.value,
                dimensions.bulletWidth,
                dimensions.bulletHeight,
                false
            )
        }
        //pink
        for (el in pinkBullet.withIndex()) {
            pinkBullet[el.index] = Bitmap.createScaledBitmap(
                el.value,
                dimensions.bulletWidth,
                dimensions.bulletHeight,
                false
            )
        }
        //blu
        for (el in bluBullet.withIndex()) {
            bluBullet[el.index] = Bitmap.createScaledBitmap(
                el.value,
                dimensions.bulletWidth,
                dimensions.bulletHeight,
                false
            )
        }
        //yellow
        for (el in yellowBullet.withIndex()) {
            yellowBullet[el.index] = Bitmap.createScaledBitmap(
                el.value,
                dimensions.bulletWidth,
                dimensions.bulletHeight,
                false
            )
        }

        brick = Bitmap.createScaledBitmap(
            brick,
            dimensions.polygonWidth,
            dimensions.polygonHeight,
            false
        )

        star = Bitmap.createScaledBitmap(
            star,
            dimensions.starWidth,
            dimensions.starHeight,
            false
        )
        star2 = Bitmap.createScaledBitmap(
            star2,
            dimensions.starWidth,
            dimensions.starHeight,
            false
        )
        star3 = Bitmap.createScaledBitmap(
            star3,
            dimensions.starWidth,
            dimensions.starHeight,
            false
        )

        ball = Bitmap.createScaledBitmap(ball, dimensions.ballWidth, dimensions.ballHeight, false)

        paddleImg = Bitmap.createScaledBitmap(
            paddleImg,
            dimensions.paddleWidth,
            dimensions.paddleHeight,
            false
        )

        bonusImg = Bitmap.createScaledBitmap(
            bonusImg,
            dimensions.ballWidth,
            dimensions.ballHeight,
            false
        )

        paddleImgLife = Bitmap.createScaledBitmap(
            paddleImg,
            dimensions.lifePaddleWidth,
            dimensions.lifePaddleHeight,
            false
        )
    }

    var bulletColorCTR = 0;
    fun getColoredBullet(): Array<Bitmap> {
        when (++bulletColorCTR) {
            1 ->
                return whiteBullet
            2 ->
                return greenBullet
            3 ->
                return orangeBullet
            4 ->
                return pinkBullet
            5 ->
                return yellowBullet
            6 -> {
                bulletColorCTR = 0
                return bluBullet
            }
            else -> {
                bulletColorCTR = 0
                return whiteBullet
            }
        }
    }
}