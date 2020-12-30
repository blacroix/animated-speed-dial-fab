package com.example.asdfab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			Scaffold {}
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
