package com.pragma.presentation.internal.di.modules;

import android.content.Context;

import com.pragma.data.cache.ResultsCache;
import com.pragma.data.cache.ResultsCacheImpl;
import com.pragma.data.executor.JobExecutor;
import com.pragma.data.repository.ResultDataRepository;
import com.pragma.domain.executor.PostExecutionThread;
import com.pragma.domain.executor.ThreadExecutor;
import com.pragma.domain.repository.RestaurantResultRepository;
import com.pragma.presentation.AndroidApplication;
import com.pragma.presentation.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    ResultsCache provideResultsCache(ResultsCacheImpl resultsCache) {
        return resultsCache;
    }


    @Provides
    @Singleton
    RestaurantResultRepository provideRestaurantRepository(ResultDataRepository resultDataRepository) {
       return resultDataRepository;
    }

  /* TODO need to update the user repository with our own model
  @Provides @Singleton UserCache provideUserCache(UserCacheImpl userCache) {
    return userCache;
  }

  @Provides @Singleton UserRepository provideUserRepository(UserDataRepository userDataRepository) {
    return userDataRepository;
  }
  */
}
