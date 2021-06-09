package com.irfan.spikapps.ui.homeall.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.irfan.spikapps.databinding.FragmentProfileBinding
import com.irfan.spikapps.ui.homeall.profile.info.InfoActivity
import com.irfan.spikapps.ui.homeall.profile.kp.KPActivity
import com.irfan.spikapps.ui.homeall.profile.sk.SKActivity
import com.irfan.spikapps.ui.homelogin.HomeLoginActivity

class ProfileFragment : Fragment() {

    private lateinit var profileBinding: FragmentProfileBinding
    private lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileBinding = FragmentProfileBinding.inflate(layoutInflater,container,false)
        return profileBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser

        profileBinding.btnInfo.setOnClickListener{
            requireActivity().run {
                startActivity(Intent(this, InfoActivity::class.java))
            }
        }

        profileBinding.btnKp.setOnClickListener{
            requireActivity().run {
                startActivity(Intent(this, KPActivity::class.java))
            }
        }

        profileBinding.btnSK.setOnClickListener{
            requireActivity().run {
                startActivity(Intent(this, SKActivity::class.java))
            }
        }

        profileBinding.tvSignOut.setOnClickListener {
            mAuth.signOut()
            requireActivity().run {
                startActivity(Intent(this, HomeLoginActivity::class.java))
                finish()
            }
        }

        profileBinding.tvUsernameProfile.text = currentUser?.displayName
        profileBinding.tvEmailUsernameProfile.text = currentUser?.email

        Glide.with(this)
            .load(currentUser?.photoUrl)
            .into(profileBinding.ivAvatarProfile)
    }
}