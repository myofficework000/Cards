<h1 align="center">Compose Card In Jetpack Compose Library</h1></br>

</h1>
<p align="center">
 <img alt="material" src="https://custom-icon-badges.demolab.com/badge/material%20you-palegreen?style=for-the-badge&logoColor=black&logo=material-you"/></a>
  <img alt="API" src="https://img.shields.io/badge/Api%2023+-50f270?logo=android&logoColor=black&style=for-the-badge"/></a>
  <img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-a503fc?logo=kotlin&logoColor=white&style=for-the-badge"/></a>
<<<<<<< HEAD
  <img alt="Jetpack Compose" src="https://img.shields.io/static/v1?style=for-the-badge&message=Jetpack+Compose&color=4285F4&logo=Jetpack+Compose&logoColor=FFFFFF&label="/></a>
=======
>>>>>>> 6c1bec2e3f20997eab54f8476115fcd755eb2891
</p>
	  
<p align="center"> 💳 ComposeCards is a beautifully designed payment view library for Credit and Debit Cards. Made using Jetpack Compose 🎉. It allows you to easily integrate Payment View with much smooth UI and animations. </p>

<br>

<p align="center">
<img src="assets/Untitled.gif" width="280"/>
</p>


 ## What's included?📜
- 🗂️Automatic card type recognition that supports the following cards:
  - Visa
  - Mastercard
  - RuPay
  - American Express
  - Maestro
  - Diners Club 
    etc

- 🤩Simple VisualTransformation subclasses for the following use cases:
  - Card number (with custom separators, digit masking)

## Steps to Add the library to your project✨


Add the following to your **root** `build.gradle` file:
```gradle
allprojects {
	repositories {
		maven { url 'https://jitpack.io' }
	}
}
```

Lastly, add the following dependency to your app's `build.gradle.kts` (Kotlin) or `build.gradle` (Groovy) file:

<details>
<summary>Kotlin (KTS)</summary>
<br>

```kotlin
dependencies {
        implementation ("com.github.myofficework000:Cards:1.0.0")
}
```
</details>

<details>
<summary>Groovy</summary>
<br>

## Usage📓
You can use the `CardDetails` by simply passing this to the screen you want to use. For example:
```Kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCardsTheme {
                CardDetails()
            }
        }
    }
}
```
## License
Copyright 2024 Abhishek Pathak

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

## Setup Requirements

- An Android device or emulator 📱
- Android Studio 💻

## Getting Started

Ready to try it out yourself? Here's what to do:

1. Clone this project 🐑
2. Import the project into Android Studio 🚀
3. Connect your Android device via USB or start your emulator 🌐
4. Once the project has finished setting up, click the "run" button 🏃‍♂️

### Contact - Let's become friend
- [Github](https://github.com/cheetahmail007)
- [Linkedin](https://www.linkedin.com/in/myofficework/)
- [MEDIUM](https://medium.com/@myofficework000)

<p>
Don't forget to star ⭐ the repo it motivates me to share more open source
</p>
