#!/bin/bash

# get script folder
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
cd ${SCRIPT_DIR}

for i in Linux Win32 x64 ; do
    for j in Shared Static ; do
        for k in Release Debug ; do
            echo "rm -rf ${SCRIPT_DIR}/../output/${i}${j}/${k}/examples"
            rm -rf ${SCRIPT_DIR}/../output/${i}${j}/${k}/examples
            echo "mkdir -p ${SCRIPT_DIR}/../output/${i}${j}/${k}/examples"
            mkdir -p ${SCRIPT_DIR}/../output/${i}${j}/${k}/examples
            echo "cp -r ${SCRIPT_DIR}/../../../doc/examples ${SCRIPT_DIR}/../output/${i}${j}/${k}"
            cp -r ${SCRIPT_DIR}/../../../doc/examples ${SCRIPT_DIR}/../output/${i}${j}/${k}
        done
    done
    echo
done

for i in `find ${SCRIPT_DIR}/../output -name ".gitignore" -print` ; do rm -f $i; done
