package com.vm.manager

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var vmManager: VmManager
    private lateinit var terminalOutput: TextView
    private lateinit var edtRam: EditText
    private lateinit var edtCores: EditText
    private lateinit var edtIsoPath: EditText
    private lateinit var btnPlay: Button
    private lateinit var btnPause: Button
    private lateinit var btnStop: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vmManager = VmManager()

        terminalOutput = findViewById(R.id.terminalOutput)
        edtRam = findViewById(R.id.edtRam)
        edtCores = findViewById(R.id.edtCores)
        edtIsoPath = findViewById(R.id.edtIsoPath)
        btnPlay = findViewById(R.id.btnPlay)
        btnPause = findViewById(R.id.btnPause)
        btnStop = findViewById(R.id.btnStop)

        btnPlay.setOnClickListener {
            val ram = edtRam.text.toString().toIntOrNull() ?: 2048
            val cores = edtCores.text.toString().toIntOrNull() ?: 2
            val iso = edtIsoPath.text.toString().trim()

            vmManager.configureSpecs(ram, cores, iso)
            appendOutput("\nInitializing Virtual Machine engine...\n")
            
            vmManager.playVm { line ->
                runOnUiThread {
                    appendOutput(line)
                }
            }
        }

        btnPause.setOnClickListener {
            vmManager.pauseVm { line ->
                runOnUiThread { appendOutput(line) }
            }
        }

        btnStop.setOnClickListener {
            vmManager.stopVm { line ->
                runOnUiThread { appendOutput(line) }
            }
        }
    }

    private fun appendOutput(text: String) {
        terminalOutput.append(text)
    }
}
