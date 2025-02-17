package com.d205.data.repository.report.remote

import android.annotation.SuppressLint
import android.util.Log
import com.d205.data.model.BaseResponse
import com.d205.data.api.ReportApi
import com.d205.data.model.report.GraphResponse
import com.d205.data.model.report.ReportDateResponse
import com.d205.data.model.report.ReportResponse
import com.d205.domain.model.report.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

const val TAG = "ReportRemoteDataSourceImpl"
class ReportRemoteDataSourceImpl @Inject constructor(
    private val reportApi: ReportApi
): ReportRemoteDataSource {
    override fun getReport(date: String): Flow<BaseResponse<ReportResponse>> = flow {
        emit(reportApi.getReport(date))
    }

    override fun updateTask(task_seq: Long, task: Task): Flow<Boolean> = flow {
        val response = reportApi.updateTask(task_seq, task)
        if(response.status == 200) {
            emit(true)
        } else {
            emit(false)
        }

    }.catch { e ->
        emit(false)
    }

    override fun deleteTask(task_seq: Long): Flow<Boolean> = flow{
        val response = reportApi.deleteTask(task_seq)
        if(response.status == 200) {
            emit(true)
        } else{
            emit(false)
        }
    }.catch { e ->
        emit(false)
    }

    @SuppressLint("LongLogTag")
    override fun getReportDate(): Flow<BaseResponse<ReportDateResponse>> = flow {
        Log.d(TAG, "getReportDate: ${emit(reportApi.getReportDate())}")
        Log.d(TAG, "getReportDate: ${reportApi.getReportDate()}")
        emit(reportApi.getReportDate())
    }

    @SuppressLint("LongLogTag")
    override fun getGraph(): Flow<BaseResponse<GraphResponse>> = flow {
        emit(reportApi.getGraph())
    }


}