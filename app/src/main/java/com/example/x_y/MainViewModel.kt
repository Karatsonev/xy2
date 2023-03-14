package com.example.x_y

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val x1 = MutableLiveData("")
    val x2 = MutableLiveData("")
    val y1 = MutableLiveData("")
    val y2 = MutableLiveData("")
    val x = MutableLiveData("")
    val y = MutableLiveData("")
    val xyEnabled = MediatorLiveData<Boolean>()
    val result = MutableLiveData("")

    fun listenForUserInteraction(
        x1: MutableLiveData<String>,
        x2: MutableLiveData<String>,
        y1: MutableLiveData<String>,
        y2: MutableLiveData<String>
    ) {

        xyEnabled.removeSource(x1)
        xyEnabled.removeSource(x2)
        xyEnabled.removeSource(y1)
        xyEnabled.removeSource(y2)

        xyEnabled.addSource(x1) {
            xyEnabled.value =
                it.isNotEmpty() && x2.value!!.isNotEmpty() && y1.value!!.isNotEmpty() && y2.value!!.isNotEmpty()
        }

        xyEnabled.addSource(x2) {
            xyEnabled.value =
                it.isNotEmpty() && x1.value!!.isNotEmpty() && y1.value!!.isNotEmpty() && y2.value!!.isNotEmpty()
        }

        xyEnabled.addSource(y1) {
            xyEnabled.value =
                it.isNotEmpty() && x2.value!!.isNotEmpty() && x1.value!!.isNotEmpty() && y2.value!!.isNotEmpty()
        }

        xyEnabled.addSource(y2) {
            xyEnabled.value =
                it.isNotEmpty() && x2.value!!.isNotEmpty() && y1.value!!.isNotEmpty() && x1.value!!.isNotEmpty()
        }
    }

    fun calculate(type: PointType) {

        val x1Num = x1.value!!.toDouble()
        val x2Num = x2.value!!.toDouble()
        val y1Num = y1.value!!.toDouble()
        val y2Num = y2.value!!.toDouble()

        when (type) {
            PointType.X -> {
                val yNum = y.value!!.toDouble()
                val xNum = (((yNum - y1Num) * (x2Num - x1Num)) / (y2Num - y1Num)) + x1Num
                result.value = "X = $xNum"
            }

            PointType.Y -> {
                val xNum = x.value!!.toDouble()
                val yNum = (((xNum - x1Num) * (y2Num - y1Num)) / (x2Num - x1Num)) + y1Num
                result.value = "Y = $yNum"
            }
        }
    }

    fun clear() {
        x1.value = ""
        x2.value = ""
        y1.value = ""
        y2.value = ""
        x.value = ""
        y.value = ""
        result.value = ""
    }

}