package com.pragma.data.cache;

import android.content.Context;

import com.pragma.data.cache.serializer.Serializer;
import com.pragma.data.entity.Result;
import com.pragma.data.exception.ResultsNotFoundException;
import com.pragma.domain.executor.ThreadExecutor;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by anbu.ezhilan on 12/03/2017.
 */

@Singleton
public class ResultsCacheImpl implements ResultsCache {


    private static final String SETTINGS_FILE_NAME = "com.fernandocejas.android10.SETTINGS";
    private static final String SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update";

    private static final String DEFAULT_FILE_NAME = "user_";
    private static final long EXPIRATION_TIME = 60 * 10 * 1000;

    private final Context context;
    private final File cacheDir;
    private final Serializer serializer;
    private final FileManager fileManager;
    private final ThreadExecutor threadExecutor;

    /**
     * Constructor of the class {@link ResultsCacheImpl}
     * @param context
     * @param serializer {@link Serializer} for object Serialization
     * @param fileManager {@link FileManager} for saving serialized objects to file system
     * @param executor {@link ThreadExecutor}
     * @return
     */
    @Inject
    ResultsCacheImpl(Context context, Serializer serializer, FileManager fileManager,
                     ThreadExecutor executor) {
        if (context == null || serializer == null || fileManager == null || executor == null) {
            throw new IllegalArgumentException("Invalid null parameter");
        }
        this.context = context.getApplicationContext();
        this.cacheDir = this.context.getCacheDir();
        this.serializer = serializer;
        this.fileManager = fileManager;
        this.threadExecutor = executor;
    }

    @Override
    public Observable<Result> get(String restaurantId) {
        return Observable.create(emitter -> {

            final File restaurantEntityFile = ResultsCacheImpl.this.buildFile(restaurantId);
            final String fileContent = ResultsCacheImpl.this.fileManager.readFileContent(restaurantEntityFile);
            final Result resultEntity = ResultsCacheImpl.this.serializer.deserialize
                                                        (fileContent, Result.class);
            if ( resultEntity != null ) {
                emitter.onNext(resultEntity);
                emitter.onComplete();
            } else {
                emitter.onError(new ResultsNotFoundException());
            }
        });
    }

    @Override
    public void put(Result resultEntity) {
        if ( resultEntity != null) {
            final File resultEntityFile = this.buildFile(resultEntity.getId());

            if ( !isCached(resultEntity.getId())) {
                final String jsonString = this.serializer.serialize(resultEntity, Result.class);
                this.executeAsynchronously(new CacheWriter(this.fileManager, resultEntityFile, jsonString));
                setLastCacheUpdateTimeMillis();
            }
        }
    }


    private File buildFile(String restaurantId) {
        final StringBuilder fileNameBuilder = new StringBuilder();
        fileNameBuilder.append(this.cacheDir.getPath());
        fileNameBuilder.append(File.separator);
        fileNameBuilder.append(DEFAULT_FILE_NAME);
        fileNameBuilder.append(restaurantId);

        return new File(fileNameBuilder.toString());
    }

    @Override
    public boolean isCached(String restaurantId) {
        final File resultEntityFile = this.buildFile(restaurantId);
        return this.fileManager.exists(resultEntityFile);
    }


    @Override
    public boolean isExpired() {
        long currentTime = System.currentTimeMillis();
        long lastUpdateTime = this.getLastCacheUpdateTimeMillis();

        boolean expired = ((currentTime - lastUpdateTime) > EXPIRATION_TIME);

        if (expired) {
            this.evictAll();
        }

        return expired;
    }

    @Override
    public void evictAll() {
        this.executeAsynchronously(new CacheEvictor(this.fileManager, this.cacheDir));
    }

    /**
     * Set in millis, the last time the cache was accessed.
     */
    private void setLastCacheUpdateTimeMillis() {
        final long currentMillis = System.currentTimeMillis();
        this.fileManager.writeToPreferences(this.context, SETTINGS_FILE_NAME,
                SETTINGS_KEY_LAST_CACHE_UPDATE, currentMillis);
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private long getLastCacheUpdateTimeMillis() {
        return this.fileManager.getFromPreferences(this.context, SETTINGS_FILE_NAME,
                SETTINGS_KEY_LAST_CACHE_UPDATE);
    }

    /**
     * Executes a {@link Runnable} in another Thread.
     *
     * @param runnable {@link Runnable} to execute
     */
    private void executeAsynchronously(Runnable runnable) {
        this.threadExecutor.execute(runnable);
    }

    /**
     * {@link Runnable} class for writing to disk.
     */
    private static class CacheWriter implements Runnable {
        private final FileManager fileManager;
        private final File fileToWrite;
        private final String fileContent;

        CacheWriter(FileManager fileManager, File fileToWrite, String fileContent) {
            this.fileManager = fileManager;
            this.fileToWrite = fileToWrite;
            this.fileContent = fileContent;
        }

        @Override
        public void run() {
            this.fileManager.writeToFile(fileToWrite, fileContent);
        }
    }

    /**
     * {@link Runnable} class for evicting all the cached files
     */
    private static class CacheEvictor implements Runnable {
        private final FileManager fileManager;
        private final File cacheDir;

        CacheEvictor(FileManager fileManager, File cacheDir) {
            this.fileManager = fileManager;
            this.cacheDir = cacheDir;
        }

        @Override
        public void run() {
            this.fileManager.clearDirectory(this.cacheDir);
        }
    }

}
