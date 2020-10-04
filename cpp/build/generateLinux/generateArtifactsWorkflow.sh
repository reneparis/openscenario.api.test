#!/bin/bash

################################################################
# setup some variables
################################################################
# get script folder
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
# cd to script dir
cd ${SCRIPT_DIR}
# add colors
. ${SCRIPT_DIR}/colors.sh
# build real or fake?
CFAKE="#"
CREAL=
if [[ $1 == "real" ]] ; then
    CFAKE=
    CREAL="#"
fi


################################################################
# prepare artifacts workflow
################################################################
# create github workflow file buildArtifactsForPackage.yml
WORKFLOWFILE=buildArtifactsForPackage.yml

REQUIRED_ARTIFACTS=
for i in Linux Win32 x64 ; do
    for j in Shared Static ; do
        for k in Debug Release ; do
		    REQUIRED_ARTIFACTS="${REQUIRED_ARTIFACTS}, ${i}${j}${k}Build"
        done
    done
done
REQUIRED_ARTIFACTS=`echo ${REQUIRED_ARTIFACTS} | cut  -c3-`

echo "name: OpenScenarioCppArtifacts
on:
  workflow_dispatch:
    # Inputs the workflow accepts.
    inputs:
      name:
        description: 'Artifact name'
        default: 'OpenScenarioWindowsLinuxDevPackage'


jobs:
  CreatePackage:
    runs-on: ubuntu-latest
    needs: [${REQUIRED_ARTIFACTS}]

    steps:
    - uses: actions/checkout@v2

    - name: update system
      run:  sudo apt-get update

" > ${WORKFLOWFILE}


echo
echo -e "${GREEN}Generating artifacts workflow ${WHITE}"


# Create steps for download artifacts
STEP=1
#STEP=`expr ${STEP} + 1`
echo
echo -e "${GREEN}Step ${STEP}: Generating job for download artifacts with package creation ${WHITE}"
for i in Linux Win32 x64 ; do
    echo "    # ${i} builds" >> ${WORKFLOWFILE}
    for j in Shared Static ; do
        for k in Debug Release ; do
            echo "    - name: get artifacts ${i}${j}${k}
      uses: actions/download-artifact@v2
      with:
        name: ${i}${j}${k}
        path: ./cpp/build/output/${i}${j}/${k}
" >> ${WORKFLOWFILE}
        done
    done
done
# Create steps for building the package
echo -e "
    - name: create package
      run: ./cpp/build/generateLinux/createLinuxWindowsBinPackage.sh

    - name: check package
      run: ls ./cpp/build/generateLinux/openScenario*

    - name: publish artifacts
      uses: actions/upload-artifact@v2
      with:
        name: \${{ github.event.inputs.name }}
        path: ./cpp/build/generateLinux/openScenario.v1_0.API\n\n" >> ${WORKFLOWFILE}


