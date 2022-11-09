package com.d205.sdutyplus.view.report.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.EditText
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.d205.domain.model.report.Task
import com.d205.sdutyplus.databinding.DialogTaskBinding
import com.d205.sdutyplus.view.report.ReportViewModel

class TaskDialog(private val task: Task) : DialogFragment() {
    private lateinit var binding: DialogTaskBinding
    private val reportViewModel: ReportViewModel by activityViewModels()

    private val contentViews: List<ConstraintLayout> by lazy {
        listOf(binding.clContent1, binding.clContent2, binding.clContent3)
    }

    private val contentEditTexts: List<EditText> by lazy {
        listOf(binding.etContent1, binding.etContent2, binding.etContent3)
    }

    private val removeContentBtns: List<ImageView> by lazy {
        listOf(binding.ivRemoveContent1, binding.ivRemoveContent2, binding.ivRemoveContent3)
    }

    private val contentStrings: MutableList<String> by lazy {
        mutableListOf()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogTaskBinding.inflate(inflater, container, false)
        dialog?.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            requestFeature(Window.FEATURE_NO_TITLE)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val action = arguments?.getString("Action", "Add")

        when (action) {
            "CustomAdd" -> {
                // 임의로 Task 추가
            }
            "Info" -> {
                InfoTask()
            }
        }
    }


    private fun InfoTask() {
        initView()
        initBtn()
//            when (task.subTaskDtos.size) {
//                0 -> {
//                    clContent1.visibility = View.GONE
//                    clContent2.visibility = View.GONE
//                    clContent3.visibility = View.GONE
//                }
//                1 -> {
//                    if (task.subTaskDtos[0].content.isNotEmpty()) {
//                        clContent1.visibility = View.VISIBLE
//                        etContent1.setText(task.subTaskDtos[0].content)
//                        ivRemoveContent1.visibility = View.GONE
//                    } else {
//                        clContent1.visibility = View.GONE
//                    }
//                    clContent2.visibility = View.GONE
//                    clContent3.visibility = View.GONE
//                }
//                2 -> {
//                    if (task.subTaskDtos[0].content.isNotEmpty()) {
//                        clContent1.visibility = View.VISIBLE
//                        etContent1.setText(task.subTaskDtos[0].content)
//                        ivRemoveContent1.visibility = View.GONE
//                    } else {
//                        clContent1.visibility = View.GONE
//                    }
//
//                    if (task.subTaskDtos[1].content.isNotEmpty()) {
//                        clContent2.visibility = View.VISIBLE
//                        etContent2.setText(task.subTaskDtos[1].content)
//                        ivRemoveContent2.visibility = View.GONE
//                    } else {
//                        clContent2.visibility = View.GONE
//                    }
//
//                    clContent3.visibility = View.GONE
//                }
//                3 -> {
//                    if (task.subTaskDtos[0].content.isNotEmpty()) {
//                        clContent1.visibility = View.VISIBLE
//                        etContent1.setText(task.subTaskDtos[0].content)
//                        ivRemoveContent1.visibility = View.GONE
//                    } else {
//                        clContent1.visibility = View.GONE
//                    }
//
//                    if (task.subTaskDtos[1].content.isNotEmpty()) {
//                        clContent2.visibility = View.VISIBLE
//                        etContent2.setText(task.subTaskDtos[1].content)
//                        ivRemoveContent2.visibility = View.GONE
//                    } else {
//                        clContent2.visibility = View.GONE
//                    }
//
//                    if (task.subTaskDtos[2].content.isNotEmpty()) {
//                        clContent3.visibility = View.VISIBLE
//                        etContent3.setText(task.subTaskDtos[2].content)
//                        ivRemoveContent3.visibility = View.GONE
//                    } else {
//                        clContent3.visibility = View.GONE
//                    }
//                }
//            }

    }

    private fun initView() {
        binding.apply {
            for(i in 0 until 3){
                contentViews[i].visibility = View.GONE
            }

            tvStartTime.text = task.startTime.substring(11, 16)
            tvEndTime.text = task.endTime.substring(11, 16)
            etTitle.setText(task.content)
            ibAddContent.visibility = View.GONE
            //task data 넣기
            for(i in 0 until task.subTaskDtos.size) {
                contentViews[i].visibility = View.VISIBLE
                contentEditTexts[i].setText(task.subTaskDtos[i].content)
                removeContentBtns[i].visibility = View.GONE
            }

        }
    }

    private fun initBtn() {
        binding.apply {
            btnDelete.setOnClickListener {
                ConfirmDialog(task).show(this@TaskDialog.parentFragmentManager, "ConfirmDialog")
                dismiss()
            }
            btnModify.setOnClickListener {
                modifyTask()
            }
            btnSave.text = "확인"
            btnSave.setOnClickListener {
                dismiss()
            }
        }
    }


    private fun modifyTask() {
        binding.apply {
            for(i in 0 until 3){
                removeContentBtns[i].visibility = View.VISIBLE
            }

            ibAddContent.setOnClickListener {
                val nextContent: View  = getNextInVisibleContentView()
                if(nextContent is ConstraintLayout) {
                    nextContent.visibility = View.VISIBLE
                }
            }

            initRemoveContextBtns()

            btnDelete.text = "취소"
            btnDelete.setOnClickListener {
                dismiss()
            }

            btnModify.visibility = View.GONE

            btnSave.text = "저장"
            btnSave.setOnClickListener {
                // 태스크 수정 함수
            }

        }
    }

    private fun initRemoveContextBtns() {
        for((index, removeBtn) in removeContentBtns.withIndex()) {
            removeBtn.setOnClickListener {

                // visible 인 content의 내용들을 저장
                for((index,contentView) in contentViews.withIndex()) {
                    if(contentView.visibility == View.VISIBLE) {
                        contentStrings.add(contentEditTexts[index].text.toString())
                    }
                }

                Log.d("remove","기존 내용 $contentStrings")

                // remove 한 index의 내용을 삭제
                contentStrings.removeAt(index)
                Log.d("remove","삭제 인덱스 $index 내용 $contentStrings")

                // 가장 뒤에서 visible인 contentView를 Gone 처리
                for((index,contentView) in contentViews.reversed().withIndex()) {
                    if(contentView.visibility == View.VISIBLE) {
                        contentView.visibility = View.GONE
                        contentEditTexts[2-index].setText("")
                        Log.d("remove","삭제된 et $index 내용 ${contentEditTexts[index].text.toString()}")
                        break
                    }
                }

                // Visible인 Content들에 SetText
                var count = 0
                for((index,contentView) in contentViews.withIndex()) {
                    if(contentView.visibility == View.VISIBLE) {
                        contentEditTexts[index].setText(contentStrings[index])
                        count ++
                    }
                }
                Log.d("remove","남은 카운트 $count")

                //Strings 배열 초기화
                contentStrings.clear()
                if(count != 3) {
                    binding.ibAddContent.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun getNextInVisibleContentView(): View {
        for((index, contentView) in contentViews.withIndex()) {
            if(contentView.visibility == View.GONE) {
                if(index == contentViews.size -1) {
                    binding.ibAddContent.visibility = View.GONE
                }
                return contentView
            }
        }
        return View(requireContext())
    }


}