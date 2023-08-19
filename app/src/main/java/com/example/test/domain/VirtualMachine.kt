package com.example.test.domain

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

data class VirtualMachine(
    var name: String,
    var id: Int,
//    var hostname: String,
//    var fqdn: String,
//    var mode: Mode,
//    var backupFrequency: Int,
//    var availability: Availability,
//    var hostServer: Int,
//    var cluster: Int,
    var state: State,
    var vcpUAmount: Int,
    var memoryAmount: Int,
    var storageAmount: Int,


    var vcpUAmountInUse: Int,
    var memoryAmountInUse: Int,
    var storageAmountInUse: Int,



//    var highAvailability: Boolean,
//    var requestDate: LocalDate,
    var startDate: String,
    var endDate: String
)