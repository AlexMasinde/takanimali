package com.example.takanimali.data


sealed interface ReportResource {
    object NotReported : ReportResource
    object Loading : ReportResource
    object Reported : ReportResource
}