package com.mironov.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.mironov.myapplication.api.`interface`.ClientDto.AuthData
import com.mironov.myapplication.api.`interface`.ClientDto.UserData
import com.mironov.myapplication.api.`interface`.ClientService.ApiClient
import com.mironov.myapplication.api.`interface`.PreferencesHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment() {

    private fun sendLoginRequest(email: String, password: String, callback: (String?) -> Unit) {
        val userData = UserData(email, password)
        val apiService = ApiClient.getNoTokenApiService()


        val call = apiService.login(userData)
        call.enqueue(object : Callback<AuthData> {
            override fun onResponse(call: Call<AuthData>, response: Response<AuthData>) {
                println(response.body())
                if (response.isSuccessful) {
                    val jwtToken = response.body()
                    println("from login : " + jwtToken.toString())
                    callback(jwtToken.toString())

                } else {
                    Toast.makeText(requireActivity(), "Неверные учетные данные", Toast.LENGTH_SHORT).show()
                    callback(null)
                }
            }

            override fun onFailure(call: Call<AuthData>, t: Throwable) {
                println("Failed to make request: ${t.message}")
                Toast.makeText(requireActivity(), "Ошибка при выполнении запроса", Toast.LENGTH_SHORT).show()
                callback(null)
            }
        })
    }

    private fun sendGetUsernameRequest(callback: (Boolean) -> Unit) {
        val apiService = ApiClient.getApiService(PreferencesHelper(requireContext()).getJwtToken().toString())
        println("from get : " + PreferencesHelper(requireContext()).getJwtToken().toString())

        val call = apiService.getUsername()

        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    callback(true)
                } else {
                    callback(false)
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                println("Failure: ${t.message}")
                callback(false)
            }
        })
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val storage = PreferencesHelper(requireContext())
        val root = inflater.inflate(R.layout.fragment_login, container, false)
        val navController = NavHostFragment.findNavController(this)

        val inputText = root.findViewById<EditText>(R.id.inputText)
        val authBtn = root.findViewById<Button>(R.id.authBtn)
        val goRegistrationBtn = root.findViewById<Button>(R.id.goToRegistration)
        val password = root.findViewById<EditText>(R.id.password)

        authBtn.setOnClickListener {
            if (inputText.text.isNotEmpty() && password.text.isNotEmpty()) {
                sendLoginRequest(inputText.text.toString(), password.text.toString()) { jwtToken ->
                    if (jwtToken != null) {
                        storage.saveJwtToken(jwtToken)
                        println("token saved:" + jwtToken)
                        sendGetUsernameRequest { isSuccess ->

                            if (isSuccess) {
                                navController.navigate(R.id.lobbyFragment)
                            } else {
                                navController.navigate(R.id.postRegistrationFragment)
                            }
                        }
                    }
                }
            }
            else{
                Toast.makeText(requireContext(), "Поля пустые", Toast.LENGTH_SHORT).show()
            }

        }

        goRegistrationBtn.setOnClickListener {
            navController.navigate(R.id.registrationFragment)
        }
        return root
    }
}





