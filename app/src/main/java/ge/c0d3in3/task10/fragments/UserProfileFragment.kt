package ge.c0d3in3.task10.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import ge.c0d3in3.task10.App
import ge.c0d3in3.task10.R
import ge.c0d3in3.task10.model.User
import kotlin.properties.Delegates

class UserProfileFragment : Fragment(R.layout.user_profile_fragment) {

    private var editable by Delegates.notNull<Boolean>()
    private var user = App.sharedPreferencesManager.getUser() ?: User()

    private lateinit var userIv: ImageView
    private lateinit var userEtLayout: TextInputLayout
    private lateinit var userSurnameEtLayout: TextInputLayout
    private lateinit var userAgeEtLayout: TextInputLayout
    private lateinit var userImageEtLayout: TextInputLayout
    private lateinit var saveButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editable = arguments?.getBoolean(EDITABLE) ?: false
    }

    override fun onResume() {
        super.onResume()
        if(!editable) {
            user = App.sharedPreferencesManager.getUser() ?: user
            updateUI()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userIv = view.findViewById<ImageView>(R.id.userIv)
        if (user.profileImageUrl.isNotBlank()) Glide.with(requireContext())
            .load(user.profileImageUrl).into(userIv)
        initViews(view)
        saveButton.setOnClickListener { saveUser() }
    }

    private fun initViews(view: View) {
        userIv = view.findViewById(R.id.userIv)
        userEtLayout = view.findViewById(R.id.userNameLayout)
        userSurnameEtLayout = view.findViewById(R.id.userSurnameLayout)
        userAgeEtLayout = view.findViewById(R.id.userAgeLayout)
        userImageEtLayout = view.findViewById(R.id.userImageLayout)
        saveButton = view.findViewById(R.id.saveButton)
        changeEditTextEditMode()
        updateUI()
    }

    private fun initInputs() {
        userEtLayout.editText?.setText(user.name)
        userSurnameEtLayout.editText?.setText(user.surname)
        userAgeEtLayout.editText?.setText(user.age)
        userImageEtLayout.editText?.setText(user.profileImageUrl)
    }

    private fun changeEditTextEditMode() {
        with(editable) {
            userEtLayout.editText?.isEnabled = this
            userSurnameEtLayout.editText?.isEnabled = this
            userAgeEtLayout.editText?.isEnabled = this
            userImageEtLayout.editText?.isEnabled = this
            saveButton.visibility = if(this) View.VISIBLE else View.GONE
        }
    }

    private fun updateUI(){
        initInputs()
        if (user.profileImageUrl.isNotBlank()) Glide.with(requireContext())
            .load(user.profileImageUrl).into(userIv)
    }

    private fun saveUser() {
        user.name = userEtLayout.editText!!.text()
        user.surname = userSurnameEtLayout.editText!!.text()
        user.age = userAgeEtLayout.editText!!.text()
        user.profileImageUrl = userImageEtLayout.editText!!.text()
        App.sharedPreferencesManager.saveUser(user)
        updateUI()
    }

    companion object {
        fun newInstance(editable: Boolean) =
            UserProfileFragment().apply {
                arguments = bundleOf(EDITABLE to editable)
            }

        const val EDITABLE = "editable"
    }

    private fun EditText.text() = this.text.toString()
}