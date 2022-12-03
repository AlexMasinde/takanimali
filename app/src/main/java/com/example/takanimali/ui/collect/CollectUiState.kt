package com.example.takanimali.ui.collect

data class CollectUiState(
   val idError: String? = null,
   val quantityError: String? = null,
   val IOAuthError: String? = null,
   val HTTPAuthError: String? = null,
   val collectUiError: Boolean  = false

)
