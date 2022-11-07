package com.d205.sdutyplus.view.join


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.d205.domain.model.common.JobHashtag
import com.d205.domain.model.user.UserDto
import com.d205.sdutyplus.R
import com.d205.sdutyplus.base.BaseFragment
import com.d205.sdutyplus.databinding.FragmentJoinProfileBinding
import com.d205.sdutyplus.uitls.KAKAO_JOIN
import com.d205.sdutyplus.uitls.NAVER_JOIN
import com.d205.sdutyplus.uitls.PROFILE
import com.d205.sdutyplus.uitls.showToast
import com.d205.sdutyplus.view.MainActivity
import com.d205.sdutyplus.view.common.CropImageActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

private const val TAG = "JoinProfileFragment"

@AndroidEntryPoint
class JoinProfileFragment : BaseFragment<FragmentJoinProfileBinding>(R.layout.fragment_join_profile) {
    private val args by navArgs<JoinProfileFragmentArgs>()
    private val profileViewModel: ProfileViewModel by viewModels()
    private val joinViewModel: JoinViewModel by viewModels()

    private lateinit var profileImageUrl: String
    private var jobHashtag: JobHashtag? = null

    private val getImageResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == Activity.RESULT_OK){
            val uri = it.data!!.getStringExtra("uri")
            Log.d(TAG, "uri : $uri")

            binding.ivProfile.setImageURI(Uri.parse(uri))
            profileImageUrl = uri!!
        }
        else{
            Log.d(TAG, "resultLauncher: NO DATA")
        }
    }

    override fun initOnViewCreated() {
        initView()
    }

    private fun initView() {
        binding.apply {
            btnJoin.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    joinUser()
                }
            }

            ivProfile.setOnClickListener {
                launchImageCrop()
            }

            btnJobSelect.setOnClickListener {
                openTagSelectDialog()
            }
        }
    }

    private suspend fun checkNicknameIsUsed(): Boolean =
        profileViewModel.checkNickname(binding.etNickname.text.toString())

    private fun getSocialType(): Int = args.socialType

    private fun joinUser() {
        CoroutineScope(Dispatchers.Main).launch {
            joinViewModel.addUser(
                UserDto(
                    imgUrl = profileImageUrl,
                    nickname = binding.etNickname.text.toString(),
                    job = jobHashtag!!.seq)
            )
            if(isUserJoinedSucceeded()) {
                moveToMainActivity()
            }
            else {
                requireContext().showToast("회원가입에 실패했습니다.")
            }
        }
    }

    private fun isUserJoinedSucceeded() = joinViewModel.isJoinSucceeded.value!!

    private fun launchImageCrop() {
        val intent = Intent(requireContext(), CropImageActivity::class.java)
        intent.putExtra("flag", PROFILE)
        getImageResultLauncher.launch(intent)
    }

    private fun moveToMainActivity() {
        startActivity(Intent(requireContext(), MainActivity::class.java))
    }

    private fun openTagSelectDialog() {
        TagSelectDialog(requireContext()).let {
            it.arguments = bundleOf("flag" to PROFILE)
            it.onClickConfirm = object : TagSelectDialog.OnClickConfirm {
                override fun onClick(selectedJob: JobHashtag?) {
                    jobHashtag = selectedJob
                    binding.apply {
                        btnJob.text = jobHashtag!!.name
                        btnJob.visibility = View.VISIBLE
                        btnJobSelect.visibility = View.GONE
                    }
                }
            }
            it.show(parentFragmentManager, null)
        }
    }
}