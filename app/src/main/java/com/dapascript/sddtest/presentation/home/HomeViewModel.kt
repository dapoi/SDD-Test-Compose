package com.dapascript.sddtest.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dapascript.sddtest.data.repository.PromoRepository
import com.dapascript.sddtest.data.source.PromoResponse
import com.dapascript.sddtest.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val promoRepository: PromoRepository
) : ViewModel() {

    private val _getPromos = MutableStateFlow<Resource<List<PromoResponse>>>(Resource.Loading)
    val getPromos = _getPromos.asStateFlow()

    init {
        viewModelScope.launch {
            promoRepository.getPromos().collect {
                _getPromos.value = it
            }
        }
    }
}