# Create jobs for compiling OpenSCENARIO for each platform and saving its output as upload-artifact
STEP=`expr ${STEP} + 1`
echo
echo -e "${GREEN}Step ${STEP}: Generating jobs for platform builds and upload artifacts ${WHITE}"
for i in Linux Win32 x64 ; do
    PLATFORM="windows-latest"
    SYSTEM_UPDATE=
    INSTALL_UUID_DEV=
    MKDIR_PARAM=
    if [[ ${i} == "Linux" ]] ; then
        PLATFORM="ubuntu-latest"
        SYSTEM_UPDATE="- name: update system\n      run:  sudo apt-get update\n"
        INSTALL_UUID_DEV="- name: install uuid-dev package\n      run:  sudo apt-get install uuid-dev\n"
        MKDIR_PARAM="-p "
    fi
    for j in Shared Static ; do
        ON_OFF=OFF
        if [[ ${j} == "Static" ]] ; then ON_OFF=ON ; fi
        echo "  # ${i} ${j}" >> ${WORKFLOWFILE}
        for k in Debug Release ; do
            echo -e "  ${i}${j}${k}Build:" >> ${WORKFLOWFILE}
            echo -e "    runs-on: ${PLATFORM}\n" >> ${WORKFLOWFILE}
            echo -e "    steps:" >> ${WORKFLOWFILE}
            echo -e "    - uses: actions/checkout@v2\n" >> ${WORKFLOWFILE}
            if [[ ${i} == "Linux" ]] ; then
                echo -e "    ${SYSTEM_UPDATE}" >> ${WORKFLOWFILE}
                echo -e "    ${INSTALL_UUID_DEV}" >> ${WORKFLOWFILE}
            fi
            echo -e "    - name: cmake generate solution ${k}" >> ${WORKFLOWFILE}
            if [[ ${i} == "Linux" ]] ; then
                GENERATE_SOLUTION="run: cmake -DCMAKE_BUILD_TYPE=\"${k}\" -DBUILD_STATIC_LIBS=\"${ON_OFF}\" -DPLATFORM_PARAM=\"${i}\" ./cpp\n"
                BUILD_SOLUTION="run: make\n"
                FAKE_BUILD="run: cd ./cpp/build/output/${i}${j}/${k} ; touch IndexerTester ; touch libantlr4-runtime.a ; touch libantlr4-runtime.so ; touch libantlr4-runtime.so.4.8 ; touch libOpenScenarioLib.v1_0.so ; touch libOpenScenarioLib.v1_0.so.0 ; touch libOpenScenarioLib.v1_0.so.0.9.0\n"
            else
                GENERATE_SOLUTION="run: cmake -G \"Visual Studio 16 2019\" -A ${i} ./cpp -DMASTER_PROJECT=TRUE -DBUILD_STATIC_LIBS=\"${ON_OFF}\"\n"
                BUILD_SOLUTION="run: C:/\"Program Files (x86)\"/\"Microsoft Visual Studio\"/2019/Enterprise/MSBuild/Current/Bin/MSBuild.exe OpenScenario-Cpp.sln /t:Build /p:Configuration=${k} /p:Platform=${i}\n"
                FAKE_BUILD="run: cd ./cpp/build/output/${i}${j}/${k} ; touch IndexerTester ; touch antlr4-runtime.lib ; touch antlr4-runtime.dll ; touch antlr4-runtime.dll.4.8 ; touch OpenScenarioLib.v1_0.dll ; touch libOpenScenarioLib.v1_0.dll.0 ; touch OpenScenarioLib.v1_0.dll.0.9.0\n"
            fi
            echo -e "      ${GENERATE_SOLUTION}" >> ${WORKFLOWFILE}
            echo -e "${CFAKE}    - name: build solution ${k}" >> ${WORKFLOWFILE}
            echo -e "${CFAKE}      ${BUILD_SOLUTION}" >> ${WORKFLOWFILE}
            echo -e "     # fake build" >> ${WORKFLOWFILE}
            echo -e "${CREAL}    - name: fake build solution ${k}" >> ${WORKFLOWFILE}
            echo -e "${CREAL}      run: mkdir ${MKDIR_PARAM}./cpp/build/output/${i}${j}/${k}" >> ${WORKFLOWFILE}
            echo -e "${CREAL}    - name: fake build binaries" >> ${WORKFLOWFILE}
            echo -e "${CREAL}      ${FAKE_BUILD}" >> ${WORKFLOWFILE}

            echo -e "    - name: publish artifacts" >> ${WORKFLOWFILE}
            echo -e "      uses: actions/upload-artifact@v2" >> ${WORKFLOWFILE}
            echo -e "      with: " >> ${WORKFLOWFILE}
            echo -e "        name: ${i}${j}${k}" >> ${WORKFLOWFILE}
            echo -e "        path: ./cpp/build/output/${i}${j}/${k}/*\n\n" >> ${WORKFLOWFILE}
        done
    done
done


# Copy the workflow to its github place
STEP=`expr ${STEP} + 1`
echo
echo -e "${GREEN}Step ${STEP}: Copying ${WORKFLOWFILE} to the folder .github/workflows ${WHITE}"
cp ${WORKFLOWFILE} ../../../.github/workflows

echo
echo -e "${GREEN}Finished: Generating artifacts workflow ${WHITE}"
