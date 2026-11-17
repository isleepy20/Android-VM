package com.androidvm

import android.util.Log
import java.io.File

class VmManager {
    var ramMegabytes: Int = 2048
    var cpuCores: Int = 2
    var isoPath: String = ""
    var isRunning: Boolean = false
    var isPaused: Boolean = false

    private var process: Process? = null

    fun configureSpecs(ram: Int, cores: Int, iso: String) {
        ramMegabytes = ram
        cpuCores = cores
        isoPath = iso
        Log.d("VmManager", "Configured VM: RAM=${ram}MB, Cores=$cores, ISO=$iso")
    }

    fun playVm(onOutput: (String) -> Unit) {
        if (isRunning && isPaused) {
            isPaused = false
            onOutput("\n[VM Resumed]\n")
            return
        }

        if (isRunning) return

        if (isoPath.isBlank() || !File(isoPath).exists()) {
            onOutput("\n[Error]: Invalid or missing ISO path. Please select a valid operating system ISO.\n")
            return
        }

        val command = listOf(
            "qemu-system-x86_64",
            "-m", ramMegabytes.toString(),
            "-smp", cpuCores.toString(),
            "-cdrom", isoPath,
            "-nographic"
        )

        try {
            val processBuilder = ProcessBuilder(command)
            processBuilder.redirectErrorStream(true)
            process = processBuilder.start()
            isRunning = true
            isPaused = false

            onOutput("\n[VM Started Successfully]\n")

            Thread {
                val reader = process?.inputStream?.bufferedReader()
                reader?.forEachLine { line ->
                    onOutput("$line\n")
                }
                isRunning = false
            }.start()

        } catch (e: Exception) {
            isRunning = false
            onOutput("\n[Failed to start VM]: ${e.message}\n")
        }
    }

    fun pauseVm(onOutput: (String) -> Unit) {
        if (!isRunning) return
        isPaused = true
        onOutput("\n[VM Paused]\n")
    }

    fun stopVm(onOutput: (String) -> Unit) {
        if (!isRunning && process == null) return
        try {
            process?.destroy()
            process = null
            isRunning = false
            isPaused = false
            onOutput("\n[VM Stopped Completely]\n")
        } catch (e: Exception) {
            onOutput("\n[Error stopping VM]: ${e.message}\n")
        }
    }
}
