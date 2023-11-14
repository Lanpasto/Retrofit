package com.lanpasto.crud

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.google.gson.JsonObject
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var apiService: ApiService
    private var progressDialog:ProgressDialog?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiService=RetrofitHelper.getInstance().create(ApiService::class.java)

        findViewById<Button>(R.id.btnGet).setOnClickListener{
            getUserByID()
        }
        findViewById<Button>(R.id.btnUpdate).setOnClickListener{
            updateUser()
        }
        findViewById<Button>(R.id.btnDelete).setOnClickListener{
            deleteUser()
        }
        findViewById<Button>(R.id.btnCreate).setOnClickListener{
            createUser()
        }
    }

    private fun createUser() {
        lifecycleScope.launch {
            showLoading("Getting, please wait")
            val body=JsonObject().apply {
                addProperty("name","coding delivery")
                addProperty("job","android developer")
            }
            val result = apiService.createUser(body)

            if(result.isSuccessful){
                Log.e("ooooo","CreateUser success: ${result.body()}")

            }else{
                Log.e("ooooo","CreateUser field: ${result.message()}")

            }
            progressDialog?.dismiss()

        }
    }

    private fun deleteUser() {
        lifecycleScope.launch {
            showLoading("Getting, please wait")

            val result = apiService.deleteUser("2")
            if(result.isSuccessful){
                Log.e("ooooo","DeleteUser success: ${result.body()}")

            }else{
                Log.e("ooooo","DeleteUser field: ${result.message()}")

            }
            progressDialog?.dismiss()

        }

    }

    private fun updateUser(){
        lifecycleScope.launch {
            showLoading("Getting, please wait")
            val body=JsonObject().apply {
                addProperty("name","coding delivery")
                addProperty("job","android developer")
            }
            val result = apiService.updateUser("2",body)
            if(result.isSuccessful){
                    Log.e("ooooo","GetUserByID success: ${result.body()}")

                }else{
                    Log.e("OOOOOO","GetUserByID field: ${result.message()}")
                }
            progressDialog?.dismiss()
            }
        }


    private fun getUserByID() {
        lifecycleScope.launch {

            showLoading("Getting, please wait")
            val result = apiService.getUserByID("2")
            if(result.isSuccessful){
                Log.e("ooooo","GetUserByID success: ${result.body()?.data}")

            }else{
                Log.e("OOOOOO","GetUserByID field: ${result.message()}")
            }
           progressDialog?.dismiss()
        }
    }

    private fun showLoading(msg: String){
        progressDialog=ProgressDialog.show(this,null,msg,true)
    }
}