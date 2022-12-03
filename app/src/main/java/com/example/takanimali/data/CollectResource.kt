package com.example.takanimali.data

sealed interface CollectResource {
        object Collected : CollectResource
        object Loading : CollectResource
        object NotCollected : CollectResource
}