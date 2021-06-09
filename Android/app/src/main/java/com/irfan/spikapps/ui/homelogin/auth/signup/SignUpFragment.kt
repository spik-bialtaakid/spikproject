package com.irfan.spikapps.ui.homelogin.auth.signup

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.irfan.spikapps.databinding.FragmentSignUpBinding
import com.irfan.spikapps.ui.homelogin.HomeLoginActivity
import kotlin.math.log

class SignUpFragment : Fragment() {

    private lateinit var fragmentSignUpBinding: FragmentSignUpBinding

    private lateinit var mAuth: FirebaseAuth

    private var databaseReference :  DatabaseReference? = null
    private var database: FirebaseDatabase? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentSignUpBinding = FragmentSignUpBinding.inflate(layoutInflater,container,false)
        return fragmentSignUpBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")

        fragmentSignUpBinding.btnBackSignUp.setOnClickListener{
            requireActivity().run {
                startActivity(Intent(this, HomeLoginActivity::class.java))
                finish()
            }
        }

        register()
    }

    private fun register() {

        fragmentSignUpBinding.ivAvatarSingUp.setOnClickListener{
            ImagePicker.with(this)
                    .cameraOnly()
                    .start()
        }


        fragmentSignUpBinding.btnRegister.setOnClickListener {


            when {
                TextUtils.isEmpty(fragmentSignUpBinding.etFullNameSignUp.text.toString()) -> {
                    fragmentSignUpBinding.etFullNameSignUp.error = "Please enter full name "
                    return@setOnClickListener
                }
                TextUtils.isEmpty(fragmentSignUpBinding.etEmailSignUp.text.toString()) -> {
                    fragmentSignUpBinding.etEmailSignUp.error = "Please enter last email  "
                    return@setOnClickListener
                }
                TextUtils.isEmpty(fragmentSignUpBinding.etPasswordSignUp.text.toString()) -> {
                    fragmentSignUpBinding.etPasswordSignUp.error = "Please enter user Password "
                    return@setOnClickListener
                }
                TextUtils.isEmpty(fragmentSignUpBinding.etConfirmPasswordSignUp.text.toString()) -> {
                    fragmentSignUpBinding.etConfirmPasswordSignUp.error = "Please enter password "
                    return@setOnClickListener
                }
                else -> mAuth.createUserWithEmailAndPassword(
                    fragmentSignUpBinding.etEmailSignUp.text.toString(),
                    fragmentSignUpBinding.etConfirmPasswordSignUp.text.toString()
                )
                    .addOnCompleteListener {
                        if (it.isSuccessful) {

                            val currentUser = mAuth.currentUser
                            val currentUSerDb = databaseReference?.child((currentUser?.uid!!))
                            currentUSerDb?.child("etFullNameSignUp")
                                ?.setValue(fragmentSignUpBinding.etFullNameSignUp.text.toString())


                            Toast.makeText(context, "Registration Success. ", Toast.LENGTH_LONG)
                                .show()

                        } else {
                            Toast.makeText(
                                context,
                                "Registration failed, please try again! ",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        Log.d(TAG, "register: ")
                    }
            }
        }
    }

}