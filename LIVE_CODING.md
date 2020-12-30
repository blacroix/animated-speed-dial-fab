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
    drawCircle(Color.Red, 5.dp)
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
val sizeState = FloatPropKey(label = "size")
// ...
state(FabState.IDLE) {
    this[sizeState] = 0f
}
state(FabState.EXPLODED) {
    this[sizeState] = 4000f
}
// ...
drawCircle(Color.Red, state[sizeState])
```

1. Define distance prop key and use it

```kotlin
val distanceState = DpPropKey(label = "distance")
// ...
state(FabState.IDLE) {
    this[distanceState] = 28.dp
}
state(FabState.EXPLODED) {
    this[distanceState] = 88.dp
}
// ...
distanceFromBottom = state[distanceState]
```

1. Bonus: smooth animation on background

```kotlin
transition(fromState = FabState.IDLE, toState = FabState.EXPLODED) {
    sizeState using tween(500)
}
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
val colorState = ColorPropKey(label = "color")
// ...
state(FabState.IDLE) {
    this[colorState] = Color.Transparent
}
state(FabState.EXPLODED) {
    this[colorState] = Color.White
}
// ...
Text(
    text = "Hello, KED!",
    modifier = Modifier.padding(
        end = 68.dp,
        bottom = 16.dp
    ),
    color = state[colorState]
)
```