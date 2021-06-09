package com.irfan.spikapps.ui.homeall.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.irfan.spikapps.databinding.FragmentHomeBinding
import com.irfan.spikapps.ui.homeall.home.tflite.DetectorActivity

class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var mAuth: FirebaseAuth
    var databaseReference :  DatabaseReference? = null
    var database: FirebaseDatabase? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")


        homeBinding.tvUsername.text = currentUser?.displayName
        homeBinding.tvEmailUsername.text = currentUser?.email

        Glide.with(this)
            .load(currentUser?.photoUrl)
            .into(homeBinding.ivAvatarHome)

        loadProfile()
    }

    private fun loadProfile() {

        val user = mAuth.currentUser
        val userreference = databaseReference?.child(user?.uid!!)

        homeBinding.tvEmailUsername.text = user?.email

        userreference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                homeBinding.tvUsername.text = snapshot.child("etFullNameSignUp").value.toString()
                homeBinding.tvUsername.text = snapshot.child("etFullNameSignUp").value.toString()


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        homeBinding.btnTfLite.setOnClickListener{
            requireActivity().run {
                startActivity(Intent(this, DetectorActivity::class.java))
            }
        }
        homeBinding.btnKamus.setOnClickListener{
            requireActivity().run {
                startActivity(Intent(this, KamusActivity::class.java))
            }
        }
    }
}