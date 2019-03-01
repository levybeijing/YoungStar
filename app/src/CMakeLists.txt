# 指定CMake最低版本

cmake_minimum_required(VERSION 3.4.1)


# 定义常量

set(SRC_DIR src/main/cpp/lamemp3)


# 指定关联的头文件目录

include_directories(src/main/cpp/lamemp3)


# 查找在某个路径下的所有源文件

aux_source_directory(src/main/cpp/lamemp3 SRC_LIST)


# 设置 *.so 文件输出路径，要放在在add_library之前，不然不会起作用

set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/src/main/jniLibs/${ANDROID_ABI})


# 声明库名称、类型、源码文件

add_library(lame-mp3-utils SHARED src/main/cpp/lame-mp3-utils.cpp ${SRC_LIST})


# 定位某个NDK库，这里定位的是log库

find_library( # Sets the name of the path variable.

              log-lib


              # Specifies the name of the NDK library that

              # you want CMake to locate.

              log )


# 将NDK库链接到native库中，这样native库才能调用NDK库中的函数

target_link_libraries( # Specifies the target library.

                       lame-mp3-utils


                       # Links the target library to the log library

                       # included in the NDK.

                       ${log-lib} )