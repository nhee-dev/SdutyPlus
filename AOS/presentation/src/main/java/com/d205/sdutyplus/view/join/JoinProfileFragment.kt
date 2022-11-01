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
import com.d205.sdutyplus.R
import com.d205.sdutyplus.base.BaseFragment
import com.d205.sdutyplus.databinding.FragmentJoinProfileBinding
import com.d205.sdutyplus.uitls.PROFILE
import com.d205.sdutyplus.uitls.showToast
import com.d205.sdutyplus.view.MainActivity
import com.d205.sdutyplus.view.common.CropImageActivity

private const val TAG = "JoinProfileFragment"
class JoinProfileFragment : BaseFragment<FragmentJoinProfileBinding>(R.layout.fragment_join_profile) {
    private val args by navArgs<JoinProfileFragmentArgs>()
    private val profileViewModel: ProfileViewModel by viewModels()

    private lateinit var imageUrl: String
    private var jobHashtag: JobHashtag? = null

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == Activity.RESULT_OK){
            val uri = it.data!!.getStringExtra("uri")
            Log.d(TAG, "uri : $uri")
            binding.ivProfile.setImageURI(Uri.parse(uri))
            imageUrl = uri!!
        }else{
            Log.d(TAG, "resultLauncher: NODATA")
        }
    }

    override fun init() {
        initView()
    }

    private fun initView() {
        binding.apply {
            btnJoin.setOnClickListener {
                profileViewModel.checkNickname(etNickname.text.toString())
                if(profileViewModel.isUsedNickname.value!!) {
                    requireContext().showToast("이미 사용중인 닉네임입니다.")
                }
                else {
                    moveToMainActivity()
                }
            }

            ivProfile.setOnClickListener {
                val intent = Intent(requireContext(), CropImageActivity::class.java)
                intent.putExtra("flag", PROFILE)
                resultLauncher.launch(intent)
            }

            btnJobInterest.setOnClickListener {
                openTagSelectDialog()
            }
        }
    }

    private fun moveToMainActivity() {
        startActivity(Intent(requireContext(), MainActivity::class.java))
    }

    private fun openTagSelectDialog() {
        TagSelectDialog(requireContext()).let {
            it.arguments = bundleOf("flag" to PROFILE)
            it.onClickConfirm = object : TagSelectDialog.OnClickConfirm{
                override fun onClick(selectedJobList: JobHashtag?) {
                    jobHashtag = selectedJobList
                    binding.apply {
                        // 직업과 관심분야 선택을 하나로 표현하기 위해 수정
                        // job은 필수 선택
                        btnJob.text = jobHashtag!!.name
                        btnJob.visibility = View.VISIBLE
                        btnJobInterest.visibility = View.GONE
                    }
                }
            }
            it.show(parentFragmentManager, null)
        }
    }
}