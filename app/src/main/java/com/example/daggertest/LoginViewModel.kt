package com.example.daggertest

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    var progrssbar = MutableLiveData<Int>()
    var userNameErrorTxt = MutableLiveData<String>()
    var userPassErrorTxt = MutableLiveData<String>()

    private val loginRepositoryImpl =  LoginRepositoryImpl()

    fun getUser(username: String,password: String,loginView: LoginView){
        progrssbar.postValue(View.VISIBLE)
        if (username.isEmpty() || password.isEmpty()){
            progrssbar.postValue(View.GONE)
            if (username.isEmpty())userNameErrorTxt.postValue("Username required")
            if (password.isEmpty())userPassErrorTxt.postValue("Password required")
            return
        }
        viewModelScope.launch(Dispatchers.IO){

            try {
                val response = loginRepositoryImpl.getUser(username,password)
                response.let {
                    progrssbar.postValue(View.GONE)
                    viewModelScope.launch {
                        loginView.successful("Successful")
                    }
                }
            }catch (e: ApiException){
                progrssbar.postValue(View.GONE)
                e.localizedMessage?.let {
                    loginView.loginFailed(it)
                }
            }
        }
    }

}