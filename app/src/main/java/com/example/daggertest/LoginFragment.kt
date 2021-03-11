package com.example.daggertest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginFragment : Fragment(),LoginView {
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        btn_login.setOnClickListener {
            attemptLogin()
        }
    }

    private fun attemptLogin(){
        loginViewModel.getUser(etUsername.text.toString(), etUserPass.text.toString(), this)
    }

    override suspend fun successful(loginResponse: String) {
        withContext(Dispatchers.Main){
            Toast.makeText(activity, loginResponse, Toast.LENGTH_SHORT).show()
        }
    }

    override suspend fun loginFailed(message: String) {
        withContext(Dispatchers.Main){
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        }

    }

}