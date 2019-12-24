#include <Jni.h>
#include "AES.cpp"

#include <android/log.h>
#define TAG    "jni-log"
#define LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,TAG,__VA_ARGS__)

extern "C"
JNIEXPORT jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *env;

    if (vm->GetEnv(reinterpret_cast<void **>(&env), JNI_VERSION_1_6) != JNI_OK) {
        return JNI_ERR;
    }

    LOGD("JNI_OnLoad");

    LOGD("%s",getKey(env));


    return JNI_VERSION_1_6;
}