[![Maven Central](https://maven-badges.herokuapp.com/maven-central/community.flock.kotlinx.rgxgen/kotlin-rgxgen/badge.svg)](https://maven-badges.herokuapp.com/maven-central/community.flock.kotlinx.rgxgen/kotlin-rgxgen)

# kotlin-rgxgen

kotlin-rgxgen is a Kolin port of [curious-odd-man/RgxGen](https://github.com/curious-odd-man/RgxGen)

# Usage

## Install

```
implementation("community.flock.kotlinx.rgxgen:kotlin-rgxgen:0.0.1")
```

## Example

```kotlin
val randomString = RgxGen.parse("\\w{1,50}").generate()
```

# Contributing
Contributions to kotlin-rgxgen are welcome! To contribute, please fork the repository and submit a pull request.