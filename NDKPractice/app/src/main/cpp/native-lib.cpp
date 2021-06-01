#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_lazypotato_ndkpractice_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

static jlong fib(jlong n) {
    return n<=0 ? 0 : n == 1 ? 1 : fib(n-1) + fib(n-2);
}

extern "C" JNIEXPORT jlong JNICALL
Java_com_lazypotato_ndkpractice_java_Calculator_add(JNIEnv *env, jclass clazz, jlong n1, jlong n2) {
    return n1 + n2;
}

extern "C" JNIEXPORT jlong JNICALL Java_com_lazypotato_ndkpractice_java_Calculator_subtract(JNIEnv *env, jclass clazz, jlong n1, jlong n2) {
    return  n1 - n2;
}

extern "C" JNIEXPORT jlong JNICALL
Java_com_lazypotato_ndkpractice_kotlin_Calculator_00024Companion_add(JNIEnv *env, jobject thiz, jlong n1, jlong n2) {
    return n1 + n2;
}
extern "C" JNIEXPORT jlong JNICALL
Java_com_lazypotato_ndkpractice_kotlin_Calculator_00024Companion_subtract(JNIEnv *env, jobject thiz, jlong n1, jlong n2) {
    return  n1 - n2;
}