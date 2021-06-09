package com.irfan.spikapps.ui.homelogin.auth.signin

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.irfan.spikapps.R
import com.irfan.spikapps.databinding.FragmentSigninBinding
import com.irfan.spikapps.ui.HomeAllActivity
import com.irfan.spikapps.ui.homelogin.HomeLoginActivity


class SigninFragment : Fragment() {

    private lateinit var fragmentSigninBinding: FragmentSigninBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    companion object{
        private const val RC_SIGN_IN = 120
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      fragmentSigninBinding = FragmentSigninBinding.inflate(layoutInflater,container,false)
        return fragmentSigninBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()

        fragmentSigninBinding.btnBackSignIn.setOnClickListener {
            requireActivity().run {
                startActivity(Intent(this, HomeLoginActivity::class.java))
                finish()
            }
        }


        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        fragmentSigninBinding.btnSignInGoogle.setOnClickListener{
            signIn()
        }
        login()
    }

    private fun login() {

        fragmentSigninBinding.btnSignInHome.setOnClickListener {

            if(TextUtils.isEmpty(fragmentSigninBinding.etEmailSignIn.text.toString())){
                fragmentSigninBinding.etEmailSignIn.setError("Please enter username")
                return@setOnClickListener
            }
            else if(TextUtils.isEmpty(fragmentSigninBinding.etPasswordSignIn.text.toString())){
                fragmentSigninBinding.etPasswordSignIn.setError("Please enter password")
                return@setOnClickListener
            }
            mAuth.signInWithEmailAndPassword(fragmentSigninBinding.etEmailSignIn.text.toString(), fragmentSigninBinding.etPasswordSignIn.text.toString())
                .addOnCompleteListener {
                    if(it.isSuccessful) {
                        requireActivity().run {
                            startActivity(Intent(this, HomeAllActivity::class.java))
                            finish()
                        }
                    } else {
                        Toast.makeText(context, "Login failed, please try again! ", Toast.LENGTH_LONG).show()
                    }
                }

        }
    }


    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception
            if (task.isSuccessful) {
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    Log.d("SignInFragment", "firebaseAuthWithGoogle:" + account.id)
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    Log.w("SignInFragment", "Google sign in failed", e)
                }
            } else {
                Log.w("SignInFragment", exception.toString())
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("SignInFragment", "signInWithCredential:success")
                    requireActivity().run {
                        startActivity(Intent(this, HomeAllActivity::class.java))
                        finish()
                    }
                } else {
                    // If sign in fails, display a message to the user.
                    Log.d("SignInFragment", "signInWithCredential:failure")
                }
            }
    }
}