cmake_minimum_required(VERSION 3.27)
project(vole_machine)

set(CMAKE_CXX_STANDARD 17)

add_executable(vole_machine main.cpp
        volemachine.h
        volemachine.cpp)
