cmake_minimum_required(VERSION 3.20.0)
find_package(Zephyr REQUIRED HINTS $ENV{ZEPHYR_BASE})
project(gdb_test)

# Ajout direct de cJSON comme source à compiler
target_sources(app PRIVATE
    src/main.c
    ${CMAKE_CURRENT_SOURCE_DIR}/lib/cJSON/cJSON.c
)

# Inclusion du répertoire contenant l'en-tête cJSON
target_include_directories(app PRIVATE
    ${CMAKE_CURRENT_SOURCE_DIR}/lib/cJSON
)
