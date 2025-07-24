package com.example.shmryandex.app.presentation.options.sync

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shmryandex.app.domain.usecase.sync.GetSyncIntervalUseCase
import com.example.shmryandex.app.domain.usecase.sync.SchedulePeriodicSyncUseCase
import com.example.shmryandex.app.domain.usecase.sync.SetSyncIntervalUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SyncViewModel @Inject constructor(
    private val schedulePeriodicSyncUseCase: SchedulePeriodicSyncUseCase,
    private val setSyncIntervalUseCase: SetSyncIntervalUseCase,
    private val getSyncIntervalUseCase: GetSyncIntervalUseCase
): ViewModel() {

    private val _syncInterval = MutableStateFlow(2)
    val syncInterval = _syncInterval.asStateFlow()

    init {
        getSyncInterval()
    }

    private fun getSyncInterval()  {
        viewModelScope.launch(Dispatchers.IO) {
            _syncInterval.value = getSyncIntervalUseCase().toInt()
        }
    }

    fun setSyncInterval(interval: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            setSyncIntervalUseCase(interval.toLong())
            getSyncInterval()
            schedulePeriodicSyncUseCase()
        }
    }
}