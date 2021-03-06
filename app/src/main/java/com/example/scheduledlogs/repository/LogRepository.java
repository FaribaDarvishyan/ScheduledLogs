package com.example.scheduledlogs.repository;


import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.scheduledlogs.database.LogDao;
import com.example.scheduledlogs.database.LogRoomDataBase;
import com.example.scheduledlogs.model.Log;

import java.util.List;

public class LogRepository {
    private static LogRepository sInstance;
    private LogDao mLogDao;

    public static LogRepository getInstance(Context context) {
        if(sInstance==null){
            sInstance=new LogRepository(context);
        }
        return sInstance;
    }

    private LogRepository(Context context) {
        LogRoomDataBase logDataBase=LogRoomDataBase.getDatabase(context);
        mLogDao=logDataBase.getLogDao();
    }

    public LiveData<List<Log>> getLogsLiveData() {
        return mLogDao.getLogs();
    }

    public void insertLog(Log log){
        LogRoomDataBase.mDatabaseExecutor.execute(() -> mLogDao.insertLogs(log));
    }

    public void updateLog(Log log){
        LogRoomDataBase.mDatabaseExecutor.execute(() -> mLogDao.updateLogs(log));
    }
}
