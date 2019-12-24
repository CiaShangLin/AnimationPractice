
#include <jni.h>



char *getKey(JNIEnv *env) {
    jobject c=env->FindClass("java/lang/String");

    char* key= const_cast<char *>("key");
    return key;
}
