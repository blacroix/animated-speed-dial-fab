# Live Coding

1. Explain Dial
1. Explain Scaffold
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