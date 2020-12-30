# Live Coding

1. Explain Dial
1. Explain Scaffold
1. Design

```kotlin
floatingActionButton = {
    Box(
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = {
            },
        ) {

        }
    }
}
```

1. Add expandable background

```kotlin
Canvas(
    modifier = Modifier.padding(28.dp)
) {
    drawCircle(Color.Red, 5f)
}
```

1. Add 1 Speed Dial

```kotlin
Dial(
    color = Color.Yellow,
    distanceFromBottom = 28.dp
)
```

1. Define `FabState`

```kotlin
enum class FabState {
	IDLE, EXPLODED
}
```

1. Define `transitionDefinition`

```kotlin
val fabExplosionTransitionDefinition = transitionDefinition<FabState> {
	state(FabState.IDLE) {
	}
	state(FabState.EXPLODED) {
	}
}
```

1. Define animating state

```kotlin
val animatingFab = remember { mutableStateOf(false) }
```

1. Set onClick method

```kotlin
animatingFab.value = !animatingFab.value
```

1. Define transition state

```kotlin
val state = transition(
    definition = fabExplosionTransitionDefinition,
    initState = FabState.IDLE,
    toState = if (!animatingFab.value) {
        FabState.IDLE
    } else {
        FabState.EXPLODED
    }
)
```

1. Define size prop key and use it

```kotlin
val radiusSize = FloatPropKey(label = "radiusSize")
// ...
state(FabState.IDLE) {
    this[radiusSize] = 0f
}
state(FabState.EXPLODED) {
    this[radiusSize] = 4000f
}
// ...
drawCircle(Color.Red, state[radiusSize])
```

1. Smooth animation on background

```kotlin
transition(fromState = FabState.IDLE, toState = FabState.EXPLODED) {
    sizeState using tween(500)
}
```

1. Define distance prop key and use it

```kotlin
val distanceFromBottom = DpPropKey(label = "distanceFromBottom")
// ...
state(FabState.IDLE) {
    this[distanceFromBottom] = 28.dp
}
state(FabState.EXPLODED) {
    this[distanceFromBottom] = 88.dp
}
// ...
distanceFromBottom = state[distanceFromBottom]
```

1. Bonus: another Dial

```kotlin
val delta = (state[distanceState] - 28.dp) * .8f
// ...
Dial(
    color = Color.Blue,
    distanceFromBottom = state[distanceState] + delta
)
```

1. Bonus: appearing text ✨

```kotlin
val colorOfMessage = ColorPropKey(label = "colorOfMessage")
// ...
state(FabState.IDLE) {
    this[colorOfMessage] = Color.Transparent
}
state(FabState.EXPLODED) {
    this[colorOfMessage] = Color.White
}
// ...
Text(
    text = "Hello, KED!",
    modifier = Modifier.padding(
        end = 68.dp,
        bottom = 16.dp
    ),
    color = state[colorOfMessage]
)
```