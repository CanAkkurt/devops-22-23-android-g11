package com.example.test.database.VirutalMachine

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.test.domain.*
import com.squareup.moshi.Json
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

@Entity(tableName = "virtual_machine_table")
data class DatabaseVirtualMachine(

    @ColumnInfo(name = "virtual_machine_name")
    var name: String,
    @PrimaryKey
    var id: Int,
//    @ColumnInfo(name = "hostname")
//    var hostname: String,
//    @ColumnInfo(name = "fqdn")
//    var fqdn: String,
//    @ColumnInfo(name = "mode")
//    var mode: String,
//    @ColumnInfo(name = "backup_frequency")
//    var backupFrequency: Int,
//    @ColumnInfo(name = "availability")
//    var availability: String,
//    @ColumnInfo(name = "host_server")
//    var hostServer: Int,
//    @ColumnInfo(name = "cluster")
//    var cluster: Int,
    @ColumnInfo(name = "State")
    var state: String,
    @ColumnInfo(name = "vcpu_amount")
    var vcpUAmount: Int,
    @ColumnInfo(name = "memory_amount")
    var memoryAmount: Int,
    @ColumnInfo(name = "storage_amount")
    var storageAmount: Int,


    @ColumnInfo(name = "vcpu_amountInUse")
    var vcpUAmountInUse: Int,
    @ColumnInfo(name = "memory_amountInUse")
    var memoryAmountInUse: Int,
    @ColumnInfo(name = "storage_amountInUse")
    var storageAmountInUse: Int,






//    @ColumnInfo(name = "high_availability")
//    var highAvailability: Boolean,
//    @ColumnInfo(name = "request_date")
//    var requestDate: String,
    @ColumnInfo(name = "begin_date")
    var startDate: String,
    @ColumnInfo(name = "end_date")
    var endDate: String
)

// Convert a single DatabaseCustomer into a normal domain Customer
fun DatabaseVirtualMachine.asDomainModel(): VirtualMachine {
    return VirtualMachine(
        id = id,
        name = name,
//        hostname = hostname,
//        fqdn = fqdn,
//        mode = mode.asDomainMode(),
//        backupFrequency = backupFrequency,
//        availability = availability.asDomainAvailability(),
//        hostServer = hostServer,
//        cluster = cluster,
        state = state.asDomainState(),
        vcpUAmount = vcpUAmount,
        memoryAmount = memoryAmount,
        storageAmount = storageAmount,




        vcpUAmountInUse = vcpUAmountInUse,
        memoryAmountInUse = memoryAmountInUse,
        storageAmountInUse = storageAmountInUse,
//        highAvailability = highAvailability,
//        requestDate = requestDate.asDomainDate(requestDate),
        startDate = startDate,
        endDate = endDate,
    )
}

// Convert a list of DatabaseCustomers in a list of normal domain Customers
fun List<DatabaseVirtualMachine>.asDomainModel(): List<VirtualMachine> {
    return map { it.asDomainModel() }
}

fun String.asDomainMode() : Mode {
    if (lowercase().equals("iaas")) {
        return Mode.IAAS
    } else if (lowercase().equals("ai")) {
        return Mode.AI
    } else if (lowercase().equals("ms_sql")) {
        return Mode.MS_SQL
    } else if (lowercase().equals("mysql")) {
        return Mode.MySQL
    } else if (lowercase().equals("postgresql")) {
        return Mode.PostgreSQL
    }
    return Mode.MongoDB

}

fun String.asDomainAvailability() : Availability {
    if (lowercase().equals("business_hours")) {
        return Availability.BUSINESS_HOURS
    }
    return Availability.ALWAYS

}

fun String.asDomainState() : State {
    if (lowercase().equals("requested")) {
        return State.Requested
    } else if (lowercase().equals("inprogress")) {
        return State.InProgress
    } else if (lowercase().equals("denied")) {
        return State.Denied
    }
    return State.Accepted

}

fun asDomainDate(date: String) : LocalDateTime {
    val format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSX")
    val result: LocalDateTime = LocalDateTime.parse(date, format)



    return result

}

