package utils;
import android.graphics.Bitmap;
import android.util.LruCache;

import java.io.File;


public class DetailsCache {

    private LruCache<String, Bitmap> memoryCache;


    // Get max available VM memory, exceeding this amount will throw an
    // OutOfMemory exception. Stored in kilobytes as LruCache takes an
    // int in its constructor.
//    final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
//
//    // Use 1/8th of the available memory for this memory cache.
//    final int cacheSize = maxMemory / 8;
//
//    memoryCache = new LruCache<String, Bitmap>(cacheSize) {
//        @Override
//        protected int sizeOf(String key, Bitmap bitmap) {
//            // The cache size will be measured in kilobytes rather than
//            // number of items.
//            return bitmap.getByteCount() / 1024;
//        }
//    };
//





}
