package com.example.besttodo.ui.todos

import co.zsmb.rainbowcake.base.OneShotEvent

data class Failed(val message: String) : OneShotEvent

data class ActionSuccess(val message: String) : OneShotEvent