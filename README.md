# Android-VM

<div align="center">

[![Build Android VM APK](https://github.com/isleepy20/Android-VM/actions/workflows/build-apk.yml/badge.svg)](https://github.com/isleepy20/Android-VM/actions/workflows/build-apk.yml)
[![Platform](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com)
[![Gradle Version](https://img.shields.io/badge/Gradle-8.5-blue.svg)](https://gradle.org)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

</div>

---

## 🚀 Overview

**Android-VM** is a streamlined, automated Android project framework engineered for continuous integration (CI/CD) and lightweight virtual environment execution. Built with modern Gradle toolchains and GitHub Actions, it compiles, validates, and packages debug-ready Android application packages (`.apk`) automatically upon every push.

---

## 🛠️ Technical Stack & Architecture

* **Build System:** Gradle 8.5 via custom runner execution.
* **Android Gradle Plugin (AGP):** v8.2.2 with centralized repository management (`settings.gradle`).
* **Target & Compile SDK:** API Level 34 (Android 14) with backward compatibility down to API Level 26 (Android 8.0 Oreo).
* **Language/Framework:** Kotlin 1.9.22, AndroidX Core, AppCompat, and Material Components.
* **CI/CD Pipeline:** Fully automated GitHub Actions workflow (`ubuntu-latest`) configured with isolated dependency fetching and artifact publishing.

---

## 📦 Project Directory Structure

```text
Android-VM/
├── .github/
│   └── workflows/
│       └── build-apk.yml       # Automated CI/CD build pipeline
├── app/
│   ├── build.gradle            # App-level dependencies and SDK targets
│   └── src/                    # Application source code & resources
├── gradle/
│   └── wrapper/                # Gradle wrapper configuration
├── build.gradle                # Root project configuration
├── gradle.properties           # Global Gradle & AndroidX flags
└── settings.gradle             # Centralized plugin & repository management
