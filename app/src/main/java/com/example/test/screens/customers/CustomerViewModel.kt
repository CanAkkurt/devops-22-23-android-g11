package com.example.test.screens.customers

import android.app.Application
import android.content.res.Resources
import android.os.AsyncTask
import android.util.Log.i
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.startup.StartupLogger.i
import com.example.test.database.customer.CustomerDatabase
import com.example.test.database.customer.asDomainModel
import com.example.test.domain.Customer
import com.example.test.network.ApiStatus
import com.example.test.repository.CustomerRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import timber.log.Timber.Forest.i
import java.util.*

class CustomerViewModel(application: Application, id: Int) : ViewModel() {
    //live data objects
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val database = CustomerDatabase.getInstance(application.applicationContext)
    private val customerRepository = CustomerRepository(database)
    private val _customer = MutableLiveData<Customer>()
    val customer: LiveData<Customer>
        get() = _customer

    private val _backupCustomer = MutableLiveData<Customer>()
    val backupCustomer: LiveData<Customer>
        get() = _backupCustomer

    private val _backupContact = MutableLiveData<Customer>()
    val backupContact: LiveData<Customer>
        get() = _backupContact


    init {

        Timber.i("Getting Member $id")

//        Timber.i("Getting MemberDetails ${customers}")


        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            customerRepository.refreshCustomers()

//             val customerTest = customerRepository.customers.value!!.get(1)

//            Timber.i("Getting MemberDetails ${customerTest}")
            try {
                val temp = database.customerDatabaseDao.getCustomerById(id)
                    ?: throw Resources.NotFoundException("Member $id was not found.")
                _customer.value = temp.asDomainModel()
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                Timber.e("Exception occurred while refreshing the customersDetails", e.message)
                _status.value = ApiStatus.ERROR
//            }
            }
        }
    }
}