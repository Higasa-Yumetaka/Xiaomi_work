package com.example.work_liuchangxu.work_0430

class MyLoadingEvent(state: Int) {
    @JvmField
    var LOADING_STATE = 0
    var current = 0

    init {
        LOADING_STATE = state
    }

    companion object {
        @JvmField
        var STATE_LOADING = -1
        var STATE_COMPLETE = -2
        var STATE_END = -3
    }
}
