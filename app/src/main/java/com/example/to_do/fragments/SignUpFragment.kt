package com.example.to_do.fragments

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.to_do.R
import com.example.to_do.databinding.FragmentSignUpBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var navControl: NavController
    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        registerEvents()
    }

    private fun init(view: View){
        navControl = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()
    }

    private fun registerEvents() {

        binding.AuthRegister.setOnClickListener {
            navControl.navigate(R.id.action_signUpFragment_to_signInFragment)
        }
     binding.nextBtn.setOnClickListener {
         val email = binding.EmailEt.text.toString().trim()
         val pass = binding.PassEt.text.toString().trim()
         val verify = binding.RePassEt.text.toString().trim()

         if (email.isNotEmpty() && pass.isNotEmpty() && verify.isNotEmpty()){
             if (pass == verify){
                 auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(
                     OnCompleteListener {
                         if (it.isSuccessful){
                                Toast.makeText(context,"Register Successfully",Toast.LENGTH_SHORT)
                                    .show()
                             navControl.navigate(R.id.action_signUpFragment_to_homeFragment)
                         }else{
                             Toast.makeText(context,it.exception?.message,Toast.LENGTH_SHORT)
                                 .show()
                         }
                     })
             }
         }
     }
    }
}