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


################################################################
# prepare artifacts workflow
################################################################
# create github workflow flie buildArtifactsForPackage.yml
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

STEP=1
#STEP=`expr ${STEP} + 1`
echo -e "${GREEN}Step ${STEP}: Generating artifacts workflow ${WHITE}"
for i in Linux Win32 x64 ; do
    echo "    # ${i} builds:" >> ${WORKFLOWFILE}
    for j in Shared Static ; do
        for k in Debug Release ; do
        echo "    - name get artifacts ${i}${j}${k}
      uses: actions/download-artifact@v2
      with:
        name: ${i}${j}${k}
        path: ./cpp/build/output/${i}${j}/${k}
" >> ${WORKFLOWFILE}
        done
    done
done

echo -e "${GREEN}Finished: Generating artifacts workflow ${WHITE}"
