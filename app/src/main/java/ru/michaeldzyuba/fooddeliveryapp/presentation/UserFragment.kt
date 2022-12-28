package ru.michaeldzyuba.fooddeliveryapp.presentation

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import ru.michaeldzyuba.fooddeliveryapp.R
import ru.michaeldzyuba.fooddeliveryapp.databinding.FragmentUserBinding
import ru.michaeldzyuba.fooddeliveryapp.utils.AppPreferences
import ru.michaeldzyuba.fooddeliveryapp.utils.RoundedCornersTransform
import java.io.ByteArrayOutputStream
import java.io.IOException


class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding: FragmentUserBinding
        get() = _binding ?: throw RuntimeException("FragmentUserBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fullName = AppPreferences.getFullName()
        if (fullName.isNotEmpty()) {
            binding.tvFullName.text = fullName
        }
        val age = AppPreferences.getAge()
        if (age != -1) {
            binding.tvAge.text = age.toString()
        }
        binding.ivAvatar.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, SELECT_PHOTO)
        }
        binding.btnLogout.setOnClickListener {
            AppPreferences.clearAll()
            requireActivity().findNavController(R.id.fragmentContainer)
                .navigate(R.id.action_bottomNavigationContainerFragment_to_loginFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        setImage()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SELECT_PHOTO) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    val uri: Uri? = data.getData()
                    getImage(uri)
                }
            }
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun setImage() {
        val uri = AppPreferences.getImage()
        Picasso.get()
            .load(Uri.parse(uri))
            .centerCrop()
            .resize(binding.ivAvatar.getMeasuredWidth(), binding.ivAvatar.getMeasuredHeight())
            .transform(RoundedCornersTransform(1000, 0))
            .error(R.drawable.ic_user)
            .into(binding.ivAvatar)

    }

    private fun getImage(uri: Uri?) {
//        var bitmap: Bitmap? = null
        try {
//            bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), uri)
            AppPreferences.saveImage(uri?.toString() ?: "")
            setImage()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(requireActivity(), "Error", Toast.LENGTH_SHORT).show()
        }
    }

//    private fun bitmapToString(bitmap: Bitmap): String {
//        val baos = ByteArrayOutputStream()
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
//        val b = baos.toByteArray()
//        return Base64.encodeToString(b, Base64.DEFAULT)
//    }

//    private fun stringToBitmap(image: String): Bitmap? {
//        val b = Base64.decode(image, Base64.DEFAULT)
//        return BitmapFactory.decodeByteArray(b, 0, b.size)
//    }

    companion object {
        private const val SELECT_PHOTO = 111
    }
}