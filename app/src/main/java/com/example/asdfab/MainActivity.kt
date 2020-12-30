package com.example.asdfab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ColorPropKey
import androidx.compose.animation.DpPropKey
import androidx.compose.animation.core.FloatPropKey
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.animation.transition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val sizeState = FloatPropKey(label = "size")
val colorState = ColorPropKey(label = "color")
val distanceState = DpPropKey(label = "distance")

enum class FabState {
	IDLE, EXPLODED
}

val fabExplosionTransitionDefinition = transitionDefinition<FabState> {
	state(FabState.IDLE) {
		this[sizeState] = 0f
		this[colorState] = Color.Transparent
		this[distanceState] = 28.dp
	}
	state(FabState.EXPLODED) {
		this[sizeState] = 4000f
		this[colorState] = Color.White
		this[distanceState] = 88.dp
	}
	transition(fromState = FabState.IDLE, toState = FabState.EXPLODED) {
		sizeState using tween(500)
	}
}

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			Scaffold(
				topBar = {
					TopAppBar(title = {
						Text(text = "Hello, World!")
					})
				},
				floatingActionButton = {
					val animatingFab = remember { mutableStateOf(false) }
					val state = transition(
						definition = fabExplosionTransitionDefinition,
						initState = FabState.IDLE,
						toState = if (!animatingFab.value) {
							FabState.IDLE
						} else {
							FabState.EXPLODED
						}
					)
					Box(
						contentAlignment = Alignment.BottomEnd
					) {
						Canvas(
							modifier = Modifier.padding(28.dp)
						) {
							drawCircle(Color.Red, state[sizeState])
						}
						Text(
							text = "Hello, KED!",
							modifier = Modifier.padding(
								end = 68.dp,
								bottom = 16.dp
							),
							color = state[colorState]
						)
						val delta = (state[distanceState] - 28.dp) * .8f
						Dial(
							color = Color.Yellow,
							distanceFromBottom = state[distanceState] + delta * 3
						)
						Dial(
							color = Color.Magenta,
							distanceFromBottom = state[distanceState] + delta * 2
						)
						Dial(
							color = Color.Blue,
							distanceFromBottom = state[distanceState] + delta
						)
						Dial(
							color = Color.Green,
							distanceFromBottom = state[distanceState]
						)
						FloatingActionButton(
							onClick = {
								animatingFab.value = !animatingFab.value
							},
						) {

						}
					}
				}
			) {}
		}
	}
}

@Composable
fun Dial(color: Color, distanceFromBottom: Dp) {
	Canvas(
		modifier = Modifier.padding(
			end = 28.dp,
			bottom = distanceFromBottom
		)
	) {
		drawCircle(color, 44f)
	}
}
