package com.example.x_y

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.viewModels
import com.example.x_y.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }
        setContentView(binding.root)

        viewModel.listenForUserInteraction(viewModel.x1, viewModel.x2, viewModel.y1, viewModel.y2)

        viewModel.x.observe(this) {
            if (it.isNotEmpty()) {
                viewModel.y.value = ""
                viewModel.calculate(PointType.Y)
            }
        }

        viewModel.y.observe(this) {
            if (it.isNotEmpty()) {
                viewModel.x.value = ""
                viewModel.calculate(PointType.X)
            }
        }

        viewModel.xyEnabled.observe(this) { enabled ->
            if (enabled) {
                viewModel.x.value?.let {
                    if (it.isNotEmpty()) {
                        viewModel.y.value = ""
                        viewModel.calculate(PointType.Y)
                    }
                }

                viewModel.y.value?.let {
                    if (it.isNotEmpty()) {
                        viewModel.x.value = ""
                        viewModel.calculate(PointType.X)
                    }
                }
            }
        }

        binding.btnClear.setOnClickListener {
            viewModel.clear()
        }

    }
}