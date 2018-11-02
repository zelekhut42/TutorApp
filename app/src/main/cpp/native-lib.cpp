#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_robertcurtis_jingnicai_sidiamadou_tutor_tutorapp_TutorActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
