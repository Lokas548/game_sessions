package com.mironov.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.mironov.myapplication.R
import com.mironov.myapplication.api.`interface`.ClientDto.UserData
import com.mironov.myapplication.api.`interface`.ClientDto.UsernameData
import com.mironov.myapplication.api.`interface`.ClientService.ApiClient
import com.mironov.myapplication.api.`interface`.PreferencesHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response




class PostRegistrationFragment : Fragment() {

    private fun setUsernameRequest(nickname: String) {
        val userData = UsernameData(nickname)
        val apiService = ApiClient.getApiService(PreferencesHelper(requireContext()).getJwtToken().toString())
        val call = apiService.setUsername(userData)

        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    Toast.makeText(requireActivity(), "Успешно", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(requireActivity(), "Ошибка: ${response.code()} ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                println("Failed to make request: ${t.message}")
                Toast.makeText(requireActivity(), "Ошибка при выполнении запроса", Toast.LENGTH_SHORT).show()
            }
        })
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val root = inflater.inflate(R.layout.fragment_post_registration, container, false)
        val setNicknameBtn = root.findViewById<Button>(R.id.toMainScreenBtn)
        val inputNickname = root.findViewById<EditText>(R.id.inputNickname)
        val navController = NavHostFragment.findNavController(this)


        setNicknameBtn.setOnClickListener {
            setUsernameRequest(inputNickname.text.toString())
            navController.navigate(R.id.searchFragment)
        }

        return root

    }


}